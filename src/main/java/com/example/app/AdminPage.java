package com.example.app;

import com.example.entity.Credit;
import com.example.entity.User;
import com.example.service.CreditService;
import com.example.service.UserService;
import com.example.util.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

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
        try {
            User u = new User(nameField.getText(), passField.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer utilisateur");
            alert.setContentText("Voulez vous vraiment supprimer cet utilisateur?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    new UserService().delete(u);
                } catch (Exception e) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Utilisateur non existant");
                    alert.setContentText("Veuillez entrer un login valide");
                    alert.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        AdminPane.getChildren().setAll(pane);
    }

}
