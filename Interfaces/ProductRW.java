package Interfaces;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The ProductRW interface represents a contract for reading and writing product
 * data.
 * It extends the ReadFile and WriteFile interfaces.
 */
/**
 * This interface represents a product read/write functionality.
 * It extends the ReadFile, WriteFile, and AppendFile interfaces.
 */
public interface ProductRW extends ReadFile, WriteFile, AppendFile {
    final static String fileProducts = "./Data/Products.csv";

    /**
     * Reads the product data from a file.
     * 
     * @return an ArrayList of strings representing the lines read from the file
     */
    public default ArrayList<String> readFileProducts() {
        return readfile(fileProducts);
    }

    /**
     * Writes the product data to a file.
     * 
     * @param s       the data to be written, represented as a HashMap
     * @param fString the file path where the data should be written
     */
    public default void writeFileProducts(HashMap<String, ? extends Object> s) {
        writefile(s, fileProducts);
    }

    /**
     * Appends the product data to a file.
     * 
     * @param o the data to be appended
     */
    public default void appendFileProducts(Object o) {
        appendfile(o, fileProducts);
    }
}
