package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;


import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.models.Project;

import java.util.List;

public class ProjectDataRepository {

    private ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    // --- GET USER ---

    public LiveData<List<Project>> getProjects() {
        return this.projectDao.getProjects();
    }


}