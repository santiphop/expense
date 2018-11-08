import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class EditpageController {
    private Transaction selectedTransaction;
    private Account account;
    private String sRadio;
    ToggleGroup typeGroup = new ToggleGroup();

    @FXML
    Label nameLabel;
    @FXML
    TextField dateTextField;
    @FXML
    TextField amountTextField;
    @FXML
    TextField noteTextField;
    @FXML
    Button cancelButton;
    @FXML
    Button saveButton;
    @FXML
    protected RadioButton depositRadio;
    @FXML
    protected RadioButton expenseRadio;

    @FXML
    void depositRadioSelect() {
        sRadio = depositRadio.getText();
    }

    @FXML
    void expenseRadioSelect() {
        sRadio = expenseRadio.getText();
    }


    @FXML
    void initialize() {
        depositRadio.setToggleGroup(typeGroup);
        expenseRadio.setToggleGroup(typeGroup);
        nameLabel.setText(HomepageController.getAccount().getName());
        selectedTransaction = HomepageController.getSelectedTransaction();
        account = HomepageController.getAccount();
        dateTextField.setText(String.valueOf(selectedTransaction.getDate()));
        amountTextField.setText(String.valueOf(selectedTransaction.getAmount()));
        noteTextField.setText(String.valueOf(selectedTransaction.getNote()));
    }

    @FXML
    void cancel(ActionEvent event) {
        changePage("homepage.fxml", event);
    }

    @FXML
    void save(ActionEvent event) {
        selectedTransaction.setDate(MyHeader.convertToDate(dateTextField.getText()));
        selectedTransaction.setAmount(Double.parseDouble(amountTextField.getText()));
        if (sRadio != null)
            selectedTransaction.setType(sRadio.toLowerCase());
        selectedTransaction.setNote(noteTextField.getText());
        MyHeader.writeFile(account.formatContent(), Main.filename, false);
        changePage("homepage.fxml", event);
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
