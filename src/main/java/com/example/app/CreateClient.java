package com.example.app;

import com.example.entity.Client;
import com.example.service.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateClient {

    @FXML
    private Button AjouterClientBtn;

    @FXML
    private AnchorPane createClientPane;

    @FXML
    private TextField maxField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField numTelField;

    @FXML
    void ajouterClient(ActionEvent event) {
        if((nomField.getText().isBlank())|| (numTelField.getText().isBlank()) || (maxField.getText().isBlank())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("failed!");
            alert.setHeaderText(null);
            alert.setContentText("svp remplissez tous les champs");

            alert.showAndWait();
        }
        else {
            new ClientService().insert(new Client(null, nomField.getText(), numTelField.getText(), Double.parseDouble(maxField.getText())));
            String title = "Success!";
            String message = " client ajouté";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            // alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Client ajouté");

            alert.showAndWait();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        }
    }

    @FXML
    void max(ActionEvent event) {

    }

    @FXML
    void nom(ActionEvent event) {

    }

    @FXML
    void numTel(ActionEvent event) {

    }

}
