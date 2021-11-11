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

/**
 *
 * @author man21
 */
public class Server {
    static Vector<ThreadClient> ar = new Vector<>();
    static int i = 0;
    public static void main(String[] args) throws IOException
    {
        ServerSocket sevverSocket = new ServerSocket(9001);
        Socket socket;
        while (true)
        {
            socket = sevverSocket.accept();
            System.out.println("New client request received : " + socket);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            System.out.println("Creating a new handler for this client...");
            //System.out.println(dis.readUTF());
            ThreadClient mtch = new ThreadClient(socket,"client " + i, dis,dos);
            Thread t = new Thread(mtch);
            System.out.println("Adding this client to active client list");
            ar.add(mtch);
            t.start();
            System.out.println(i);
            i++;           
        }
    }
}
