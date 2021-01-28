package com.jinasoft.study06_ksj.ListView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.jinasoft.study06_ksj.R;

public class DrinkCategoryActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

//        ArrayAdapter<Drink_ModelClass> listAdapter = new ArrayAdapter<>(
//                this, android.R.layout.simple_list_item_1,
//                Drink_ModelClass.drinks);
        ListView listView = (ListView)findViewById(R.id.list_drinks);
        SQLiteOpenHelper sqLiteOpenHelper = new StarbuzzDatabaseHelper(this);
        try {
            db = sqLiteOpenHelper.getReadableDatabase();
            cursor = db.query("DRINK",
                    new String[]{"_id", "NAME"},
                    null,null, null, null, null);
            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);
            listView.setAdapter(simpleCursorAdapter);
        }catch (SQLException e){
            Toast.makeText(this, "데이타베이스 없음", Toast.LENGTH_SHORT).show();
        }


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}