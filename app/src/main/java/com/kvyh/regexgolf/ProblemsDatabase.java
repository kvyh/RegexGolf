package com.kvyh.regexgolf;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Problem.class, Solution.class}, version = 1)
public abstract class ProblemsDatabase extends RoomDatabase {
    public abstract ProblemDao problemDao();
}
