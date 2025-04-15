package com.example.workouttrener;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WorkoutTrener extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |   // Скрыть статус-бар
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |  // Скрыть панель навигации
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY // Чтобы панель не появлялась снова
        );
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        Button buttonDontTouch = findViewById(R.id.button1);
        buttonDontTouch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentTrainingList = new Intent(WorkoutTrener.this, TrainingListActivity.class);
                startActivity(intentTrainingList);
            }
        });
        Button buttonAddTraining = findViewById((R.id.button2));
        buttonAddTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddTraining =  new Intent(WorkoutTrener.this, TrainingActivity.class);
                startActivity(intentAddTraining);
            }
        });

        Button buttonStats = findViewById(R.id.button3);
        buttonStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStats = new Intent(WorkoutTrener.this, StatsActivity.class);
                startActivity(intentStats);
            }
        });

        ImageButton buttonSettings = findViewById(R.id.settings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSettings = new Intent(WorkoutTrener.this, SettingsActivity.class);
                startActivity(intentSettings);
            }
        });

    }
}