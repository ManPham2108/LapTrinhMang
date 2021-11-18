/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Database.AccountDAL;
import Model.AccountModel;
import Model.SendMessageModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException; 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner; 
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger; 

/**
 *
 * @author man21
 */
public class ThreadClient implements Runnable{
    public BufferedReader read;
    public BufferedWriter write;
    private Socket s;
    private boolean isloggedin;
    private AccountDAL ac;
    private SendMessageModel smm = new SendMessageModel();
    private Gson gson = new Gson();
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public ThreadClient(Socket s,BufferedReader read, BufferedWriter write) {
        this.s = s;
        this.id = id;
        this.read = read;
        this.write = write;
        this.isloggedin=true;
    }
    public ThreadClient(){};
    @Override
    public void run(){
        while (true) {            
            try {
                String reccive = Reccive();
                StringTokenizer st = new StringTokenizer(reccive, "#");
                switch (st.nextToken()){
                    case "login":
                        checkUser(st.nextToken());
                        break;
                    case "ClientToClient":
                        smm = gson.fromJson(st.nextToken(),new TypeToken<SendMessageModel>() {}.getType());
                        for(ThreadClient tc : Server.ar){
                            if(smm.getToUserId().equals(tc.getId())){
                                tc.write.write("ClientToClient#"+smm.getFromUserId()+":"+smm.getMessage());
                                tc.write.newLine();
                                tc.write.flush();
                                break;                            
                            }
                        }
                        break;
                    case "register":
                        break;
                }
        } catch (Exception ex) {
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
    public void checkUser(String account) throws IOException, Exception{
        ac = new AccountDAL();
        StringTokenizer st = new StringTokenizer(account,":");
        AccountModel am =  ac.getUser(st.nextToken(), st.nextToken());
        if(am == null){
            send("loginfaile#");
        }
        else{
            System.out.println(am.getFullName()+" login sucess");
            setId(am.getId());
            loadListUser(am);
        }  
    }
    public void loadListUser(AccountModel ab){
        ac = new AccountDAL();
        int i=0;
        for(AccountModel a : ac.allAccount){
            if(a.getId().equalsIgnoreCase(ab.getId())){
                i = ac.allAccount.indexOf(a);
                break;
            }
        }
        ac.allAccount.remove(i);
        String listuser = convertArToString(ac.allAccount);
        String user = convertArToString(ab);
        send("loginsucess#"+listuser+"#"+user);
    }
    public String convertArToString(Object object){
        return gson.toJson(object);
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
