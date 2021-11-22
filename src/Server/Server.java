/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Database.AccountDAL;
import Model.AccountModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {
    static Vector<ThreadClient> listUserLogin = new Vector<>();
    public ServerSocket serverSocket;
    public Socket socket;
    public BufferedReader read;
    public BufferedWriter write;
    
    public void startServer() throws IOException{
        serverSocket = new ServerSocket(9001); 
        sendMessage.start();
        while (true)
        {
            socket = serverSocket.accept();
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ThreadClient mtch = new ThreadClient(socket, read,write);
            Thread t = new Thread(mtch);
            listUserLogin.add(mtch);
            t.start();           
        }
    }
    Thread sendMessage = new Thread(new Runnable(){
        @Override
        public void run() {
            while (true) {                
            AccountDAL ac = new AccountDAL();
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            StringTokenizer st = new StringTokenizer(command,">");
            switch(st.nextToken().toLowerCase()){
                case "alluser":
                    int sum = ac.allAccountInfor.size();
                    System.out.println("Sum user: "+sum);
                    break;
                case "alluseronline":
                    int sumuseronline=0;
                    for(ThreadClient tc : listUserLogin){
                        if(tc.getId() != null){
                            sumuseronline++;
                        }
                    }
                    System.out.println("Sum user online: "+sumuseronline);
                    break;
                case "block":
                    for(ThreadClient tc : listUserLogin){
                        if(tc.getId().equals(st.nextToken())){
                            tc.send("block#~");
                            tc.setBlock(true);
                            break;
                        }    
                    }
                    break;
                case "allmessage":
                    String message = st.nextToken();
                    for(ThreadClient tc : listUserLogin){
                        if(tc.getId()!=null){
                            tc.send("messagesystem#~system^&"+message);
                        }    
                    }
                    break;
                }
            }
        }
    });
    public static void main(String[] args) throws IOException
    {
        Server sv = new Server();
        sv.startServer();
    }
}
