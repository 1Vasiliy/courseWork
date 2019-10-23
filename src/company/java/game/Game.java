package company.java.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gameSample.fxml"));
        Scene scene = new Scene(root, 600, 600);

        primaryStage.setTitle("Snake");

        scene.getRoot().requestFocus();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}