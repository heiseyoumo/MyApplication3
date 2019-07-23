package com.fancy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fancy.myapplication.event.AdBean;
import com.fancy.myapplication.event.OrderEvent;


/**
 * @author pengkuanwang
 * @date 2019-07-03
 */
public class DemoActivity extends BaseActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        textView = findViewById(R.id.textView);
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DemoActivity.this, Demo1Activity.class));
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * ThreadMode MainThread
     * EventType  orderEvent
     * Method     onEventMainThread
     *
     * @param orderEvent
     */

    public void onEventMainThread(OrderEvent orderEvent) {
        int status = orderEvent.getStatus();
        textView.setText("状态值:" + status);
    }

    public void onEventBackgroundThread(AdBean adBean) {
        String adUrl = adBean.getAdUrl();
        textView.setText("状态值:" + adUrl);
    }

    public void onEventAsync(OrderEvent orderEvent) {
        int status = orderEvent.getStatus();
        textView.setText("状态值:" + status);
    }
}
