package com.sonhoai.sonho.tiktak;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MultiPlayer extends AppCompatActivity {
    private ImageView img;
    private ChessMul chessMul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
        img = findViewById(R.id.img2);

        chessMul = new ChessMul(MultiPlayer.this, 600,600,8,8);
        chessMul.init();
        img.setImageBitmap(chessMul.drawBoard());
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return chessMul.onTouch(view, motionEvent);
            }
        });
//        TwoPlayers twoPlayers = new TwoPlayers("192.168.141.2", 8081, new CallBack() {
//            @Override
//            public void onTaskCompleted(String result) {
//                Log.i("AAAA", result);
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//
//            }
//        });
//
//        twoPlayers.execute();
    }
}
