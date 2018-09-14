import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller {
    private Account account;

    @FXML
    void initialize() {
        account = new Account();
    }

    @FXML
    void fileWrite(ActionEvent event) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("purchase_history.txt", true);
            PrintWriter out = new PrintWriter(new BufferedWriter(fileWriter));
            out.println("purchased");
            out.flush();
        } catch (IOException io) {
            System.err.println("IO Error");
        } catch (NullPointerException n) {
            System.err.println("null");
        }
    }
}
