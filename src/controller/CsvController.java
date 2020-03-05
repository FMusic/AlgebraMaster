package controller;

import model.AlgebraEvent;
import model.GoogleCal;
import model.Subject;
import model.Subjects;

import java.util.ArrayList;
import java.util.List;

public class CsvController {
    public static Subjects getGoogleFromAlgebra(String algebracsv){
        List<AlgebraEvent> algEvents = getAlgebraEvents(algebracsv);
        List<GoogleCal> googleEvents = getGoogleFromAlgList(algEvents);
        StringBuilder sb = new StringBuilder();
        googleEvents.forEach(x->{
            sb.append(x.toString());
        });
        Subjects subs = new Subjects();
        final int[] counter = {0};
        googleEvents.forEach(x->{
            subs.add(x);
            subs.getSubjects().put(counter[0]++, x.getSubject());
        });
        subs.setLines(sb.toString());
        return subs;
    }

    private static List<GoogleCal> getGoogleFromAlgList(List<AlgebraEvent> algEvents) {
        List<GoogleCal> google = new ArrayList<>();
        algEvents.forEach(x->{
            GoogleCal gEvent = new GoogleCal();

            gEvent.setStartDate(x.getDate().split("-")[0]);
            gEvent.setEndDate(x.getDate().split("-")[1]);
            gEvent.setDesc(x.getPlace() + System.lineSeparator() + x.getLecturer());
            gEvent.setLocation("Algebra University College, Ilica 242, 10000, Zagreb, Croatia");

            google.add(gEvent);
        });
        return google;
    }

    private static List<AlgebraEvent> getAlgebraEvents(String csvAlg) {
        List<AlgebraEvent> algEvents = new ArrayList<>();
        String[] schLines = csvAlg.split(System.lineSeparator());
        for (int i = 0; i < schLines.length; i++) {
            AlgebraEvent ae = new AlgebraEvent();
            String[] line = schLines[i].split(AlgebraEvent.DELIMITER);
            ae.setDate(line[0]);
            ae.setTimeFrom(line[1].split("-")[0]);
            ae.setTimeTo(line[1].split("-")[1]);
            ae.setSubject(line[2]);
            ae.setLecturer(line[3]);
            ae.setStatus(line[4]);
            ae.setPlace(line[5]);
        }
        return algEvents;
    }
}
