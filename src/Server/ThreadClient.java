/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Database.AccountDAL;
import Database.GroupDAL;
import Encrypt.DES_For_Server;
import Encrypt.PKC_RSA;
import Encrypt.UtilsAES;
import Encrypt.UtilsRSA;
import Model.AccountModel;
import Model.GroupModel;
import Model.SendMessageModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger; 

public class ThreadClient implements Runnable{
    public BufferedReader read;
    public BufferedWriter write;
    private Socket s;
    private AccountDAL ac = new AccountDAL();
    private Gson gson = new Gson();
    private String id;
    private Server server = new Server();
    private String otp = null;
    private DES_For_Server des = new DES_For_Server();
    private String sessionkey = null;
    private String keyrsa;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public ThreadClient(Socket s,BufferedReader read, BufferedWriter write,String keyrsa) {
        this.s = s;
        this.read = read;
        this.write = write;
        this.keyrsa = keyrsa;
    }
    public ThreadClient(){};
    @Override
    public void run(){
        try {
            write.write("hello#~"+keyrsa);
            write.newLine();
            write.flush();
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {            
            try {
                String reccive = Reccive();
                if(!reccive.contains("hello#~")){
                     reccive = UtilsAES.DecryptText(sessionkey, reccive);
                     System.out.println("server nhan: "+reccive);
                }
                String[] message = reccive.split("#~");
                switch (message[0]){
                    case "hello":
                        sessionkey = UtilsRSA.DecryptText(message[1]);
                        System.out.println("key: "+sessionkey);
                        break;
                    case "login":
                        checkUser(message[1]);
                        break;
                    case "ClientToClient":
                        SendMessageModel smm = gson.fromJson(message[1],new TypeToken<SendMessageModel>() {}.getType());
                        for(ThreadClient tc : Server.listUserLogin){
                            if(smm.getToUserId().equals(tc.getId())){
                                tc.send("ClientToClient#~"+smm.getFromUserId()+"#-~"+smm.getMessage());
                                break;                            
                            }
                        }
                        saveMessage(smm.getFromUserId(), smm.getToUserId(), smm.getMessage());
                        break;
                    case "messagegroup":
                        GroupDAL group = new GroupDAL();
                        SendMessageModel mesgroup = gson.fromJson(message[1],new TypeToken<SendMessageModel>() {}.getType());
                        ArrayList<String> list = group.allUserInGroup(mesgroup.getToUserId());
                        list.remove(mesgroup.getFromUserId());
                        for(String s:list){
                            for(ThreadClient tc : server.listUserLogin){
                                if(tc.getId().equals(s)){
                                    tc.send("ClientToClient#~"+mesgroup.getToUserId()+"#-~"+mesgroup.getMessage());
                                    break;
                                }
                            }
                        }
                        saveMessageGroup(mesgroup.getFromUserId(), mesgroup.getToUserId(), mesgroup.getMessage());
                        break;
                    case "OTP":
                        OtpAuthentication authen = new OtpAuthentication();
                         otp = String.valueOf(authen.randomOtp());
                        authen.sendMail(message[1], otp);
                        break;
                    case "authenotp":
                        if(message[1].equals(otp)){
                            send("authenotp#~true");
                            otp="";
                        }
                        else{
                            send("authenotp#~false");
                        }
                        break;
                    case "register":
                        AccountModel userRegister = new AccountModel();
                        userRegister = gson.fromJson(message[1],new TypeToken<AccountModel>() {}.getType());
                        ac.Insert(userRegister);
                        saveLog(userRegister.getUsername()+" register success");
                        break;
                    case "creategroup":
                        GroupModel g = gson.fromJson(message[1],new TypeToken<GroupModel>() {}.getType());
                        GroupDAL gr = new GroupDAL();
                        gr.insert(g);
                        break;
                    case "updateuser":
                        AccountModel updateuser = new AccountModel();
                        updateuser = gson.fromJson(message[1],new TypeToken<AccountModel>() {}.getType());
                        ac.UpdateInfor(updateuser);
                        saveLog(updateuser.getId()+" update information account");
                        break;
                    case "loadmessage":
                        StringTokenizer load = new StringTokenizer(message[1],"^&");
                        String tmp = load.nextToken();
                        if(tmp.contains("G")){
                            loadMessageGroup(load.nextToken(), tmp);
                        }
                        if(!tmp.contains("G")){
                            loadMessage(tmp, load.nextToken());
                        }
                        break;
                    case "logout":
                        updateStatus(message[1], "false");
                        saveLog("User Id "+message[1]+" logout");
                        for(ThreadClient tc : server.listUserLogin){
                            if(tc.getId()==null || tc.getId().equals(message[1])){
                                tc.setId("");
                                break;
                            }
                        }
                        break;
                }
            } catch (Exception ex) {
               break;
            }
        }
        try {
            userLogout();
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void checkUser(String account) throws IOException, Exception{
        StringTokenizer st = new StringTokenizer(account,"<,");
        //System.out.println(st.nextToken());
        AccountModel am =  ac.getUser(st.nextToken(), st.nextToken());
        if(am == null){
            send("loginfaile#~");
        }
        else{
            setId(am.getId());
            if(am.getBlock().equals("True")){
                send("block#~");
            }
            else{
                System.out.println(am.getFullName()+" login sucess");              
                am.setStatus(true);
                //cập nhật trạng thái user online
                updateStatus(am.getId(),"true");
                //load danh sách user
                loadListUser(am);
                //load danh sách gr
                loadListGroup(am.getId());
                //lưu log user login
                saveLog("User Id "+am.getId()+" login success");
            }   
        }  
    }
    public void loadListGroup(String iduser) throws Exception{
        GroupDAL group = new GroupDAL();
        ArrayList<GroupModel> g = group.getGroupUser(iduser);
        String listgroup = convertArToString(g);
        send("loadgroup#~"+listgroup);
    }
    public void loadListUser(AccountModel ab){
        //set trạng thái cho user đang online
        for(AccountModel am : ac.allAccountInfor){
            for(ThreadClient tc : server.listUserLogin){
                if(am.getId().equals(tc.getId())){
                    am.setStatus(true);
                    break;
                }
            }
        }
        
        for(AccountModel a : ac.allAccountInfor){
            if(a.getId().equals(ab.getId())){
                int i = ac.allAccountInfor.indexOf(a);
                ac.allAccountInfor.remove(i);
                break;
            }
        }
        String listuser = convertArToString(ac.allAccountInfor);
        String user = convertArToString(ab);
        send("loginsucess#~"+listuser+"#~"+user);
    }
    public void updateStatus(String id,String status) throws IOException{
        for(ThreadClient tc : Server.listUserLogin){
            if(tc.getId()!=null && !tc.getId().equals(id)){
                tc.send("status#~"+status+"#~"+id);
            }
        }
        
    }
    public void saveLog(String log) throws IOException{
        BufferedWriter W = new BufferedWriter(new FileWriter(new File("./src/Server/LogServer.txt").getAbsoluteFile(),true));
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        W.write(timeStamp+" "+log+"\n");
        W.close();
    }
    public void saveMessage(String fromuserid,String touserid,String message) throws IOException{
        String url1 = "./src/Server/SaveMessage/"+fromuserid+"+"+touserid+".txt";
        String url2= "./src/Server/SaveMessage/"+touserid+"+"+fromuserid+".txt";
        File file1 = new File(url1);
        File file2 = new File(url2);
        if(file1.exists()==false && file2.exists()==false){
            BufferedWriter savemessage = new BufferedWriter(new FileWriter(new File(url1).getAbsoluteFile(),true));
            savemessage.write(fromuserid+"#!~"+message+"\n");
            savemessage.close();
        }
        else{
            if(file1.exists()){
                BufferedWriter savemessage = new BufferedWriter(new FileWriter(new File(url1).getAbsoluteFile(),true));
                savemessage.write(fromuserid+"#!~"+message+"\n");
                savemessage.close();
            }
            if(file2.exists()==true){
                BufferedWriter savemessage = new BufferedWriter(new FileWriter(new File(url2).getAbsoluteFile(),true));
                savemessage.write(fromuserid+"#!~"+message+"\n");
                savemessage.close();
            }
        }
    }
    public void loadMessage(String fromuserid,String touserid) throws FileNotFoundException, IOException{
        String url1 = "./src/Server/SaveMessage/"+fromuserid+"+"+touserid+".txt";
        String url2= "./src/Server/SaveMessage/"+touserid+"+"+fromuserid+".txt";
        File file1 = new File(url1);
        File file2 = new File(url2);
        if(file1.exists()){
            BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(url1)));
            String line = read.readLine();
            while(line!=null){
                String[] a = line.split("#!~");
                send("loadmessage#~yes#~"+a[0]+"#-~"+a[1]);
                line = read.readLine();
            }
            read.close();
        }
        if(file2.exists()){
            BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(url2)));
            String line = read.readLine();
            while(line!=null){
                String[] a = line.split("#!~");
                send("loadmessage#~yes#~"+a[0]+"#-~"+a[1]);
                line = read.readLine();
            }
            read.close();
        }
        else{
            send("loadmessage#~not");
        }
    }
    public void saveMessageGroup(String iduser,String groupid,String message) throws IOException{
        String url = "./src/Server/SaveMessage/"+groupid+".txt";
        BufferedWriter savemessage = new BufferedWriter(new FileWriter(new File(url).getAbsoluteFile(),true));
        savemessage.write(iduser+"#!~"+message+"\n");
        savemessage.close();
    }
    public void loadMessageGroup(String iduser,String groupid) throws FileNotFoundException, IOException{
        String url = "./src/Server/SaveMessage/G"+groupid+".txt";
        File file = new File(url);
        if(file.exists()){
            BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(url)));
            String line = read.readLine();
            while(line!=null){
                String[] a = line.split("#!~");
                send("loadmessage#~yes#~"+a[0]+"#-~"+a[1]);
                line = read.readLine();
            }
            read.close();
        }
        else{
            send("loadmessage#~not");
        }
        

    }
    public void userLogout() throws IOException{
        //cập nhật trạng thái user offline
        updateStatus(getId(),"false");
        //save log user logout
        saveLog("User Id "+getId()+" logout");
        for(ThreadClient tc : server.listUserLogin){
            if(tc.getId()==null || tc.getId().equals(getId())){
                int i = server.listUserLogin.indexOf(tc);
                server.listUserLogin.remove(i);
                break;
            }
        }
        this.read.close();
        this.write.close();
        this.s.close();
    }
    public void send(String message){
        try {
            String enc = UtilsAES.EncryptText(sessionkey, message);
            write.write(enc);
            write.newLine();
            write.flush();
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String Reccive() throws IOException{
        return read.readLine();
    }
    public String convertArToString(Object object){
        return gson.toJson(object);
    }
}
