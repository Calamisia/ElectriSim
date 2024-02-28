module com.example.electriccircuit {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.electriccircuit to javafx.fxml;
    exports com.example.electriccircuit;
}