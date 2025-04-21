package com.example.workouttrener;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Training.class}, version = 1)
public abstract class AppDatebase extends RoomDatabase{

    private static AppDatebase instance;
    public abstract TrainingDAO trainingDAO();
    public static synchronized AppDatebase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatebase.class,
                    "training_database"
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
