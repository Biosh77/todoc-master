package com.cleanup.todoc_J.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cleanup.todoc_J.models.Task;

import java.util.List;


@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task ")
    LiveData<List<Task>> getTasks();

    @Insert
    long insertTask(Task task);

    @Update
    int updateTask(Task task);

    @Query("DELETE FROM Task WHERE id = :TaskId")
    int deleteTask(long TaskId);



}


