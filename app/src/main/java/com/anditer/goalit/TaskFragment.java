package com.anditer.goalit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anditer.goalit.POJOs.Task;
import com.anditer.goalit.adapters.TaskAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {
    ArrayList<Task> tasksList = new ArrayList<>();
    RecyclerView taskRecyclerView;
    TaskAdapter taskAdapter;
    LinearLayoutManager layoutManager;
    private String TAG = "com.anditer.goalit";

    private void addTasksToTaskList(){
        for (int i=0;i<10;i++){
            Task task = new Task();
            task.setTitle("This is the title for task " + String.valueOf(i));
            tasksList.add(task);
        }
    }


    public TaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        addTasksToTaskList();
        taskRecyclerView = view.findViewById(R.id.taskRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        taskRecyclerView.setLayoutManager(layoutManager);
        taskAdapter = new TaskAdapter(getContext(), tasksList);
        taskRecyclerView.setAdapter(taskAdapter);

        return view;


    }

}
