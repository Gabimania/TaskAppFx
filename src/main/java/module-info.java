module com.example.taskapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.taskapp to javafx.fxml;
    exports com.example.taskapp;
    exports com.example.taskapp.ControllerViews;
    opens com.example.taskapp.ControllerViews to javafx.fxml;
}