package com.fancy.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;

/**
 * @author pengkuanwang
 * @date 2019-07-16
 */
public class Demo3Activity extends Activity {
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo1);
        final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String id = "channel_1";
        String description = "123";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel;
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(id, "123", importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
            Intent intent = new Intent(Demo3Activity.this, Demo1Activity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 200, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            notification = new Notification.Builder(this, id)
                    .setContentTitle("Title")
                    .setSmallIcon(R.mipmap.ic_pause_disable)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_play_pressed))
                    .setContentText("ContentText")
                    .setAutoCancel(true)
                    .setSubText("subText")
                    .setShowWhen(true)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    .build();
        } else {
            Intent intent = new Intent(Demo3Activity.this, Demo1Activity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 200, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Demo3Activity.this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("5 new message")
                    .setContentText("twain@android.com")
                    .setTicker("New message")//第一次提示消息的时候显示在通知栏上
                    .setNumber(12)
                    .setContentIntent(pendingIntent)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setAutoCancel(true);//自己维护通知的消失
            notification = mBuilder.build();
        }
        TextView textView = findViewById(R.id.sendNotification_Button);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotificationManager.notify(1, notification);
            }
        });
    }
}
