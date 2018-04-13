///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package server;
//
//import java.io.DataInput;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author sonho
// */
//public class Threads extends Thread {
//
//    private Client player;
//    private DataInputStream dataInputStream;
//    private DataOutputStream dataOutputStream;
//
//    private String message;
//
//    public Threads(Client player) {
//        this.player = player;
//    }
//
//    @Override
//    public void run() {
//        super.run();
//        try {
//            while (true) {
//                String n;
//                n = (new DataInputStream(player.getSocket().getInputStream())).readUTF();
//                System.out.println(n);
//                for (int i = 0; i < Server.getListClient().size(); i++) {
//                    dataInputStream = new DataInputStream(Server.getListClient().get(i).getSocket().getInputStream());
//                    dataOutputStream = new DataOutputStream(Server.getListClient().get(i).getSocket().getOutputStream());
//                    dataOutputStream.writeUTF(n);
//                    dataOutputStream.flush();
//                }
//            }
//          
//        }catch(Exception e){
//            
//        }
//    }
//}
////                if(!Server.getListClient().get(i).getSocket().equals(socket)){
////                    dataOutputStream.writeUTF("Chao");
////                    dataOutputStream.flush();
////                }
