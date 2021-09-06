package com.streamplayer.playstream.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.util.Util;
import com.streamplayer.playstream.R;

public class VideoPlayerActivity extends AppCompatActivity {
    private StyledPlayerView playerView;
    private SimpleExoPlayer player;
    private String videoUri;
    boolean playWhenReady = true;
    int currentWindow = 0;
    long playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        playerView = findViewById(R.id.player_view);
        initPlayer();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                videoUri = null;
            } else {
                videoUri = extras.getString("url");
            }
        } else {
            videoUri = (String) savedInstanceState.getSerializable("url");
        }
        String tempUri = "https://www.youtube.com/watch?v=Dl41DM2Td3I";

        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        MediaItem mediaItem1 = MediaItem.fromUri(tempUri);

//        player.setMediaItem(mediaItem);
        player.setMediaItem(mediaItem1);
        player.prepare();
        player.play();

    }

    private void initPlayer() {
        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24)
            initPlayer();
    }

    @Override
    protected void onStop() {
        if (Util.SDK_INT >= 24)
            releasePlayer();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ((Util.SDK_INT < 24 || player == null)) {
            initPlayer();
            hideSystemUI();
        }
    }

    @Override
    protected void onPause() {
        if (Util.SDK_INT >= 24)
            releasePlayer();
        super.onPause();
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }

    }

    private void hideSystemUI() {
        playerView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }

}