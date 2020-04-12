package com.realtion.human.model;

public class RelationUserModel {
    private UsersModel user;
    private String relation;


    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public UsersModel getUser() {
        return user;
    }

    public void setUser(UsersModel user) {
        this.user = user;
    }
}
