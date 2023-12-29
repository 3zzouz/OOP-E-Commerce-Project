package Interfaces;

import java.util.ArrayList;

public interface ProductRW {
    final static String fileProducts = "./Data/Products.csv";

    public ArrayList<String> readfile(String fString);

    public void writefile(String s, String fString);
}
