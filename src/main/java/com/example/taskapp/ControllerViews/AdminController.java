package com.example.taskapp.ControllerViews;

import com.example.taskapp.Controller.TaskController;
import com.example.taskapp.Models.Rol;
import com.example.taskapp.Models.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.util.List;

public class AdminController extends ControllerView {
    @FXML
    protected TableView<User> tbUsers;
    @FXML
    protected TableColumn<User, Integer> idColumn;
    @FXML
    protected TableColumn<User, String> usernameColumn;
    @FXML
    protected TableColumn<User, String> rolColumn;
    @FXML
    protected TextField txtUsername;
    @FXML
    protected TextField txtPassword;
    @FXML
    protected PasswordField txtRePassword;
    @FXML
    protected ComboBox<Rol> comboRol;
    @FXML
    protected Label idlabel;
    @FXML
    protected Button create;
    @FXML
    protected  Button update;

    @FXML
    protected Button delete;

    private ObservableList<User> observableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getIduser()));
        usernameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getUsername()));
        rolColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getRol().getDescription()));
        comboRol.setConverter(new StringConverter<Rol>() {
            @Override
            public String toString(Rol rol) {
                if (rol != null)
                    return rol.getDescription();
                else return "Select one";
            }

            @Override
            public Rol fromString(String s) {
                return null;
            }
        });

        tbUsers.setOnMouseClicked(e->{
            if(e.getClickCount()==1) {
                User user = tbUsers.getSelectionModel().getSelectedItem();
                txtUsername.setText(user.getUsername());
                comboRol.setValue(user.getRol());
                create.setVisible(false);
                update.setVisible(true);
                delete.setVisible(true);

            }
            });
    }

    public AdminController() {

    }

    @Override
    public void cargaInicial() {
        List<User> userList = taskController.getAllUser();
        observableList.addAll(userList);
        tbUsers.setItems(observableList);
        List<Rol> rolList = taskController.getAllRol();
        comboRol.getItems().addAll(rolList);

    }

    public void createUser(ActionEvent actionEvent) {
        if (txtPassword.getText().equals(txtRePassword.getText())) {
            taskController.createUser(txtUsername.getText(),txtPassword.getText(),comboRol.getSelectionModel().getSelectedItem().getIdrol());
            List<User> userList = taskController.getAllUser();
            observableList.clear();
            observableList.addAll(userList);
            tbUsers.refresh();


        } else {
            idlabel.setText("Password must be equals");
        }
    }

    public void btnUpdateUser(ActionEvent actionEvent) {
        if(txtPassword.getText().equals(txtRePassword.getText())){
            User user = tbUsers.getSelectionModel().getSelectedItem();
            user.setPassword(txtPassword.getText());
            user.setRol(comboRol.getSelectionModel().getSelectedItem());
            taskController.updateUser(user);
        }else{
            idlabel.setText("Passwords has to be equals");
        }
       // taskController.updateUser();
        create.setVisible(true);
        update.setVisible(false);


    }


    public void btnDelete(ActionEvent actionEvent) {
        User user = tbUsers.getSelectionModel().getSelectedItem();
        observableList.remove(user);
        tbUsers.refresh();
    }
}
