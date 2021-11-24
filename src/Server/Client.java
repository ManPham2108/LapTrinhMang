/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Form.Body.Chat.Chat_Body;
import Form.Body.Event.PublicEvent;
import Model.AccountModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;


public class Client{
    static Client instance;
    private Socket socket=null;
    private BufferedReader read = null;
    private BufferedWriter write = null;
    private String localhost = "127.0.0.1";
    private String internet = "serverchat.ddns.net";
    public AccountModel User;
    private Gson gson = new Gson();
    public static Client getInstance() {
        if(instance==null){
            instance = new Client();
        }
        return instance;
    }
    public void connect() throws IOException{ 
        socket = new Socket(localhost, 9001);
        read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        t.start();
    }
    Thread t = new Thread(){
        @Override
        public void run() {
            while (true) {
                try { 
                    String msg = read.readLine();
                    System.out.println("Da nhan: "+msg);
                    StringTokenizer st = new StringTokenizer(msg,"#~");
                    String type = st.nextToken();
                    switch(type){
                        case "loginsucess":
                            ArrayList<AccountModel> listUser= gson.fromJson(st.nextToken(),new TypeToken<ArrayList<AccountModel>>() {}.getType());
                            User = gson.fromJson(st.nextToken(),new TypeToken<AccountModel>() {}.getType());
                            PublicEvent.getInstance().getEvenLoginSuccess().LoginSuccess("success");
                            PublicEvent.getInstance().getEventMenuLeft().addlistUser(listUser);
                            PublicEvent.getInstance().getEventMenuLeft().listUserCreatGroup(listUser);
                            break;
                        case "loginfaile":
                            PublicEvent.getInstance().getEvenLoginSuccess().LoginSuccess("Notsuccess");
                            break;
                        case "ClientToClient":
                              PublicEvent.getInstance().getEventChat().reciveMessage(st.nextToken());
                              break;
                        case "status":
                            String status = st.nextToken();
                            if(status.equals("true")){
                                PublicEvent.getInstance().getEventMenuLeft().updateStatusOnline(st.nextToken());
                            }
                            if(status.equals("false")){
                                PublicEvent.getInstance().getEventMenuLeft().updateStatusOffline(st.nextToken());
                            }
                            break;
                        case "block":
                            PublicEvent.getInstance().getEventMain().BlockUser();
                            break;
                        case "messagesystem":
                            PublicEvent.getInstance().getEventChat().reciveMessage(st.nextToken());
                            break;
                        case "loadmessage":
                            String tmp = st.nextToken();
                            if(tmp.equals("yes")){
                                PublicEvent.getInstance().getEventChat().loadMessage(st.nextToken());
                            }
                    } 
                }catch (IOException ex) {
                    break;
                }
            }
            try {
                read.close();
                write.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
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
