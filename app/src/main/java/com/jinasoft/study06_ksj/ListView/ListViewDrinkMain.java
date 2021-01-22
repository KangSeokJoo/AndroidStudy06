package com.jinasoft.study06_ksj.ListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinasoft.study06_ksj.R;

public class ListViewDrinkMain extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_drink_main);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    Intent in = new Intent(ListViewDrinkMain.this, DrinkCategoryActivity.class);
                    startActivity(in);
                }
            }
        };
        ListView listView = (ListView) findViewById(R.id.ListView_OptionLV);
        listView.setOnItemClickListener(itemClickListener);
    }
}
