import java.util.List;

public class CheckoutService {
    private static final double SHIPPING_FEE = 30;

    public static void checkout(Customer customer, Cart cart) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalArgumentException(
                    "Your cart is empty!!\n Please add at least one item before checking out."
            );
        }

        List<CartItem> cartItems = cart.getItems();
        double subtotal = 0.0;

        for (CartItem item : cartItems) {
            Product p = item.getProduct();

            // Quick check: product expirable?
            if (p instanceof ExpirableProduct) {
                ExpirableProduct exp = (ExpirableProduct) p;
                if (exp.isExpired()) {
                    throw new IllegalArgumentException(
                            p.getName() + " has expired\n Please remove it from your cart"
                    );
                }
            }
            if (item.getQuantity() > p.getQuantity()) {
                throw new IllegalArgumentException(
                        "Sorry! Not enough stock for " + p.getName() + "\n Amount available: " + p.getQuantity()
                );
            }
            subtotal += item.getSubtotal();
        }

        List<Shippable> shippables = cart.getShippableItems();
        double shippingCost = shippables.isEmpty() ? 0.0 : SHIPPING_FEE;
        double total = subtotal + shippingCost;

        if (customer.getBalance() < total) {
            throw new IllegalArgumentException("Insufficient funds \n Balance: " + customer.getBalance() + "\n Receipt Total: " + total);
        }
        customer.pay(total);

        // Reduce product stock now that payment’s confirmed
        for (CartItem item : cartItems) {
            Product p = item.getProduct();
            p.reduceQuantity(item.getQuantity());
        }

        if (!shippables.isEmpty()) {
            ShippingService.ship(shippables);
        }

        System.out.println("** Checkout Receipt **");
        for (CartItem item : cartItems) {
            String name = item.getProduct().getName();
            System.out.printf("%dx %-14s %.0f\n", item.getQuantity(), name, item.getSubtotal());
        }


        System.out.println("----------------------------");
        System.out.printf("Subtotal         : %.0f\n", subtotal);
        System.out.printf("Shipping         : %.0f\n", shippingCost);
        System.out.printf("Total Amount     : %.0f\n", total);
        System.out.printf("Remaining Balance: %.0f\n", customer.getBalance());
    }
}
