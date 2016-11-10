package com.jianjian.wpflovekrj.realmdemo.adapter;

import android.content.Context;
import android.view.View;

import com.jianjian.wpflovekrj.realmdemo.R;
import com.jianjian.wpflovekrj.realmdemo.bean.Cat;
import com.jianjian.wpflovekrj.realmdemo.util.BaseAdapter;
import com.jianjian.wpflovekrj.realmdemo.util.BaseViewHolder;

import java.util.List;

/**
 * Created by wyl_fight on 2016/11/9.
 */

public class LikeCatAdapter extends BaseAdapter<Cat> {

    public LikeCatAdapter(Context mContext, List<Cat> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
    }

    @Override
    protected void convert(Context mContext, BaseViewHolder holder, Cat cat) {
        holder.setText(R.id.tv_name, cat.getName())
                .setText(R.id.tv_id,cat.getId())
                .setVisible(R.id.iv_like, View.INVISIBLE);
    }
}
