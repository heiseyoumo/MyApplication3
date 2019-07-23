package com.fancy.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        imageView = findViewById(R.id.imageView);
        Context context = imageView.getContext();
        Context baseContext = getBaseContext();
        Application application = getApplication();
        Context applicationContext = getApplicationContext();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        float dpToPx = DM.dpToPx(80);
        float density = Resources.getSystem().getDisplayMetrics().density;
        Toast.makeText(applicationContext, "density:" + density, Toast.LENGTH_SHORT).show();
        RequestManager requestManager = Glide.with(this);
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)    //加载成功之前占位图
                .error(R.mipmap.ic_launcher)    //加载错误之后的错误图
                .override(100, 100)    //指定图片的尺寸
                .fitCenter()   //指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于 ImageView的宽或者是高。是指其中一个满足即可不会一定铺满 imageview）
                .centerCrop()//指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的宽高都 大于等于ImageView的宽度，然后截取中间的显示。）
                .skipMemoryCache(true)    //不使用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL)    //缓存所有版本的图像
                .diskCacheStrategy(DiskCacheStrategy.NONE)    //不使用硬盘本地缓存
                .diskCacheStrategy(DiskCacheStrategy.DATA)    //只缓存原来分辨率的图片
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);//只缓存最终的图片
        RequestBuilder<Drawable> load = requestManager.load("uri").apply(options).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        });
        ViewTarget<ImageView, Drawable> into = load.into(imageView);
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity", "onResume");
        super.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            int width = imageView.getWidth();
            int height = imageView.getHeight();
            Log.d("MainActivity", "width=" + width + ",height=" + height);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
