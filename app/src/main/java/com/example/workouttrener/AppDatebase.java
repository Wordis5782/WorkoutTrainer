package com.example.workouttrener;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Training.class, TrainingStats.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class AppDatebase extends RoomDatabase {
    private static AppDatebase instance;

    public abstract TrainingDAO trainingDAO();
    public abstract TrainingStatsDAO trainingStatsDAO();

    public static synchronized AppDatebase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatebase.class,
                    "workout_database"
            ).build();
        }
        return instance;
    }
}
