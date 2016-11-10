package com.jianjian.wpflovekrj.realmdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.jianjian.wpflovekrj.realmdemo.R;
import com.jianjian.wpflovekrj.realmdemo.adapter.LikeDogAdapter;
import com.jianjian.wpflovekrj.realmdemo.bean.Dog;
import com.jianjian.wpflovekrj.realmdemo.util.DefaultItemTouchHelpCallback;
import com.jianjian.wpflovekrj.realmdemo.util.RealmHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyl_fight on 2016/11/10.
 */

public class AllDogActivity extends AppCompatActivity {

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    private RealmHelper mRealmHelper;
    private List<Dog> mDogs = new ArrayList<>();
    private LikeDogAdapter mLikeDogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_dog);
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);

        initToolbar();
        initData();
        addListener();
    }

    private void initToolbar() {
        mToolbar.setTitle("查询所有");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    private void initData() {
        mRealmHelper = new RealmHelper(this);
        mDogs = mRealmHelper.queryAllDog();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mLikeDogAdapter = new LikeDogAdapter(this,mDogs,R.layout.item_dog);
        mRecyclerView.setAdapter(mLikeDogAdapter);

        setSwipeDelete();

    }

    private void setSwipeDelete() {
        DefaultItemTouchHelpCallback mCallback = new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                //删除数据库数据
                mRealmHelper.deleteDog(mDogs.get(adapterPosition).getId());
                //滑动删除
                mDogs.remove(adapterPosition);
                mLikeDogAdapter.notifyItemRemoved(adapterPosition);

            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {

                return false;
            }
        });
        mCallback.setDragEnable(false);
        mCallback.setSwipeEnable(true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void addListener() {
        mLikeDogAdapter.setOnItemClickListener((view, position) ->{
            Intent intent = new Intent(AllDogActivity.this,UpdateActivity.class);
            intent.putExtra("uid",mDogs.get(position).getId());
            startActivityForResult(intent,100);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode==100){
            mDogs.clear();
            List<Dog> dogs=mRealmHelper.queryAllDog();
            mDogs.addAll(dogs);
            mLikeDogAdapter.notifyDataSetChanged();
        }
    }
}
