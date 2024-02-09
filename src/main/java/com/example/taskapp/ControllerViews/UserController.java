package com.example.taskapp.ControllerViews;

import com.example.taskapp.Controller.TaskController;

public class UserController implements IControllerView{
    private TaskController taskController = new TaskController();
    @Override
    public void setTaskController(TaskController taskController) {
        this.taskController= taskController;

    }
}
