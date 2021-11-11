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
    private Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    public Client() throws IOException{
        s = new Socket("127.0.0.1", 9001);
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());
        //dos.writeUTF("man");
    }
    public void Close(){
        try {
            dos.close();
            dis.close();  
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public Socket getS() {
        return s;
    }
    
    public void send(String message){
        String msg = message;
        try {
            dos.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    //public void read(JTextArea text) throws IOException{
//        Thread t = new Thread(){
//            JTextArea textarea = text;
//            public void run(){
//                while (true) {
//                    try {
//                        // read the message sent to this client
//                        
//                        System.out.println(msg);
//                        if(msg!=null){
//                            textarea.append(msg);
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }   
//            }
//        };
//        t.start();
    //}
}
