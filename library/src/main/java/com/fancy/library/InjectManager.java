package com.fancy.library;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author pengkuanwang
 * @date 2019-07-23
 */
public class InjectManager {
    public static void inject(Activity activity) {
        /**
         * 注册布局文件
         */
        injectLayoutId(activity);
        /**
         * 注册资源文件
         */
        injectResId(activity);

        /**
         * 点击事件的注册
         */
        injectEvent(activity);
    }

    /**
     * 点击事件的注册
     */
    public static void injectEvent(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType != null) {
                    EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                    if (eventBase != null) {
                        String setOnListener = eventBase.setOnListener();
                        Class<?> listenerType = eventBase.listenerType();
                        String callBackListener = eventBase.callBackListener();
                        ListenerInvocationHandler handler = new ListenerInvocationHandler(activity);
                        handler.putMethod(callBackListener, method);
                        Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, handler);
                        if (listener instanceof View.OnClickListener) {
                            Toast.makeText(activity, "哈哈哈哈哈哈哈", Toast.LENGTH_SHORT).show();
                        }
                        try {
                            Method valueMethod = annotationType.getDeclaredMethod("value");
                            int[] value = (int[]) valueMethod.invoke(annotation);
                            for (int viewId : value) {
                                View view = activity.findViewById(viewId);
                                Class<? extends View> viewClass = view.getClass();
                                Method setter = viewClass.getMethod(setOnListener, listenerType);
                                setter.invoke(view, listener);
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 注册资源文件
     */
    private static void injectResId(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(InjectView.class)) {
                return;
            }
            InjectView annotation = field.getAnnotation(InjectView.class);
            if (annotation != null) {
                int value = annotation.value();
                try {
                    Method method = aClass.getMethod("findViewById", int.class);
                    Object view = method.invoke(activity, value);
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectLayoutId(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        if (!aClass.isAnnotationPresent(setContentView.class)) {
            return;
        }
        setContentView annotation = aClass.getAnnotation(setContentView.class);
        /**
         * 第一种low逼写法
         * activity.setContentView(annotation.value());
         */
        try {
            Method method = aClass.getMethod("setContentView", int.class);
            method.invoke(activity, annotation.value());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
