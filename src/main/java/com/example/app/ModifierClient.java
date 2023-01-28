package com.example.app;

import com.example.entity.Article;
import com.example.entity.ClientArticle;
import com.example.service.ArticleService;
import com.example.service.ClientArticleService;
import com.example.service.ClientService;
import com.example.util.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifierClient implements Initializable {

    @FXML
    private AnchorPane ModifClientPane;

    @FXML
    private Button ModifPrixBtn;

    @FXML
    private TableView<Article> articleTable;

    @FXML
    private TableColumn<Article, String> NomC;

    @FXML
    private TableColumn<Article, Double> PrixC;

    @FXML
    private TextField PrixField;

    @FXML
    private Button ValiderBtn;

    @FXML
    private TextField maxField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField numTelField;
    private ClientArticle cca;

    @FXML
    void max(ActionEvent event) {

    }

    @FXML
    void modiferPrix(ActionEvent event) {
        if (cca != null) {
            if (PrixField.getText().isBlank()) {
                new ClientArticleService().delete(cca);
                cca = null;
            } else {
                cca.setCustomPrice(Double.parseDouble(PrixField.getText()));
                new ClientArticleService().modify(cca);
            }
        } else {
            if (PrixField.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez saisir un montant");
                alert.show();
            } else {
                new ClientArticleService().insert(
                        new ClientArticle(
                                Main.selectedClient.getId(),
                                articleTable.getSelectionModel().getSelectedItem().getId(),
                                Double.parseDouble(PrixField.getText()
                                )
                        )
                );
            }
        }
        refresh();
        selectArticle(null);
    }

    @FXML
    void nom(ActionEvent event) {

    }

    @FXML
    void numTel(ActionEvent event) {

    }

    @FXML
    void validerModification(ActionEvent event) throws IOException {
        if (maxField.getText().isBlank() || nomField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("champs vide");
            alert.setContentText("Veuillez Remplir les champs nécessaires");
            alert.show();
        } else if(Main.selectedClient.getTotalCredits()<=Double.parseDouble(maxField.getText())) {
            Main.selectedClient.setMax(Double.parseDouble(maxField.getText()));
            new ClientService().modify(Main.selectedClient);
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ListClients.fxml"));
            ModifClientPane.getChildren().setAll(pane);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            // alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le montant ajouté est inférieur au total des crédits du client");
            alert.show();
        }
    }

    @FXML
    void selectArticle(MouseEvent event) {
        ClientArticleService clientArticles = new ClientArticleService();
        cca = clientArticles.findOne(Main.selectedClient.getId(), articleTable.getSelectionModel().getSelectedItem().getId());
        if (PrixField.getText().isBlank() && cca != null) {
            ModifPrixBtn.setText("Set Default");
        }
        ModifPrixBtn.setDisable(false);
        PrixField.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomField.setText(Main.selectedClient.getFullName());
        numTelField.setText(Main.selectedClient.getPhoneNumber());
        maxField.setText(String.valueOf(Main.selectedClient.getMax()));
        ModifPrixBtn.setDisable(true);
        PrixField.setDisable(true);
        NomC.setCellValueFactory(
                a -> a.getValue().titleProperty()
        );
        PrixC.setCellValueFactory(
                a -> a.getValue().defaultPriceProperty().asObject()
        );
        articleTable.getColumns().setAll(NomC, PrixC);
        articleTable.getItems().clear();
        articleTable.getItems().addAll(new ArticleService().find(null, null));
        refresh();
    }

    public void refresh() {
        ClientArticleService clientArticles = new ClientArticleService();
        for (int i=0; i < articleTable.getItems().size(); i++) {
            ClientArticle ca = clientArticles.findOne(Main.selectedClient.getId(), articleTable.getItems().get(i).getId());
            if (ca != null) {
                articleTable.getItems().get(i).setDefaultPrice(ca.getCustomPrice());
            }
        }
        articleTable.refresh();
    }
}
