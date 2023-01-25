package com.example.app;

import com.example.service.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
        ListClients.selectedClient.setMax(Double.parseDouble(ModifMax.getText()));
        new ClientService().modify(ListClients.selectedClient);
    }

}

