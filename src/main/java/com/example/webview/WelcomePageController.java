package com.example.webview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class WelcomePageController {

    @FXML
    private Button LogInBtn1;

    @FXML
    private Button signUp1;

    @FXML
    void goToLogin(ActionEvent event) throws IOException {
        Parent scene2 = load(getClass().getResource("Log in.fxml"));
        Scene displayScene = new Scene(scene2);
        Stage window = (Stage)((Node)(event.getSource())).getScene().getWindow();
        window.setScene(displayScene);
        window.show();

    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        Parent dashParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Sign up.fxml")));
        Scene displayScene = new Scene(dashParent);
        Stage window = (Stage)((Node)(event.getSource())).getScene().getWindow();
        window.setScene(displayScene);
        window.show();
    }

}
