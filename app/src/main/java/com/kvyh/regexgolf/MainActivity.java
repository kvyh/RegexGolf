package com.kvyh.regexgolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;

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
        if (adapter.problems.size() == 0){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            addDefaultProblems();}
    }
    public RequestQueue requestQueue;

    public void addDefaultProblems() {
        //requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://kvyh.github.io/RegexGolf/app/src/main/res/raw/default_problems.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("problems");
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
                } catch (JSONException e) { Log.e("problemsJSONfile", e.getMessage()); }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyJSONfile", error.getMessage());
            }
        });
        requestQueue.add(request);
        adapter.reload();
    }
}
