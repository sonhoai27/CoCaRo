/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sonho
 */
public class Server {

    static final int SocketServerPORT = 8081;
    String msg = "";
    private List<Socket> listClient;

    ServerSocket serverSocket;

    public Server() {
        System.out.println(ServerSocketHelper.getIpAddress());
        listClient = new ArrayList<>();
        ServerThread serverThread = new ServerThread();
        serverThread.start();
    }

    public static void main(String[] args) {
        Server server = new Server();
    }

    private class ServerThread extends Thread {

        public ServerThread() {
        }

        @Override
        public void run() {
         
            try {
                serverSocket = new ServerSocket(SocketServerPORT);
                System.out.println("I'm waiting here: "
                        + serverSocket.getLocalPort());
                System.out.println("CTRL + C to quit");

                Socket socket = serverSocket.accept();
               
                Socket socket2 = serverSocket.accept();
                System.out.println(socket2.getInetAddress());
                listClient.add(socket2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
