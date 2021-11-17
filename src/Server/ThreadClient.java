/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Database.AccountDAL;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException; 
import java.io.ObjectOutputStream;
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
    private BufferedReader read;
    private BufferedWriter write;
    Socket s;
    boolean isloggedin;
    private AccountDAL ac = new AccountDAL();
    public ThreadClient(Socket s,BufferedReader read, BufferedWriter write) {
        this.s = s; 
        this.read = read;
        this.write = write;
        this.isloggedin=true;
    }
    public ThreadClient(){};
    
    @Override
    public void run(){
        loadListUser();
        try {
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            os.writeObject("a#"+ac.allAccount);
            
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String convertArToString(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }
    public void loadListUser(){
        String listuser = convertArToString(ac.allAccount);
        send("listUser#"+listuser);
    }
    public void sendMessage(String text){
        StringTokenizer st = new StringTokenizer(text,"#");
        String sender = st.nextToken();
        String receiver = st.nextToken();
        String msg = st.nextToken();
    }
    public void send(String message){
        try {
            write.write(message);
            write.newLine();
            write.flush();
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String Reccive() throws IOException{
        return read.readLine();
    }
}
