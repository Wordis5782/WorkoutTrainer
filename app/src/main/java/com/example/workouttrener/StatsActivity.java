package com.example.workouttrener;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class StatsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StatsAdapter adapter;
    private List<TrainingStats> statsList = new ArrayList<>();
    private TrainingStatsDAO statsDao;
    private TextView noStatsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        recyclerView = findViewById(R.id.statistics_recycler);
        noStatsText = findViewById(R.id.statistics_title);
        statsDao = AppDatebase.getInstance(this).trainingStatsDAO();

        adapter = new StatsAdapter(statsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadStats();
    }

    private void loadStats() {
        new Thread(() -> {
            try {
                List<TrainingStats> stats = statsDao.getAll();
                runOnUiThread(() -> {
                    statsList.clear();
                    statsList.addAll(stats);
                    adapter.notifyDataSetChanged();
                    updateEmptyState();
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Ошибка при загрузке статистики", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

    private void updateEmptyState() {
        if (statsList.isEmpty()) {
            noStatsText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noStatsText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}