package com.cleanup.todoc_J.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cleanup.todoc_J.database.dao.ProjectDao;
import com.cleanup.todoc_J.database.dao.TaskDao;
import com.cleanup.todoc_J.models.Project;
import com.cleanup.todoc_J.models.Task;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)

public abstract class TodocDatabase extends RoomDatabase {


    // --- SINGLETON --- // une seule instance pour toute l'app
    private static volatile TodocDatabase INSTANCE;  // pour utiliser la même variable sans copie, 1 pour tout les threads

    // --- DAO ---
    public abstract ProjectDao projectDao();

    public abstract TaskDao taskDao();

    // --- INSTANCE ---
    public static TodocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDatabase.class) { // protège, pour pas que 2 threads aient accès à la même ressource
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }

            }
        }
        return INSTANCE;
    }


    private static Callback prepopulateDatabase() {
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);



                Project[] projects = Project.getAllProjects();

                for(int i = 0; i<=2; i++) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", projects[i].getId());
                    contentValues.put("name", projects[i].getName());
                    contentValues.put("color", projects[i].getColor());


                    db.insert("Project", OnConflictStrategy.IGNORE, contentValues);
                }
            }
        };
    }
}

