package controller;

import model.AlgebraEvent;
import model.GoogleCal;
import model.SubjectsGCal;

import java.util.ArrayList;
import java.util.List;

public class CsvController {
    public static SubjectsGCal getGoogleFromAlgebra(String algebracsv){
        List<AlgebraEvent> algEvents = getAlgebraEvents(algebracsv);
        List<GoogleCal> googleEvents = getGoogleFromAlgList(algEvents);
        SubjectsGCal subs = new SubjectsGCal();
        for (int i = 0; i < googleEvents.size(); i++) {
            subs.add(googleEvents.get(i));
            subs.getSubjects().add(googleEvents.get(i).getSubject());
        }
        return subs;
    }

    private static List<GoogleCal> getGoogleFromAlgList(List<AlgebraEvent> algEvents) {
        List<GoogleCal> google = new ArrayList<>();
        algEvents.forEach(x->{
            GoogleCal gEvent = new GoogleCal();
            gEvent.setSubject(x.getStatus() + " " + x.getSubject());
            gEvent.setDesc(x.getPlace() + " - " + x.getLecturer());
            gEvent.setStartDate(x.getDate());
            gEvent.setEndDate(x.getDate());
            gEvent.setDesc(x.getPlace() + " - " + x.getLecturer());
            gEvent.setLocation("Algebra University College, Ilica 242, 10000, Zagreb, Croatia");
            gEvent.setStartTime(x.getTimeFrom());
            gEvent.setEndTime(x.getTimeTo());
            google.add(gEvent);
        });
        return google;
    }

    private static List<AlgebraEvent> getAlgebraEvents(String csvAlg) {
        //We're throwing away first line because we don't need it
        List<AlgebraEvent> algEvents = new ArrayList<>();
        String[] schLines = csvAlg.split(System.lineSeparator());
        for (int i = 1; i < schLines.length; i++) {
            AlgebraEvent ae = new AlgebraEvent();
            String[] line = schLines[i].split(AlgebraEvent.DELIMITER);
            ae.setDate(line[0]);
            ae.setTimeFrom(line[1].split("-")[0]);
            ae.setTimeTo(line[1].split("-")[1]);
            ae.setSubject(line[2]);
            ae.setLecturer(line[3]);
            ae.setStatus(line[4]);
            ae.setPlace(line[5]);
            algEvents.add(ae);
        }
        return algEvents;
    }
}
