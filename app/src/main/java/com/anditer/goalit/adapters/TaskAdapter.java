package com.anditer.goalit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.anditer.goalit.POJOs.Task;
import com.anditer.goalit.R;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder>{

    private ArrayList<Task> tasks;
    private Context context;
    private String TAG = "com.anditer.goalit.adapters";

    public TaskAdapter(Context context, ArrayList<Task> tasks){
        this.tasks = tasks;
        this.context = context;
    }


    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.task_item,parent,false);
        Log.e(TAG, "onCreateViewHolder");
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        Task task = tasks.get(position);
        holder.taskTitle.setText(task.getTitle());
        Log.e(TAG, "onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        if (tasks.size()>0){
            return tasks.size();
        }
        return 0;
    }



    class TaskHolder extends RecyclerView.ViewHolder{
        private TextView taskTitle;
        private CheckBox taskStatus;

        private TaskHolder(View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.taskName);
            taskStatus = itemView.findViewById(R.id.taskCheckBox);

            setIsRecyclable(false);
        }
    }
}
