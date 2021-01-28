package com.jinasoft.study06_ksj.Bind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;

import com.jinasoft.study06_ksj.R;

import org.w3c.dom.Text;

import java.util.Locale;

public class BindMain extends AppCompatActivity {

    private OdometerService odometerService;
    private boolean bound = false;

    private final int PERMISSION_REQUEST_CODE = 698;
    private final int NOTIFICATION_ID = 423;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) { // 서비스를 식별하는 이름, 서비스에서 정의 하는 IBinder
            OdometerService.OdometerBinder odometerBinder = (OdometerService.OdometerBinder)iBinder;
            odometerService = odometerBinder.getOdometer();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //서비스 연결이 끊어지면 실행할 코드
            bound = false; // 메인에서 연결이 끊어지면 bound 펄스
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_main);
        displayDistance();
    }

    private void displayDistance() {
        final TextView distanceView = (TextView)findViewById(R.id.distance);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                double distance = 0.0;
                if ( bound && odometerService != null){
                    distance = odometerService.getDistance();
                }
                String distanceStr = String.format(Locale.getDefault(), "%1$, .2f miles", distance);

                distanceView.setText(distanceStr);
               handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, OdometerService.PERMISSION_STRING) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{OdometerService.PERMISSION_STRING}, PERMISSION_REQUEST_CODE); // 런타임 권한을 요청
        }else {
            Intent intent = new Intent(this, OdometerService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    } // 액티비티가 시작 되면 서비스와 연결

    @Override
    protected void onStop() {
        super.onStop();
        if (bound){
            unbindService(connection);
            bound = false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_REQUEST_CODE : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(this, OdometerService.class);
                    bindService(intent, connection, Context.BIND_AUTO_CREATE);
                }else {
                    // 거절시 행동
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                            .setSmallIcon(android.R.drawable.ic_menu_compass)
                            .setContentTitle(getResources().getString(R.string.app_name1))
                            .setContentText(getResources().getString(R.string.permission_denied))
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setVibrate(new long[] {1000, 1000})
                            .setAutoCancel(true);

                    //액션 생성
                    Intent intent = new Intent(this, BindMain.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(
                            this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                    builder.setContentIntent(pendingIntent);

                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID, builder.build());
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}