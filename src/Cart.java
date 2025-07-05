import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<CartItem> items = new ArrayList<>();;

    public void add(Product product, int quantity)
    {
        if(quantity > product.getQuantity())
            throw new IllegalArgumentException("Not enough stock \n This product has only "+product.getQuantity()+"left in stock");
        items.add(new CartItem(product,quantity));
    }
    public List<CartItem> getItems() {
        return items;
    }
    public double getSubtotal()
    {
        double total = 0;
        for (CartItem item : items)
            total += item.getSubtotal();
        return total;
    }
        public boolean isEmpty() {
            return items.isEmpty();
        }
    public List<Shippable> getShippableItems() {
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct() instanceof Shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) item.getProduct());
                }
            }
        }
        return shippableItems;
    }
}
