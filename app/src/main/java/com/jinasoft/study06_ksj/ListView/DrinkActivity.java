package com.jinasoft.study06_ksj.ListView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinasoft.study06_ksj.R;

import org.w3c.dom.Text;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkId = (Integer)getIntent() .getExtras(). get(EXTRA_DRINKID);
        Drink_ModelClass drink_modelClass = Drink_ModelClass.drinks[drinkId];

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(drink_modelClass.getName());

        TextView description = (TextView)findViewById(R.id.description);
        description.setText(drink_modelClass.getDecription());

        ImageView photo = (ImageView)findViewById(R.id.photo);
        photo.setImageResource(drink_modelClass.getImageResourceId());
        photo.setContentDescription(drink_modelClass.getName());
    }
}