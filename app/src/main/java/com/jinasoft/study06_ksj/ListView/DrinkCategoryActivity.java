package com.jinasoft.study06_ksj.ListView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jinasoft.study06_ksj.R;

public class DrinkCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

        ArrayAdapter<Drink_ModelClass> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                Drink_ModelClass.drinks);
        ListView listView = (ListView)findViewById(R.id.list_drinks);
        listView.setAdapter(listAdapter);

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(DrinkCategoryActivity.this , DrinkActivity.class);
                        intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int) l);
                        startActivity(intent);
                    }
                };
        listView.setOnItemClickListener(itemClickListener);
    }
}