package com.jianjian.wpflovekrj.realmdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jianjian.wpflovekrj.realmdemo.R;
import com.jianjian.wpflovekrj.realmdemo.util.RealmHelper;

/**
 * Created by wyl_fight on 2016/11/10.
 */

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private EditText etName;
    private Button btnUpdate;
    private RealmHelper mRealmHelper;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        etName =  (EditText) findViewById(R.id.et_name);
        btnUpdate = (Button) findViewById(R.id.btn_update);

        btnUpdate.setOnClickListener(this);
        initToolbar();
        initData();
    }

    private void  initToolbar() {
        mToolbar.setTitle("同步改");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    private void initData() {
        mRealmHelper = new RealmHelper(this);
        uid = getIntent().getStringExtra("uid");
    }
    @Override
    public void onClick(View v) {
        String name = etName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(UpdateActivity.this,"请输入名称",Toast.LENGTH_SHORT).show();
            return;
        }
        mRealmHelper.updateDog(uid,name);
        setResult(RESULT_OK);
        finish();
    }
}
