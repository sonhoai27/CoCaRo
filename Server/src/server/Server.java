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
    private List<Client> listClient;
   
    ServerSocket serverSocket;
    
    public Server(){
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
            if (listClient.size() > 1) {
                int numberMatches = listClient.size() / 2;

                for (int i = 0; i < numberMatches; i++) {
                    Client playerA = listClient.get(i);
                    playerA.setRole(0);

                    Client playerB = listClient.get(i + numberMatches);
                    playerB.setRole(1);
                    Threads threads = new Threads(playerA, playerB);
                    System.out.println("i = " + i);
                    threads.start();
                }
                
                System.out.println(listClient);
            }
        }

        @Override
        public void run() {
            Socket socket = null;

            try {
                serverSocket = new ServerSocket(SocketServerPORT);
                System.out.println("I'm waiting here: "
                    + serverSocket.getLocalPort());
                    System.out.println("CTRL + C to quit");

                while (true) {
                    socket = serverSocket.accept();
                    System.out.println(socket.getInetAddress());
                    Client client = new Client(socket);
                    listClient.add(client);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }
    }
}
