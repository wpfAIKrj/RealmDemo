package com.jianjian.wpflovekrj.realmdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.jianjian.wpflovekrj.realmdemo.R;

/**
 * Created by wyl_fight on 2016/11/10.
 */

public class AsyncActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        Button addDelete = (Button) findViewById(R.id.btn_add_delete);
        Button updateQuery = (Button) findViewById(R.id.btn_update_query);

        addDelete.setOnClickListener(this);
        updateQuery.setOnClickListener(this);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.setTitle("异步查询");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_delete:
                startActivity(new Intent(AsyncActivity.this,AddDeleteActivity.class));
                break;
            case R.id.btn_update_query:
                startActivity(new Intent(AsyncActivity.this,AsyncQueryActivity.class));
                break;
        }
    }
}
