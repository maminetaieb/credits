package com.example.app;

import com.example.entity.Credit;
import com.example.service.CreditService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LIstCredits implements Initializable {

    @FXML
    private Text CreditsText;

    @FXML
    private AnchorPane ListCreditsPane;

    @FXML
    private Text MontantMaxText;

    @FXML
    private Button NouveaCreditBtn;
    @FXML
    private Text totalText;

    @FXML
    private Button SupprimerClientBtn;

    @FXML
    private ListView<Credit> listCreditView;

    @FXML
    private Text nomText;

    @FXML
    private Text numtelText;

    @FXML
    private Button PaiementBtn;

    @FXML
    void nouveauCredit(ActionEvent event) {

    }

    @FXML
    void supprimerClient(ActionEvent event) {

    }

    @FXML
    void paiement(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CreditService credits = new CreditService();
        List<Credit> listCredits = credits.findByClientId(ListClients.selectedClient.getId(), null, null);

        nomText.setText(ListClients.selectedClient.getFullName());
        numtelText.setText(ListClients.selectedClient.getPhoneNumber());
        totalText.setText(String.valueOf(listCredits.stream().mapToDouble(credit -> credit.getAmount()).sum()));
        listCreditView.getItems().clear();
        listCreditView.getItems().setAll(listCredits);
        listCreditView.refresh();
    }
}
