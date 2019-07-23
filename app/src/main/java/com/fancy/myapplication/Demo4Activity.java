package com.fancy.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author pengkuanwang
 * @date 2019-07-17
 */
public class Demo4Activity extends Activity {
    public static final int REQUEST_PERMISSION_CODE = 1100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasPermission = hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
                Toast.makeText(Demo4Activity.this, "hasPermission:" + hasPermission, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.createFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = "hehe.txt";
                File file = new File(getVideoCache(), txt);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write("你大爷啊".getBytes());
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        ActivityCompat.requestPermissions(Demo4Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (permissions.length >= 1) {
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 获取视频的缓存目录
     *
     * @return
     */
    public String getVideoCache() {
        return Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" + getPackageName(this) + "/pengkuanwang";
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static synchronized String getPackageName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasPermission(String permission) {
        int perm = checkCallingOrSelfPermission(permission);
        return perm == PackageManager.PERMISSION_GRANTED;
    }
}
