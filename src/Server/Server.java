package Server;

import Database.AccountDAL;
import Encrypt.UtilsRSA;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Server {
    static Vector<ThreadClient> listUserLogin = new Vector<>();
    public ServerSocket serverSocket;
    public Socket socket;
    public BufferedReader read;
    public BufferedWriter write;
    
    public void startServer() throws IOException, Exception{
        serverSocket = new ServerSocket(9001);
        System.out.println("Server is listening....");
        UtilsRSA.GenerateKeys();
        sendMessage.start();
        while (true)
        {
            socket = serverSocket.accept();
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ThreadClient mtch = new ThreadClient(socket, read,write,UtilsRSA.loadPub());
            Thread t = new Thread(mtch);
            listUserLogin.add(mtch);
            t.start();           
        }
    }
    private boolean isUserId(String str) {
        Pattern p1 = Pattern.compile("([a-z])");
        Matcher m1 = p1.matcher(str);
        Pattern p2 = Pattern.compile("([A-Z])");
        Matcher m2 = p2.matcher(str);
        if(m1.find()==true){
            return false;
        }
        if(m2.find() == true){
            return false;
        }
        Pattern p3 = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m3 = p3.matcher(str);
        if(m3.find() == true){
            return false;
        }
        if(str.equals("")){
            return false;
        }
        return true;
    }  
    Thread sendMessage = new Thread(new Runnable(){
        @Override
        public void run() {
            while (true) {                
                AccountDAL ac = new AccountDAL();
                Scanner sc = new Scanner(System.in);
                String command = sc.nextLine();
                if(command.equals("") || !command.contains(">")){
                    System.out.println("Sai cú pháp");
                    System.out.println("Nếu muốn biết tổng user hãy nhập: alluser>");
                    System.out.println("Nếu muốn biết tổng user online hãy nhập: alluseronline>");
                    System.out.println("Nếu muốn block user hãy nhập: block>userid");
                    System.out.println("Nếu muốn unblock user hãy nhập: unblock>userid");
                    System.out.println("Nếu muốn gửi tin nhắn cho tất user hãy nhập: allmessage>message");
                }
                else{
                    String [] cmd = command.split(">");
                    if(cmd.length<3){
                        switch(cmd[0]){
                            case "alluser":
                                if(cmd.length==1){
                                    AccountDAL dal = new AccountDAL();
                                    int sum = dal.allAccount.size();
                                    System.out.println("Sum user: "+sum); 
                                }
                                else{
                                    System.out.println("Sai cú pháp");
                                    System.out.println("Nếu muốn biết tổng user hãy nhập: alluser>");
                                    System.out.println("Nếu muốn biết tổng user online hãy nhập: alluseronline>");
                                    System.out.println("Nếu muốn block user hãy nhập: block>userid");
                                    System.out.println("Nếu muốn unblock user hãy nhập: unblock>userid");
                                    System.out.println("Nếu muốn gửi tin nhắn cho tất user hãy nhập: allmessage>message");
                                }
                                break;
                            case "alluseronline":
                                if(cmd.length==1){
                                    int sumuseronline=0;
                                    for(ThreadClient tc : listUserLogin){
                                        if(tc.getId() != null && !tc.getId().equals("")){
                                            sumuseronline++;
                                        }
                                    }
                                    System.out.println("Sum user online: "+sumuseronline);
                                }
                                else{
                                    System.out.println("Sai cú pháp");
                                    System.out.println("Nếu muốn biết tổng user hãy nhập: alluser>");
                                    System.out.println("Nếu muốn biết tổng user online hãy nhập: alluseronline>");
                                    System.out.println("Nếu muốn block user hãy nhập: block>userid");
                                    System.out.println("Nếu muốn unblock user hãy nhập: unblock>userid");
                                    System.out.println("Nếu muốn gửi tin nhắn cho tất user hãy nhập: allmessage>message");
                                }
                                break;
                            case "block":
                                try {
                                    String userid = cmd[1];
                                    if(isUserId(userid)){
                                        for(ThreadClient tc : listUserLogin){
                                            if(tc.getId().equals(userid)){
                                                tc.send("block#~");
                                                tc.updateStatus(tc.getId(), "false");
                                                tc.saveLog("User Id "+tc.getId()+" is blocked");
                                                tc.setId(null);
                                                ac.UpdateBlock(userid, "True");
                                                break;
                                            }    
                                        }
                                    }
                                    else{
                                        System.out.println("Sai cú pháp");
                                        System.out.println("Nếu muốn biết tổng user hãy nhập: alluser>");
                                        System.out.println("Nếu muốn biết tổng user online hãy nhập: alluseronline>");
                                        System.out.println("Nếu muốn block user hãy nhập: block>userid");
                                        System.out.println("Nếu muốn unblock user hãy nhập: unblock>userid");
                                        System.out.println("Nếu muốn gửi tin nhắn cho tất user hãy nhập: allmessage>message");
                                    }
                                    break;
                                } catch (Exception e) {
                                } 
                            case "unblock":
                                try {
                                    String id = cmd[1];
                                    if(isUserId(id)){
                                        ac.UpdateBlock(id, "False");
                                    }
                                    else{
                                        System.out.println("Sai cú pháp");
                                        System.out.println("Nếu muốn biết tổng user hãy nhập: alluser>");
                                        System.out.println("Nếu muốn biết tổng user online hãy nhập: alluseronline>");
                                        System.out.println("Nếu muốn block user hãy nhập: block>userid");
                                        System.out.println("Nếu muốn unblock user hãy nhập: unblock>userid");
                                        System.out.println("Nếu muốn gửi tin nhắn cho tất user hãy nhập: allmessage>message");
                                    }
                                    break;
                                } catch (Exception e) {
                                }
                            case "allmessage":
                                String message = cmd[1];
                                for(ThreadClient tc : listUserLogin){
                                    if(tc.getId()!=null){
                                        tc.send("messagesystem#~system#-~"+message.replace("#~","^1g*u~").replace("#-~", "`qh$v0"));
                                    }    
                                }
                                break;
                            default:
                                System.out.println("Sai cú pháp");
                                System.out.println("Nếu muốn biết tổng user hãy nhập: alluser>");
                                System.out.println("Nếu muốn biết tổng user online hãy nhập: alluseronline>");
                                System.out.println("Nếu muốn block user hãy nhập: block>userid");
                                System.out.println("Nếu muốn unblock user hãy nhập: unblock>userid");
                                System.out.println("Nếu muốn gửi tin nhắn cho tất user hãy nhập: allmessage>message");
                                break;
                        }
                    }
                    else{
                        System.out.println("Sai cú pháp");
                        System.out.println("Nếu muốn biết tổng user hãy nhập: alluser>");
                        System.out.println("Nếu muốn biết tổng user online hãy nhập: alluseronline>");
                        System.out.println("Nếu muốn block user hãy nhập: block>userid");
                        System.out.println("Nếu muốn unblock user hãy nhập: unblock>userid");
                        System.out.println("Nếu muốn gửi tin nhắn cho tất user hãy nhập: allmessage>message");
                    }  
                }              
            }
        }
    });
    public static void main(String[] args) throws IOException, Exception
    {
        Server sv = new Server();
        sv.startServer();
    }
}
