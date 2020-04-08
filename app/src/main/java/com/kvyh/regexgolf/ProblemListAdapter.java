package com.kvyh.regexgolf;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemListAdapter extends
        RecyclerView.Adapter<ProblemListAdapter.ProblemListViewHolder> {
    public static class ProblemListViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView;
        public TextView titleTextView;
        public TextView addedTextView;

        ProblemListViewHolder(View view) {
            super(view);
            containerView = view.findViewById(R.id.problem_list_entry);
            titleTextView = view.findViewById(R.id.problem_list_entry_title_text_view);
            addedTextView = view.findViewById(R.id.problem_list_entry_added_text_view);

            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Problem current = (Problem) containerView.getTag();
                    Intent intent = new Intent(v.getContext(), GolfProblemActivity.class);
                    intent.putExtra("Problem", current);

                    v.getContext().startActivity(intent);
                }
            });
        }
    }
    public List<Problem> problems = new ArrayList<>();
    private RequestQueue requestQueue;

    ProblemListAdapter(Context context) {
        reload();
        if (problems.size() == 0) {
            addDefaultProblems(context);

            notifyDataSetChanged();
        }
    }

    public void addDefaultProblems(Context context) {
        requestQueue = Volley.newRequestQueue(context);

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
        reload();
        Log.i("kvyhregex", String.valueOf(problems.size()));
    }

    @NonNull
    @Override
    public ProblemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.problem_list_entry, parent, false);

        return new ProblemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProblemListViewHolder holder, int position) {
        Problem current = problems.get(position);
        holder.titleTextView.setText(current.getTitle());
        holder.addedTextView.setText(current.getInfo());

        holder.containerView.setTag(current);
    }

    @Override
    public int getItemCount() {
        return problems.size();
    }

    public void reload() {
        problems = MainActivity.database.problemDao().getAllProblems();
        Log.i("kvyhregex", String.valueOf(problems.size()));
        notifyDataSetChanged();
    }
}
