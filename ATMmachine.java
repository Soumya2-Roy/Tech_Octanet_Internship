import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ATM {
    private double balance;
    private String pin;
    private List<String> transactionHistory;

    public ATM(double initialBalance, String initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
    }

    public double checkBalance() {
        return balance;
    }

    public String withdraw(double amount) {
        if (amount > balance) {
            return "Insufficient funds.";
        } else if (amount <= 0) {
            return "Invalid withdrawal amount.";
        } else {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            return "Withdrew: $" + amount + ". New balance: $" + balance;
        }
    }

    public String deposit(double amount) {
        if (amount <= 0) {
            return "Invalid deposit amount.";
        } else {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            return "Deposited: $" + amount + ". New balance: $" + balance;
        }
    }

    public String changePin(String newPin) {
        if (newPin.length() != 4 || !newPin.matches("\\d{4}")) {
            return "Invalid PIN. It must be 4 digits.";
        } else {
            pin = newPin;
            return "PIN changed successfully.";
        }
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}

public class ATMApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM(1000, "1234");
        boolean running = true;

        while (running) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + atm.checkBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    System.out.println(atm.withdraw(withdrawAmount));
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    System.out.println(atm.deposit(depositAmount));
                    break;
                case 4:
                    System.out.print("Enter new PIN: ");
                    String newPin = scanner.next();
                    System.out.println(atm.changePin(newPin));
                    break;
                case 5:
                    System.out.println("Transaction History:");
                    for (String transaction : atm.getTransactionHistory()) {
                        System.out.println(transaction);
                    }
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
