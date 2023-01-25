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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
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
    protected static boolean payment;

    @FXML
    void nouveauCredit(ActionEvent event) throws IOException {
        payment = false;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Montant.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 449, 112);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void supprimerClient(ActionEvent event) {
        new ClientService().delete(ListClients.selectedClient);
    }

    @FXML
    void paiement(ActionEvent event) throws IOException {
        payment = true;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Montant.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 449, 112);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
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
