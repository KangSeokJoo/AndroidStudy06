package com.jinasoft.study06_ksj.Service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.jinasoft.study06_ksj.R;

public class ServiceIntentMain extends IntentService {

    public static final String EXTRA_MESSAGE = "message";
    public static final int NOTIFICATION_ID = 5453;

    public ServiceIntentMain() {
        super("ServiceIntentMain");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //서비스가 인텐트를 받았을때 실행
        synchronized (this){
            try {
                wait(1000); //10초
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        showText(text);
    }

    private void showText(final String text){
        //알림 빌더 생성
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(getString(R.string.question))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[]{0,1000})
                .setAutoCancel(true);

        //액션생성
        Intent intent = new Intent(this, ServiceMain.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent); // 알림에 추가

        //알림보내기
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build()); // 알림 관리자를 이용해 알림을 표시

    }
}
