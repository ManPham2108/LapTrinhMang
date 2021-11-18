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
    public static void main(String[] args) throws IOException
    {
        ServerSocket sevverSocket = new ServerSocket(9001); 
        Socket socket;
        while (true)
        {
            socket = sevverSocket.accept();
            BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ThreadClient mtch = new ThreadClient(socket, read,write);
            Thread t = new Thread(mtch);
            ar.add(mtch);
            t.start();           
        }
    }
}
