package util;

import controllers.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Win {

    public static void openWindow(Stage window, String fxml) {
        window.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainMenuController.class.getClassLoader().getResource(fxml));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
