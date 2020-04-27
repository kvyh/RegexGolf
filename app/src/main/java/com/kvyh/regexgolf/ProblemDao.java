package com.kvyh.regexgolf;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProblemDao {
    @Query("Select solution FROM solutions WHERE problem_id = :problem_id")
    List<String> getProblemSolutions(int problem_id);

    @Query("Insert INTO solutions (problem_id, solution, length) " +
            "VALUES (:problemId, :regex, :length)")
    void submitCorrect(String regex, int problemId, int length);

    @Query("Select * FROM problems")
    List<Problem> getAllProblems();

    // Anything that calls this needs to check for unique target/reject string
    @Query("INSERT INTO problems (title, complexity, shortest, target, reject, source) " +
            "VALUES (:title, :complexity, :shortest, :target_string, :reject_string, :source)")
    void recordProblem(String title, int complexity, int shortest,
                       String target_string, String reject_string, String source);

    @Query("UPDATE problems SET shortest = :length WHERE id = :id")
    void updateshortest(int id, int length);
}
