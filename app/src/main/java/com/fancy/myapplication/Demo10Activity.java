package com.fancy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * @author pengkuanwang
 * @date 2019-07-23
 */
public class Demo10Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        String str1 = new StringBuilder("计算机").append("软件").toString();
        boolean b = str1.intern() == str1;
        Log.d("Demo10Activity", "b:" + b);

        String str2 = new StringBuilder("ja").append("va").toString();
        boolean b1 = str2.intern() == str2;
        Log.d("Demo10Activity", "b1:" + b1);
    }
}
