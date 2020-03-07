package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SubjectsGCal extends ArrayList<GoogleCal> {
    private String lines;
    private Set<String> subjects;
    private static String[] googleCalColumns = {"Subject", "Start Date", "End Date","Start Time", "End Time","Description", "Location"};

    public SubjectsGCal() {
        subjects = new HashSet<>();
    }

    public Set<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<String> subjects) {
        this.subjects = subjects;
    }

    public String getLines() {
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < googleCalColumns.length -1 ; i++) {
            sb.append(googleCalColumns[i]);
            sb.append(GoogleCal.DELIMITER);
        }
        sb.append(googleCalColumns[i++]);
        sb.append(System.lineSeparator());
        for (int j = 0; j < size(); j++) {
            GoogleCal gEvent = get(j);
            if (subjects.contains(gEvent.getSubject())) {
                sb.append(gEvent.toString());
            }
        }
        lines = sb.toString();
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }
}
