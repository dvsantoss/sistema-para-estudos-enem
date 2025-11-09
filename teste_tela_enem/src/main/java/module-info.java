module br.ufrn.tads {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    
    opens br.ufrn.tads.controllers to javafx.fxml;
    exports br.ufrn.tads;
}
