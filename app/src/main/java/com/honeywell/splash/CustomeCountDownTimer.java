package com.honeywell.splash;


import android.os.Handler;

public class CustomeCountDownTimer implements Runnable {
    private final ICountDownHandler countDownHandler;
    private int time;
    private final Handler handler;
    private boolean isRuning;
    private int countDownTime;

    //实时显示时间
    //递减
    //结束后显示跳过
    public CustomeCountDownTimer(int time,ICountDownHandler countDownHandler){
        this.time = time;
        this.countDownHandler = countDownHandler;
        this.countDownTime = time;
        handler = new Handler();
    }
    //实现的具体逻辑
    @Override
    public void run() {
        if (isRuning) {
            if (countDownHandler != null) {
                countDownHandler.onTicker(countDownTime);
            }
            if(countDownTime == 0) {
                if(countDownHandler != null){
                    countDownHandler.onFinish();
                }
            } else {
                countDownTime = time--;
                handler.postDelayed(this,1000);
            }
        }
    }
    //观察者回调接口 (IOC 数据回调)
    public interface ICountDownHandler {
        //倒计时回调
        void onTicker (int time);
        //结束时回调
        void onFinish ();
    }

    public void star() {
        isRuning = true;
        handler.post(this);
    }
    public void cancel() {
        isRuning = false;
        handler.removeCallbacks(this);
    }

}
