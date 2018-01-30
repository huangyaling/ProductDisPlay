package com.cambricon.productdisplay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.cambricon.productdisplay.R;

/**
 * Created by huangyaling on 18-1-30.
 */

public class ClassificationActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classification_layout);
        initView();
        setToolbar();
    }
    public void initView(){
        toolbar=findViewById(R.id.classification_toolbar);
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
        toolbar.setTitle(R.string.gv_text_item1);
        setSupportActionBar(toolbar);
        /*显示Home图标*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
