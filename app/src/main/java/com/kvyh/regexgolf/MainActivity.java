package com.kvyh.regexgolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public static ProblemListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static ProblemsDatabase database;
    private Problem tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(getApplicationContext(), ProblemsDatabase.class, "problems")
                .allowMainThreadQueries()
                .build();

        recyclerView = findViewById(R.id.main_menu);
        adapter = new ProblemListAdapter();
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        adapter.reload();
        Log.i("problemsJSONfile", "problems loaded: " + String.valueOf(adapter.problems.size()));
        if (adapter.problems.size() == 0){
            addDefaultProblems();}
    }

    private void addDefaultProblems() {
        try {
            String fileString = "";
            InputStreamReader reader = new InputStreamReader(getResources().openRawResource(R.raw.default_problems));
            int read = 1;
            while ((read > -1)) {
                read = (int) reader.read();
                if (read > -1) {
                    fileString += (char) read;
                }
            }

            JSONObject json = new JSONObject(fileString);
            JSONArray array = json.getJSONArray("problems");
            for (int i = 0; i < array.length(); i += 1) {
                JSONObject prob = array.getJSONObject(i);
                Problem tempProblem = new Problem(prob.getInt("id"),
                                                prob.getString("title"),
                                                prob.getInt("difficulty"),
                                                prob.getInt("shortest"),
                                                prob.getString("target"),
                                                prob.getString("reject"),
                                                prob.getString("source"));
                tempProblem.addToDB();
            }

        }  catch(JSONException e) {
            Log.e("problemsJSONfile", e.getMessage());
        }  catch(IOException e) {
            Log.e("problemsJSONfile", e.getMessage());
        }
        adapter.reload();
    }
}
