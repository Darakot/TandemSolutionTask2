package controllers;

import dto.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.PalindromeGameService;
import util.Win;

import java.util.stream.Collectors;

public class GameController {
    private PalindromeGameService palindromeGameService = PalindromeGameService.getInstance();


    @FXML
    private Label nickname;

    @FXML
    private Label victoryPoints;

    @FXML
    private TextField strPalindrome;

    @FXML
    private Button playGame;

    @FXML
    private Label message;

    @FXML
    private Button returnMenu;

    @FXML
    private ChoiceBox<String> players;

    @FXML
    void initialize() {
        ObservableList<String> playersObList = FXCollections.observableArrayList(palindromeGameService.allPlayers()
                .stream()
                .map(Player::getNick)
                .collect(Collectors.toList()));
        players.setItems(playersObList);

        returnMenu.setOnAction(actionEvent -> {
            Stage window = (Stage) returnMenu.getScene().getWindow();
            Win.openWindow(window,"gui/menu.fxml");
        });

        players.setOnAction(actionEvent -> {
            String nickPlayer = players.getValue();

            if(!nickPlayer.equals("")) {
                Player player = palindromeGameService.getPlayer(nickPlayer);
                nickname.setText(player.getNick());
                victoryPoints.setText(player.getVpStr());
            }
        });

        playGame.setOnAction(actionEvent -> {
            String nickPlayer = players.getValue();
            Player player = palindromeGameService.getPlayer(nickPlayer);
            if(!strPalindrome.equals("")) {
                String resultGame = palindromeGameService.playGame(player, strPalindrome.getText());
                nickname.setText(player.getNick());
                victoryPoints.setText(player.getVpStr());
                message.setText(resultGame);
            }
        });


    }
}
