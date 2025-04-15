package com.example.workouttrener;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttrener.databinding.ActivityTrainingBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class TrainingActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityTrainingBinding binding;
    private TextInputEditText nameField, typeField, durationField, difficultyField;
    private Button addButton;
    private RecyclerView trainingList;
    private List<Training> trainings;
    private TrainingAdapter adapter;
    private Integer editingIndex = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrainingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |   // Скрыть статус-бар
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |  // Скрыть панель навигации
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY // Чтобы панель не появлялась снова
        );
        nameField = findViewById(R.id.training_name);
        typeField = findViewById(R.id.training_type);
        durationField = findViewById(R.id.training_duration);
        difficultyField = findViewById(R.id.training_difficulty);
        addButton = findViewById(R.id.btn_add_training);
        trainingList = findViewById(R.id.training_list);

        trainings = new ArrayList<>();
        adapter = new TrainingAdapter(trainings,  position -> {
            // Получаем тренировку по позиции
            Training t = trainings.get(position);
            nameField.setText(t.getName());
            typeField.setText(t.getType());
            durationField.setText(String.valueOf(t.getDuration()));
            difficultyField.setText(t.getDifficulty());
            editingIndex = position; // Сохраняем индекс редактируемой тренировки
            addButton.setText("Сохранить");
        });

        trainingList.setLayoutManager(new LinearLayoutManager(this));
        trainingList.setAdapter(adapter);

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitleTextColor(Color.parseColor("#FFFCF2"));
        getSupportActionBar().setTitle("Workout Trainer");

        // Обработчик клика на кнопку "Добавить тренировку"
        addButton.setOnClickListener(v -> {
            String name = nameField.getText().toString();
            String type = typeField.getText().toString();
            int duration = 0;
            try {
                duration = Integer.parseInt(durationField.getText().toString()); // Парсим длительность
            } catch (NumberFormatException e) {
                e.printStackTrace(); // В случае ошибки, например, если ввели не число, оставляем duration равным 0
            }
            String difficulty = difficultyField.getText().toString();

            // Проверяем на дубликаты
            if (isDuplicate(name)) {
                // Если тренировка с таким названием уже существует
                Snackbar.make(v, "Тренировка с таким названием уже существует!", Snackbar.LENGTH_SHORT).show();
                return;
            }

            if (editingIndex != null) {
                // Если индекс не равен null, редактируем существующую тренировку
                Training t = trainings.get(editingIndex);
                t.setName(name);
                t.setType(type);
                t.setDuration(duration);
                t.setDifficulty(difficulty);
                editingIndex = null; // Сброс индекса редактирования
                addButton.setText("Добавить тренировку");
            } else {
                // Добавляем новую тренировку в список
                trainings.add(new Training(name, type, duration, difficulty));
            }

            // Очищаем поля ввода
            clearFields();

            // Обновляем адаптер
            adapter.notifyDataSetChanged();
        });
    }

    // Метод для проверки на дубликаты по названию
    private boolean isDuplicate(String name) {
        for (Training t : trainings) {
            if (t.getName().equalsIgnoreCase(name)) {
                return true; // Если тренировка с таким названием уже есть
            }
        }
        return false; // Нет дубликатов
    }

    private void clearFields() {
        nameField.setText("");
        typeField.setText("");
        durationField.setText("");
        difficultyField.setText("");
    }
}
