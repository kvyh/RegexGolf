package com.kvyh.regexgolf;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProblemDao {
    @Query("Select * FROM solutions WHERE id = :problem_id")
    List<Solution> getProblemSolutions(int problem_id);

    @Query("Insert INTO solutions (problem_id, solution, length) " +
            "VALUES (:problemId, :regex, :length)")
    void submitCorrect(String regex, int problemId, int length);

    @Query("Select * FROM problems")
    List<Problem> getAllProblems();

    // Anything that calls this needs to check for unique target/reject string
    @Query("INSERT INTO problems (title, difficulty, shortest, target, reject, source) " +
            "VALUES (:title, :difficulty, :shortest, :target_string, :reject_string, :source)")
    void recordProblem(String title, int difficulty, int shortest,
                       String target_string, String reject_string, String source);

}
