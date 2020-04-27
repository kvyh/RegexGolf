package com.kvyh.regexgolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

        recyclerView = findViewById(R.id.main_recycler);
        adapter = new ProblemListAdapter(getApplicationContext());
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        adapter.reload();
        // If the adapter isn't registering problems correctly, reload
        if (adapter.problems.size() < database.problemDao().getAllProblems().size()){
            finish();
            startActivity(getIntent());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_reload:
                adapter.addDefaultProblems(getApplicationContext());
                adapter.reload();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.reload();
    }
}
