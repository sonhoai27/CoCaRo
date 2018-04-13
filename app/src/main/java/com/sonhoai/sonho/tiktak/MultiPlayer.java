package com.sonhoai.sonho.tiktak;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.sonhoai.sonho.tiktak.ulti.CallBack;
import com.sonhoai.sonho.tiktak.ulti.Client;

import java.net.Socket;

public class MultiPlayer extends AppCompatActivity {
    private ImageView img;
    private ChessMul chessMul;
    private Client client;
    private CallBack callBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
        img = findViewById(R.id.img2);
        init();
        chessMul = new ChessMul(MultiPlayer.this, 600,600,8,8);
        chessMul.init();
        img.setImageBitmap(chessMul.drawBoard());
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return chessMul.onTouch(view, motionEvent);
            }
        });
    }

    private void init(){
        client = new Client("192.168.141.2", 8081);
        callBack = new CallBack() {
            @Override
            public void onTaskCompleted(String result) {
                Log.i("AAA", result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        };
        client.execute();
    }
}
