import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EditpageController {
    private Transaction selectedTransaction;
    private Account account;
    private String sRadio = null;
    private ToggleGroup typeGroup = new ToggleGroup();
    private boolean isSaveable = false;

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
        isSaveable = true;
        setSaveButton();
    }

    @FXML
    void expenseRadioSelect() {
        sRadio = expenseRadio.getText();
        isSaveable = true;
        setSaveButton();
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
        else System.out.println(sRadio);
        selectedTransaction.setNote(noteTextField.getText());
        if (account.getFilename().contains(".txt"))
           MyHeader.writeTextFile(account.formatContent(), Main.txtfilename, false);
        if (account.getFilename().contains(".db"))
           MyHeader.updateDBFile(selectedTransaction.getUpdateQuery(), account.getFilename());
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

    private void setSaveButton() {
        boolean bool = isSaveable;
        saveButton.setDisable(!bool);
    }


}
