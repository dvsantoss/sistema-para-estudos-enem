package br.ufrn.tads.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

import br.ufrn.tads.App;
import br.ufrn.tads.servicy.imp.*;
public class TelaMenuController {


    @FXML
    private Text nomeUser;

    @FXML
    public void initialize() {
        nomeUser.setText(Login.getUserAtual().getName());
    }

    @FXML
    private Button btn_ajuda;

    @FXML
    private Button btn_calendario;

    @FXML
    private Button btn_menu;

    @FXML
    private Button btn_provas;

    @FXML
    private Button btn_questoes;

    @FXML
    void ajuda_screen(ActionEvent event) {

    }

    @FXML
    void calendario__screen(ActionEvent event) {

    }

    @FXML
    void menu_screen(ActionEvent event) {

    }

    @FXML
    void prova_screen(ActionEvent event) throws IOException{
        App.setRoot("provaScreen");
    }

    @FXML
    void questoes_screen(ActionEvent event) throws IOException{
        App.setRoot("questoesScreen");
    }
}
