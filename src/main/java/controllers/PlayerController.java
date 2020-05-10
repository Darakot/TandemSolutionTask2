package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.PalindromeGameService;
import util.Win;

public class PlayerController {
    private PalindromeGameService palindromeGameService = PalindromeGameService.getInstance();

    @FXML
    private TextField name;

    @FXML
    private TextField nickname;

    @FXML
    private Button createPlayer;

    @FXML
    private Button returnMenu;

    @FXML
    void initialize() {
        returnMenu.setOnAction(actionEvent -> {
            Stage window = (Stage) returnMenu.getScene().getWindow();
            Win.openWindow(window,"gui/menu.fxml");
        });

        createPlayer.setOnAction(actionEvent -> {
                if(!name.equals("") && !nickname.equals("")) {
                    palindromeGameService.newPlayer(nickname.getText(), name.getText());
                }
            Stage window = (Stage) createPlayer.getScene().getWindow();
            Win.openWindow(window,"gui/menu.fxml");
        });
    }
}
