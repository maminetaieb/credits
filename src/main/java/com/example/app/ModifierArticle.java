package com.example.app;

import com.example.entity.Article;
import com.example.service.ArticleService;
import com.example.util.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifierArticle implements Initializable {

    @FXML
    private TableColumn<Article, String> NomC;

    @FXML
    private TableColumn<Article, Double> PrixC;

    @FXML
    private Button ValiderBtn;

    @FXML
    private TableView<Article> articleView;
    ObservableList<Article> listData;

    @FXML
    private AnchorPane modifArticlePane;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prixField;

    @FXML
    void selectArticle(MouseEvent event) {
        Article a = articleView.getSelectionModel().getSelectedItem();
        nomField.setText(a.getTitle());
        prixField.setText(String.valueOf(a.getDefaultPrice()));
    }

    @FXML
    void validerModif(ActionEvent event) {
        Article a = articleView.getSelectionModel().getSelectedItem();
        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez selectionner un article");
            alert.show();
        } else {
            a.setTitle(nomField.getText());
            a.setDefaultPrice(Double.parseDouble(prixField.getText()));
            new ArticleService().modify(a);
            nomField.setText("");
            prixField.setText("");
        }
        refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NomC.setCellValueFactory(
                a -> a.getValue().titleProperty()
        );
        PrixC.setCellValueFactory(
                a -> a.getValue().defaultPriceProperty().asObject()
        );
        articleView.getColumns().setAll(NomC, PrixC);
        refresh();
    }

    public void refresh() {
        ArticleService articles = new ArticleService();
        listData = FXCollections.observableArrayList(articles.find(null, null));
        articleView.getItems().clear();
        articleView.getItems().addAll(listData);
        articleView.refresh();
    }

    @FXML
    void goToListeClients(ActionEvent event) throws IOException {
        Main.selectedClient = null;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListClients.fxml"));
        modifArticlePane.getChildren().setAll(pane);
    }
}
