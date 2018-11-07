import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class HomepageController {

    private static Account account = new Account(Main.nameAccount, Main.filename);

    private static Transaction selectedTransaction;

    @FXML
    protected Label nameLabel;
    @FXML
    protected Button addButton;
    @FXML
    protected Button editButton;
    @FXML
    protected TextField dateField;
    @FXML
    protected TextField amountField;
    @FXML
    protected TextField noteField;
    @FXML
    protected TableView<Transaction> tableView;
    @FXML
    protected TableColumn dateCol;
    @FXML
    protected TableColumn amountCol;
    @FXML
    protected TableColumn typeCol;
    @FXML
    protected TableColumn noteCol;

    @FXML
    void initialize(){
        nameLabel.setText(account.getName());
        dateField.setText(String.valueOf(LocalDate.now()));
        displayTable();
    }

    @FXML
    void add(ActionEvent event){
        String currentDate = dateField.getText();
        String currentAmount = amountField.getText();
        String currentNote = noteField.getText();
        double amount = Double.parseDouble(currentAmount);
        Transaction currentTransaction = new Transaction(convertToDate(currentDate), amount, currentNote);
        account.add(currentTransaction);
        displayTable();
        clearAllTextField();
        String content = currentTransaction.formatContent();
        writeFile(content, Main.filename);
    }

    @FXML
    void edit(ActionEvent event){
        selectedTransaction = tableView.getSelectionModel().getSelectedItem();
        changePage("editpage.fxml", event);

    }

    public static Transaction getSelectedTransaction() {
        return selectedTransaction;
    }

    public static Account getAccount() {
        return account;
    }

    private void clearAllTextField() {
        amountField.setText("");
        noteField.setText("");
    }

    private LocalDate convertToDate(String currentDate) {
        String[] date = currentDate.split("-");
        int[] intDate = new int[3];
        for (int i = 0; i < 3; i++) {
            intDate[i]=Integer.parseInt(date[i]);
        }
        return LocalDate.of(intDate[0], intDate[1], intDate[2]);
    }

    private void displayTable() {
        dateCol.setCellValueFactory(new PropertyValueFactory<Transaction,String>("date"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Transaction,String>("amount"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Transaction,String>("type"));
        noteCol.setCellValueFactory(new PropertyValueFactory<Transaction,String>("note"));
        tableView.setItems(account.getList());
        tableView.getColumns().setAll(dateCol, amountCol, typeCol, noteCol);
    }

    private void changePage(String filename, ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
        try {
            stage.setScene(new Scene(loader.load(),800,600));
            stage.setResizable(false);
            stage.show();
        } catch(IOException e1){
            e1.printStackTrace();
        }
    }

    private void writeFile(String content, String filename) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(filename, true))) {
            buffer.write(content);
            buffer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
