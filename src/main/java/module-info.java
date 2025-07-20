module com.example.myuno {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.myuno to javafx.fxml;
    opens com.example.myuno.controller to javafx.fxml;
    exports com.example.myuno;
}