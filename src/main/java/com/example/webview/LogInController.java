package com.example.webview;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class LogInController implements Initializable {



    @FXML
    private MFXButton LogInBtn;

    @FXML
    private MFXButton createBtn;

    @FXML
    private MFXTextField mailBtn;

    @FXML
    private MFXTextField passBtn;

    @FXML
    private MFXProgressBar progress1;


    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public LogInController() {
        connection = ConnectionUtil.connectdb();
    }
    public void loginAction(ActionEvent event){
        String email = mailBtn.getText().toString();
        String password = passBtn.getText().toString();

        String sql = "SELECT * FROM users WHERE email = ? and password = ?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                infoBox("Please enter correct Email and Password", null, "Failed");
            }else{
                infoBox("Login Successful", null, "Success");
                SwitchToDash(event);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void SwitchToDash( ActionEvent event) throws IOException {
        Parent scene2 = FXMLLoader.load(getClass().getResource("Dash.fxml"));
        Scene displayScene = new Scene(scene2);
        Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        window.setScene(displayScene);
        window.show();
    }

    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @FXML
    protected void switchToSignUp(ActionEvent event) throws IOException, InterruptedException {
        Parent scene2 = FXMLLoader.load(getClass().getResource("Sign up.fxml"));
        Scene displayScene = new Scene(scene2);
        Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        window.setScene(displayScene);
        window.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
