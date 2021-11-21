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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class Server {
    static Vector<ThreadClient> listUserLogin = new Vector<>();
    public ServerSocket serverSocket;
    public Socket socket;
    public BufferedReader read;
    public BufferedWriter write;
    
    public void startServer() throws IOException{
        serverSocket = new ServerSocket(9001); 
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
    public static void main(String[] args) throws IOException
    {
        Server sv = new Server();
        sv.startServer();
    }
}
