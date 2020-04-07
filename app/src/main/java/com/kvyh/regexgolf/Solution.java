package com.kvyh.regexgolf;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "solutions",
        foreignKeys = @ForeignKey(entity = Problem.class, parentColumns = "id",
                childColumns = "problem_id", onDelete = ForeignKey.CASCADE))
public class Solution {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "problem_id")
    public int problemId;
    @ColumnInfo(name = "solution")
    public String solution;
    @ColumnInfo(name = "length")
    public int length;
}
