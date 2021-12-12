package Server;

import Database.AccountDAL;
import Database.GroupDAL;
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
    private BufferedReader read;
    private BufferedWriter write;
    private Socket s;
    private AccountDAL ac = new AccountDAL();
    private Gson gson = new Gson();
    private String id = null;
    private Server server = new Server();
    private String sessionkey = null;
    private String keyrsa;
    private GroupDAL gr;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Socket getS() {
        return s;
    }

    public ThreadClient(Socket s,BufferedReader read, BufferedWriter write,String keyrsa) throws Exception {
        this.s = s;
        this.read = read;
        this.write = write;
        this.keyrsa = keyrsa;
        gr = new GroupDAL();
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
        String otp = null;
        while (true) {            
            try {
                String reccive = Reccive();
                if(!reccive.contains("hello#~")){
                     reccive = UtilsAES.DecryptText(sessionkey, reccive);
                     //System.out.println("server nhan: "+reccive);
                }
                String[] message = reccive.split("#~");
                switch (message[0]){
                    case "hello":
                        sessionkey = UtilsRSA.DecryptText(message[1]);
                        System.out.println("key: "+sessionkey);
                        break;
                    case "login":
                        checkUser(message[1],message[2]);
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
                        SendMessageModel mesgroup = gson.fromJson(message[1],new TypeToken<SendMessageModel>() {}.getType());
                        String username = message[2];
                        ArrayList<String> list = gr.allUserInGroup(mesgroup.getToUserId());
                        list.remove(mesgroup.getFromUserId());
                        for(String s:list){
                            for(ThreadClient tc : server.listUserLogin){
                                if(tc.getId().equals(s)){
                                    tc.send("ClientToClient#~"+mesgroup.getToUserId()+"#-~"+mesgroup.getMessage()+"#-~"+username);
                                    break;
                                }
                            }
                        }
                        saveMessageGroup(mesgroup.getFromUserId(), mesgroup.getToUserId(), mesgroup.getMessage(),username);
                        break;
                    case "seenmsg":
                        for(ThreadClient tccc : server.listUserLogin){
                            if(tccc.getId()!=null && tccc.getId().equals(message[1])){
                                tccc.send("seenmsg#~"+message[2]);
                                break;
                            }
                        }
                        break;
                    case "OTP":
                        AccountDAL check = new AccountDAL();
                        if(check.getAllAccount("Username='"+message[1]+"'", null).size()>0){
                            send("checkuser#~false");
                        }
                        else{
                            send("checkuser#~true");
                            OtpAuthentication authen = new OtpAuthentication();
                            otp = String.valueOf(authen.randomOtp());
                            authen.sendMail(message[1], otp);
                        }
                        break;
                    case "authenotp":
                        if(message[1].equals("check")){
                            if(message[2].equals(otp)){
                                send("authenotp#~true");
                                otp=null;
                            }
                            else{
                                send("authenotp#~false");
                            }
                        }
                        if(message[1].equals("timeout")){
                            otp = null;
                        }
                        break;
                    case "blockuser":
                        StringTokenizer block = new StringTokenizer(message[1],"^&");
                        String type = block.nextToken();
                        String iduser = block.nextToken();
                        String iduserblock = block.nextToken();
                        if(type.equals("block")){
                            for(ThreadClient tc : server.listUserLogin){
                                if(tc.getId().equals(iduserblock)){
                                    tc.send("blockuser#~updateuserblock^&"+iduser);
                                    break;
                                }
                            }
                            ac.insertBlockUser(iduser, iduserblock);
                        }
                        if(type.equals("unblock")){
                            for(ThreadClient tc : server.listUserLogin){
                                if(tc.getId().equals(iduserblock)){
                                    tc.send("blockuser#~updateuserunblock^&"+iduser);
                                    break;
                                }
                            }
                            ac.deleteBlockUser(iduser, iduserblock);
                        }
                        if(type.equals("blockgroup")){
                            gr.updateBlockGroup(iduser, iduserblock,"True");
                        }
                        if(type.equals("unblockgroup")){
                            gr.updateBlockGroup(iduser, iduserblock,"False");
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
                        gr.insert(g);
                        String grnew = convertArToString(gr.updateGrNew());
                        for(String l:g.getListUserGroup()){
                            for(ThreadClient t:server.listUserLogin){
                                if(l.equals(t.getId())){
                                    t.send("loadgroup#~loadgrnew#~"+grnew);
                                    break;
                                }
                            }
                        }
                        saveLog("User Id "+g.getListUserGroup().get(0)+" create group "+g.getNameGroup());
                        break;
                    case "exitgroup":
                        gr.deleteUserInGr(message[1], message[2]);
                        break;
                    case "updateuser":
                        AccountModel updateuser = new AccountModel();
                        if(message[1].equals("infor")){
                            updateuser = gson.fromJson(message[2],new TypeToken<AccountModel>() {}.getType());
                            ac.UpdateInfor(updateuser);
                            saveLog("User Id "+updateuser.getId()+" update information account");
                        }
                        if(message[1].equals("checkpassold")){
                            if(ac.checkPassOld(message[2], message[3])){
                                send("confirmPass#~true");
                            }
                            else{
                                send("confirmPass#~false");
                            }
                        }
                        if(message[1].equals("updatepass")){
                            ac.updatePass(message[2], message[3]);
                        }
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
                        setId(null);
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
    public void checkUser(String user,String pass) throws IOException, Exception{
        AccountModel am =  ac.getUser(user, pass);
        if(am==null){
            send("loginfaile#~wrongaccount");
        }
        if(am!=null && checkUserLoging(am.getId())){
            send("loginfaile#~notsuccess");
        }
        if(am!=null && checkUserLoging(am.getId())==false){
            if(am.getBlock().equals("True")){
                send("block#~");
            }
            else{
                setId(am.getId());
                System.out.println(am.getFullName()+" login sucess");              
                am.setStatus(true);
                //cập nhật trạng thái user online
                updateStatus(am.getId(),"true");
                //load danh sách user
                loadListUser(am);
                //load danh sách gr
                loadListGroup(am.getId());
                //load listblock
                loadListUserBlock(am.getId());
                //lưu log user login
                saveLog("User Id "+am.getId()+" login success");
            }
        }  
    }
    public boolean checkUserLoging(String id){
        for(ThreadClient tc : server.listUserLogin){
            if(tc.getId()!= null && tc.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    public void loadListGroup(String iduser) throws Exception{
        ArrayList<GroupModel> g = gr.getGroupUser(iduser);
        String listgroup = convertArToString(g);
        send("loadgroup#~load#~"+listgroup);
    }
    public void loadListUser(AccountModel ab){
        //set trạng thái cho user đang online
        AccountDAL acc = new AccountDAL();
        for(AccountModel am : acc.allAccountInfor){
            for(ThreadClient tc : server.listUserLogin){
                if(am.getId().equals(tc.getId())){
                    am.setStatus(true);
                    break;
                }
            }
        }
        for(AccountModel a : acc.allAccountInfor){
            if(a.getId().equals(ab.getId())){
                int i = acc.allAccountInfor.indexOf(a);
                acc.allAccountInfor.remove(i);
                break;
            }
        }
        String listuser = convertArToString(acc.allAccountInfor);
        String user = convertArToString(ab);
        send("loginsucess#~"+listuser+"#~"+user);
    }
    public void updateStatus(String id,String status) throws IOException{
        if(id!=null){
            for(ThreadClient tc : Server.listUserLogin){
                if(tc.getId()!=null && !tc.getId().equals(id)){
                    tc.send("status#~"+status+"#~"+id);
                }
            }
        }
        
    }
    public void loadListUserBlock(String iduser) throws Exception{
        String userblock = convertArToString(ac.listUserblock(iduser));
        String userblocked = convertArToString(ac.listUserBlocked(iduser));
        String groupblock = convertArToString(gr.loadListGroupBlock(iduser));
        if(!userblock.equals("[]")){
            send("blockuser#~userblock^&"+userblock);
        }
        if(!userblocked.equals("[]")){
            send("blockuser#~userblocked^&"+userblocked);
        }
        if(!groupblock.equals("[]")){
            send("blockuser#~blockgroup^&"+groupblock);
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
    }
    public void saveMessageGroup(String iduser,String groupid,String message,String username) throws IOException{
        String url = "./src/Server/SaveMessage/"+groupid+".txt";
        BufferedWriter savemessage = new BufferedWriter(new FileWriter(new File(url).getAbsoluteFile(),true));
        savemessage.write(iduser+"#!~"+message.trim()+"#!~"+username+"\n");
        savemessage.close();
    }
    public void loadMessageGroup(String iduser,String groupid) throws FileNotFoundException, IOException{
        String url = "./src/Server/SaveMessage/"+groupid+".txt";
        File files = new File(url);
        if(files.exists()){
            BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(url)));
            String line = read.readLine();
            while(line!=null){
                String[] a = line.trim().split("#!~");
                send("loadmessage#~yes#~"+a[0]+"#-~"+a[1]+"#-~"+a[2]);
                line = read.readLine();
            }
            read.close();
        }
    }
    public void userLogout() throws IOException{
        //cập nhật trạng thái user offline
            updateStatus(getId(),"false");
            //save log user logout
            if(getId()!=null){
                saveLog("User Id "+getId()+" logout");
            }
            for(ThreadClient tc : server.listUserLogin){
                if(tc.getS().equals(getS())){
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
