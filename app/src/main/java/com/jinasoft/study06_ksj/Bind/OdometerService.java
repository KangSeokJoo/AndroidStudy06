package com.jinasoft.study06_ksj.Bind;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class OdometerService extends Service {

    private final IBinder binder = new OdometerBinder();
//    private final Random random = new Random();
    private LocationListener listener;

    private LocationManager locationManager;
    public static final String PERMISSION_STRING = Manifest.permission.ACCESS_FINE_LOCATION;

    private  static double mDouble;
    private  static Location mLoocation = null;

    @Override
    public void onCreate() {
        super.onCreate();
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //이동한 거리를 추적하는ㄴ 코드
                if (mLoocation == null){
                    mLoocation = location;
                }mDouble += location.distanceTo(mLoocation);
                mLoocation = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }
        };
        locationManager = (LocationManager)  getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, PERMISSION_STRING) == PackageManager.PERMISSION_GRANTED) {
            String provider = locationManager.getBestProvider(new Criteria(), true);
            if (provider != null){
                locationManager.requestLocationUpdates(provider, 1000, 1, listener);
            }
        }
    }

    public class OdometerBinder extends Binder {
        OdometerService getOdometer(){
            return OdometerService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null && listener != null){
            if (ContextCompat.checkSelfPermission(this, PERMISSION_STRING) == PackageManager.PERMISSION_GRANTED){
                locationManager.removeUpdates(listener);
            }
            locationManager = null;
            listener = null;
        }
    }

    public double getDistance() {
        return this.mDouble / 1609.344;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //서비스를 연결하는 코드
        return binder;
    }


}