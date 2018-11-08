import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class EditpageController {
    private Transaction selectedTransaction;
    private Account account;

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
    Button editButton;

    @FXML
    void initialize() {
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
    void edit(ActionEvent event) {
        selectedTransaction.setDate(convertToDate(dateTextField.getText()));
        selectedTransaction.setAmount(Double.parseDouble(amountTextField.getText()));
        selectedTransaction.setNote(amountTextField.getText());
        writeFile(account.formatContent(), Main.filename);
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

    private void writeFile(String content, String filename) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(filename))) {
            buffer.write(content);
            buffer.flush();

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
