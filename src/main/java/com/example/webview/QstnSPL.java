package com.example.webview;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
public class QstnSPL extends Controller implements Initializable {
    @FXML
    private WebView webView;
    @FXML
    private TextField textField;
    private WebEngine engine;
    private String homePage;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        engine = webView.getEngine();
        homePage = "https://discord.gg/bJT6Fk8REa";
        textField.setText(homePage);
        loadPage();
    }

    public void loadPage() {
        engine.load("https://discord.gg/JvRM3xxs");
    }
}