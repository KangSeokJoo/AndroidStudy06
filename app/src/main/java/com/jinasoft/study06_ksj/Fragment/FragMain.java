package com.jinasoft.study06_ksj.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jinasoft.study06_ksj.R;

public class FragMain extends AppCompatActivity implements WorkoutListFragment.Listener{ // 리스너를 인터페이스 구현

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_main);
    }

//    public void onShowDetails(View view) {
//        Intent intent = new Intent(this, DetailActivity.class);
//        startActivity(intent);
//    }

    @Override
    public void itemClicked(long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int)id); // 운동 id를 디테일액티비티로 전달
        startActivity(intent);
    }
}