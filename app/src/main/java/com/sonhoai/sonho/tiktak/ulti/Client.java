package com.sonhoai.sonho.tiktak.ulti;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends AsyncTask<Void, Void, String> {
    private String address;
    private int port;
    public static Socket socket;
    public CallBack callBack;

    public Client(String address, int port){
        this.port = port;
        this.address = address;
    }
    private void connect() {
        try {
            socket = new Socket(address, port);
        } catch (IOException ex) {
            Log.d("exception", ex.toString());
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        connect();
        while (true){
            DataInputStream inputStream = null;
            try {
                inputStream = new DataInputStream(socket.getInputStream());
                String serverMessage = inputStream.readUTF();
                callBack.onTaskCompleted(serverMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        callBack.onTaskCompleted(s);
    }

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket) {
        Client.socket = socket;
    }
}
