package com.example.app;

import com.example.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        if (nameField.getText().equals("admin") && passField.getText().equals("F@goçytoSe")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
            loginPane.getChildren().setAll(pane);

        } else if (new UserService().authenticate(nameField.getText(), passField.getText()) != null) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ListClients.fxml"));
            loginPane.getChildren().setAll(pane);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Utilisateur non enregistre");
            alert.setHeaderText("Verifier login et mot de passe");
            alert.show();
        }
    }

}
