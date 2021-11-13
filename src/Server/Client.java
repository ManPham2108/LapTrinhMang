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
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author man21
 */
public class Client{
    private Socket socket;
//    DataInputStream dis;
//    DataOutputStream dos;
    BufferedReader read = null;
    BufferedWriter write = null;
    String localhost = "127.0.0.1";
    String internet = "serverchat.ddns.net";
    public Client() throws IOException{ 
        socket = new Socket(internet, 9001);
//        dis = new DataInputStream(s.getInputStream());
//        dos = new DataOutputStream(s.getOutputStream());
        read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //dos.writeUTF("man");
    }
    public void Close(){
        try {
            write.close();
            read.close();    
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void send(String message){
        String msg = message;
        try {
            write.write(msg);
            write.newLine();
            write.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    public void read(JTextArea textarea) throws IOException{
        Thread t = new Thread(){
            public void run(){
                while (true) {
                    try {
                        String msg = read.readLine();
                        System.out.println(msg);
                        if(msg!=null){
                            textarea.append(msg+"\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
        
    }
}
