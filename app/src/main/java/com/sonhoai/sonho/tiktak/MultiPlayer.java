package com.sonhoai.sonho.tiktak;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sonhoai.sonho.tiktak.ulti.CallBack;
import com.sonhoai.sonho.tiktak.ulti.Client;
import com.sonhoai.sonho.tiktak.ulti.Push;

public class MultiPlayer extends AppCompatActivity implements CallBack{
    private ImageView img2;
    private ChessMul chessMul;
    private Client client;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
        img2 = findViewById(R.id.img2);
        init();
        client.callBack = MultiPlayer.this;
        chessMul = new ChessMul(MultiPlayer.this, 600,600,8,8);
        chessMul.init();

        img2.setImageBitmap(chessMul.drawBoard());

        img2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Move move = chessMul.onTouchMove(view, motionEvent);
                    Push push = new Push();
                    push.init(move);
                    push.start();
                }
                return true;
            }
        });
    }

    private void init(){
        client = new Client("192.168.141.2", 8081);
        client.start();
    }
    @Override
    public void onTaskCompleted(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("AAAA", result);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onFailure(Exception e) {

    }
}
