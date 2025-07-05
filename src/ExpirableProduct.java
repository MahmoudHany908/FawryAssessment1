public class ExpirableProduct extends Product {

    private boolean isExpired;

    public ExpirableProduct(String name, double price, int quantity, boolean isExpired) {
        super(name, price, quantity);
        this.isExpired = isExpired;
    }

    public boolean isExpired() {
        return isExpired;
    }
}
