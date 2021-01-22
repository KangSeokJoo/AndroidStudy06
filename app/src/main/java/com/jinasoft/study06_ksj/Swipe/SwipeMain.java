package com.jinasoft.study06_ksj.Swipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ShareActionProvider;
import android.widget.Toolbar;

import com.jinasoft.study06_ksj.R;

public class SwipeMain extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_main);
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //섹션페이퍼 어댑터 뷰페이퍼에 연결
        SectionsPaperAdapter pagerAdapter = new SectionsPaperAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager)findViewById(R.id.paper);
        pager.setAdapter(pagerAdapter); // 뷰페이퍼에 연결
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            default:return super.onOptionsItemSelected(item);
        }
    }

    private class SectionsPaperAdapter extends FragmentPagerAdapter {

        // 임포트로 자동으로 생성 한 3가지 메서드
        public SectionsPaperAdapter(@NonNull FragmentManager fm) { // 매니저 인자를 받는 생성자 만드시 있어야함
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) { // 각 페이지에 표시할 프래그먼트를 지정
            switch (position){
                case 0:
                    return new TopFragment(); // 메인에 표시한 다음
            }
        }

        @Override
        public int getCount() { // 뷰 페이저의 페이지 수를 지정하는 겟 카운트
            return 4;
        }
    }

}