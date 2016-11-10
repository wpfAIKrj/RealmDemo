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

public class QueryActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        Button allQuery =  (Button) findViewById(R.id.btn_query);
        Button conditionQuery = (Button) findViewById(R.id.btn_condition_query);
        Button otherQuery = (Button) findViewById(R.id.btn_other_query);

        allQuery.setOnClickListener(this);
        conditionQuery.setOnClickListener(this);
        otherQuery.setOnClickListener(this);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.setTitle("查询");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                startActivity(new Intent(QueryActivity.this,AllDogActivity.class));
                break;
            case R.id.btn_condition_query:
                startActivity(new Intent(QueryActivity.this,ConditionQueryActivity.class));
                break;
            case R.id.btn_other_query:
                startActivity(new Intent(QueryActivity.this,OtherQueryActivity.class));
                break;
        }
    }
}
