package com.fancy.library;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pengkuanwang
 * @date 2019-07-23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(callBackListener = "onClick", setOnListener = "setOnClickListener", listenerType = View.OnClickListener.class)
public @interface onClick {
    int[] value();
}
