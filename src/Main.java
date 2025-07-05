public class Main {
    public static void main(String[] args) {
        ExpirableProduct cheese = new ExpirableProduct("Cheese", 100, 10, false);
        ExpirableProduct biscuits = new ExpirableProduct("Biscuits", 150, 5, false);
        ShippableProduct tv = new ShippableProduct("TV", 300, 3, 5000); // 5kg
        Product scratchCard = new Product("ScratchCard", 50, 20) {};

        Customer customer = new Customer("Muhammed Saad", 1000);

        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(tv, 1);
        cart.add(scratchCard, 1);

        try {
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error during checkout: " + e.getMessage());
        }
    }
}
