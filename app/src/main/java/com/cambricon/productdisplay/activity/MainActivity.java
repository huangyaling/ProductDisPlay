package com.cambricon.productdisplay.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cambricon.productdisplay.R;
import com.cambricon.productdisplay.adapter.GridViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private android.support.v7.widget.Toolbar toolbar;
    private android.support.design.widget.NavigationView navigationview;
    private android.support.v4.widget.DrawerLayout drawerlayout;
    /*创建一个Drawerlayout和Toolbar联动的开关*/
    private ActionBarDrawerToggle toggle;

    private GridView mGridView;
    private GridViewAdapter mGridViewAdapter;
    private List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();

    final int REQUST_CODE = 001;
    final int CLASSIFICATION = 0;
    final int DETECTION = 1;
    final int VOICE = 2;
    final int MOREFUNCTIONS=3;

    //tab
    private LinearLayout tab_test;
    private LinearLayout tab_data;
    private LinearLayout tab_adv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        hideScrollBar();
        setActionBar();
        setDrawerToggle();
        setListener();
    }

    /**
     * 初始化View
     * */
    private void initViews() {
        tab_test = findViewById(R.id.tab_test);
        tab_data = findViewById(R.id.tab_data);
        tab_adv = findViewById(R.id.tab_adv);
        this.drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.navigationview = (NavigationView) findViewById(R.id.navigation_view);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        mGridView = findViewById(R.id.functions_gv);
        mGridViewAdapter = new GridViewAdapter(MainActivity.this);
        mGridView.setAdapter(mGridViewAdapter);
    }

    /**
     * 去掉navigation中的滑动条*
     */
    private void hideScrollBar() {
        navigationview.getChildAt(0).setVerticalScrollBarEnabled(false);
    }

    /**
     * 设置ActionBar
     * */
    private void setActionBar() {
        setSupportActionBar(toolbar);
        /*显示Home图标*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 设置Drawerlayout的开关,并且和Home图标联动
     * */
    private void setDrawerToggle() {
        toggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, 0, 0);
        drawerlayout.addDrawerListener(toggle);
        /*同步drawerlayout的状态*/
        toggle.syncState();
    }

    /**
     * 设置监听器
     * */
    private void setListener() {
        //测拉菜单监听
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                }
                drawerlayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case CLASSIFICATION:
                        Log.i("huangyaling", "classification");
                        startActivity(new Intent(MainActivity.this,ClassificationActivity.class));
                        break;
                    case DETECTION:
                        startActivity(new Intent(MainActivity.this,DetectionActivity.class));
                        break;
                    case VOICE:
                        //startActivity(new Intent(MainActivity.this,MainActivity.class));
                        break;
                    case MOREFUNCTIONS:
                        break;
                }
            }
        });
        tab_adv.setOnClickListener(this);
        tab_test.setOnClickListener(this);
        tab_data.setOnClickListener(this);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_test:
                break;
            case R.id.tab_data:
                startActivity(new Intent(MainActivity.this,DataStatisticsActivity.class));
                break;
            case R.id.tab_adv:
                Log.d("huangyaling","tab_adv");
                startActivity(new Intent(MainActivity.this,AdvertiseActivity.class));
                break;
        }
    }
}
