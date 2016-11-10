package com.jianjian.wpflovekrj.realmdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jianjian.wpflovekrj.realmdemo.R;
import com.jianjian.wpflovekrj.realmdemo.adapter.AsyncCatAdapter;
import com.jianjian.wpflovekrj.realmdemo.bean.Cat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyl_fight on 2016/11/10.
 */

public class AddDeleteActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private AsyncCatAdapter mAdapter;
    private List<Cat> mCats = new ArrayList<>();
    private String[] letters=new String[]{"A","B","C","D","E","F","G","H","I","J","K","M","N","U","X","Y","Z"};
    private String[] letters1=new String[]{"a","c","u","p","q","y"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delete);
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);

        initToolbar();
        initData();
        setRecycleView();
    }

    private void initToolbar() {
        mToolbar.setTitle("异步增删");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    private void initData() {
        for (int i=0;i<15;i++){
            Cat cat=new Cat();
            String name=letters[i]+letters1[i%5]+letters1[i%3];
            cat.setName(name);
            cat.setAge(i%4);
            cat.setId("10"+i);
            mCats.add(cat);
        }
    }
    private void setRecycleView() {
        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter=new AsyncCatAdapter(this,mCats,R.layout.item_dog);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cancleTask();
    }
}
