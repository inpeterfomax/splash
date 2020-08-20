package com.honeywell.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class SplashActivity extends AppCompatActivity {
    private FullScreenVideoView mVideoView;
    private TextView mTvTimer;
    private CustomeCountDownTimer customCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //系统返回 Bundle对象
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);  //将application的布局加入到了系统的布局里，所以会有上面的页眉
        mTvTimer = (TextView) findViewById(R.id.tv_splash_timer);
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        });
        mVideoView = (FullScreenVideoView) findViewById(R.id.vv_play);
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp){
                mp.start();
            }
        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();  //重复播放
            }
        });

         customCountDownTimer = new CustomeCountDownTimer(5, new CustomeCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {

                mTvTimer.setText(time + "S");
            }

            @Override
            public void onFinish() {
                mTvTimer.setText("Skip");
            }
        });
        customCountDownTimer.star();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean isDestroyed() {
        return super.isDestroyed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        customCountDownTimer.cancel();
    }
}
