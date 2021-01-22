package com.jinasoft.study06_ksj.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jinasoft.study06_ksj.R;


public class WorkoutDetailFragment extends Fragment {

    private long workoutId;

    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { //안드로이드가 프래그먼트 레이아웃을 필요로 할때 호출
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false); // XML 프래그먼트 레이아웃을 자바 객체로 인플레이트 함
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView(); // 프래그먼트의 뷰래퍼런스를 얻으려면 먼저 GetView() 메서드로 프래그먼트의 루트 뷰의 래퍼런스를 얻은 다음 자식뷰 찾아야함
        if (view != null){
            TextView title = (TextView)view.findViewById(R.id.textTitle);
            Workout_ModelClass workout = Workout_ModelClass.workouts[(int) workoutId];
            Log.d("확인해보고싶음" ,"" + workout);
            title.setText(workout.getName());
            TextView description = (TextView)view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }

    public void setWorkout(long id){
        this.workoutId = id;
    }
}