package com.example.app;

import com.example.entity.Credit;
import com.example.service.CreditService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Historique implements Initializable {
    @FXML
    private Text totalCredit;
    @FXML
    private AnchorPane historiquePane;

    @FXML
    private ListView<Credit> historiqueView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CreditService credits = new CreditService();
        List<Credit> list =credits.find(null, null);
        totalCredit.setText(String.valueOf(list.stream().mapToDouble(c->c.getAmount()).sum()));
        historiqueView.getItems().clear();
        historiqueView.getItems().setAll(list);
        historiqueView.refresh();
    }
}
