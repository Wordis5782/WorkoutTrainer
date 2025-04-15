package com.example.workouttrener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrainingListAdapter extends RecyclerView.Adapter<TrainingListAdapter.TrainingListViewHolder>{


    private Context context;
    private List<Training> trainingList;

    public TrainingListAdapter(List<Training> trainingList, Context context)
    {
        this.trainingList = trainingList;
        this.context = context;
    }
    @Override
    public TrainingListAdapter.TrainingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_training, parent, false);
        return new TrainingListAdapter.TrainingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingListViewHolder holder, int position) {
        Training training = trainingList.get(position);
        holder.name.setText(training.getName());
        holder.type.setText(training.getType());
        holder.duration.setText(String.valueOf(training.getDuration()));
        holder.difficulty.setText(training.getDifficulty());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TrainingListViewHolder extends RecyclerView.ViewHolder {
        TextView name, type, duration, difficulty;

        public TrainingListViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.training_name);
            type = itemView.findViewById(R.id.training_type);
            duration = itemView.findViewById(R.id.training_duration);
            difficulty = itemView.findViewById(R.id.training_difficulty);
        }
    }
}
