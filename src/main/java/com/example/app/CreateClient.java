package com.example.app;

import com.example.entity.Client;
import com.example.service.ClientService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateClient implements Initializable {

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
        if((nomField.getText().isBlank())|| (numTelField.getText().isBlank()) || numTelField.getText().length() != 8 || (maxField.getText().isBlank()) || Double.parseDouble(maxField.getText()) < 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("failed!");
            alert.setHeaderText(null);
            alert.setContentText("Verifier les champs");

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numTelField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    numTelField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        maxField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    maxField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
