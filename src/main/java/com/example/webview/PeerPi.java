package com.example.webview;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class PeerPi extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Welcome Page.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Peer Pie");
        stage.getIcons().add(new Image("C:\\Users\\USER\\Desktop\\Arafath JAVA\\Webview\\src\\main\\resources\\Assets\\logo-no-background2.png"));
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}