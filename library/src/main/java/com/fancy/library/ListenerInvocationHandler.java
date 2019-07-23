package com.fancy.library;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author pengkuanwang
 * @date 2019-07-23
 */
public class ListenerInvocationHandler implements InvocationHandler {

    Object target;
    HashMap<String, Method> hashMap = new HashMap<>();

    public ListenerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (target != null) {
            String name = method.getName();
            method = hashMap.get(name);
            if (method != null) {
                if (method.getGenericParameterTypes().length == 0) {
                    return method.invoke(target);
                }
                return method.invoke(target, args);
            }
        }
        return null;
    }

    public void putMethod(String methodName, Method method) {
        hashMap.put(methodName, method);
    }
}
