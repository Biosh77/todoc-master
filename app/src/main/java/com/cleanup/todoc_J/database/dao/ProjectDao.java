package com.cleanup.todoc_J.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.support.annotation.VisibleForTesting;

import com.cleanup.todoc_J.models.Project;

import java.util.List;


@Dao
public interface ProjectDao {


    @Query("SELECT * FROM Project ")
    LiveData<List<Project>> getProjects();


    // FOR TEST
    @Insert
    @VisibleForTesting
    // TODO Public ... createProject
    void insertProject(Project project);

    }



