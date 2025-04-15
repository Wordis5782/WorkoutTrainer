package com.example.workouttrener;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TrainingListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView totalDurationTextView;
    private List<Training> trainingList;
    private TrainingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_list);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |   // Скрыть статус-бар
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |  // Скрыть панель навигации
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY // Чтобы панель не появлялась снова
        );
        recyclerView = findViewById(R.id.training_list);
        totalDurationTextView = findViewById(R.id.total_time);

        trainingList = getMockTrainings();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayAdapter<Training> adapter = new ArrayAdapter<Training>(
                this,
                R.layout.item_training,
                getMockTrainings()
        );
        updateTotalDuration();
    }

    private void updateTotalDuration() {
        int total = 0;
        for (Training t : trainingList) {
            if (t.isSelected()) {
                total += t.getDuration();
            }
        }
        totalDurationTextView.setText("Общее время: " + total + " мин");
    }

    private List<Training> getMockTrainings() {
        List<Training> list = new ArrayList<>();
        list.add(new Training("Кардио", "Аэробика", 20, "Средняя"));
        list.add(new Training("Силовая", "Тренажеры", 30, "Сложная"));
        list.add(new Training("Растяжка", "Йога", 15, "Легкая"));
        return list;
    }
}
