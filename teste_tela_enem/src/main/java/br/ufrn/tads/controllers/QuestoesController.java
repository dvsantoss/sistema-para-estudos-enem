package br.ufrn.tads.controllers;

import br.ufrn.tads.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import br.ufrn.tads.servicy.imp.Login;

import java.io.IOException;
import java.util.Optional;

public class QuestoesController {

    @FXML
    public void initialize() {
        nomeUser.setText(Login.getUserAtual().getName());
    }
    
    @FXML
    private Button btn_ajuda;

    @FXML
    private Button btn_calendario;

    @FXML
    private Button btn_cnt;

    @FXML
    private Button btn_lct;

    @FXML
    private Button btn_menu;

    @FXML
    private Button btn_mt;

    @FXML
    private Button btn_provas;

    @FXML
    private Button btn_questoes;

    @FXML
    private Button cht;

    @FXML
    private Text nomeUser;

    @FXML
    void ajuda_screen(ActionEvent event) {

    }

    @FXML
    void calendario__screen(ActionEvent event) {

    }

    @FXML
    void cht_function(ActionEvent event)throws IOException {
        showConfirmationDialog();
        ResponderController.setProvaGeral(false);
        ResponderController.setTemaAtual("ciencias-humanas");
        App.setRoot("responderScreen");
    }

    @FXML
    void cnt_function(ActionEvent event)throws IOException {
        showConfirmationDialog();
        ResponderController.setProvaGeral(false);
        ResponderController.setTemaAtual("ciencias-natureza");
        App.setRoot("responderScreen");
    }

    @FXML
    void lct_function(ActionEvent event)throws IOException {
        showConfirmationDialog();
        ResponderController.setProvaGeral(false);
        ResponderController.setTemaAtual("linguagens");
        App.setRoot("responderScreen");
    }

    @FXML
    void menu_screen(ActionEvent event) throws IOException {
        App.setRoot("menuScreen");
    }

    @FXML
    void mt_function(ActionEvent event)throws IOException {
        showConfirmationDialog();
        ResponderController.setProvaGeral(false);
        ResponderController.setTemaAtual("matematica");
        App.setRoot("responderScreen");
    }

    @FXML
    void prova_screen(ActionEvent event) throws IOException{
        App.setRoot("provaScreen");
    }

    @FXML
    void questoes_screen(ActionEvent event) {

    }

    private void showConfirmationDialog() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Dicas De Topicos");
        alert.setHeaderText("Deseja Ser Informado De Qual é o Topico Da Questão?");
        alert.setContentText("Ao clicar em OK, a ação será executada.");

        // Mostra o diálogo e aguarda a resposta do usuário
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
                ResponderController.setAtivadoTopico(true);
        } else {
                ResponderController.setAtivadoTopico(false);
            }
    }

}
