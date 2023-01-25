package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Login {

    @FXML
    private Button LoginBtn;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passField;

    @FXML
    void login(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListClients.fxml"));
        loginPane.getChildren().setAll(pane);
    }

}
