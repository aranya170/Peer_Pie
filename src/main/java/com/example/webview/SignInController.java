package com.example.webview;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class SignInController {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/peer_pie_data?useSSL=false&allowPublicKeyRetrieval=true";

    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "arafath221158";
    private static final String INSERT_QUERY = "INSERT INTO users (firstName, lastName, phone, email, password) VALUES (?, ?, ?, ?, ?)";
    @FXML
    private MFXTextField UniID;

    @FXML
    private MFXTextField emailField;

    @FXML
    private MFXTextField firstNameField;

    @FXML
    private MFXTextField lastNameField;

    @FXML
    private MFXPasswordField passwordField;

    @FXML
    private AnchorPane signUp;

    @FXML
    private MFXButton signUpBtn;

    @FXML
    void signUp(ActionEvent event) throws IOException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String UID = UniID.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || UID.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
            return;
        }

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, UID);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, password);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Sign up successful");
                alert.showAndWait();
                switchToDash(event);


            }
        } catch (SQLException e) {
            printSQLException(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Sign up failed. Please try again later");
            alert.showAndWait();
        }


    }

    @FXML
    private void switchToDash(ActionEvent event) throws IOException {
        Parent dashParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dash.fxml")));
        Scene dashScene = new Scene(dashParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashScene);
        window.show();
    }

    @FXML
    private void switchToLogIn(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
            }
        }

    }
}


//package com.example.peer_pi;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.input.InputMethodEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class SignInController implements Initializable {
//
//    @FXML
//    private AnchorPane signUp;
//
//    @FXML
//    private Label statusLabel;
//
//    @FXML
//    private TextField firstNameField;
//
//    @FXML
//    private TextField lastNameField;
//
//    @FXML
//    private TextField phoneField;
//
//    @FXML
//    private TextField emailField;
//
//    @FXML
//    private Button signUpBtn;
//
//    @FXML
//    private PasswordField passwordField;
//
//    @FXML
//    void Click_On_SignIn(ActionEvent event) {
//        String firstnameField = firstNameField.getText();
//        String lastnameField = lastNameField.getText();
//        String phone = phoneField.getText();
//        String email = emailField.getText();
//        String password = passwordField.getText();
//
//        // Validate input
//        if (!phone.matches("\\d{1,10}")) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setHeaderText(null);
//            alert.setContentText("Please enter a valid phone number with up to 10 digits.");
//            alert.showAndWait();
//            return;
//        }
//
//
//        // Validate phone number
//        if (!phone.matches("\\d{10}")) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setHeaderText(null);
//            alert.setContentText("Please enter a valid 10-digit phone number.");
//            alert.showAndWait();
//            return;
//        }
//
//        // Save sign-up information to file
//        try {
//            File directory = new File("C:\\Users\\USER\\Desktop\\Peer_Pie-main\\SignInController.txt");
//            directory.mkdirs();
//
//            FileWriter writer = new FileWriter(directory, true);
//            writer.write(firstnameField + "\n");
//            writer.write(lastnameField + "\n");
//            writer.write(phone + "\n");
//            writer.write(email + "\n");
//            writer.write(password + "\n");
//            writer.close();
//
//
//            // Show success message
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText(null);
//            alert.setContentText("Sign-up successful!");
//            alert.showAndWait();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("An error occurred while saving sign-up information.");
//            alert.showAndWait();
//        }
//    }
//
//    @FXML
//    void goToLogin(InputMethodEvent event) throws IOException {
//        Parent scene2 = FXMLLoader.load(getClass().getResource("Dash.fxml"));
//        Scene displayScene = new Scene(scene2);
//        Stage window = (Stage)((Node)(event.getSource())).getScene().getWindow();
//        window.setScene(displayScene);
//        window.show();
//    }
//
//    @FXML
//    void switchToDash(ActionEvent event) throws IOException {
//        Parent scene2 = FXMLLoader.load(getClass().getResource("Dash.fxml"));
//        Scene displayScene = new Scene(scene2);
//        Stage window = (Stage)((Node)(event.getSource())).getScene().getWindow();
//        window.setScene(displayScene);
//        window.show();
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        emailField.requestFocus();
//    }
//}
