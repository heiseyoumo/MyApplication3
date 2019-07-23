package com.fancy.myapplication;

/**
 * @author pengkuanwang
 * @date 2019-06-28
 */
public class Singleton {
    private Singleton() {
    }

    private volatile static Singleton singleton;

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
