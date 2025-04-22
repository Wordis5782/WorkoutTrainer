package com.example.workouttrener;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Training.class, TrainingStats.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class AppDatebase extends RoomDatabase{

    private static AppDatebase instance;
    public abstract TrainingDAO trainingDAO();
    public abstract TrainingStatsDAO trainingStatsDAO();

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Создаем новую таблицу для статистики
            database.execSQL("CREATE TABLE IF NOT EXISTS training_stats (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "exerciseCount INTEGER NOT NULL, " +
                    "totalDuration INTEGER NOT NULL, " +
                    "date INTEGER NOT NULL)");
        }
    };

    public static synchronized AppDatebase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatebase.class,
                    "training_database"
            )
            .addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration()
            .build();
        }
        return instance;
    }
}
