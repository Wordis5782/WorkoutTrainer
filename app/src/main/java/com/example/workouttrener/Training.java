package com.example.workouttrener;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import java.util.List;

@Entity(tableName = "trainings")
@TypeConverters(Converters.class)
public class Training {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public List<Exercise> exercises;

    public int getTotalDuration() {
        int total = 0;
        for (Exercise exercise : exercises) {
            total += exercise.duration;
        }
        return total;
    }
}
