package utils;


import java.io.File;
import java.io.IOException;

public class CsvAlgebraImporter {
    /*
    prve tri linije u raspored.csv koji se sada izdaje na infoeduci
    Datum;Termin;Predmet;Nastavnik;Status;Dvorana
    2019-09-30;18:45-20:15;Upravljanje informacijskim sustavima;Bariiæ, Renato;Predavanje;Lecture room Ivan Vuèetiæ
    2019-09-30;20:30-22:00;Sustavi potpore odluèivanju;Klepac, Goran;Predavanje;Lecture room Ivan Vuèetiæ

    U google cal, stupci su:
    Subject, StartDate, StartTime, EndDate, EndTime, AllDayEvent(true/false vrijednost), Description, Location, Private(true/false)
    */
    /*
    Subject - Status + Predmet
    Start Date, End Date - Datum
    Start Time - prvi dio termina
    End Time - drugi dio termina
    Description - Dvorana + \n + Nastavnik
    Location - Ilica 222, Zagreb, Croatia
     */
    private static String OLD_DELIMITER = ";";
    private static String NEW_DELIMITER = ",";

    private static String[] googleCalColumns = {"Subject", "Start Date", "End Date","Start Time", "End Time","Description", "Location"};

    public static String getNewSchedule(String oldSchedule){
        StringBuilder sb = new StringBuilder();
        String[] firstLine;

        String[] scheduleLines = oldSchedule.split(System.lineSeparator());
        firstLine = scheduleLines[0].split(OLD_DELIMITER);
        for (int i = 0; i < googleCalColumns.length; i++) {
            if(i!= googleCalColumns.length-1){
                sb.append(googleCalColumns[i]);
                sb.append(NEW_DELIMITER);
            }else {
                sb.append(googleCalColumns[i]);
            }
        }
        sb.append(System.lineSeparator());
        for (int i = 1; i < scheduleLines.length; i++) {
            String[] line = scheduleLines[i].split(OLD_DELIMITER);
            String[] times = line[1].split("-");
            sb.append(line[4]);
            sb.append(" - ");
            sb.append(line[2]);
            sb.append(NEW_DELIMITER);
            //dateStart
            sb.append(line[0]);
            sb.append(NEW_DELIMITER);
            //dateEnd
            sb.append(line[0]);
            sb.append(NEW_DELIMITER);
            sb.append(times[0]);
            sb.append(NEW_DELIMITER);
            sb.append(times[1]);
            sb.append(NEW_DELIMITER);
            sb.append(line[5]);
            sb.append(" - ");
            sb.append(line[3].replaceAll(",", " "));
            sb.append(NEW_DELIMITER);
            sb.append("Ilica 222");
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static String getNewScheduleFile(File oldFile) throws IOException {
        String string = FileUtils.readFile(oldFile);
        String newSch = getNewSchedule(string);
        return newSch;
    }
}
