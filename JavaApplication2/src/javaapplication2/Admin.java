
package javaapplication2;
import java.util.ArrayList;

class Admin extends User {
    private ArrayList<User> users;

    public Admin(String username, String password) {
        super(username, password);
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user.username);
    }

    public void viewUsers() {
        System.out.println("Registered Users:");
        for (User user : users) {
            System.out.println("- " + user.username);
        }
    }
}
