package com.example.app;

import com.example.entity.Credit;
import com.example.service.ClientService;
import com.example.service.CreditService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
        if (!creditTF.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ajouter Credit");
            alert.setContentText("Voulez vraiment ajouter le credit au client");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new CreditService().insert(
                        new Credit(null,
                                ListClients.selectedClient.getId(),
                                Double.parseDouble(creditTF.getText()),
                                new Date())
                );
                refresh();
            }
            creditTF.setText("");
        }
    }

    @FXML
    void supprimerClient(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            new ClientService().delete(ListClients.selectedClient);
            ListClients.selectedClient = null;
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ListClients.fxml"));
            ListCreditsPane.getChildren().setAll(pane);
        }
    }

    @FXML
    void paiement(ActionEvent event) throws IOException {
        if (!paymentTF.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ajouter Paiement");
            alert.setContentText("Voulez vraiment ajouter le paiement au client");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new CreditService().insert(
                        new Credit(null,
                                ListClients.selectedClient.getId(),
                                -Double.parseDouble(paymentTF.getText()),
                                new Date())
                );
                refresh();
            }
            paymentTF.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomText.setText(ListClients.selectedClient.getFullName());
        numtelText.setText(ListClients.selectedClient.getPhoneNumber());
        maxText.setText(String.valueOf(ListClients.selectedClient.getMax()));
        refresh();
        creditTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    creditTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        paymentTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    paymentTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    private void refresh() {
        CreditService credits = new CreditService();
        List<Credit> listCredits = credits.findByClientId(ListClients.selectedClient.getId(), null, null);
        totalText.setText(String.valueOf(listCredits.stream().mapToDouble(credit -> credit.getAmount()).sum()));
        listCreditView.getItems().clear();
        listCreditView.getItems().setAll(listCredits);
        listCreditView.refresh();
    }

    @FXML
    void goToListeClients(ActionEvent event) throws IOException {
        ListClients.selectedClient = null;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListClients.fxml"));
        ListCreditsPane.getChildren().setAll(pane);
    }
}
