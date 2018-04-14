package com.sonhoai.sonho.tiktak.ulti;

import com.sonhoai.sonho.tiktak.Move;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Push extends Thread {
    private Socket socket;
    private Move move;
    private DataOutputStream dataOutputStream;

    public Push() {
        try {
            this.socket = Client.socket;
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (Exception ex) {

        }
    }

    public void init(Move move) {
        this.move = move;
    }
    @Override
    public void run() {
        super.run();
        try {
            String message = "" + this.move.getRowIndex() +"-"+ this.move.getColIndex();
            this.dataOutputStream.writeUTF(message);
            this.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
