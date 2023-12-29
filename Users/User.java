package Users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Exceptions.WrongPasswordException;
import Interfaces.AccountRW;
import Security.HashPasswords;

public abstract class User implements AccountRW {
    protected static int idCounter = 0;
    protected String name, email, address, username;
    protected int age, phoneNumber;
    protected Date accountCreationDate, lastLoginDate;
    protected boolean isBlocked, isLoggedIn;
    protected HashPasswords Password;
    protected int permissionLevel = 2;
    protected static ArrayList<String> users;

    public User() {
        setUsers();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter desired UserName: ");
        String username = sc.nextLine();
        while (existUser(username)) {
            System.out.println("Username already existUsers. Please enter another username: ");
            username = sc.nextLine();
        }
        setUsername(username);
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        setName(name);
        System.out.println("Enter your email: ");
        String email = sc.nextLine();
        setEmail(email);
        System.out.println("Enter your age: ");
        int age = sc.nextInt();
        setAge(age);
        System.out.println("Enter your phone number: ");
        int phoneNumber = sc.nextInt();
        setPhoneNumber(phoneNumber);
        System.out.println("Enter your address : ");
        address = sc.nextLine();
        setAddress(address);

        System.out.println("Enter your Password: ");
        String Password = sc.nextLine();
        System.out.println("Confirm Password : ");
        String confirmPassword = sc.nextLine();
        while (!confirmPassword.equals(Password)) {
            System.out.println("Passwords do not match. Please enter your Password again: ");
            Password = sc.nextLine();
            System.out.println("Confirm Password : ");
            confirmPassword = sc.nextLine();
        }
        setPassword(Password);
        sc.close();
        this.accountCreationDate = new Date();
        setBlocked(false);

        String res = this.name + " , " + this.email + " , " + this.Password + " , " + this.address + " , "
                + this.username + " , " + this.age + " , "
                + this.phoneNumber + " , " + this.accountCreationDate + " , " + this.accountCreationDate + " , "
                + "false" + " , "
                + this.isLoggedIn + " , " + this.permissionLevel;
        writefile(res, AccountRW.fileAccounts);
        users.add(res);
        System.out.println("Account Created Successfully");
        login(Password);
    }

    public void updateUsersLists() {
        if (permissionLevel > 0) {
            System.out.println("You do not have permission to update users lists.");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(AccountRW.fileAccounts));
            for (String user : users) {
                writer.write(user);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean existUser(String username) {
        setUsers();
        for (String user : users) {
            String[] userInformation = user.split(" , ");
            if (userInformation[4].equals(username)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> readfile(String fString) {
        String line;
        ArrayList<String> res = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fString));
            while ((line = reader.readLine()) != null) {
                res.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public void writefile(String s, String fString) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fString, true));
            writer.write(s);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void setUsers() {
        users = readfile(AccountRW.fileAccounts);
    }

    public void login(String Password) {
        setUsers();
        for (String user : users) {
            String[] userInformation = user.split(" , ");
            if (userInformation[4].equals(this.username)) {
                if (userInformation[9].equals("true")) {
                    this.isBlocked = true;
                } else {
                    this.isBlocked = false;
                }
                if (userInformation[10].equals("true")) {
                    this.isLoggedIn = true;
                } else {
                    this.isLoggedIn = false;
                }
            }
        }
        if (this.isBlocked == true) {
            System.out.println("Your account is blocked. Please contact an admin to unblock it.");
            return;
        }
        if (this.isLoggedIn == true) {
            System.out.println("You are already logged in.");
            return;
        }
        try {
            if (this.Password.verifyPassword(Password)) {
                this.isLoggedIn = true;
                setLastLoginDate(new Date());
                System.out.println("You are now logged in. Welcome " + this.name + "!");
            } else {
                throw new WrongPasswordException();
            }
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
        }
    }

    public void logout() {
        if (this.isLoggedIn == false) {
            System.out.println("You are already logged out.");
        } else {
            this.isLoggedIn = false;
        }
    }

    public void changePassword(String oldPassword, String newPassword) {
        try {
            if (this.Password.verifyPassword(oldPassword)) {
                this.Password = new HashPasswords(newPassword);
            } else {
                throw new WrongPasswordException();
            }
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
        }
    }

    protected String getName() {
        return name;
    }

    protected String getEmail() {
        return email;
    }

    protected String gethPassword() {
        return Password.getHashedPassword();
    }

    protected String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    protected int getAge() {
        return age;
    }

    protected int getPhoenNumber() {
        return phoneNumber;
    }

    protected Date getAccountCreationDate() {
        return accountCreationDate;
    }

    protected Date getLastLoginDate() {
        return lastLoginDate;
    }

    protected boolean isBlocked() {
        return isBlocked;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    protected void setName(String name) {
        if (name.length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters long.");
        }
        this.name = name;
    }

    protected void setPassword(String Password) {
        if (Password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        this.Password = new HashPasswords(Password);
    }

    protected void setAddress(String address) {
        if (address.length() < 10) {
            throw new IllegalArgumentException("Address must be at least 10 characters long.");
        }
        this.address = address;
    }

    protected void setAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be at least 18.");
        }
        this.age = age;
    }

    protected void setPhoneNumber(int phoenNumber) {
        if (String.valueOf(phoenNumber).length() != 8 || !isAllDigits(String.valueOf(phoenNumber))) {
            throw new IllegalArgumentException("Phone number must be 8 digits long.");
        }
        this.phoneNumber = phoenNumber;
    }

    protected boolean isAllDigits(String str) {
        return str.matches("\\d+");
    }

    protected void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    protected void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    protected void setEmail(String email) {
        if (!email.contains("@") || !email.contains(".") || email.length() < 5) {
            throw new IllegalArgumentException("Email must be a valid email address.");
        }
        this.email = email;
    }

    protected void setUsername(String username) {
        if (username.length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters long.");
        }
        this.username = username;
    };

    public void searchProduct() {
        // i am going to use the method searchProducts from ProductManager
        // ask the user to give the information about the product he wants to search
        // then call the method searchProducts from ProductManager
        System.out.println("Enter the name of the product you want to search : ");
        Scanner sc = new Scanner(System.in);
        String productName = sc.nextLine();
        System.out.println("Enter the quantity of the product you want to search : (0 to ignore quantity)");
        int quantity = sc.nextInt();
        System.out.println(
                "Enter the min and max price of the product you want to search : (to search for a particular price enter the same min and max and if there is no min or max enter 0 for the non desired value)");
        System.out.println("Min price : ");
        double minPrice = sc.nextDouble();
        System.out.println("Max price : ");
        double maxPrice = sc.nextDouble();
        sc.close();
        if (minPrice > maxPrice) {
            System.out.println("Invalid input");
            return;
        }
        Map<String, Object> filtres = new HashMap<>();
        filtres.put("price", new Double[] { minPrice, maxPrice });
        filtres.put("quantity", quantity);

        List<String> results = ProductManager.searchProducts(productName, filtres);
        if (results.size() == 0) {
            System.out.println("No results found");
        } else {
            System.out.println("Results : ");
            for (String result : results) {
                System.out.println(result);
            }
        }
    };
}
