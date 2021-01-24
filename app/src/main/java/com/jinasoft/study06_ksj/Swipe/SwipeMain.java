package com.jinasoft.study06_ksj.Swipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.jinasoft.study06_ksj.R;


public class SwipeMain extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //섹션페이퍼 어댑터 뷰페이퍼에 연결
        SectionsPaperAdapter pagerAdapter = new SectionsPaperAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager)findViewById(R.id.paper);
        pager.setAdapter(pagerAdapter); // 뷰페이퍼에 연결

        //ViewPager를 TabLayout에 연결
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (androidx.appcompat.widget.ShareActionProvider)MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("원하는 피자?");
        return super.onCreateOptionsMenu(menu);
    }

    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create_order :
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return  true;
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
            switch (position) {
                case 0:
                    return new TopFragment();
                case 1:
                    return new PizzaFragment();
                case 2:
                    return new PastaFragment();
                case 3:
                    return new StoresFragment();
            }
            return null;
        }

        @Override
        public int getCount() { // 뷰 페이저의 페이지 수를 지정하는 겟 카운트
            return 4;
        }
    }


    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return getResources().getText(R.string.home_tab);
            case 1:
                return getResources().getText(R.string.pizza_tab);
            case 2:
                return getResources().getText(R.string.pasta_tab);
            case 3:
                return getResources().getText(R.string.store_tab);
        }return null;
    }
}