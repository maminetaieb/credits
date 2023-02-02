package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ModifierArticle {

    @FXML
    private TableColumn<?, ?> NomC;

    @FXML
    private TableColumn<?, ?> PrixC;

    @FXML
    private Button ValiderBtn;

    @FXML
    private TableView<?> articleView;

    @FXML
    private AnchorPane modifArticlePane;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prixField;

    @FXML
    void selectArticle(MouseEvent event) {

    }

    @FXML
    void validerModif(ActionEvent event) {

    }

}
