package br.ufrn.tads.controllers;

import java.io.IOException;

import br.ufrn.tads.App;
import br.ufrn.tads.servicy.imp.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ProvaController{

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
    void menu_screen(ActionEvent event) throws IOException {
        App.setRoot("menuScreen");
    }


    @FXML
    void questoes_screen(ActionEvent event)throws IOException {
        App.setRoot("provaScreen");
    }

}
