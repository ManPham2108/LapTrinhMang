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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException; 
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger; 

public class ThreadClient implements Runnable{
    public BufferedReader read;
    public BufferedWriter write;
    private Socket s;
    private AccountDAL ac = new AccountDAL();;
    private SendMessageModel smm = new SendMessageModel();
    private Gson gson = new Gson();
    private String id;
    private Server server = new Server();
    private boolean block;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    
    public ThreadClient(Socket s,BufferedReader read, BufferedWriter write) {
        this.s = s;
        this.id = id;
        this.read = read;
        this.write = write;
        this.block = block;
    }
    public ThreadClient(){};
    @Override
    public void run(){
        while (true) {            
            try {
                String reccive = Reccive();
                StringTokenizer st = new StringTokenizer(reccive, "#~");
                switch (st.nextToken()){
                    case "login":
                        if(isBlock()==true){
                            send("block#~");
                        }
                        else{
                            checkUser(st.nextToken());      
                        }   
                        break;
                    case "ClientToClient":
                        smm = gson.fromJson(st.nextToken(),new TypeToken<SendMessageModel>() {}.getType());
                        for(ThreadClient tc : Server.listUserLogin){
                            if(smm.getToUserId().equals(tc.getId())){
                                tc.write.write("ClientToClient#~"+smm.getFromUserId()+"^&"+smm.getMessage());
                                tc.write.newLine();
                                tc.write.flush();
                                break;                            
                            }
                        }
                        break;
                    case "register":
                        AccountModel userRegister = new AccountModel();
                        userRegister = gson.fromJson(st.nextToken(),new TypeToken<AccountModel>() {}.getType());
                        ac.Insert(userRegister);
                        saveLog(userRegister.getUsername()+" register success");
                        break;
                    case "updateuser":
                        AccountModel updateuser = new AccountModel();
                        updateuser = gson.fromJson(st.nextToken(),new TypeToken<AccountModel>() {}.getType());
                        ac.Update(updateuser);
                        saveLog(updateuser.getId()+" update information account");
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
            System.out.println(am.getFullName()+" login sucess");
            setId(am.getId());
            am.setStatus(true);
            //cập nhật trạng thái user online
            updateStatus(am.getId(),"true");
            //load danh sách user
            loadListUser(am);
            //lưu log user login
            saveLog("User Id "+am.getId()+" login success");
        }  
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
    public String convertArToString(Object object){
        return gson.toJson(object);
    }
    public void updateStatus(String id,String status) throws IOException{
        for(ThreadClient tc : Server.listUserLogin){
            if(tc.getId()!=null && !tc.getId().equals(id)){
                tc.write.write("status#~"+status+"#~"+id);
                tc.write.newLine();
                tc.write.flush();
            }
        }
        
    }
    public void saveLog(String log) throws IOException{
        BufferedWriter W = new BufferedWriter(new FileWriter(new File("./src/Server/LogServer.txt").getAbsoluteFile(),true));
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        W.write(timeStamp+" "+log+"\n");
        W.close();
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
