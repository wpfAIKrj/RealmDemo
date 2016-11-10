package com.jianjian.wpflovekrj.realmdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jianjian.wpflovekrj.realmdemo.R;
import com.jianjian.wpflovekrj.realmdemo.adapter.LikeDogAdapter;
import com.jianjian.wpflovekrj.realmdemo.bean.Dog;
import com.jianjian.wpflovekrj.realmdemo.util.RealmHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyl_fight on 2016/11/10.
 */

public class ConditionQueryActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private EditText etId,etAge;
    private Button btnId,btnAge;
    private RecyclerView mRecyclerView;
    private RealmHelper mRealmHelper;
    private LikeDogAdapter mAdapter;
    private List<Dog> mDogs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_query);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        etId =  (EditText) findViewById(R.id.et_id);
        etAge = (EditText) findViewById(R.id.et_age);
        btnId = (Button) findViewById(R.id.btn_query_id);
        btnAge = (Button) findViewById(R.id.btn_query_age);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        btnId.setOnClickListener(this);
        btnAge.setOnClickListener(this);
        initToolbar();
        initRecycleView();
    }

    private void  initToolbar() {
        mToolbar.setTitle("条件查询");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    private void initRecycleView() {

        mRealmHelper = new RealmHelper(this);
        mAdapter = new LikeDogAdapter(this, mDogs, R.layout.item_dog);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query_id:
                queryById();
                break;
            case R.id.btn_query_age:
                queryByAge();
                break;
        }
    }

    private void queryById() {
        String id = etId.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this,"请输入id",Toast.LENGTH_SHORT).show();
            return;
        }

        mDogs.clear();
        Dog dog = mRealmHelper.queryDogById(id);
        if (dog != null) {
            mDogs.add(dog);
        } else {
            Toast.makeText(ConditionQueryActivity.this, "查询结果为空", Toast.LENGTH_SHORT).show();
        }
        mAdapter.notifyDataSetChanged();
    }

    private void queryByAge() {
        String age = etAge.getText().toString().trim();
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this,"请输入年龄",Toast.LENGTH_SHORT).show();
            return;
        }
        mDogs.clear();
        List<Dog> dogs = mRealmHelper.queryDobByAge(Integer.parseInt(age));
        if (dogs!=null && dogs.size() != 0) {
            mDogs.addAll(dogs);
        }else {
            Toast.makeText(ConditionQueryActivity.this, "查询结果为空", Toast.LENGTH_SHORT).show();
        }
        mAdapter.notifyDataSetChanged();
    }
}
