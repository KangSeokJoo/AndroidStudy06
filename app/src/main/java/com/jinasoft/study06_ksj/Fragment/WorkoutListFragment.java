package com.jinasoft.study06_ksj.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jinasoft.study06_ksj.R;


public class WorkoutListFragment extends ListFragment {

    static interface Listener{
        void  itemClicked(long id);
    };
    private Listener listener;
    public WorkoutListFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] names = new String[Workout_ModelClass.workouts.length];
        for (int i =0; i < names.length; i++){
            names[i] = Workout_ModelClass.workouts[i].getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container,savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) { // 프래그먼트를 액티비티에 연결하면 이 메서드가 호출됀다.
        super.onAttach(context);
        this.listener = (Listener)context;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        if (listener != null){
            listener.itemClicked(id); // 클릭되면ㄴ 리스너에 알림림
        }
       super.onListItemClick(l, v, position, id);
    }
}