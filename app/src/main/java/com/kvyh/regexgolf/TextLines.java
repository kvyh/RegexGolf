package com.kvyh.regexgolf;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextLines {
    private String stringFormat;
    private List<String> lines;
    private List<Boolean> selected;
    private SpannableString displayString;
    public Integer background;
    public List<CharacterStyle> highlights;

    TextLines(String line_break_lines) {
        this.stringFormat = line_break_lines;
        this.displayString = new SpannableString(line_break_lines);
        this.lines = make_list(line_break_lines);
        this.selected = new ArrayList<>();
        for (int i = 0; i < lines.size(); i += 1) {
            this.selected.add(false);
        }
        this.background = Color.GREEN;
        this.highlights = new ArrayList<>();
    }

    private List<String> make_list(String multiline) {
        String str[] = multiline.split("\n");
        List<String> by_line = new ArrayList<String>();
        by_line = Arrays.asList(str);
        return by_line;
    }

    public SpannableString getDisplayString(String regex) {
        performRegex(regex);
        // Remove all in-place spans
        for (int i = 0; i < highlights.size(); i += 1) {
            displayString.removeSpan(highlights.get(i));
        }
        highlights.clear();
        // Add a highlight anywhere the regex matches
        Pattern p = Pattern.compile(regex);
        int line_start = 0;
        for (int i = 0; i < lines.size(); i += 1) {
            Matcher matcher = p.matcher(lines.get(i));
            boolean found = false;
            // Underline and bold matched text
            while (matcher.find()) {
                found = true;
                highlights.add(new UnderlineSpan());
                displayString.setSpan(highlights.get(highlights.size()-1),
                        line_start + matcher.start(), line_start + matcher.end(), 0);
                highlights.add(new StyleSpan(Typeface.BOLD));
                displayString.setSpan(highlights.get(highlights.size()-1),
                        line_start + matcher.start(), line_start + matcher.end(), 0);
            }
            // Highlight line that is selected
            if (found) {
                highlights.add(new BackgroundColorSpan(background));
                displayString.setSpan(highlights.get(highlights.size()-1),
                        line_start, line_start + lines.get(i).length(), 0);
            }
            // Move the line_start to the start of the next line
            line_start += lines.get(i).length() + 1;
        }
        Matcher matcher = p.matcher(stringFormat);
        while (matcher.find()) {
            highlights.add(new BackgroundColorSpan(background));
            displayString.setSpan(highlights.get(highlights.size()-1), matcher.start(), matcher.end(), 0);
        }
        return displayString;
    }

    public String getString() {
        return stringFormat;
    }

    public List<String> getLines() {
        return lines;
    }

    public void performRegex(String regex) {
        Pattern p = Pattern.compile(regex);
        selected.clear();
        for (int i = 0; i < lines.size(); i += 1) {
            Matcher m = p.matcher(lines.get(i));
            if (m.find()) {
                Log.i("kvyhPerformRegex", "selected line: " + lines.get(i));
                selected.add(true);
            }
            else {
                Log.i("kvyhPerformRegex", "Didn't select line: " + lines.get(i));
                selected.add(false);
            }
        }
    }
}
