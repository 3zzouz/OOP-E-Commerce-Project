package Interfaces;

import java.util.ArrayList;

public interface AccountRW {
    final static String fileAccounts = "./Data/Accounts.csv";

    public void writefile(String s,String fString);

    public ArrayList<String> readfile(String fString);
}
