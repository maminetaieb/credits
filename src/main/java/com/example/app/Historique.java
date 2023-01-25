package com.example.app;

import com.example.entity.Credit;
import com.example.service.CreditService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class Historique implements Initializable {

    @FXML
    private AnchorPane historiquePane;

    @FXML
    private ListView<Credit> historiqueView;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CreditService credits = new CreditService();
        historiqueView.getItems().clear();
        historiqueView.getItems().setAll(credits.find(null, null));
        historiqueView.refresh();
    }
    @FXML
    void changeDates(ActionEvent event) {
        CreditService credits = new CreditService();
        historiqueView.getItems().clear();
        //historiqueView.getItems().setAll(startDate.is==null?credits.findByDate(java.sql.Date.valueOf(startDate.getValue()), java.sql.Date.valueOf(endDate.getValue())));
        historiqueView.refresh();
    }

}
