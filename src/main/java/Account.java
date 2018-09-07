/**
 * Created by 708 on 8/31/2018.
 */
public class Account {
    private double balance;
    private double income;
    private double expense;

    public Account() {
        this.balance = 0;
        this.income = 0;
        this.expense = 0;
    }

    public void deposit(double amount) {
        this.balance += amount;
        this.income += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
        this.expense += amount;
    }

    public double getBalance() {
        return balance;
    }

    public double getIncome() {
        return income;
    }

    public double getExpense() {
        return expense;
    }
}
