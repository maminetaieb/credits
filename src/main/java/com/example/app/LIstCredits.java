package com.example.app;

import com.example.entity.Credit;
import com.example.service.ClientService;
import com.example.service.CreditService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
    private Text maxText;

    @FXML
    private Button NouveaCreditBtn;
    @FXML
    private TextField creditTF;
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
    private TextField paymentTF;

    @FXML
    void nouveauCredit(ActionEvent event) throws IOException {
        new CreditService().insert(
                new Credit(null,
                        ListClients.selectedClient.getId(),
                        Double.parseDouble(creditTF.getText()),
                        new Date())
        );
        refresh();
    }

    @FXML
    void supprimerClient(ActionEvent event) {
        new ClientService().delete(ListClients.selectedClient);
    }

    @FXML
    void paiement(ActionEvent event) throws IOException {
        new CreditService().insert(
                new Credit(null,
                        ListClients.selectedClient.getId(),
                        -Double.parseDouble(paymentTF.getText()),
                        new Date())
        );
        refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nomText.setText(ListClients.selectedClient.getFullName());
        numtelText.setText(ListClients.selectedClient.getPhoneNumber());
        maxText.setText(String.valueOf(ListClients.selectedClient.getMax()));
        refresh();
    }
    private void refresh() {
        CreditService credits = new CreditService();
        List<Credit> listCredits = credits.findByClientId(ListClients.selectedClient.getId(), null, null);
        totalText.setText(String.valueOf(listCredits.stream().mapToDouble(credit -> credit.getAmount()).sum()));
        listCreditView.getItems().clear();
        listCreditView.getItems().setAll(listCredits);
        listCreditView.refresh();
    }
}
