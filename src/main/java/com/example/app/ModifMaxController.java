package com.example.app;

import com.example.service.ClientService;
import com.example.util.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ModifMaxController {

    @FXML
    private TextField ModifMax;

    @FXML
    private Button ValiderBtn;

    @FXML
    private AnchorPane modiffMaxPane;

    @FXML
    void modifMax(ActionEvent event) {

    }

    @FXML
    void valider(ActionEvent event) {
        if (ModifMax.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("champs vide");
            alert.setContentText("vérifiez le montant");
        }
            if(Main.selectedClient.getTotalCredits()<=Double.parseDouble(ModifMax.getText())) {
                Main.selectedClient.setMax(Double.parseDouble(ModifMax.getText()));
                new ClientService().modify(Main.selectedClient);
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

            }

            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                // alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Le montant ajouté est inférieur au total des crédits du client");

                alert.showAndWait();
            }

    }

}

