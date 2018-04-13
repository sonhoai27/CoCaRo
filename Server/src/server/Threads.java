/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 *
 * @author sonho
 */
public class Threads extends Thread{
    private Client playerA;
    private Client playerB;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    
    private String message;

    public Threads(Client playerA, Client playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        this.message = message;
    }
    
    
}
