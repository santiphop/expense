import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        readFile("expense_history.txt");
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

    private void readFile(String filename) {
        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                // "\\s" == any spaces
                String[] splitLine = line.split("\\s+");
                add(new Transaction(
                        convertToDate(splitLine[0]), Double.parseDouble(splitLine[1]), splitLine[2]
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LocalDate convertToDate(String currentDate) {
        String[] date = currentDate.split("-");
        int[] intDate = new int[3];
        for (int i = 0; i < 3; i++) {
            intDate[i]=Integer.parseInt(date[i]);
        }
        return LocalDate.of(intDate[0], intDate[1], intDate[2]);
    }

}
