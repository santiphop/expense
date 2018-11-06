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

public class EditpageController {
    Transaction selectedTransaction;
    Account account;

    @FXML
    Label nameLabel;
    @FXML
    Label dateLabel;
    @FXML
    TextField amountTextField;
    @FXML
    Label noteLabel;
    @FXML
    Button cancelButton;
    @FXML
    Button editButton;

    @FXML
    void initialize() {
        selectedTransaction = HomepageController.getSelectedTransaction();
        account = HomepageController.getAccount();
        dateLabel.setText(String.valueOf(selectedTransaction.getDate()));
        amountTextField.setText(String.valueOf(selectedTransaction.getAmount()));
        noteLabel.setText(String.valueOf(selectedTransaction.getNote()));
    }

    @FXML
    void cancel(ActionEvent event) {
        changePage("homepage.fxml", event);
        System.out.println();
    }

    @FXML
    void edit(ActionEvent event) {
        selectedTransaction.setAmount(Double.parseDouble(amountTextField.getText()));
        System.out.println(account.formatContent());
        writeFile(account.formatContent(), "expense_history.txt");
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

}
