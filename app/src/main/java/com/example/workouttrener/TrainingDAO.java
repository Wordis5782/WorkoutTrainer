package com.example.workouttrener;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface TrainingDAO {
    @Query("SELECT * FROM trainings")
    List<Training> getAll();

    @Query("SELECT * FROM trainings WHERE id = :id")
    Training getById(int id);

    @Insert
    void insert(Training training);

    @Delete
    void delete(Training training);

    @Query("DELETE FROM trainings")
    void deleteAll();
}
