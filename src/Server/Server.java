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
            System.out.println("Client "+i+" connect success");
            BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            DataInputStream dis = new DataInputStream(socket.getInputStream());
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            ThreadClient mtch = new ThreadClient(socket,"client " + i, read,write);
            Thread t = new Thread(mtch);
            ar.add(mtch);
            t.start();
            System.out.println(i);
            i++;           
        }
    }
}
