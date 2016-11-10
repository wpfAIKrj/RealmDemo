package com.jianjian.wpflovekrj.realmdemo.adapter;

import android.content.Context;
import android.view.View;

import com.jianjian.wpflovekrj.realmdemo.R;
import com.jianjian.wpflovekrj.realmdemo.bean.Dog;
import com.jianjian.wpflovekrj.realmdemo.util.BaseAdapter;
import com.jianjian.wpflovekrj.realmdemo.util.BaseViewHolder;

import java.util.List;


/**
 * Created by matou0289 on 2016/10/20.
 */

public class LikeDogAdapter extends BaseAdapter<Dog> {

    public LikeDogAdapter(Context mContext, List<Dog> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
    }

    @Override
    protected void convert(Context mContext, BaseViewHolder holder, Dog dog) {
        holder.setText(R.id.tv_name, dog.getName())
                .setText(R.id.tv_id,dog.getId())
                .setVisible(R.id.iv_like, View.INVISIBLE);

    }
}
