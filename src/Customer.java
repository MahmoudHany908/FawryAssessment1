public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void pay(double amount)
    {
        try {
            if (amount > balance) {
                throw new IllegalArgumentException("Insufficient Money in your balance" + balance);
            }
            balance -= amount;
            System.out.println("Payment successful!!\n Remaining balance: " + balance);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
}
