package com.cleanup.todoc_J.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;


import com.cleanup.todoc_J.models.Project;
import com.cleanup.todoc_J.models.Task;
import com.cleanup.todoc_J.repositories.ProjectDataRepository;
import com.cleanup.todoc_J.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // REPOSITORIES

    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;

    //DATA


    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }


    // FOR PROJECT

    public LiveData<List<Project>> getProjects() {
        return projectDataSource.getProjects();
    }


    // FOR TASK

    public LiveData<List<Task>> getTasks() {
        return taskDataSource.getTasks();
    }

    public void createTask(Task task) {
        executor.execute(() -> {
            taskDataSource.createTask(task);
        });
    }

    public void deleteTask(long TaskId) {
        executor.execute(() -> {
            taskDataSource.deleteTask(TaskId);
        });
    }

    public void updateTask(Task task) {
        executor.execute(() -> {
            taskDataSource.updateTask(task);
        });

    }
}


