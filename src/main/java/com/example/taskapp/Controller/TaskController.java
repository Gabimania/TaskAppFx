package com.example.taskapp.Controller;
import com.example.taskapp.Models.Task;
import com.example.taskapp.Models.User;

import java.time.LocalDate;
import java.util.List;

public class TaskController {
    public User userLogged;

    public boolean login(String username,String password){

        User user=new User();
        userLogged=user.login(username,password);
        if(userLogged!=null){
            return true;
        }else {
            return false;
        }

    }
    public boolean createUser(String username,String pass,int rol){
        User user=new User();
        return user.insertar("(username,password,idrol) values (?,?,?)",username,pass,rol);
    }

    public boolean editPassword(String username,String password){
        User user=new User();
        return user.actualizar("password=? where username=?",password,username);
    }

    public boolean createTask(String title, String description, LocalDate deadline){
        Task task = new Task();
        return task.insertar("(title, description, deadline,iduser) values(?,?,?,?)", title, description,deadline,userLogged.getIduser());

    }

    public List<Task> getAllTasksByUser(){
        Task task = new Task();
        return task.getAllByUser(userLogged.getIduser());
    }

    public List <Task> getAllTask(){
        Task task = new Task();
        return task.getAllTasks();
        
    }

    public boolean updateStatusTask(int id_task){
        return new Task().actualizar("status = 1 where idtask = ?", id_task);

    }

    public boolean deteletask(int idtask){
        return new Task().borrar("idtask= ?", idtask);
    }


    public boolean isAdmin() {
        return userLogged.getRol().getIdrol()==2?true:false;
    }
}
