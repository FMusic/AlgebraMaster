package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Subjects extends ArrayList<GoogleCal> {
    private String lines;
    private HashMap<Integer, String> subjects;

    public HashMap<Integer, String> getSubjects() {
        return subjects;
    }

    public void setSubjects(HashMap<Integer, String> subjects) {
        this.subjects = subjects;
    }

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }
}
