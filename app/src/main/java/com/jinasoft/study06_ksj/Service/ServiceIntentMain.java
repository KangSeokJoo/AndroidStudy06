package com.jinasoft.study06_ksj.Service;

import android.app.IntentService;
import android.app.NotificationChannel;
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
                wait(1000); //1초
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        showText(text);
    }

    private void showText(final String text){
        //알림 빌더 생성
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this , "default1");

        //액션생성
        Intent intent = new Intent(this, ServiceMain.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent); // 알림에 추가

        //알림보내기
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build()); // 알림 관리자를 이용해 알림을 표시

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder.setSmallIcon(R.drawable.ic_launcher_foreground); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남


            String channelName ="매일 알람 채널1";
            String description = "매일 정해진 시간에 알람합니다1.";
            int importance = NotificationManager.IMPORTANCE_HIGH; //소리와 알림메시지를 같이 보여줌

            NotificationChannel channel = new NotificationChannel("default1", channelName, importance);
            channel.setDescription(description);

//            if (notificationManager != null) {
            // 노티피케이션 채널을 시스템에 등록
            notificationManager.createNotificationChannel(channel);
        }else {
            builder.setSmallIcon(R.mipmap.ic_launcher); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남
            builder .setSmallIcon(android.R.drawable.sym_def_app_icon)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(getString(R.string.question))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setVibrate(new long[]{0,1000})
                    .setAutoCancel(true);

            if(notificationManager != null){
                notificationManager.notify(1, builder.build());
            }
        }


    }
}
