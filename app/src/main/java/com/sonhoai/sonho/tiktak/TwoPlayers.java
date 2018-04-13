package com.sonhoai.sonho.tiktak;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TwoPlayers extends AsyncTask<Void, Void, String> {
    private CallBack callBack;
    public Exception mException;
    String address;
    int port;

    public TwoPlayers(String address, int port, CallBack callBack) {
        this.callBack = callBack;
        this.address = address;
        this.port = port;
    }

    @Override
    protected String doInBackground(Void... strings) {
        try {
            Socket socket = new Socket(address, port);
            String result = "A";

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        callBack.onTaskCompleted(result);
    }
}
