package com.example.taskapp.ControllerViews;

import com.example.taskapp.Controller.TaskController;

public class AdminController implements IControllerView{
    private TaskController taskController;
    @Override
    public void setTaskController(TaskController taskController) {
        this.taskController= taskController;

    }
}
