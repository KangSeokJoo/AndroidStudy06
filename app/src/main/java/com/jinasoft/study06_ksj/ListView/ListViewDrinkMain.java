package com.jinasoft.study06_ksj.ListView;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinasoft.study06_ksj.R;

public class ListViewDrinkMain extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_drink_main);
        setupOptionsListView();
        setupFavoritesListView();

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

    private void setupFavoritesListView() {
        //커서로 리스트뷰 채우기
        ListView listView2 = (ListView) findViewById(R.id.list_drinks);
        try {
            SQLiteOpenHelper sqLiteOpenHelper = new StarbuzzDatabaseHelper(this);
            db= sqLiteOpenHelper.getReadableDatabase();
            cursor = db.query("DRINK",
                    new String[] { "_id", "NAME"}, "FAVORITE = 1",
                    null, null, null, null);
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(ListViewDrinkMain.this, android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[] {android.R.id.text1}, 0);
            listView2.setAdapter(cursorAdapter);
        }catch (SQLException e){
            Toast.makeText(this, "데이타베이스 없음", Toast.LENGTH_SHORT).show();
        }
        //음료를 클릭하면 drinkAct로 이동
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListViewDrinkMain.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int)l);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Cursor cursor2 = db.query("DRINK",
                new String[] {"_id", "NAME"},
                "FAVORITE = 1", null,null,null,null);
        ListView listView3 = (ListView) findViewById(R.id.list_drinks);
        CursorAdapter adapter = (CursorAdapter) listView3.getAdapter();
        adapter.changeCursor(cursor2);
        cursor = cursor2;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    private void setupOptionsListView() {
    }
}
