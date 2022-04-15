package com.batman.baselibrary.db.Database;

import android.content.Context;

import com.batman.baselibrary.db.dao.TaskDao;
import com.batman.baselibrary.db.entity.TaskEntity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TaskEntity.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "task.db")
                            .addMigrations(MIGRATION_1_2)
                            .addMigrations(MIGRATION_2_3)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //添加 name字段
            database.execSQL("ALTER TABLE TaskEntity "
                    + " ADD COLUMN  title TEXT");
        }
    };

    static Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //添加 date字段
            database.execSQL("ALTER TABLE TaskEntity "
                    + " ADD COLUMN  date TEXT");
        }
    };

}
