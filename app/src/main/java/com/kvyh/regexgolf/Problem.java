package com.kvyh.regexgolf;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableString;
import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

import javax.xml.transform.Source;

@Entity(tableName = "problems")
public class Problem implements Parcelable {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "difficulty")
    private int difficulty;
    @ColumnInfo(name = "shortest")
    private int shortest;
    @ColumnInfo(name = "target")
    private String targetString;
    @ColumnInfo(name = "reject")
    private String rejectString;
    @ColumnInfo(name = "source")
    private String source;
    @Ignore
    private TargetText targetText;
    @Ignore
    private RejectText rejectText;

    Problem(int id, String title, int difficulty, int shortest,
            String targetString, String rejectString, String source) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.shortest = shortest;
        this.targetString = targetString;
        this.rejectString = rejectString;
        this.targetText = new TargetText(targetString);
        this.rejectText = new RejectText(rejectString);
        this.source = source;
    }

    public int getId() { return id; }

    public String getTitle() {
        return title;
    }

    public String getTargetString() {
        return targetString;
    }

    public TargetText getTargetText() {
        return targetText;
    }

    public SpannableString getTargetDisplayString(String regex) { return targetText.getDisplayString(regex); }

    public String getRejectString() {
        return rejectString;
    }

    public RejectText getRejectText() {
        return rejectText;
    }

    public SpannableString getRejectDisplayString(String regex) { return rejectText.getDisplayString(regex); }

    public int getDifficulty() { return difficulty; }

    public int getShortest() { return shortest; }

    public String getSource() { return source; }

    public String getInfo() {
        return "Difficulty: " + difficulty
                + " Shortest known solution: " + shortest
                + " Source:" + source;
    }
    // Add problem to database if not already there
    public int addToDB() {
        List<Problem> check = MainActivity.database.problemDao().getAllProblems();
        for (int i = 0; i < check.size(); i += 1) {
            if (check.get(i).getTargetString().equals(targetString)
                && check.get(i).getRejectString().equals(rejectString)) {
                return -1;
            }
        }
        MainActivity.database.problemDao().recordProblem(
                title, difficulty, shortest, targetString, rejectString, source
        );
        return 0;
    }


    // Make this parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeInt(difficulty);
        dest.writeInt(shortest);
        dest.writeString(targetString);
        dest.writeString(rejectString);
        dest.writeString(source);
    }

    public static final Creator<Problem> CREATOR = new Creator<Problem>() {
        @Override
        public Problem createFromParcel(Parcel in) {
            return new Problem(in);
        }

        @Override
        public Problem[] newArray(int size) {
            return new Problem[size];
        }
    };

    protected Problem(Parcel in) {
        id = in.readInt();
        title = in.readString();
        difficulty = in.readInt();
        shortest = in.readInt();
        targetText = new TargetText(in.readString());
        rejectText = new RejectText(in.readString());
        source = in.readString();
    }
}
