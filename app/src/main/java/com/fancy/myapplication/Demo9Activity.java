package com.fancy.myapplication;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fancy.library.InjectView;
import com.fancy.library.onClick;
import com.fancy.library.setContentView;

/**
 * @author pengkuanwang
 * @date 2019-07-22
 */
@setContentView(R.layout.demo9)
public class Demo9Activity extends BaseActivity {
    @InjectView(R.id.textView)
    private TextView tv;
    @InjectView(R.id.button)
    private TextView btn;
    String str="bacd";

    @Override
    public void initData() {
        tv.setText("成功啦!1111");
    }

    @onClick({R.id.button})
    public void show() {
        Toast.makeText(this, btn.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @onClick({R.id.textView})
    public void show(View view) {
        Toast.makeText(this, tv.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
