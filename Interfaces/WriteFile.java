package Interfaces;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * This interface is used to overwrite the data to the file.
 */
public interface WriteFile {
    /**
     * Writes the data from the given HashMap to the specified file.
     * Each key-value pair in the HashMap is written as a separate line in the file.
     *
     * @param hmap    the HashMap containing the data to be written
     * @param fString the file path where the data will be written
     */
    public default void writefile(HashMap<String, ? extends Object> hmap, String fString) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fString));
            for (String key : hmap.keySet()) {
                writer.write(hmap.get(key).toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
