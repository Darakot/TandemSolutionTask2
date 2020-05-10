package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.Win;

/**
 * Главное меню
 * openNewGame - начинает новую игру, переходим на другое окно
 * openNewPlayer - создаем нового игрока, переходим на другое окно
 * openLeaderBoard - Смотрим таблицу лидеров, переходим на другое окно
 */

public class MainMenuController {
    @FXML
    private Button openNewGame;

    @FXML
    private Button openNewPlayer;

    @FXML
    private Button openLeaderBoard;

    @FXML
    void initialize() {
        openNewGame.setOnAction(actionEvent ->{
            Stage window = (Stage) openNewGame.getScene().getWindow();
            Win.openWindow(window,"gui/game.fxml");
        });

        openNewPlayer.setOnAction(actionEvent ->{
            Stage window = (Stage) openNewGame.getScene().getWindow();
            Win.openWindow(window,"gui/newPlayer.fxml");
        });

        openLeaderBoard.setOnAction(actionEvent ->{
            Stage window = (Stage) openNewGame.getScene().getWindow();
            Win.openWindow(window,"gui/leaderBoard.fxml");
        });

    }
}
