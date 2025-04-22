package com.example.workouttrener;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface TrainingStatsDAO {
    @Insert
    void insert(TrainingStats stats);

    @Query("SELECT * FROM training_stats ORDER BY date DESC")
    List<TrainingStats> getAll();

    @Query("DELETE FROM training_stats")
    void deleteAll();
} 