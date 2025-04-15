package com.example.workouttrener;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.stats);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |   // Скрыть статус-бар
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |  // Скрыть панель навигации
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY // Чтобы панель не появлялась снова
        );

    }
}