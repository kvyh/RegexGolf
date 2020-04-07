package com.kvyh.regexgolf;

import android.graphics.Color;
import android.text.style.BackgroundColorSpan;

public class RejectText extends TextLines {
    RejectText(String line_break_lines) {
        super(line_break_lines);
        this.background = Color.MAGENTA;
    }
}
