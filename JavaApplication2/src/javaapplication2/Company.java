
package javaapplication2;
import java.util.HashMap;

class Company extends User {
    private HashMap<String, Product> products;
    private HashMap<String, Double> offers;

    public Company(String username, String password) {
        super(username, password);
        products = new HashMap<>();
        offers = new HashMap<>();
    }

    public void addProduct(String name, double price, int stock) {
        products.put(name, new Product(name, price, stock));
        System.out.println("Product added: " + name);
    }

    public void addOffer(String productName, double discountRate) {
        if (products.containsKey(productName)) {
            offers.put(productName, discountRate);
            System.out.println("Offer added: " + (discountRate * 100) + "% off on " + productName);
        } else {
            System.out.println("Product " + productName + " not found.");
        }
    }

    public void displayOffers() {
        System.out.println("Current Offers:");
        for (String product : offers.keySet()) {
            System.out.println("- " + product + ": " + (offers.get(product) * 100) + "% off");
        }
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }

    public HashMap<String, Double> getOffers() {
        return offers;
    }
}
