package com.example.workouttrener;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface TrainingDAO {
    @Insert
    long insert(Training training);
    @Update
    void update(Training training);
    @Delete
    void delete(Training training);
    @Query("SELECT * FROM trainings")
    List<Training> getAll();
    @Query("DELETE FROM trainings")
    void deleteAll();
}
