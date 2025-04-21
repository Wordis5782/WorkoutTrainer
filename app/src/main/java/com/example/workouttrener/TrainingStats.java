package com.example.workouttrener;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "training_stats")
public class TrainingStats {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int exerciseCount;
    public int totalDuration;
    public Date date;

    public TrainingStats(int exerciseCount, int totalDuration, Date date) {
        this.exerciseCount = exerciseCount;
        this.totalDuration = totalDuration;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getExerciseCount() { return exerciseCount; }
    public void setExerciseCount(int exerciseCount) { this.exerciseCount = exerciseCount; }

    public int getTotalDuration() { return totalDuration; }
    public void setTotalDuration(int totalDuration) { this.totalDuration = totalDuration; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
} 