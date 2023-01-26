package com.example.app;

import com.example.entity.Credit;
import com.example.service.CreditService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Historique implements Initializable {

    @FXML
    private AnchorPane historiquePane;

    @FXML
    private ListView<Credit> historiqueView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CreditService credits = new CreditService();
        historiqueView.getItems().clear();
        historiqueView.getItems().setAll(credits.find(null, null));
        historiqueView.refresh();
    }
}
