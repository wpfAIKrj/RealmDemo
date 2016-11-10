package com.jianjian.wpflovekrj.realmdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jianjian.wpflovekrj.realmdemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView add = (TextView) findViewById(R.id.btn_add);
        TextView query = (TextView) findViewById(R.id.btn_query);
        TextView async = (TextView) findViewById(R.id.btn_async);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);

        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);

        add.setOnClickListener(this);
        query.setOnClickListener(this);
        async.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                startActivity(new Intent(MainActivity.this,AddListActivity.class));
                break;
            case R.id.btn_query:
                startActivity(new Intent(MainActivity.this,QueryActivity.class));
                break;
            case R.id.btn_async:
                startActivity(new Intent(MainActivity.this,AsyncActivity.class));
                break;
        }
    }
}
