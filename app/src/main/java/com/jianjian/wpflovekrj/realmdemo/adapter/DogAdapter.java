package com.jianjian.wpflovekrj.realmdemo.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.jianjian.wpflovekrj.realmdemo.R;
import com.jianjian.wpflovekrj.realmdemo.bean.Dog;
import com.jianjian.wpflovekrj.realmdemo.util.BaseAdapter;
import com.jianjian.wpflovekrj.realmdemo.util.BaseViewHolder;
import com.jianjian.wpflovekrj.realmdemo.util.RealmHelper;

import java.util.List;

/**
 * Created by wyl_fight on 2016/11/9.
 */

public class DogAdapter extends BaseAdapter<Dog> {

    private RealmHelper mRealmHleper;

    public DogAdapter(Context mContext, List<Dog> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
        mRealmHleper = new RealmHelper(mContext);
    }

    @Override
    protected void convert(Context mContext, BaseViewHolder holder, final Dog dog) {
        holder.setText(R.id.tv_name, dog.getName())
                .setText(R.id.tv_id, dog.getId());

        final ImageView iv = holder.getView(R.id.iv_like);

        if (mRealmHleper.isDogExist(dog.getId())) {
            iv.setSelected(true);
        } else {
            iv.setSelected(false);
        }


        iv.setOnClickListener(v -> {
                if (iv.isSelected()) {
                    iv.setSelected(false);
                    mRealmHleper.deleteDog(dog.getId());

                } else {
                    iv.setSelected(true);
                    mRealmHleper.addDog(dog);
                }
            }
        );


    }

}
