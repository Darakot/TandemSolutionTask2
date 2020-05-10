package controllers;

import dto.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.PalindromeGameService;
import util.Win;

/**
 * Таблица лидеров с 2 таблицами.
 * 1 - ник игрока
 * 2 - очки победы
 * returnMenu - кнопка возврата в меню
 */
public class LeaderBoard {
    PalindromeGameService palindromeGameService = PalindromeGameService.getInstance();

    @FXML
    private TableView<Player> leaderBoard;

    @FXML
    private TableColumn<Player, String> players;

    @FXML
    private TableColumn<Player, Integer> vp;

    @FXML
    private Button returnMenu;

    @FXML
    void initialize() {
        ObservableList<Player> playersObList = FXCollections.observableArrayList(palindromeGameService.allPlayers());
        players.setCellValueFactory(new PropertyValueFactory<Player, String>("nick"));
        vp.setCellValueFactory(new PropertyValueFactory<Player, Integer>("vp"));

        leaderBoard.setItems(playersObList);

        returnMenu.setOnAction(actionEvent -> {
            Stage window = (Stage) returnMenu.getScene().getWindow();
            Win.openWindow(window,"gui/menu.fxml");
        });
    }
}
