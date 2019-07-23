package com.fancy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

/**
 * @author pengkuanwang
 * @date 2019-07-22
 */
public class Demo7Activity extends Activity {
    public String TAG = "Demo7Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo1);
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
        }).flatMap(integer -> {
            final ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                strings.add("我是事件" + integer + "拆分后的子事件" + i);
            }
            return Observable.fromIterable(strings);

        }).subscribe(s -> {
            Log.d(TAG, s);
        });

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer integer) throws Exception {
                return null;
            }
        });
    }
}
