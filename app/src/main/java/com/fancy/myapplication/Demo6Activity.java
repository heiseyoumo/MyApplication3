package com.fancy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * @author pengkuanwang
 * @date 2019-07-22
 */
public class Demo6Activity extends Activity {
    int[] arr = {167, 34, 78, 23};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo5);
        for (int b : arr) {
            Log.d("Demo6Activity", String.valueOf(b));
        }
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0, length = arr.length; i < length; i++) {
                    for (int j = 0; j < length - i - 1; j++) {
                        if (arr[j] > arr[j + 1]) {
                            int temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }
                }
                for (int b : arr) {
                    Log.d("Demo6Activity", String.valueOf(b));
                }
            }
        });
    }
}
