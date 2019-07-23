package com.fancy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.fancy.myapplication.callback.JsonUtilCallBack;
import com.fancy.myapplication.event.AdBean;

import java.util.List;

/**
 * @author pengkuanwang
 * @date 2019-07-16
 */
public class Demo2Activity extends Activity {
    String json = "[ {\n" +
            "\t\"adContent\": \"uploadimage/IMG20190626173341170.png\",\n" +
            "\t\"adDesc\": \"首页顶部banner\",\n" +
            "\t\"adName\": \"首页顶部banner\",\n" +
            "\t\"adUrl\": \"http://m.baidu.com/\",\n" +
            "\t\"adWord\": \"首页顶部banner\",\n" +
            "\t\"id\": \"P000004017\",\n" +
            "\t\"positionName\": \"Android顶部广告位\",\n" +
            "\t\"sort\": 0\n" +
            "}, {\n" +
            "\t\"adContent\": \"uploadimage/IMG20190626173341170.png\",\n" +
            "\t\"adDesc\": \"首页顶部banner\",\n" +
            "\t\"adName\": \"首页顶部banner\",\n" +
            "\t\"adUrl\": \"http://m.baidu.com/\",\n" +
            "\t\"adWord\": \"首页顶部banner\",\n" +
            "\t\"id\": \"P000004019\",\n" +
            "\t\"positionName\": \"Android顶部广告位\",\n" +
            "\t\"sort\": 0\n" +
            "}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo2);
        JsonUtilCallBack<List<AdBean>> listJsonUtilCallBack = new JsonUtilCallBack<List<AdBean>>() {
            @Override
            public void onSuccess(List<AdBean> adBeans) {
                Toast.makeText(Demo2Activity.this, "adBeans.size():" + adBeans.size(), Toast.LENGTH_SHORT).show();
            }
        };
        listJsonUtilCallBack.onSuccess(json);
    }
}
