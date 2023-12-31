package Interfaces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * The ReadFile interface provides a method to read the contents of a file.
 */
public interface ReadFile {
    /**
     * Reads the contents of a file and returns them as a list of strings.
     *
     * @param fString the path of the file to be read
     * @return an ArrayList containing the lines of the file, or null if an error occurs
     */
    public default ArrayList<String> readfile(String fString) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fString));
            ArrayList<String> list = new ArrayList<String>();
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            reader.close();
            return list;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
