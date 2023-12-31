package Interfaces;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The AccountRW interface represents the functionality for reading from and
 * writing to an accounts file.
 * It extends the AppendFile, WriteFile, and ReadFile interfaces.
 */
public interface AccountRW extends AppendFile, WriteFile, ReadFile {
    final static String fileAccounts = "./Data/Accounts.csv";

    /**
     * Appends an object to the accounts file.
     * 
     * @param o the object to be appended
     */
    public default void appendFileAccounts(Object o) {
        appendfile(o, fileAccounts);
    }

    /**
     * Writes a HashMap of objects to the accounts file.
     * 
     * @param h the HashMap containing the objects to be written
     */
    public default void writeFileAccounts(HashMap<String, ? extends Object> h) {
        writefile(h, fileAccounts);
    }

    /**
     * Reads the accounts file and returns the contents as an ArrayList of strings.
     * 
     * @return the contents of the accounts file as an ArrayList of strings
     */
    public default ArrayList<String> readfileAccounts() {
        return readfile(fileAccounts);
    }
}
