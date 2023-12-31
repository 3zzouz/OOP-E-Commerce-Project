package Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Exceptions.WrongPasswordException;
import Products.Product;
import Security.HashPasswords;
import Utility.DateFormat;

public abstract class User {
    protected String name, email, address, username;
    protected int age, phoneNumber;
    protected DateFormat accountCreationDate, lastLoginDate;
    protected boolean isBlocked, isLoggedIn;
    protected HashPasswords Password;
    protected int permissionLevel = 2;
    public static HashMap<String, User> users;

    // constructor for the user class
    public User() {

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
        sc.nextLine();
        setAge(age);
        System.out.println("Enter your phone number: ");
        int phoneNumber = sc.nextInt();
        sc.nextLine();
        setPhoneNumber(phoneNumber);
        System.out.println("Enter your address : ");
        String addres = sc.nextLine();
        setAddress(addres);
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
        ;
        Date date = new Date();
        this.accountCreationDate = new DateFormat(date);
        this.lastLoginDate = new DateFormat(date);
        setBlocked(false);
        setIsLoggedIn(false);
    }

    // add paramaterized constructor
    public User(String name, String email, String address, String username, int age, int phoneNumber, String Password) {
        setName(name);
        setEmail(email);
        setAddress(address);
        setUsername(username);
        setAge(age);
        setPhoneNumber(phoneNumber);
        setPassword(Password);
        Date date = new Date();
        this.accountCreationDate = new DateFormat(date);
        this.lastLoginDate = new DateFormat(date);
        setBlocked(false);
        setIsLoggedIn(false);
    }

    // to check if the user existUsers or not by checking the username
    public static boolean existUser(String username) {

        return users.containsKey(username);
    }

    // an override for the toString method
    public String toString() {
        return this.username + " , " + this.permissionLevel + " , " + this.name + " , " + this.email + " , "
                + this.Password.getHashedPassword() + " , " + this.address
                + " , " + this.age + " , "
                + this.phoneNumber + " , " + this.accountCreationDate.getSDate() + " , " + this.lastLoginDate.getSDate()
                + " , "
                + this.isBlocked + " , "
                + this.isLoggedIn;
    }

    // login function
    public void login(String Password) throws WrongPasswordException {

        if (this.isBlocked == true) {
            System.out.println("Your account is blocked. Please contact an admin to unblock it.");
            return;
        }
        if (this.isLoggedIn == true) {
            System.out.println("You are already logged in.");
            return;
        }

        if (this.Password.verifyPassword(Password) == true) {
            this.isLoggedIn = true;
            setLastLoginDate(new DateFormat(new Date()));
            users.put(this.username, this);
        } else {
            throw new WrongPasswordException();
        }
    }

    public static User login(String username, String Password) throws WrongPasswordException {
        if (!users.containsKey(username)) {
            System.out.println("User not found");
            return null;
        }
        User user = users.get(username);
        user.login(Password);
        System.out.println("You are now logged in. Welcome " + user.name + "!");
        return user;
    }

    // logout function
    public void logout() {
        if (this.isLoggedIn == false) {
            System.out.println("You are already logged out.");
        } else {
            this.isLoggedIn = false;
            users.put(this.username, this);
        }
    }

    public static void logout(String username) {
        if (!users.containsKey(username)) {
            System.out.println("User not found");
            return;
        }
        User user = users.get(username);
        user.logout();
    }

    // change password function
    public static void changePassword(String username, String oldPassword, String newPassword) {

        try {
            User user = users.get(username);
            if (!existUser(username)) {
                System.out.println("User not found");
                return;
            }
            if (user.Password.verifyPassword(oldPassword)) {
                user.setPassword(newPassword);
                users.put(username, user);
                System.out.println("Password changed successfully");

            } else {
                throw new WrongPasswordException();
            }
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * this method to search for a product (using filters)
     */
    public static void searchProduct() {
        // i am going to use the method searchProducts from ProductManager
        // ask the user to give the information about the product he wants to search
        // then call the method searchProducts from ProductManager
        System.out.println("Enter the name of the product you want to search : ");
        Scanner sc = new Scanner(System.in);
        String productName = sc.nextLine();
        System.out.println("Enter the minimum quantity of the product you want to search : (0 to ignore quantity)");
        int quantity = sc.nextInt();
        sc.nextLine();
        System.out.println(
                "Enter the min and max price of the product you want to search : (to search for a particular price enter the same min and max and if there is no min or max enter 0 for the non desired value)");
        System.out.println("Min price : ");
        double minPrice = sc.nextDouble();
        System.out.println("Max price : ");
        double maxPrice = sc.nextDouble();
        ;
        if (minPrice > maxPrice) {
            System.out.println("Invalid input");
            return;
        }
        Map<String, Object> filtres = new HashMap<>();
        filtres.put("price", new Double[] { minPrice, maxPrice });
        filtres.put("quantity", quantity);

        ArrayList<Product> results = ProductManager.searchProducts(productName, filtres);
        if (results.size() == 0) {
            System.out.println("No results found");
        } else {
            System.out.println("Results : ");
            for (Product result : results) {
                System.out.println(result.toString());
            }
        }
    }

    // getters
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

    protected DateFormat getAccountCreationDate() {
        return accountCreationDate;
    }

    protected DateFormat getLastLoginDate() {
        return lastLoginDate;
    }

    protected boolean isBlocked() {
        return isBlocked;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    };

    public boolean getIsBlocked() {
        return this.isBlocked;
    };

    public boolean getIsLoggedIn() {
        return this.isLoggedIn;
    }

    public int getPermissionLevel() {
        return this.permissionLevel;
    };

    // setters
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

    protected void setLastLoginDate(DateFormat dateFormat) {
        this.lastLoginDate = dateFormat;
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

    protected void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

}
