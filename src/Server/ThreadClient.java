/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException; 
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner; 
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger; 

/**
 *
 * @author man21
 */
public class ThreadClient implements Runnable{
    private String name;
    final BufferedReader read;
    final BufferedWriter write;
    Socket s;
    boolean isloggedin;
    public ThreadClient(Socket s, String name,BufferedReader read, BufferedWriter write) {
        this.s = s; 
        this.read = read;
        this.write = write;
        this.name = name;
        this.isloggedin=true;
    }
    @Override
    public void run() {
        String received;
        while(s.isConnected())
        {
            try
            {
                received = read.readLine();
                System.out.println(received);
                StringTokenizer st = new StringTokenizer(received, "#");
                String MsgToSend = st.nextToken();
                String recipient = st.nextToken();
                for (ThreadClient mc : Server.ar)
                {
                    if (mc.name.equals(recipient) && mc.isloggedin==true)
                    {
                        mc.write.write(MsgToSend);
                        mc.write.newLine();
                        mc.write.flush();
                        break;
                    }
                }
            } catch (IOException e) {
                break; 
            }
        }
        try {
            this.read.close();
            this.write.close();
            this.s.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
