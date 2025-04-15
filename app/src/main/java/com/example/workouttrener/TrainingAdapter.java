package com.example.workouttrener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder> {

    private List<Training> trainingList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public TrainingAdapter(List<Training> trainingList, OnItemClickListener onItemClickListener) {
        this.trainingList = trainingList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public TrainingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_training, parent, false);
        return new TrainingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrainingViewHolder holder, int position) {
        Training training = trainingList.get(position);
        holder.name.setText(training.getName());
        holder.type.setText(training.getType());
        holder.duration.setText(String.valueOf(training.getDuration()));
        holder.difficulty.setText(training.getDifficulty());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainingList.size();
    }

    public static class TrainingViewHolder extends RecyclerView.ViewHolder {
        TextView name, type, duration, difficulty;

        public TrainingViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.training_name);
            type = itemView.findViewById(R.id.training_type);
            duration = itemView.findViewById(R.id.training_duration);
            difficulty = itemView.findViewById(R.id.training_difficulty);
        }
    }
}
