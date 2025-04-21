package com.example.workouttrener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TrainingListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TrainingListAdapter adapter;
    private List<Training> trainingList = new ArrayList<>();
    private TrainingDAO trainingDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_list);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        recyclerView = findViewById(R.id.training_recycler);
        trainingDao = AppDatebase.getInstance(this).trainingDAO();

        adapter = new TrainingListAdapter(trainingList, this::onTrainingClick);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadTrainings();
    }

    private void loadTrainings() {
        new Thread(() -> {
            try {
                List<Training> trainings = trainingDao.getAll();
                runOnUiThread(() -> {
                    trainingList.clear();
                    trainingList.addAll(trainings);
                    adapter.notifyDataSetChanged();
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Ошибка при загрузке тренировок", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

    private void onTrainingClick(Training training) {
        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("training_id", training.id);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTrainings();
    }
}
