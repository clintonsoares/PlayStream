package com.streamplayer.playstream.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.streamplayer.playstream.R;

public class MainActivity extends AppCompatActivity {

    private Button videosBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videosBtn = (Button) findViewById(R.id.btn_toVideosList);

        videosBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,VideosListActivity.class);
            startActivity(intent);
        });

    }
}