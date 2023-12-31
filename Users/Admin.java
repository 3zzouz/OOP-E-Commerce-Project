package Users;

import java.util.Scanner;

public class Admin extends ProductManager {
    protected Admin() {
        super();
        this.permissionLevel = 0;
        users.put(username, this);
        updateUsersLists();
        System.out.println("Admin created successfully");
    }

    // second constructor for Admin class
    protected Admin(String username, String name, String email, String address, String Password, int age,
            int phoneNumber, String accountCreationDate, String lastLoginDate, String isBlocked,
            String isLoggedIn) {

        super(username, name, email, address, Password, age, phoneNumber, accountCreationDate, lastLoginDate, isBlocked,
                isLoggedIn);
        setUsers();
        this.permissionLevel = 0;
        users.put(username, this);
        updateUsersLists();
        System.out.println("Admin created successfully");
    }

    protected void viewAllUsers() {
        setUsers();
        System.out.println("Users : ");
        for (User user : User.users.values()) {
            System.out.println(user.toString());
        }
    }

    protected void viewUser() {
        setUsers();
        System.out.println("Enter the person's username: ");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        sc.close();
        if (!users.containsKey(username)) {
            System.out.println("User not found");
            return;
        }
        User user = users.get(username);
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Address: " + user.getAddress());
        System.out.println("Age: " + user.getAge());
        System.out.println("Phone Number: " + String.valueOf(user.getPhoneNumber()));
        System.out.println("Account Creation Date: " + user.getAccountCreationDate().getSDate());
        System.out.println("Last Login Date: " + user.getLastLoginDate().getSDate());
        System.out.println("Is Blocked: " + Boolean.toString(user.getIsBlocked()));
        System.out.println("Is Logged In: " + Boolean.toString(user.getIsLoggedIn()));
        System.out.println("Permission Level: " + Integer.toString(user.getPermissionLevel()));
    }

    protected void blockUser() {
        setUsers();
        System.out.println("Enter the person's username: (to block)");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        sc.close();
        if (!existUser(username)) {
            System.out.println("User not found");
            return;
        }
        users.get(username).setBlocked(true);
        users.get(username).logout();
    }

    protected void unblockUser() {
        setUsers();
        System.out.println("Enter the person's username: (to unblock)");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        sc.close();
        if (!existUser(username)) {
            System.out.println("User not found");
            return;
        }
        users.get(username).setBlocked(false);
        updateUsersLists();
    }
}
