package com.example.workouttrener;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class TrainingListAdapter extends RecyclerView.Adapter<TrainingListAdapter.TrainingListViewHolder> {

    private List<Training> trainingList;
    private final OnItemClickListener listener;

    public TrainingListAdapter(List<Training> trainingList, OnItemClickListener listener) {
        this.trainingList = trainingList;
        this.listener = listener;
    }

    @Override
    public TrainingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_training, parent, false);
        return new TrainingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrainingListViewHolder holder, int position) {
        Training training = trainingList.get(position);
        holder.name.setText(training.getName());
        holder.duration.setText(String.valueOf(training.getDuration()));
        
        // Удаляем старый слушатель
        holder.checkBox.setOnCheckedChangeListener(null);
        // Устанавливаем состояние чекбокса
        holder.checkBox.setChecked(training.isSelected());

        // Слушатель изменения состояния чекбокса
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Обновляем модель данных
            training.setSelected(isChecked);
            Log.d("TrainingAdapter", "Training " + training.getName() + " checked: " + isChecked);
            
            // Уведомляем о изменении только конкретного элемента
            if (listener != null) {
                listener.onItemClick();
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainingList.size();
    }

    public interface OnItemClickListener {
        void onItemClick();  // Сигнал для обновления данных активности
    }

    public static class TrainingListViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView duration;
        CheckBox checkBox;

        public TrainingListViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.training_name);
            duration = itemView.findViewById(R.id.training_duration);
            checkBox = itemView.findViewById(R.id.checkbox_select);
        }
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
        notifyDataSetChanged();  // Оповещаем адаптер, что данные изменились
    }

    public List<Training> getTrainingList() {
        return trainingList;
    }
}
