module br.ufrn.tads {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    exports br.ufrn.tads.model;
    opens br.ufrn.tads.controllers to javafx.fxml;
    exports br.ufrn.tads;
}
