package model;

import java.util.Date;

public class AlgebraEvent {
    public static String DELIMITER = ";";
    private String date;
    private String timeFrom;
    private String timeTo;
    private String subject;
    private String lecturer;
    private String status;
    private String place;

    public AlgebraEvent(String date, String timeFrom, String timeTo, String subject, String lecturer, String status, String place) {
        this.date = date;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.subject = subject;
        this.lecturer = lecturer;
        this.status = status;
        this.place = place;
    }

    public AlgebraEvent() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
