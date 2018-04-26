package com.anditer.goalit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anditer.goalit.POJOs.Goal;
import com.anditer.goalit.R;

import java.util.ArrayList;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalHolder>{
    private ArrayList<Goal> goalsList;
    private Context context;

    public GoalAdapter(Context context, ArrayList<Goal> goals){
        goalsList = goals;
        this.context = context;
    }


    @Override
    public GoalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.goal_item, parent, false);
        return new GoalHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GoalHolder holder, int position) {
        Goal goal = goalsList.get(position);
        holder.goalTitle.setText(goal.getTitle());
        //holder.daysLeft.setText("Days Left: " + goal.getEndDate());
        //holder.taskAdded.setText("Tasks Added: " + goal.getAddedDate());

    }

    @Override
    public int getItemCount() {
        if (goalsList.size()>0){
            return goalsList.size();
        }
        return 0;
    }

    class GoalHolder extends RecyclerView.ViewHolder{
        private TextView goalTitle, taskAdded, daysLeft;

        public GoalHolder(View itemView) {
            super(itemView);
            goalTitle = itemView.findViewById(R.id.goalTitle);
            //taskAdded = itemView.findViewById(R.id.tasksAdded);
            //daysLeft = itemView.findViewById(R.id.daysLeft);
        }
    }

}
