package com.example.workouttrener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsViewHolder> {
    private List<TrainingStats> statsList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

    public StatsAdapter(List<TrainingStats> statsList) {
        this.statsList = statsList;
    }

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stats, parent, false);
        return new StatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {
        TrainingStats stats = statsList.get(position);
        holder.exerciseCount.setText("Количество упражнений: " + stats.getExerciseCount());
        holder.totalDuration.setText("Общее время: " + stats.getTotalDuration() + " мин");
        holder.date.setText("Дата: " + dateFormat.format(stats.getDate()));
    }

    @Override
    public int getItemCount() {
        return statsList.size();
    }

    public void setStatsList(List<TrainingStats> statsList) {
        this.statsList = statsList;
        notifyDataSetChanged();
    }

    static class StatsViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseCount;
        TextView totalDuration;
        TextView date;

        StatsViewHolder(View itemView) {
            super(itemView);
            exerciseCount = itemView.findViewById(R.id.stats_exercise_count);
            totalDuration = itemView.findViewById(R.id.stats_total_duration);
            date = itemView.findViewById(R.id.stats_date);
        }
    }
} 