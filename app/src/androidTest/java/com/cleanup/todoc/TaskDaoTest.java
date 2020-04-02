package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.models.Project;
import com.cleanup.todoc.models.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class TaskDaoTest {

    // FOR DATA

    private TodocDatabase database;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void  initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), TodocDatabase.class).allowMainThreadQueries().build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    // DATA SET FOR TEST

    private static Project PROJECT_TEST = new Project(1,"Project 1",1);



    private static Task task = new Task(1,1,"Blabla",1);



    @Test
    public void getProject() throws InterruptedException {
        database.projectDao().insertProject(PROJECT_TEST);
        List<Project> projects = LiveDataTestUtil.getValue(database.projectDao().getProjects());
        Project project = projects.get(0);
        assertTrue(project.getName().equals(PROJECT_TEST.getName())&& project.getId() == PROJECT_TEST.getId());

    }

    @Test
    public void getTasksWhenNoTaskInserted() throws InterruptedException {
        //TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertAndGetTasks() throws InterruptedException {
        database.projectDao().insertProject(PROJECT_TEST);
        database.taskDao().insertTask(task);

        List<Task>tasks = LiveDataTestUtil.getValue(database.taskDao().getTasks());
        Task task1 = tasks.get(0);
        assertTrue(task1.getName().equals(task.getName()));
    }


    @Test
    public void InsertAndDeleteTask() throws InterruptedException {
        database.projectDao().insertProject(PROJECT_TEST);
        database.taskDao().insertTask(task);

        List<Task>tasks = LiveDataTestUtil.getValue(database.taskDao().getTasks());
        Task task1 = tasks.get(0);
        assertTrue(task1.getName().equals(task.getName()));

        database.taskDao().deleteTask(task.getId());

        tasks = LiveDataTestUtil.getValue(database.taskDao().getTasks());

        assertTrue(tasks.isEmpty());
    }



}
