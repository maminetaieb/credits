package com.example.app;

import com.example.entity.Client;
import com.example.service.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class ListClients implements Initializable {
    protected static Client selectedClient;
    @FXML
    private Button HistoriqueBtn;

    @FXML
    private Button chercherBtn;

    @FXML
    private TextField chercherField;

    @FXML
    private TableColumn<Client, Double> creditsC;

    @FXML
    private TableView<Client> listClient;
    private ObservableList<Client> listData;

    @FXML
    private AnchorPane listClientPane;

    @FXML
    private TableColumn<Client, String> nomC;

    @FXML
    private Button nouveauClientBtn;

    @FXML
    private TableColumn<Client, String> numTelC;

    @FXML
    private Button sedeconnecterBtn;

    @FXML
    private Button voirClientBtn;

    @FXML
    void historique(ActionEvent event) {

    }

    @FXML
    void chercherClient(ActionEvent event) {
        ClientService clients = new ClientService();
        if (chercherField.getText().isBlank()) {
            listData = FXCollections.observableArrayList(clients.find(null, null));
        } else {
            try {
                Integer.parseInt(chercherField.getText());
                listData = FXCollections.observableArrayList(
                        clients.findByPhoneNumber(chercherField.getText(), null, null)
                );
            } catch (Exception e) {
                listData = FXCollections.observableArrayList(
                        clients.findByFullName(chercherField.getText(), null, null)
                );
                listClient.getItems().clear();
                listClient.getItems().addAll(listData);
                listClient.refresh();
            }
        }
        listClient.getItems().clear();
        listClient.getItems().addAll(listData);
        listClient.refresh();
    }

    @FXML
    void chercherClientField(ActionEvent event) {

    }

    @FXML
    void nouveauClient(ActionEvent event) {

    }

    @FXML
    void voirClient(ActionEvent event) throws IOException {
        selectedClient = listClient.getSelectionModel().getSelectedItem();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListCredits.fxml"));
        listClientPane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomC.setCellValueFactory(
                c -> c.getValue().fullNameProperty()
        );
        numTelC.setCellValueFactory(
                c -> c.getValue().phoneNumberProperty()
        );
        creditsC.setCellValueFactory(
                c -> c.getValue().totalCreditsProperty().asObject()
        );
        listClient.getColumns().setAll(nomC, numTelC, creditsC);
        chercherClient(null);
    }
}
