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
        adapter = new ProblemListAdapter(getApplicationContext());
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        adapter.reload();
        // If the adapter isn't registering problems correctly, reload
        if (adapter.problems.size() == 0){
            finish();
            startActivity(getIntent());
        }
    }

}
