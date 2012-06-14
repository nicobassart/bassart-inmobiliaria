package inmobiliaria.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Messages_ErrorController implements Initializable {
    @FXML private Label error;

    @Override public void initialize(URL location, ResourceBundle resources) {
        FadeTransition ft = new FadeTransition(new Duration(3000), error);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }
}
