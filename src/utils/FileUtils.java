package utils;

import java.io.*;

public class FileUtils {
    public static String readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader((new FileReader(file)));
        String line = null;
        StringBuilder sb = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append(ls);
            }
            return sb.toString();
        } finally {
            reader.close();
        }
    }

    public static void saveStringToFile(File fileGot, String string, String extension) throws FileNotFoundException {
        String fileName = fileGot.getPath();
        fileName = fileName + extension;
        File file = new File(fileName);
        PrintWriter pw;
        pw = new PrintWriter(file);
        pw.println(string);
        pw.close();
    }
}
