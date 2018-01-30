package com.cambricon.productdisplay.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cambricon.productdisplay.R;

/**
 * Created by dell on 18-1-30.
 */

public class HomeBaseActivity extends AppCompatActivity {
    private LinearLayout parentLinearLayout;//加入父类和子类布局
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(R.layout.home_bottom_tab);
    }

    //初始化contentview
    private void initContentView(int layoutResId){
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentLinearLayout = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        viewGroup.addView(parentLinearLayout);
        LayoutInflater.from(this).inflate(layoutResId,parentLinearLayout,true);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID,parentLinearLayout,true);
    }

    @Override
    public void setContentView(View view) {
        parentLinearLayout.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        parentLinearLayout.addView(view,params);
    }

}
