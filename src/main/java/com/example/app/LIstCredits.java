package com.example.app;

import com.example.entity.Credit;
import com.example.service.ClientService;
import com.example.service.CreditService;
import com.example.util.Main;
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
    private TextField ModifMax;
    @FXML
    private Button ModifBtn;
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
            if (Double.parseDouble(creditTF.getText()) + Main.selectedClient.getTotalCredits() > Main.selectedClient.getMax()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Montant Invalide");
                alert.setContentText("Credit depasse le montant maximal");
                alert.show();
            } else if((Double.parseDouble(creditTF.getText())>0)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouter Credit");
                alert.setContentText("Voulez vous vraiment ajouter ce credit au client?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    new CreditService().insert(
                            new Credit(null,
                                    Main.selectedClient.getId(),
                                    Double.parseDouble(creditTF.getText()),
                                    new Date())
                    );
                    refresh();
                }
            }
            creditTF.setText("");
        }
    }
    @FXML
    void modifierClient(ActionEvent event) throws IOException {
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ModifierClient.fxml"));
            ListCreditsPane.getChildren().setAll(pane);
        }
       /* if (ModifMax.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("champs vide");
            alert.setContentText("vérifiez le montant");
            alert.showAndWait();
        }
        else if(Main.selectedClient.getTotalCredits()<=Double.parseDouble(ModifMax.getText())) {
            Main.selectedClient.setMax(Double.parseDouble(ModifMax.getText()));
            new ClientService().modify(Main.selectedClient);


        }

        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            // alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le montant ajouté est inférieur au total des crédits du client");

            alert.showAndWait();

        }
        ModifMax.setText("");
        refresh();
*/
    }


    @FXML
    void supprimerClient(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            new ClientService().delete(Main.selectedClient);
            Main.selectedClient = null;
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ListClients.fxml"));
            ListCreditsPane.getChildren().setAll(pane);
        }
    }

    @FXML
    void paiement(ActionEvent event) throws IOException {
        if (!paymentTF.getText().isBlank()&&(Main.selectedClient.getTotalCredits()>=Double.parseDouble(paymentTF.getText()))&&(Double.parseDouble(paymentTF.getText())>0)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ajouter Paiement");
            alert.setContentText("Voulez vous vraiment ajouter ce paiement au client?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new CreditService().insert(
                        new Credit(null,
                                Main.selectedClient.getId(),
                                -Double.parseDouble(paymentTF.getText()),
                                new Date())
                );
                refresh();
            }
            paymentTF.setText("");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Montant Invalide");
            alert.setContentText("Verifiez le montant");
            alert.showAndWait();
        }
        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomText.setText(Main.selectedClient.getFullName());
        numtelText.setText(Main.selectedClient.getPhoneNumber());
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
        ModifMax.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    ModifMax.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    private void refresh() {
        CreditService credits = new CreditService();
        List<Credit> listCredits = credits.findByClientId(Main.selectedClient.getId(), null, null);
        totalText.setText(String.valueOf(listCredits.stream().mapToDouble(credit -> credit.getAmount()).sum()));
        listCreditView.getItems().clear();
        listCreditView.getItems().setAll(listCredits);
        listCreditView.refresh();
        maxText.setText(String.valueOf(Main.selectedClient.getMax()));
    }

    @FXML
    void goToListeClients(ActionEvent event) throws IOException {
        Main.selectedClient = null;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListClients.fxml"));
        ListCreditsPane.getChildren().setAll(pane);
    }
}
