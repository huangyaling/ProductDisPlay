package com.cambricon.productdisplay.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.cambricon.productdisplay.R;
import com.cambricon.productdisplay.adapter.BannerPagerAdapter;
import com.cambricon.productdisplay.task.BannerTimerTask;
import com.cambricon.productdisplay.view.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


/**
 * Created by dell on 18-1-29.
 */

public class AdvertiseActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;
    private BannerPagerAdapter mBannerPagerAdapter;
    private List<Integer> pictureList=new ArrayList<>();
    public static final int AUTOBANNER_CODE=0x1001;
    private int mBannerPosition;
    private Timer timer=new Timer();
    private BannerTimerTask mBannerTimerTask;
    private boolean mIsUserTouched=false;
    private LinearLayout mLinearLayout;
    private int mPagerindex=0;

    private android.support.v7.widget.Toolbar toolbar;

    Handler bannerHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if(!mIsUserTouched){
                mBannerPosition=mViewPager.getCurrentItem();
                mBannerPosition=(mBannerPosition+1)%mBannerPagerAdapter.FAKE_BANNER_SIZE;
                mViewPager.setCurrentItem(mBannerPosition);
                mLinearLayout.getChildAt(mPagerindex).setEnabled(false);
                if(mBannerPosition>pictureList.size()-1){
                    mPagerindex=mBannerPosition%pictureList.size();
                    Log.d("huangyaling","recycle index:"+mPagerindex);
                }else{
                    mPagerindex=mBannerPosition;
                }
                mLinearLayout.getChildAt(mPagerindex).setEnabled(true);
                Log.d("huangyaling","mPagerindex="+mPagerindex+";"+"mBannerPosition="+mBannerPosition);
            }
            return true;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advertise_layout);
        initView();
        initData();
        setToolbar();
    }

    private void initData(){
        pictureList.add(R.drawable.ad_1);
        pictureList.add(R.drawable.ad_2);
        pictureList.add(R.drawable.ad_3);
        pictureList.add(R.drawable.ad_4);
        pictureList.add(R.drawable.ad_2);
        pictureList.add(R.drawable.ad_3);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  mLinearLayout=findViewById(R.id.indicator_layout);
        for(int i=0;i<pictureList.size();i++){
            View view=new View(this);
            view.setBackgroundResource(R.drawable.indicator_bg);
            view.setEnabled(false);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(15,15);
            layoutParams.leftMargin=10;
            mLinearLayout.addView(view,layoutParams);
        }
        mLinearLayout.getChildAt(mBannerPosition).setEnabled(true);
    }

    private void initView(){

        mViewPager=(ViewPager) findViewById(R.id.ad_banner);
        mBannerPagerAdapter=new BannerPagerAdapter(AdvertiseActivity.this,pictureList);
        mViewPager.setAdapter(mBannerPagerAdapter);
        mViewPager.setCurrentItem(pictureList.size()*100);
        mViewPager.setPageTransformer(true,new DepthPageTransformer());
        toolbar=findViewById(R.id.adv_toolbar);
        mViewPager.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action=motionEvent.getAction();
                if(action==MotionEvent.ACTION_DOWN||action==MotionEvent.ACTION_MOVE){
                    mIsUserTouched=true;
                }else if(action==MotionEvent.ACTION_UP){
                    mIsUserTouched=false;
                }
                return false;
            }
        });
        startBannerTimer();
    }

    private void startBannerTimer(){
        if(timer==null){
            timer=new Timer();
        }
        if(mBannerTimerTask!=null){
            mBannerTimerTask.cancel();
        }
        mBannerTimerTask=new BannerTimerTask(bannerHandler);
        if(timer!=null && mBannerTimerTask!=null){
            timer.schedule(mBannerTimerTask,3000,3000);
        }
    }

    @Override
    protected void onDestroy() {
        if(mBannerTimerTask!=null){
            mBannerTimerTask.cancel();
            mBannerTimerTask=null;
        }
        super.onDestroy();
    }

    /**
     * toolBar返回按钮
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置toolbar属性
     */
    public void setToolbar(){
        toolbar.setTitle(R.string.home_tab_adv);
        setSupportActionBar(toolbar);
        /*显示Home图标*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mLinearLayout.getChildAt(mPagerindex).setEnabled(false);
        mLinearLayout.getChildAt(position).setEnabled(true);
        mPagerindex=position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
