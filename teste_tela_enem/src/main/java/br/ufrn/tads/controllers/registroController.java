package br.ufrn.tads.controllers;

import java.io.IOException;

import br.ufrn.tads.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class registroController {

    @FXML
    private Text ajuda_click;

    @FXML
    private TextField email_campus;

    @FXML
    private TextField login_campus;

    @FXML
    private Text login_click;

    @FXML
    private PasswordField pass_campus;

    @FXML
    private Button registro_btn;

    @FXML
    void ajuda_screen(MouseEvent event) {

    }

    @FXML
    void email_register(ActionEvent event) {

    }

    @FXML
    void login_page(MouseEvent event) throws IOException{
        App.setRoot("login");
    }

    @FXML
    void login_register(ActionEvent event) {

    }

    @FXML
    void password_register(ActionEvent event) {

    }

    @FXML
    void registrar(ActionEvent event) {

    }

}
