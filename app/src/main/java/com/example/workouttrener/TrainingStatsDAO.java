package com.example.workouttrener;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface TrainingStatsDAO {
    @Query("SELECT * FROM training_stats")
    List<TrainingStats> getAll();

    @Insert
    void insert(TrainingStats stats);

    @Delete
    void delete(TrainingStats stats);

    @Query("DELETE FROM training_stats")
    void deleteAll();
} 