package com.example.workouttrener;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import android.util.Log;

public class TrainingListActivity extends AppCompatActivity implements TrainingListAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private TextView totalDurationTextView;
    private Button startButton;
    private List<Training> trainingList;
    private TrainingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_list);

        recyclerView = findViewById(R.id.training_list);
        totalDurationTextView = findViewById(R.id.total_time);
        startButton = findViewById(R.id.button);

        trainingList = new ArrayList<>();
        adapter = new TrainingListAdapter(trainingList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadTrainingsFromDatabase();

        // Делаем кнопку неактивной по умолчанию
        startButton.setEnabled(false);
        startButton.setOnClickListener(v -> {
            // Сохраняем статистику тренировки
            saveTrainingStats();
            
            // Сбрасываем выбранные тренировки
            for (Training training : trainingList) {
                training.setSelected(false);
            }
            adapter.notifyDataSetChanged();
            updateTotalDuration();
            
            // Завершаем активность
            finish();
        });
    }

    private void loadTrainingsFromDatabase() {
        AppDatebase db = AppDatebase.getInstance(this);
        TrainingDAO trainingDao = db.trainingDAO();

        new Thread(() -> {
            try {
                List<Training> loadedTrainings = trainingDao.getAll();
                runOnUiThread(() -> {
                    adapter.setTrainingList(loadedTrainings);
                    updateTotalDuration(); // Обновляем счетчик времени
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void saveTrainingStats() {
        int selectedCount = 0;
        int totalDuration = 0;

        for (Training training : trainingList) {
            if (training.isSelected()) {
                selectedCount++;
                totalDuration += training.getDuration();
            }
        }

        if (selectedCount > 0) {
            TrainingStats stats = new TrainingStats(selectedCount, totalDuration, new Date());
            AppDatebase db = AppDatebase.getInstance(this);
            TrainingStatsDAO statsDao = db.trainingStatsDAO();

            new Thread(() -> {
                try {
                    statsDao.insert(stats);
                    Log.d("TrainingListActivity", "Stats saved successfully");
                } catch (Exception e) {
                    Log.e("TrainingListActivity", "Error saving stats: " + e.getMessage());
                    e.printStackTrace();
                }
            }).start();
        }
    }

    @Override
    public void onItemClick() {
        Log.d("TrainingActivity", "onItemClick triggered");
        // Обновляем только общее время
        updateTotalDuration();
    }

    private void updateTotalDuration() {
        int totalDuration = 0;
        boolean anySelected = false;

        // Получаем актуальный список тренировок из адаптера
        trainingList = adapter.getTrainingList();

        // Считаем общее время для выбранных тренировок
        for (Training training : trainingList) {
            if (training.isSelected()) {
                totalDuration += training.getDuration();
                anySelected = true;
            }
        }

        // Обновляем текст в TextView
        int finalTotalDuration = totalDuration;
        boolean finalAnySelected = anySelected;
        runOnUiThread(() -> {
            totalDurationTextView.setText("Общее время: " + finalTotalDuration + " мин");
            startButton.setEnabled(finalAnySelected);
        });
    }
}
