package Server;

import Encrypt.UtilsAES;
import Encrypt.UtilsRSA;
import Form.Body.Event.PublicEvent;
import Model.AccountModel;
import Model.GroupModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Client{
    static Client instance;
    private Socket socket=null;
    private BufferedReader read = null;
    private BufferedWriter write = null;
    private String localhost = "127.0.0.1";
    private String internet = "serverchat.ddns.net";
    public AccountModel User;
    private Gson gson = new Gson();
    private String sessionkey = null;
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
        sessionkey = null;
        t.start(); 
    }
    Thread t = new Thread(){
        @Override
        public void run() {
            while (true) {
                try { 
                    String msg = read.readLine();
                    //System.out.println("Da nhan: "+msg);
                    if(!msg.contains("hello#~")){
                        msg = UtilsAES.DecryptText(sessionkey,msg);
                        System.out.println("giai: "+msg);
                    }
                    String[] message = msg.split("#~");
                    String type = message[0];
                    switch(type){
                        case "hello":
                            sessionkey = UtilsAES.generateKey();
                            //System.out.println("ss: "+sessionkey);
                            String tmpa = UtilsRSA.EncryptText(sessionkey,message[1]);
                            write.write("hello#~"+tmpa);
                            write.newLine();
                            write.flush();
                            break;
                        case "loginsucess":
                            ArrayList<AccountModel> listUser= gson.fromJson(message[1],new TypeToken<ArrayList<AccountModel>>() {}.getType());
                            User = gson.fromJson(message[2],new TypeToken<AccountModel>() {}.getType());
                            PublicEvent.getInstance().getEvenLoginSuccess().LoginSuccess("success");
                            PublicEvent.getInstance().getEventMenuLeft().addlistUser(listUser);
                            break;
                        case "loginfaile":
                            if(message[1].equals("wrongaccount")){
                                PublicEvent.getInstance().getEvenLoginSuccess().LoginSuccess("Notsuccess");
                            }
                            if(message[1].equals("notsuccess")){
                                PublicEvent.getInstance().getEventMain().BlockUser(message[1]);
                            }
                            break;
                        case "loadgroup":
                            Gson g = new Gson();
                            ArrayList<GroupModel> listgroup = g.fromJson(message[2],new TypeToken<ArrayList<GroupModel>>() {}.getType());
                            if(message[1].equals("load")){
                                PublicEvent.getInstance().getEventMenuLeft().listGroup(listgroup,message[1]);
                            }
                            if(message[1].equals("loadgrnew")){
                                PublicEvent.getInstance().getEventMenuLeft().listGroup(listgroup,message[1]);
                            }
                            break;
                        case "ClientToClient":
                              PublicEvent.getInstance().getEventChat().reciveMessage(message[1]);
                              break;
                        case "status":
                            String status = message[1];
                            if(status.equals("true")){
                                PublicEvent.getInstance().getEventMenuLeft().updateStatusOnline(message[2]);
                            }
                            if(status.equals("false")){
                                PublicEvent.getInstance().getEventMenuLeft().updateStatusOffline(message[2]);
                            }
                            break;
                        case "block":
                            PublicEvent.getInstance().getEventMain().BlockUser(type);
                            break;
                        case "messagesystem":
                            PublicEvent.getInstance().getEventChat().reciveMessage(message[1]);
                            break;
                        case "loadmessage":
                            String tmp = message[1];
                            if(tmp.equals("yes")){
                                PublicEvent.getInstance().getEventChat().loadMessage(message[2].toString());
                            }
                            break;
                        case "authenotp":
                            if(message[1].equals("true")){
                                PublicEvent.getInstance().getEventAuthenOtp().authenSucess();
                            }
                            if(message[1].equals("false")){
                                PublicEvent.getInstance().getEventAuthenOtp().authenFaile();
                            }
                            break;
                        case "checkuser":
                            PublicEvent.getInstance().getEventCheckUserName().checkUserName(message[1]);
                            break;
                        case "blockuser":
                            if(message[1].contains("updateuserunblock") || message[1].contains("updateuserblock")){
                               PublicEvent.getInstance().getEventChat().updateUserBlock(message[1]);
                            }
                            else{
                                PublicEvent.getInstance().getEventChat().loadListBlock(message[1]);
                            }
                            break;
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
        String ma=UtilsAES.EncryptText(sessionkey,message);
        //System.out.println("Da gui: "+ma);
        write.write(ma);
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
