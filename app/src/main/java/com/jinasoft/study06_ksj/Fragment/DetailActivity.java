package com.jinasoft.study06_ksj.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jinasoft.study06_ksj.R;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_WORKOUT_ID = "id"; // 메인에서 디테일액티비티 전달 할 때 값을 하드코딩 하지 않도록 상수를 사용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WorkoutDetailFragment frag = (WorkoutDetailFragment)getSupportFragmentManager().findFragmentById(R.id.detail_frag);
//        frag.setWorkout(1); 하드코딩 부문
        int workoutId = (int)getIntent().getExtras().get(EXTRA_WORKOUT_ID); // 인텐트에서 id를 얻고 setWORKOut 메서드로 프래그먼트에 전달
        frag.setWorkout(workoutId);
    }
}