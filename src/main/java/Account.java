import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 708 on 8/31/2018.
 */
public class Account {
    private String name;
    private List<Transaction> transactions;

    public Account(String name) {
        this.name = name;
        transactions = new ArrayList<>();
    }

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public boolean remove(Transaction transaction) {
        return transactions.remove(transaction);
    }

    public Transaction getTransaction(int i) {
        return transactions.get(i);
    }

    public String formatContent() {
        String content = "";
        for (Transaction t :
                transactions) {
            content += t.formatContent();
        }
        return content;
    }

    public int getIndex(Transaction transaction) {
        return transactions.indexOf(transaction);
    }

    public double getCurrency() {
        double currency = 0;
        for (Transaction t :
                transactions) {
            currency += t.getAmount();
        }
        return currency;
    }

    @Override
    public String toString() {
        return Double.toString(getCurrency());
    }

    public String getName() {
        return name;
    }

    public ObservableList<Transaction> getList() {
        return FXCollections.observableArrayList(transactions);
    }
}
