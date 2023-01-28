package com.example.app;

import com.example.entity.Article;
import com.example.entity.Client;
import com.example.service.ArticleService;
import com.example.service.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AjouterArticle {

    @FXML
    private AnchorPane AjouterArticlePane;

    @FXML
    private TextField NomField;

    @FXML
    private TextField PrixField;

    @FXML
    private Button ValiderBtn;

    @FXML
    void valider(ActionEvent event) {
        if((NomField.getText().isBlank())||  (PrixField.getText().isBlank())||(Double.parseDouble(PrixField.getText()) < 0)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("failed!");
            alert.setHeaderText(null);
            alert.setContentText("Verifier les champs");

            alert.showAndWait();

        }
        else {
            new ArticleService().insert(new Article(null, NomField.getText(), Double.parseDouble(PrixField.getText())));
            String title = "Succés!";
            String message = "  Article ajouté";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            // alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Article ajouté");

            alert.showAndWait();
            NomField.setText("");
            PrixField.setText("");

        }

    }

}
