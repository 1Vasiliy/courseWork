package company.java.controllers;

import company.java.game.Game;
import company.java.Settings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    Button gameButton;
    @FXML
    Button settingsButton;
    @FXML
    Button exitButton;

    public void initialize(){

        gameButton.setOnMouseClicked(event -> {
        Game game = new Game();
            try {
                game.start((Stage) gameButton.getScene().getWindow());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        settingsButton.setOnMouseClicked(event -> {
            Settings settings = new Settings();

            try {
                settings.start((Stage) settingsButton.getScene().getWindow());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        exitButton.setOnMouseClicked(event -> System.exit(0));

    }
}
