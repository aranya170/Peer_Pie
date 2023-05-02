module com.example.webview {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires MaterialFX;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.webview to javafx.fxml;
    exports com.example.webview;
}