package com.kvyh.regexgolf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.PatternSyntaxException;

// Display desired + undesired text, input box
public class GolfProblemActivity extends AppCompatActivity {
    private EditText regexEditText;
    private TextView titleTextView;
    private TextView targetTextView;
    private TextView rejectTextView;
    private TextView errorTextView;
    private TextView solutionsTextView;
    private Problem activeProblem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golf_problem);

        activeProblem = getIntent().getParcelableExtra("Problem");

        regexEditText = findViewById(R.id.regex_input_text);
        titleTextView = findViewById(R.id.golf_problem_title_text_view);
        targetTextView = findViewById(R.id.golf_problem_desired_text_view);
        rejectTextView = findViewById(R.id.golf_problem_undesired_text_view);
        errorTextView = findViewById(R.id.golf_problem_regex_error);
        solutionsTextView = findViewById(R.id.solutions);

        regexEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                 testRegexText(false);
                 if (s.length() == 0) {
                     targetTextView.setText(activeProblem.getTargetDisplayString("^$"), TextView.BufferType.SPANNABLE);
                     rejectTextView.setText(activeProblem.getRejectDisplayString("^$"), TextView.BufferType.SPANNABLE);
                 }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        titleTextView.setText(activeProblem.getTitle());
        targetTextView.setText(activeProblem.getTargetDisplayString("^$"), TextView.BufferType.SPANNABLE);
        rejectTextView.setText(activeProblem.getRejectDisplayString("^$"), TextView.BufferType.SPANNABLE);
        solutionsTextView.setText(activeProblem.getSolutionsString());

        Button button = findViewById(R.id.regex_submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                activeProblem.submitSolution(regexEditText.getText().toString());
                testRegexText(true);
                solutionsTextView.setText(activeProblem.getSolutionsString());
            }
        });
    }
    private void testRegexText(boolean displayError) {
        try {
            targetTextView.setText(activeProblem.getTargetDisplayString(regexEditText.getText().toString()), TextView.BufferType.SPANNABLE);
            rejectTextView.setText(activeProblem.getRejectDisplayString(regexEditText.getText().toString()), TextView.BufferType.SPANNABLE);
        } catch (PatternSyntaxException e) {
            if (displayError) {
                errorTextView.setText(e.getMessage());
            }
        }
    }
}
