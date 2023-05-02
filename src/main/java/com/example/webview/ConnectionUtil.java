package com.example.webview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionUtil {

    public static Connection connectdb() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/peer_pie_data?useSSL=false&allowPublicKeyRetrieval=true", "root", "arafath221158");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Could not load database driver: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Could not connect to database: " + e.getMessage());
        }
        return conn;
    }
}
