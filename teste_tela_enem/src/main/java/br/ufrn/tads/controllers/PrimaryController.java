package br.ufrn.tads.controllers;

import java.io.IOException;

import br.ufrn.tads.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
