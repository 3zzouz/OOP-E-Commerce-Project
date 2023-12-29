package Users;

import java.util.Scanner;

public class Admin extends ProductManager {
    protected Admin() {
        this.permissionLevel = 0;
    }

    protected void viewAllUsers() {
        setUsers();
        System.out.println("Users : ");
        for (String user : User.users) {
            System.out.println(user);
        }
    }

    protected void viewUser() {
        setUsers();
        System.out.println("Enter the person's username: ");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        for (String user : users) {
            String[] userInformation = user.split(" , ");
            if (userInformation[4].equals(username)) {
                System.out.println("Name: " + userInformation[0]);
                System.out.println("Email: " + userInformation[1]);
                System.out.println("Address: " + userInformation[3]);
                System.out.println("Age: " + userInformation[5]);
                System.out.println("Phone Number: " + userInformation[6]);
                System.out.println("Account Creation Date: " + userInformation[7]);
                System.out.println("Last Login Date: " + userInformation[8]);
                System.out.println("Is Blocked: " + userInformation[9]);
                System.out.println("Is Logged In: " + userInformation[10]);
                System.out.println("Permission Level: " + userInformation[11]);
                break;
            }
        }
        sc.close();
    }

    protected void blockUser() {
        setUsers();
        System.out.println("Enter the person's username: ");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        for (String user : users) {
            int pos = users.indexOf(user);
            String[] userInformation = user.split(" , ");
            if (userInformation[4].equals(username)) {
                userInformation[9] = "true";
                user = "";
                for (int i = 0; i < 12; i++) {
                    user += userInformation[i] + " , ";
                }
                user = user.substring(0, user.length() - 3);
                users.set(pos, user);
                break;
            }
        }
        sc.close();
        updateUsersLists();
    }

    protected void unblockUser() {
        setUsers();
        System.out.println("Enter the person's username: ");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        for (String user : users) {
            String[] userInformation = user.split(" , ");
            if (userInformation[4].equals(username)) {
                userInformation[9] = "false";
                // manque la modification du fichier
                break;
            }
        }
        sc.close();
        updateUsersLists();
    }
}
