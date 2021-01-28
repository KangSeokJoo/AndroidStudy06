package com.jinasoft.study06_ksj.ListView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jinasoft.study06_ksj.R;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkId = (Integer)getIntent() .getExtras(). get(EXTRA_DRINKID);
//        Drink_ModelClass drink_modelClass = Drink_ModelClass.drinks[drinkId];


        //커서생성
        SQLiteOpenHelper sqLiteOpenHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.query("DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_resource_id"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkId)},
                    null, null, null);


        //커서의 첫 번째 레코드로 이동
        if (cursor.moveToFirst()) {
            //커서 음료 정보 얻기
            String nameText = cursor.getString(0);
            String descriptionText = cursor.getString(1); // 이름, 설명 , id
            int photoId = cursor.getInt(2);
            boolean isFavorite = (cursor.getInt(3) == 1);

            TextView name = (TextView) findViewById(R.id.name);
//            name.setText(drink_modelClass.getName());
            name.setText(nameText);

            TextView description = (TextView)findViewById(R.id.description);
//        description.setText(drink_modelClass.getDecription());
            description.setText(descriptionText);
//
            ImageView photo = (ImageView)findViewById(R.id.photo);
//        photo.setImageResource(drink_modelClass.getImageResourceId());
//        photo.setContentDescription(drink_modelClass.getName());
            photo.setImageResource(photoId);
            photo.setContentDescription(nameText);

            //favorite 체크박스 설정
            CheckBox checkBox = (CheckBox)findViewById(R.id.favorite);
            checkBox.setChecked(isFavorite);
        }cursor.close();
        sqLiteDatabase.close();

        }catch(SQLException e){
            Toast.makeText(this, "데이타베이스 오류", Toast.LENGTH_SHORT).show();
        }
    }

    //체크박스를 클릭하면 데이터베이스 갱신
    public void onFavoriteClicked(View view){
        int drinkId = (Integer)getIntent().getExtras().get(EXTRA_DRINKID);

        //체크박스의 값 얻기
//        CheckBox favorite = (CheckBox)findViewById(R.id.favorite);
//        ContentValues drinkValues = new ContentValues();
//        drinkValues.put("FAVORITE", favorite.isChecked());
//
//        //데이터베이스 래퍼런스를 얻어서 FAVORITE열 갱신
//        SQLiteOpenHelper sqLiteOpenHelper = new StarbuzzDatabaseHelper(this);
//        try {
//            SQLiteDatabase sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
//            sqLiteDatabase.update("DRINK", drinkValues, "_id = ?", new String[] {Integer.toString(drinkId)});
//            sqLiteDatabase.close();
//        }catch (SQLException e){
//            Toast.makeText(this, "데이타베이스 없음", Toast.LENGTH_SHORT).show();
//        }
        new UpdateDrinkTask().execute(drinkId);
    }

    private class UpdateDrinkTask extends AsyncTask<Integer, Void, Boolean> {
        private ContentValues drinkValues;

        protected void onPreExecute(){
            CheckBox favorite = (CheckBox) findViewById(R.id.favorite); //데이터베이스 코드를 실행하기 전 체크박스 값 객체에 저장 , 테스크를 실행하기 전에 실행하는 코드
            drinkValues = new ContentValues();
            drinkValues.put("FAVORITE", favorite.isChecked());
        }
        @Override
        protected Boolean doInBackground(Integer... integers) { //데이터베이스 코드를 여기에 정의 백그라운드 스레드에서 실행하려는 코드
            int drinkId = integers[0];
            SQLiteOpenHelper sqLiteOpenHelper = new StarbuzzDatabaseHelper(DrinkActivity.this);
            try {
                SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
                db.update("DRINK", drinkValues, "_id = ?", new String[]{Integer.toString(drinkId)});
                db.close();
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) { //데이터베이스 코드를 백그라운드로 실행한 다음 성공적으로 실행 되었는지 확인, 테스크가 끝나면 실행할 코드
            if (!success){
                Toast.makeText(DrinkActivity.this, "데이터베이스 없음", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(success);
        }
    }
}