package javaapplication2;

import java.util.Scanner;

public class JavaApplication2 {

    private static final Scanner scanner = new Scanner(System.in); // Single Scanner for the entire program

    public static void main(String[] args) {
        Admin admin = new Admin("admin", "admin");
        Company company = new Company("com", "com");
        Supermarket supermarket = new Supermarket("sup", "sup");

        admin.addUser(company);
        admin.addUser(supermarket);

        while (true) {
            System.out.println("\nLogin as:");
            System.out.println("1. Admin");
            System.out.println("2. Company");
            System.out.println("3. Supermarket");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    adminDashboard(admin);
                    break;
                case 2:
                    userDashboard(company);
                    break;
                case 3:
                    userDashboard(supermarket);
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void adminDashboard(Admin admin) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (admin.authenticate(username, password)) {
            while (true) {
                System.out.println("\nWelcome to the Admin Dashboard!");
                System.out.println("1. Add User");
                System.out.println("2. View Users");
                System.out.println("3. Exit to Main Menu");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter user type (Company/Supermarket): ");
                        String userType = scanner.nextLine();
                        System.out.print("Enter username: ");
                        String userUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String userPassword = scanner.nextLine();
                        if (userType.equalsIgnoreCase("Company")) {
                            admin.addUser(new Company(userUsername, userPassword));
                        } else if (userType.equalsIgnoreCase("Supermarket")) {
                            admin.addUser(new Supermarket(userUsername, userPassword));
                        } else {
                            System.out.println("Invalid user type.");
                        }
                        break;
                    case 2:
                        admin.viewUsers();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void userDashboard(User user) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (user.authenticate(username, password)) {
            if (user instanceof Company) {
                Company company = (Company) user;
                while (true) {
                    System.out.println("\nWelcome to the Company Dashboard!");
                    System.out.println("1. Add Product");
                    System.out.println("2. Add Offer");
                    System.out.println("3. Display Offers");
                    System.out.println("4. Exit to Main Menu");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter product name: ");
                            String productName = scanner.nextLine();
                            System.out.print("Enter price: ");
                            double price = scanner.nextDouble();
                            System.out.print("Enter stock: ");
                            int stock = scanner.nextInt();
                            scanner.nextLine();
                            company.addProduct(productName, price, stock);
                            break;
                        case 2:
                            System.out.print("Enter product name: ");
                            String prodName = scanner.nextLine();
                            System.out.print("Enter discount rate (0-1): ");
                            double discount = scanner.nextDouble();
                            scanner.nextLine();
                            company.addOffer(prodName, discount);
                            break;
                        case 3:
                            company.displayOffers();
                            break;
                        case 4:
                            return;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
            } else if (user instanceof Supermarket) {
                Supermarket market = (Supermarket) user;
                Company company = new Company("CompanyA", "company123"); 
                while (true) {
                    System.out.println("\nWelcome to the Supermarket Dashboard!");
                    System.out.println("1. Browse Products");
                    System.out.println("2. Place Order");
                    System.out.println("3. Exit to Main Menu");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            market.browseProducts(company);
                            break;
                        case 2:
                            System.out.print("Enter product name: ");
                            String productName = scanner.nextLine();
                            System.out.print("Enter quantity: ");
                            int quantity = scanner.nextInt();
                            scanner.nextLine();
                            market.placeOrder(company, productName, quantity);
                            break;
                        case 3:
                            return;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
            }
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}
