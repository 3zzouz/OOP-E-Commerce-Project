package Users;

import java.util.Scanner;

public class Admin extends ProductManager {
    public Admin() {
        super();
        this.permissionLevel = 0;
        users.put(username, this);

        System.out.println("Admin created successfully");
    }

    public Admin(String name, String email, String address, String username, int age, int phoneNumber,
            String password) {
        super(name, email, address, username, age, phoneNumber, password);
        this.permissionLevel = 0;
    }

    public void viewAllUsers() {

        System.out.println("Users : ");
        for (User user : User.users.values()) {
            System.out.println(user.toString());
        }
    }

    public void viewUser() {

        System.out.println("Enter the person's username: ");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        ;
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

    public void blockUser() {

        System.out.println("Enter the person's username: (to block)");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        ;
        if (!existUser(username)) {
            System.out.println("User not found");
            return;
        }
        users.get(username).setBlocked(true);
        users.get(username).logout();
    }

    public void unblockUser() {

        System.out.println("Enter the person's username: (to unblock)");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        ;
        if (!existUser(username)) {
            System.out.println("User not found");
            return;
        }
        users.get(username).setBlocked(false);

    }
}
