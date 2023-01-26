package com.example.app;

import com.example.entity.User;
import com.example.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminPage {

    @FXML
    private AnchorPane AdminPane;

    @FXML
    private Button CreateUserBtn;

    @FXML
    private Button DeleteUserBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passField;

    @FXML
    void createUser(ActionEvent event) throws IOException {
        new UserService().insert(new User(nameField.getText(), passField.getText()));
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        AdminPane.getChildren().setAll(pane);
    }

    @FXML
    void deleteuser(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        AdminPane.getChildren().setAll(pane);
    }

}
