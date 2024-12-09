
package javaapplication2;
class Supermarket extends User {
    public Supermarket(String username, String password) {
        super(username, password);
    }

    public void browseProducts(Company company) {
        System.out.println("Available Products:");
        for (String product : company.getProducts().keySet()) {
            Product p = company.getProducts().get(product);
            double price = p.getPrice();
            if (company.getOffers().containsKey(product)) {
                price *= (1 - company.getOffers().get(product));
            }
            System.out.printf("- %s: $%.2f (Stock: %d)%n", product, price, p.getStock());
        }
    }

    public void placeOrder(Company company, String productName, int quantity) {
        Product product = company.getProducts().get(productName);
        if (product != null) {
            if (product.getStock() >= quantity) {
                double totalPrice = product.getPrice() * quantity;
                if (company.getOffers().containsKey(productName)) {
                    totalPrice *= (1 - company.getOffers().get(productName));
                }
                product.reduceStock(quantity);
                System.out.printf("Order placed for %d x %s. Total: $%.2f%n", quantity, productName, totalPrice);
            } else {
                System.out.println("Insufficient stock for " + productName);
            }
        } else {
            System.out.println("Product " + productName + " not found.");
        }
    }
}
