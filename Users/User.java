package Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Exceptions.WrongPasswordException;
import Interfaces.AccountRW;
import Products.Product;
import Security.HashPasswords;
import Utility.DateFormat;

public abstract class User implements AccountRW {
    protected String name, email, address, username;
    protected int age, phoneNumber;
    protected DateFormat accountCreationDate, lastLoginDate;
    protected boolean isBlocked, isLoggedIn;
    protected HashPasswords Password;
    protected int permissionLevel = 2;
    protected static HashMap<String, User> users;

    // constructor for the user class
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
        Date date = new Date();
        this.accountCreationDate = new DateFormat(date);
        this.lastLoginDate = new DateFormat(date);
        setBlocked(false);
        setIsLoggedIn(false);
    }

    // second constructor for the user class
    public User(String username, String name, String email, String address, String Password, int age,
            int phoneNumber,
            String accountCreationDate, String lastLoginDate, String isBlocked, String isLoggedIn) {
        setUsername(username);
        setName(name);
        setEmail(email);
        setAddress(address);
        setPassword(Password);
        setAge(age);
        setPhoneNumber(phoneNumber);
        this.accountCreationDate = new DateFormat(accountCreationDate);
        this.lastLoginDate = new DateFormat(lastLoginDate);
        if (isBlocked.equals("true")) {
            setBlocked(true);
        } else {
            setBlocked(false);
        }
        if (isLoggedIn.equals("true")) {
            setIsLoggedIn(true);
        } else {
            setIsLoggedIn(false);
        }
    }

    // to write the users in the file
    public void updateUsersLists() {
        writeFileAccounts(users);
    }

    // to check if the user existUsers or not by checking the username
    public boolean existUser(String username) {
        setUsers();
        for (User user : users.values()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // an override for the toString method
    public String toString() {
        return this.username + " , " + this.permissionLevel + " , " + this.name + " , " + this.email + " , "
                + this.Password + " , " + this.address
                + " , " + this.age + " , "
                + this.phoneNumber + " , " + this.accountCreationDate.getSDate() + " , " + this.lastLoginDate.getSDate()
                + " , "
                + this.isBlocked + " , "
                + this.isLoggedIn;
    }

    // to synchronize the users in the file with the users in the program
    protected void setUsers() {
        ArrayList<String> usersList = readfileAccounts();
        users.clear();
        for (String user : usersList) {
            String[] userInformation = user.split(" , ");
            if (userInformation[1].equals("0")) {
                users.put(userInformation[0],
                        new Admin(userInformation[0], userInformation[2], userInformation[3], userInformation[5],
                                userInformation[4], Integer.parseInt(userInformation[6]),
                                Integer.parseInt(userInformation[7]), userInformation[8], userInformation[9],
                                userInformation[10], userInformation[11]));
            } else if (userInformation[1].equals("2")) {
                users.put(userInformation[0],
                        new Customer(userInformation[0], userInformation[2], userInformation[3], userInformation[5],
                                userInformation[4], Integer.parseInt(userInformation[6]),
                                Integer.parseInt(userInformation[7]), userInformation[8], userInformation[9],
                                userInformation[10], userInformation[11]));
            } else if (userInformation[1].equals("3")) {
                users.put(userInformation[0],
                        new ProductManager(userInformation[0], userInformation[2], userInformation[3],
                                userInformation[5],
                                userInformation[4], Integer.parseInt(userInformation[6]),
                                Integer.parseInt(userInformation[7]), userInformation[8], userInformation[9],
                                userInformation[10], userInformation[11]));
            }
        }
    }

    // login function
    public void login(String Password) {
        setUsers();
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
                setLastLoginDate(new DateFormat(new Date()));
                users.put(this.username, this);
                updateUsersLists();
                System.out.println("You are now logged in. Welcome " + this.name + "!");
            } else {
                throw new WrongPasswordException();
            }
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
        }
    }

    // logout function
    public void logout() {
        if (this.isLoggedIn == false) {
            System.out.println("You are already logged out.");
        } else {
            this.isLoggedIn = false;
            users.put(this.username, this);
            updateUsersLists();
        }
    }

    // change password function
    public void changePassword(String oldPassword, String newPassword) {
        setUsers();
        try {
            if (this.Password.verifyPassword(oldPassword)) {
                this.Password = new HashPasswords(newPassword);
                users.put(this.username, this);
                updateUsersLists();
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

        List<Product> results = ProductManager.searchProducts(productName, filtres);
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
