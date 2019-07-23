package com.fancy.myapplication.callback;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author pengkuanwang
 * @date 2019-07-16
 */
public abstract class JsonUtilCallBack<T>{
    public void onSuccess(String response) {
        Gson gson = new Gson();
        Type type = this.getClass().getGenericSuperclass();
        Type[] arguments = ((ParameterizedType) type).getActualTypeArguments();
        T result = gson.fromJson(response, arguments[0]);
        onSuccess(result);
    }

    public abstract void onSuccess(T t);
}
