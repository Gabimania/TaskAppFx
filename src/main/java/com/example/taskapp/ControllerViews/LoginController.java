package com.example.taskapp.ControllerViews;

import com.example.taskapp.Controller.TaskController;
import com.example.taskapp.TaskApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginController {
    @FXML
private TextField txtUsuario;
    @FXML
private PasswordField password;
    @FXML
    private Label info;
@FXML
    public void loginAction(ActionEvent actionEvent) {
    TaskController taskController = new TaskController();
    if(taskController.login(txtUsuario.getText(), password.getText())) {
        String view ="";
        String titleWindow;
        if (taskController.isAdmin()) {
            view = "admin-view.fxml";
            titleWindow = "Admin";

        } else {
            view ="user-view.fxml";
            titleWindow = "User";

        }
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApplication.class.getResource(view));
        try {
            Parent root = fxmlLoader.load();
            ControllerView controller = fxmlLoader.getController();
            controller.setTaskController(taskController);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle(titleWindow);
            stage.setScene(scene);
            stage.show();
            Node source = (Node) actionEvent.getSource();
            Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }else{
info.setText("El usuario no existe");

    }
}
}
