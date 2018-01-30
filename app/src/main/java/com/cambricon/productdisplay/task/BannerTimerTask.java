package com.cambricon.productdisplay.task;

import android.os.Handler;

import com.cambricon.productdisplay.activity.AdvertiseActivity;

import java.util.TimerTask;


/**
 * Created by huangyaling on 18-1-29.
 */

public class BannerTimerTask extends TimerTask {
    Handler handler;

    public BannerTimerTask(Handler handler){
        super();
        this.handler=handler;
    }
    @Override
    public void run() {
        handler.sendEmptyMessage(AdvertiseActivity.AUTOBANNER_CODE);
    }
}
