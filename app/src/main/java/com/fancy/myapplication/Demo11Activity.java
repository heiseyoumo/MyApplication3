package com.fancy.myapplication;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fancy.library.InjectView;
import com.fancy.library.setContentView;

/**
 * @author pengkuanwang
 * @date 2019-07-25
 */

@setContentView(R.layout.demo11)
public class Demo11Activity extends BaseActivity {

    @InjectView(R.id.textView)
    TextView tv;

    @Override
    public void initData() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Demo11Activity.this, "HAHA", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
