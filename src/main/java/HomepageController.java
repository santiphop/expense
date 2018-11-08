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

    ToggleGroup typeGroup = new ToggleGroup();

    private String sRadio;

    @FXML
    protected Label nameLabel;
    @FXML
    protected Button addButton;
    @FXML
    protected Button editButton;
    @FXML
    protected Button balanceButton;
    @FXML
    protected Button incomeButton;
    @FXML
    protected Button expenseButton;
    @FXML
    protected Label amountLabel;
    @FXML
    protected Label thb;
    @FXML
    protected TextField dateField;
    @FXML
    protected TextField amountField;
    @FXML
    protected TextField noteField;
    @FXML
    protected RadioButton depositRadio;
    @FXML
    protected RadioButton expenseRadio;

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
    void depositRadioSelect() {
        sRadio = depositRadio.getText();
    }

    @FXML
    void expenseRadioSelect() {
        sRadio = expenseRadio.getText();
    }

    @FXML
    void initialize(){
        nameLabel.setText(account.getName());
        dateField.setText(String.valueOf(LocalDate.now()));
        depositRadio.setToggleGroup(typeGroup);
        expenseRadio.setToggleGroup(typeGroup);
        displayTable();
    }

    @FXML
    void add(ActionEvent event){
        String currentDate = dateField.getText();
        String currentAmount = amountField.getText();
        String currentType = sRadio;
        String currentNote = noteField.getText();
        double amount = Double.parseDouble(currentAmount);
        String type = currentType.toLowerCase();
        Transaction currentTransaction = new Transaction(MyHeader.convertToDate(currentDate), amount, type, currentNote);
        account.add(currentTransaction);
        displayTable();
        clearAllTextField();
        String content = currentTransaction.formatContent();
        MyHeader.writeFile(content, Main.filename, true);
    }

    @FXML
    void edit(ActionEvent event){
        selectedTransaction = tableView.getSelectionModel().getSelectedItem();
        changePage("editpage.fxml", event);
    }

    @FXML
    void showBalance(ActionEvent event) {
        amountLabel.setText(String.valueOf(account.getBalance()));
        thb.setVisible(true);
    }

    @FXML
    void showIncome(ActionEvent event) {
        amountLabel.setText(String.valueOf(account.getDeposit()));
        thb.setVisible(true);
    }

    @FXML
    void showExpense(ActionEvent event) {
        amountLabel.setText(String.valueOf(account.getExpense()));
        thb.setVisible(true);
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
}
