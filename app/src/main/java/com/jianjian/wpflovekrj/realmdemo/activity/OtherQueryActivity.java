package com.jianjian.wpflovekrj.realmdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jianjian.wpflovekrj.realmdemo.R;
import com.jianjian.wpflovekrj.realmdemo.bean.Dog;

import io.realm.Realm;

/**
 * Created by wyl_fight on 2016/11/10.
 */

public class OtherQueryActivity extends AppCompatActivity {

    private TextView averageAge,maxAge,sumAge;
    private Toolbar mToolbar;
    private Realm mRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_query);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        averageAge = (TextView) findViewById(R.id.tv_average_age);
        maxAge = (TextView) findViewById(R.id.tv_max_age);
        sumAge = (TextView) findViewById(R.id.tv_sum_age);

        mRealm = Realm.getDefaultInstance();

        initToolbar();
        initAverageData();
        initMaxData();
        initSumAge();
    }

    private void initToolbar() {
        mToolbar.setTitle("其他查询");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    private void initAverageData() {
        double avgAge=  mRealm.where(Dog.class).findAll().average("age");
        averageAge.setText(avgAge+"岁");
    }

    private void initMaxData() {
        int maxAgeNum=  mRealm.where(Dog.class).findAll().max("age").intValue();
        maxAge.setText(maxAgeNum+"岁");
    }

    private void initSumAge() {
        Number sum=  mRealm.where(Dog.class).findAll().sum("age");
        int sumAgeNum=sum.intValue();
        sumAge.setText(sumAgeNum+"岁");
    }
}
