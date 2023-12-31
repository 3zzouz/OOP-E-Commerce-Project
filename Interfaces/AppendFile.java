package Interfaces;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This interface is used to append the data to the file.
 */
public interface AppendFile {
    /**
     * Appends the specified object to the file.
     * 
     * @param o the object to be appended
     * @param fString the file path
     */
    public default void appendfile(Object o, String fString) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fString, true));
            writer.write(o.toString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
