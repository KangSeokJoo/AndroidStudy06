package com.jinasoft.study06_ksj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jinasoft.study06_ksj.Bind.BindMain;
import com.jinasoft.study06_ksj.Fragment.FragMain;
import com.jinasoft.study06_ksj.ListView.ListViewDrinkMain;
import com.jinasoft.study06_ksj.Service.ServiceMain;
import com.jinasoft.study06_ksj.StopWatch.StopWatchMain;
import com.jinasoft.study06_ksj.Swipe.SwipeMain;


public class SelectStudyList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_study_list);

        Button btn0 = (Button)findViewById(R.id.Selcet_HintBTN);

        Button btn1 = (Button) findViewById(R.id.Selcet_Step1BTN);
        Button btn2 = (Button) findViewById(R.id.Selcet_Step2BTN);
        Button btn3 = (Button) findViewById(R.id.Selcet_Step3BTN);
        Button btn4 = (Button) findViewById(R.id.Selcet_Step4BTN);
        Button btn5 = (Button) findViewById(R.id.Selcet_Step5BTN);
        Button btn6 = (Button) findViewById(R.id.Selcet_Step6BTN);
//        Button btn7 = (Button) findViewById(R.id.Selcet_Step7BTN);
//        Button btn8 = (Button) findViewById(R.id.Selcet_Step8BTN);
//        Button btn9 = (Button) findViewById(R.id.Selcet_Step9BTN);
////
        TextView tv = (TextView)findViewById(R.id.Selcet_HintTV);

        String str1 = "STEP1 : 스톱워치 및 쓰레드 백그라운드 \n";
        String str2 = "STEP2 : 리스트 뷰 + 어댑터 사용 \n";
        String str3 = "STEP3 : 프래그먼트\n";
        String str4 = "STEP4 : 디자인 + 탭 타이틀 + 스와이프 + 스크롤\n";
        String str5 = "STEP5 : 서비스 생명주기 및 알림 10초대기\n";
        String str6 = "STEP6 : 바운드 서비스 다른 컴포넌트와 연결\n";

        btn0.setOnClickListener(view -> {
            tv.setText(str1 + str2+str3+str4+str5+str6);
        });

        btn1.setOnClickListener(view -> {
            Log.d("btn1_btn", "활성");
            Intent intent = new Intent(this, StopWatchMain.class);
            startActivity(intent);
        });
        btn2.setOnClickListener(view -> {
            Log.d("btn2_btn", "활성");
            Intent intent = new Intent(this, ListViewDrinkMain.class);
            startActivity(intent);
        });
        btn3.setOnClickListener(view -> {
            Log.d("btn3_btn", "활성");
            Intent intent = new Intent(this, FragMain.class);
            startActivity(intent);
        });
        btn4.setOnClickListener(view -> {
            Log.d("btn4_btn", "활성");
            Intent intent = new Intent(this, SwipeMain.class);
            startActivity(intent);
        });
        btn5.setOnClickListener(view -> {
            Log.d("btn5_btn", "활성");
            Intent intent = new Intent(com.jinasoft.study06_ksj.SelectStudyList.this, ServiceMain.class);
            startActivity(intent);
        });
        btn6.setOnClickListener(view -> {
            Log.d("btn6_btn", "활성");
            Intent intent = new Intent(com.jinasoft.study06_ksj.SelectStudyList.this, BindMain.class);
            startActivity(intent);
        });
//        btn7.setOnClickListener(view -> {
//            Log.d("btn7_btn", "활성");
//            Intent intent = new Intent(com.jinasoft.study06_ksj.SelectStudyList.this, SideMain.class);
//            startActivity(intent);
//        });
//        btn8.setOnClickListener(view -> {
//            Log.d("btn8_btn", "활성");
//            Intent intent = new Intent(com.jinasoft.study06_ksj.SelectStudyList.this, RealmDBMain.class);
//            startActivity(intent);
//        });
//        btn9.setOnClickListener(view -> {
//            Log.d("btn9_btn", "활성");
//            Intent intent = new Intent(com.jinasoft.study06_ksj.SelectStudyList.this, FirebaseChatMain.class);
//            startActivity(intent);
//        });


//        btn.setOnClickListener(new View.OnClickListener() {  원래 쓰던 Onclick 식
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SelectStudyList.this, EmailSave.class);
//                startActivity(intent);
//            }
//        });

//      public void GO_Act(view view){ // 다른 버튼을 눌러도 그 버튼이 한바퀴 스위치문 한바퀴를 도는게 느리지 않나 생각
//      swich(v.getId()){
//          case R.id. ~~~ ;
//              Intent in1 = new Intent ~~~~
//              startActivity(1);
//              break;
//      }


//    public void Go_Act7(View view){                         메소드로 온크릭을 기능 구현했었으나 위 방법으로 작성할 예정이였으나,
//        Intent intent = new Intent(this, ScrollView.class);
//        startActivity(intent);
//    }
//    public void Go_Act6(View view){
//        Intent intent = new Intent(this, Browser.class);
//        startActivity(intent);
//    }
//    public void Go_Act5(View view){
//        Intent intent = new Intent(this, OptionMenu.class);
//        startActivity(intent);
//    }
//    public void Go_Act4(View view){
//        Intent intent = new Intent(this, AppInQuest.class);
//        startActivity(intent);
//    }
//    public void Go_Act3(View view){
//        Intent intent = new Intent(this, KakaoMessage.class);
//        startActivity(intent);
//    }
//    public void Go_Act2(View view){
//        Intent intent = new Intent(this, ActionCall.class);
//        startActivity(intent);
//    }
//    public void Go_Act1(View view){
//        Intent intent = new Intent(this, ActivityDataSend.class);
//        startActivity(intent);
//    }

    }
}