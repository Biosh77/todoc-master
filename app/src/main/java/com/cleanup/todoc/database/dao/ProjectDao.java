package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.models.Project;


@Dao
public interface ProjectDao {


    @Query("SELECT * FROM Project ")
    LiveData<Project> getProjects();

}
