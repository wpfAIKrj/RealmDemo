package com.jianjian.wpflovekrj.realmdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import com.jianjian.wpflovekrj.realmdemo.R;
import com.jianjian.wpflovekrj.realmdemo.adapter.LikeCatAdapter;
import com.jianjian.wpflovekrj.realmdemo.bean.Cat;
import com.jianjian.wpflovekrj.realmdemo.util.DefaultItemTouchHelpCallback;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by wyl_fight on 2016/11/10.
 */

public class AsyncQueryActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private Realm mRealm;
    private List<Cat> mCats = new ArrayList<>();
    private LikeCatAdapter mAdapter;
    private RealmResults<Cat> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_query);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRealm = Realm.getDefaultInstance();

        initData();
        initToolbar();
        initRecyclerView();
    }

    private void initData() {
        results = mRealm.where(Cat.class).findAllAsync();
        results.addChangeListener(element -> {
                Log.i("TAG","111111111");
                element= element.sort("id");
                List<Cat> datas=mRealm.copyFromRealm(element);
                mCats.clear();
                mCats.addAll(datas);
                mAdapter.notifyDataSetChanged();
        });
    }

    private void initToolbar() {
        mToolbar.setTitle("异步改查");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    private void initRecyclerView() {
        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter=new LikeCatAdapter(this,mCats,R.layout.item_dog);
        mRecyclerView.setAdapter(mAdapter);
        setSwipeDelete();
    }

    private void setSwipeDelete() {
        DefaultItemTouchHelpCallback mCallback = new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                deleteCat(mCats.get(adapterPosition).getId());
                mCats.remove(adapterPosition);
                mAdapter.notifyItemRemoved(adapterPosition);
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

    private void deleteCat(String uid) {
        mRealm.executeTransactionAsync(realm -> {
                Cat cat = realm.where(Cat.class).equalTo("id",uid).findFirst();
                if(cat != null) {
                    cat.deleteFromRealm();
                }
            },() -> Toast.makeText(AsyncQueryActivity.this,"删除成功",Toast.LENGTH_SHORT).show()
            ,onError -> Toast.makeText(AsyncQueryActivity.this,"删除失败",Toast.LENGTH_SHORT).show());
    }
}
