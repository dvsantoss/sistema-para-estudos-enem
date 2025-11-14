package br.ufrn.tads.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import br.ufrn.tads.servicy.InterfaceLogin;
import br.ufrn.tads.servicy.imp.Login;

public class loginController {
    private InterfaceLogin loginReq = new Login();
    private boolean loginStatus = false;

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
                }
                else{
                    System.out.println("Senha ou Usuario Errados");
                }
            } catch (IndexOutOfBoundsException e) {
                LimiteCar.getStyleClass().removeAll(
                    "texto-limite-caracter-visible", 
                    "texto-limite-caracter-invisible"
                );
                LimiteCar.getStyleClass().add("texto-limite-caracter-visible");
                System.out.println("Mais de 10 car");
            }
            
        }
        else{
            System.out.println("Error");
        }
    }

}
