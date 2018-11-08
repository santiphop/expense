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

    private static Account accountText = new Account(Main.nameAccount, Main.txtfilename);
    private static Account accountDB = new Account(Main.nameAccount, Main.dbfilename);

    private static Account selectedAccount;
    private static Transaction selectedTransaction;

    private ToggleGroup importFileGroup = new ToggleGroup();
    private ToggleGroup typeGroup = new ToggleGroup();

    private String sFileRadio;
    private String sTypeRadio;

    @FXML
    protected RadioButton txtFileRadio;
    @FXML
    protected RadioButton dbFileRadio;
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
    void txtFileRadioSelect() {
        sFileRadio = txtFileRadio.getText();
        selectedAccount = accountText;
        displayTextTable(sFileRadio);
    }

    @FXML
    void dbFileRadioSelect() {
        sFileRadio = dbFileRadio.getText();
        selectedAccount = accountDB;
        displayTextTable(sFileRadio);
    }

    @FXML
    void depositRadioSelect() {
        sTypeRadio = depositRadio.getText();
    }

    @FXML
    void expenseRadioSelect() {
        sTypeRadio = expenseRadio.getText();
    }

    @FXML
    void initialize(){
        nameLabel.setText(accountText.getName());
        dateField.setText(String.valueOf(LocalDate.now()));
        txtFileRadio.setToggleGroup(importFileGroup);
        dbFileRadio.setToggleGroup(importFileGroup);
        depositRadio.setToggleGroup(typeGroup);
        expenseRadio.setToggleGroup(typeGroup);
//        displayTable();
    }

    @FXML
    void add(ActionEvent event){
        String currentDate = dateField.getText();
        String currentAmount = amountField.getText();
        String currentType = sTypeRadio;
        String currentNote = noteField.getText();
        double amount = Double.parseDouble(currentAmount);
        String type = currentType.toLowerCase();
        Transaction currentTransaction = new Transaction(MyHeader.convertToDate(currentDate), amount, type, currentNote);
        addTransaction(selectedAccount, currentTransaction);
        displayTextTable(sFileRadio);
        clearAllTextField();
    }

    private void addTransaction(Account account, Transaction currentTransaction) {
        account.add(currentTransaction);
        System.out.println(currentTransaction.formatContent());
        if (account.getFilename().contains(".txt"))
            MyHeader.writeTextFile(currentTransaction.formatContent(), account.getFilename(), true);
        if (account.getFilename().contains(".db"))
            MyHeader.writeDBFile(currentTransaction.formatContent(), account.getFilename());
    }

    @FXML
    void edit(ActionEvent event){
        selectedTransaction = tableView.getSelectionModel().getSelectedItem();
        changePage("editpage.fxml", event);
    }

    @FXML
    void showBalance(ActionEvent event) {
        amountLabel.setText(String.valueOf(accountText.getBalance()));
        thb.setVisible(true);
    }

    @FXML
    void showIncome(ActionEvent event) {
        amountLabel.setText(String.valueOf(accountText.getDeposit()));
        thb.setVisible(true);
    }

    @FXML
    void showExpense(ActionEvent event) {
        amountLabel.setText(String.valueOf(accountText.getExpense()));
        thb.setVisible(true);
    }

    public static Transaction getSelectedTransaction() {
        return selectedTransaction;
    }

    public static Account getAccount() {
        return selectedAccount;
    }

    private void clearAllTextField() {
        amountField.setText("");
        noteField.setText("");
    }

    private void displayTextTable(String fileType) {
        dateCol.setCellValueFactory(new PropertyValueFactory<Transaction,String>("date"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Transaction,String>("amount"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Transaction,String>("type"));
        noteCol.setCellValueFactory(new PropertyValueFactory<Transaction,String>("note"));
        if (fileType.contains(".txt"))
            tableView.setItems(accountText.getList());
        if (fileType.contains(".db"))
            tableView.setItems(accountDB.getList());
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
