package com.anditer.goalit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anditer.goalit.POJOs.Goal;
import com.anditer.goalit.adapters.GoalAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoalFragment extends Fragment {
    private ArrayList<Goal> goalList = new ArrayList<>();
    private RecyclerView goalRecyclerView;
    private LinearLayoutManager layoutManager;
    private GoalAdapter goalAdapter;

    private void populateGoalListWithGoals(){
        for (int i=0;i<10;i++){
            Goal goal = new Goal();
            goal.setTitle("Become the Best Software Developer Ever " + String.valueOf(i));
            goal.setEndDate("454");
            goal.setAddedDate("32");
            goalList.add(goal);
        }
    }

    public GoalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goal, container, false);
        populateGoalListWithGoals();
        goalRecyclerView = view.findViewById(R.id.goalRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        goalRecyclerView.setLayoutManager(layoutManager);
        goalAdapter = new GoalAdapter(getContext(), goalList);
        goalRecyclerView.setAdapter(goalAdapter);

        return view;
    }

}
