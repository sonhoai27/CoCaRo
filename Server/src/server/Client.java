/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.Socket;

/**
 *
 * @author sonho
 */
public class Client {
    private Socket socket;
    private int role;//xac dinh thang a hay b
    private Threads thread;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Threads getThread() {
        return thread;
    }

    public void setThread(Threads thread) {
        this.thread = thread;
    }

    public Client(Socket socket) {
        this.socket = socket;
    }

    public Client() {
    }

    @Override
    public String toString() {
        return "Client{" + "socket=" + socket + '}';
    }
    
    
}
