package br.ufrn.tads.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

import br.ufrn.tads.App;
import br.ufrn.tads.servicy.InterfaceLogin;
import br.ufrn.tads.servicy.imp.Login;

public class loginController {
    private InterfaceLogin loginReq = new Login();
    private boolean loginStatus = false;


    @FXML 
    private Text preenchaTodos;

    @FXML
    private Text senhaOuLoginErrados;

    @FXML
    private Text LimiteCar;

    @FXML
    private Text ajuda_click;

    @FXML
    private Text inscrever_click;

    @FXML
    private Button login_btn;

    @FXML
    private TextField login_campus;

    @FXML
    private PasswordField pass_campus;

    @FXML
    void ajuda_screen(MouseEvent event) {

    }

    @FXML
    void inscrever(MouseEvent event) {

    }

    @FXML
    void login(ActionEvent event) {
        if(!pass_campus.getText().isBlank() && !login_campus.getText().isBlank()){
            try {
                loginStatus=loginReq.loginRequest(login_campus.getText(),pass_campus.getText());
                if(loginStatus){
                    System.out.println("Sucesso");
                    try {
                        App.setRoot("menuScreen");
                    } catch (IOException e) {
                        System.out.println("Erro na mudança de tela: "+e);
                    }
                }
                else{
                    System.out.println("Senha ou Usuario Errados");
                    LimiteCar.getStyleClass().removeAll(
                    "Error-invisible",
                    "Error-visible"
                    );
                    senhaOuLoginErrados.getStyleClass().removeAll(
                        "Error-visible",
                        "Error-invisible"
                    );
                    preenchaTodos.getStyleClass().removeAll(
                        "Error-visible",
                        "Error-invisible"
                    );
                    preenchaTodos.getStyleClass().add("Error-invisible");
                    LimiteCar.getStyleClass().add("Error-invisible");
                    senhaOuLoginErrados.getStyleClass().add("Error-visible");
                }
            } catch (IndexOutOfBoundsException e) {
                LimiteCar.getStyleClass().removeAll(
                    "Error-invisible",
                    "Error-visible"
                );
                senhaOuLoginErrados.getStyleClass().removeAll(
                    "Error-visible",
                    "Error-invisible"
                );
                preenchaTodos.getStyleClass().removeAll(
                        "Error-visible",
                        "Error-invisible"
                    );
                preenchaTodos.getStyleClass().add("Error-invisible");
                senhaOuLoginErrados.getStyleClass().add("Error-invisible");
                LimiteCar.getStyleClass().add("Error-visible");
                System.out.println("Mais de 20 car");
            }
            
        }
        else{
            System.out.println("Preencha Todos os campos");
                    LimiteCar.getStyleClass().removeAll(
                    "Error-invisible",
                    "Error-visible"
                    );
                    senhaOuLoginErrados.getStyleClass().removeAll(
                        "Error-visible",
                        "Error-invisible"
                    );
                    preenchaTodos.getStyleClass().removeAll(
                        "Error-visible",
                        "Error-invisible"
                    );
                    LimiteCar.getStyleClass().add("Error-invisible");
                    senhaOuLoginErrados.getStyleClass().add("Error-invisible");
                    preenchaTodos.getStyleClass().add("Error-visible");
        }
    }

}
