/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Form.Body.Chat.Chat_Body;
import Form.Body.Event.EvenChat;
import Form.Body.Event.PublicEvent;
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
    static Client instance;
    private Socket socket;
    private BufferedReader read = null;
    private BufferedWriter write = null;
    private String localhost = "127.0.0.1";
    private String internet = "serverchat.ddns.net";
    
    public static Client getInstance() {
        if(instance==null){
            instance = new Client();
        }
        return instance;
    }
    public void connect() throws IOException{ 
        socket = new Socket(internet, 9001);
        read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Thread t = new Thread(){
            @Override
            public void run() {
                while (true) {                    
                    try {
                        String msg = read.readLine();
                        System.out.println("Da doc: "+msg);
                        PublicEvent.getInstance().getEventChat().reciveMessage(msg);
                    }catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        t.start();
    }
    public void send(String message) throws IOException{
        System.out.println("Dang gui: "+message);
        write.write(message);
        write.newLine();
        write.flush();
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
    
}
