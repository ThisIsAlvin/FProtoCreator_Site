package com.viv.entity;

/**
 * Created by viv on 16-4-24.
 */
public class User_project {
    private int id;
    private User user;
    private Project_info project_info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project_info getProject_info() {
        return project_info;
    }

    public void setProject_info(Project_info project_info) {
        this.project_info = project_info;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
