package com.jinasoft.study06_ksj.ListView;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.jinasoft.study06_ksj.R;

class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private  static final String DB_NAME = "starbuzz"; //데이터베이스 이름
    private  static final int DB_VERSION = 2;


    StarbuzzDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
// 타이핑
//    public StarbuzzDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
////자동
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        updateMyDatabase(sqLiteDatabase, i, i1);
    }

    private void updateMyDatabase(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i < 1) {
            sqLiteDatabase.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER) ;");
            insertDrink(sqLiteDatabase, "라떼", "에스프레소에 스팀우유", R.drawable.latte);
            insertDrink(sqLiteDatabase, "카푸치노", "에스프레소에 우유 + 스팀우유", R.drawable.capucino);
            insertDrink(sqLiteDatabase, "카페모카", "에스프레소에 스팀우유에 초코시럽", R.drawable.cafemoca);
        }else if (i < 2){
            sqLiteDatabase.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }
    }


    private static void insertDrink(SQLiteDatabase sqLiteDatabase, String name, String description, int resourceId){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        sqLiteDatabase.insert("DRINK", null, drinkValues);
    }
}
