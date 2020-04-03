package com.realtion.human.model;

import java.util.List;

public class UserListModel {
    private UsersModel user;
    private List<UsersModel> relatives;

    public UsersModel getUser() {
        return user;
    }

    public void setUser(UsersModel user) {
        this.user = user;
    }

    public List<UsersModel> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<UsersModel> relatives) {
        this.relatives = relatives;
    }
}
