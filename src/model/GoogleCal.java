package model;

public class GoogleCal {
    public static final String DELIMITER = ",";
    private String subject;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String desc;
    private String location;

    public GoogleCal(String subject, String startDate, String endDate, String startTime, String endTime, String desc, String location) {
        this.subject = subject;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.desc = desc;
        this.location = location;
    }

    public GoogleCal() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return subject + DELIMITER + startDate +
                DELIMITER + startTime + DELIMITER +
                endDate + DELIMITER +
                endTime + DELIMITER + "false" +
                DELIMITER + desc + DELIMITER +
                location + DELIMITER + "false" + System.lineSeparator();
    }
}
