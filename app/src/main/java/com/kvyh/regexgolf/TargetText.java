package com.kvyh.regexgolf;

import android.graphics.Color;
import android.text.style.BackgroundColorSpan;

public class TargetText extends TextLines {
    TargetText(String line_break_lines) {
        super(line_break_lines);
        this.background = Color.GREEN;
    }
}
