import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static final String filename = "expense_history.txt";
    public static final String nameAccount = "Santiphop";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        primaryStage.setTitle("Expense");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}