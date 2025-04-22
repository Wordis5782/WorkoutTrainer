package com.example.workouttrener;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private Button clearDatabaseButton;
    private AppDatebase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        clearDatabaseButton = findViewById(R.id.clear_database_button);
        db = AppDatebase.getInstance(this);

        clearDatabaseButton.setOnClickListener(v -> showClearDatabaseConfirmationDialog());
    }

    private void showClearDatabaseConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Подтверждение")
                .setMessage("Вы уверены, что хотите удалить все данные? Это действие нельзя отменить.")
                .setPositiveButton("Да", (dialog, which) -> clearDatabase())
                .setNegativeButton("Нет", null)
                .show();
    }

    private void clearDatabase() {
        new Thread(() -> {
            try {
                // Очищаем все таблицы
                db.trainingDAO().deleteAll();
                db.trainingStatsDAO().deleteAll();
                
                runOnUiThread(() -> {
                    Toast.makeText(this, "Все данные успешно удалены", Toast.LENGTH_SHORT).show();
                });
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(this, "Ошибка при удалении данных", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
}