package com.kvyh.regexgolf;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        notifyDataSetChanged();
    }
}
