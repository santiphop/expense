import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 708 on 8/31/2018.
 */
public class Account {
    private String name;
    private List<Transaction> transactions;

    public Account(String name, String filename) {
        this.name = name;
        transactions = new ArrayList<>();
        if (filename.length() > 0) MyHeader.readFile(this, filename);
    }

    public Account(String name) {
        this(name, "");
    }

    public void add(Transaction transaction) {
        transactions.add(transaction);
        transaction.setId(transactions.indexOf(transaction));
    }

    public boolean remove(Transaction transaction) {
        return transactions.remove(transaction);
    }

    public String formatContent() {
        String content = "";
        for (Transaction t :
                transactions) {
            content += t.formatContent();
        }
        return content;
    }

    public double getBalance() {
        return getDeposit() - getExpense();
    }

    public double getDeposit() {
        double deposit = 0;
        for (Transaction t :
                transactions) {
            if (t.getType().equals("deposit")) deposit += t.getAmount();
        }
        return deposit;
    }

    public double getExpense() {
        double expense = 0;
        for (Transaction t :
                transactions) {
            if (t.getType().equals("expense")) expense += t.getAmount();
        }
        return expense;
    }


    public String getName() {
        return name;
    }

    public ObservableList<Transaction> getList() {
        return FXCollections.observableArrayList(transactions);
    }


}
