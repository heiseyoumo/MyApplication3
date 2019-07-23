package com.fancy.myapplication;

import android.app.Activity;
import android.os.Bundle;

import com.fancy.library.InjectManager;

/**
 * @author pengkuanwang
 * @date 2019-07-13
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectManager.inject(this);
        initData();
    }

    public abstract void initData();
}
