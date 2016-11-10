package com.jianjian.wpflovekrj.realmdemo.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.jianjian.wpflovekrj.realmdemo.R;
import com.jianjian.wpflovekrj.realmdemo.bean.Cat;
import com.jianjian.wpflovekrj.realmdemo.util.BaseAdapter;
import com.jianjian.wpflovekrj.realmdemo.util.BaseViewHolder;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * Created by wyl_fight on 2016/11/10.
 */

public class AsyncCatAdapter extends BaseAdapter<Cat> {

    private Context mContext;
    private Realm mRealm;
    private RealmAsyncTask addAsyncTask,deleteAsyncTask;

    public AsyncCatAdapter(Context context, List<Cat> mDatas, int mLayoutId) {
        super(context, mDatas, mLayoutId);
        mContext=context;
        mRealm= Realm.getDefaultInstance();
    }

    @Override
    protected void convert(Context mContext, BaseViewHolder holder, Cat cat) {
        holder.setText(R.id.tv_id,cat.getId())
                .setText(R.id.tv_name,cat.getName());
        final ImageView imageView=holder.getView(R.id.iv_like);
        if (isLiked(cat.getId())){
            imageView.setSelected(true);
        }else {
            imageView.setSelected(false);
        }

        holder.setOnClickListener(R.id.iv_like, v -> {
                if (imageView.isSelected()){
                    deleteCat(cat.getId(),imageView);
                }else {
                    addCat(cat,imageView);
                }
        });
    }

    private boolean isLiked(String uid) {
        Cat cat = mRealm.where(Cat.class).equalTo("id",uid).findFirst();
        if(cat == null) {
            return false;
        } else {
            return true;
        }
    }

    private void addCat(Cat cat,ImageView imageView) {
        addAsyncTask = mRealm.executeTransactionAsync(realm -> realm.copyToRealm(cat)
                , () -> {
                    Toast.makeText(mContext,"收藏成功",Toast.LENGTH_SHORT).show();
                    imageView.setSelected(true);
                }
                , error -> Toast.makeText(mContext,"收藏失败",Toast.LENGTH_SHORT).show());
    }

    private void deleteCat(String catid,ImageView imageView) {
        deleteAsyncTask = mRealm.executeTransactionAsync(realm -> {
                    Cat cat = mRealm.where(Cat.class).equalTo("id",catid).findFirst();
                    cat.deleteFromRealm();
                }
                , () -> {
                    Toast.makeText(mContext,"取消收藏成功",Toast.LENGTH_SHORT).show();
                    imageView.setSelected(false);
                }
                , error -> Toast.makeText(mContext,"收藏失败",Toast.LENGTH_SHORT).show());
    }

    public void cancleTask() {
        if (addAsyncTask!=null&&!addAsyncTask.isCancelled()){
            addAsyncTask.cancel();
        }
        if (deleteAsyncTask!=null&&!deleteAsyncTask.isCancelled()){
            deleteAsyncTask.cancel();
        }
    }
}
