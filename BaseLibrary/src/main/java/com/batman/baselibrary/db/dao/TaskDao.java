package com.batman.baselibrary.db.dao;

import com.batman.baselibrary.db.entity.TaskEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM taskentity")
    Flowable<List<TaskEntity>> getAll();

    @Query("SELECT * FROM taskentity WHERE task_name = :name LIMIT 1")
    Maybe<TaskEntity> loadByIds(String name);


    @Insert
    void insertAll(TaskEntity... users);

    @Insert
    void insert(TaskEntity users);

    @Delete
    void delete(TaskEntity user);

    @Update
    void update(TaskEntity user);
}