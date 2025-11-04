class Customer extends Thread {
    static int balance = 1000;
    private final int withdrawAmount;

    public Customer(String name, int amount) {
        super(name);
        this.withdrawAmount = amount;
    }    
    @Override
    public void run() {
        synchronized (Customer.class) {
            System.out.println(getName() + " is trying to withdraw " + withdrawAmount);
            if (balance >= withdrawAmount) {
                System.out.println(getName() + " is proceeding");
                balance -= withdrawAmount;
                System.out.println(getName() + " completed withdrawing the money");
                System.out.println(getName() + " remaining balance is " + balance);
            } else {
                System.out.println(getName() + " insufficient funds. Current balance: " + balance);
            }
        }
    }
}
public class BankDemo {
        public static void main(String[] args) throws InterruptedException {
            Thread c1 = new Customer("Sunkara", 700);
            Thread c2 = new Customer("Ravi", 500);
            Thread c3 = new Customer("Maya", 400);
    
            c1.start();
            c2.start();
            c3.start();
    
            c1.join();
            c2.join();
            c3.join();

            System.out.println("Final balance: " + Customer.balance);
        }
    }

