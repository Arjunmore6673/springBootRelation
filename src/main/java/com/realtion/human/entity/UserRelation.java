package com.realtion.human.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class UserRelation {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Users user1;

    @Column
    private Users user2;

    @Column
    private String relation;

    @Column
    private Date doc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser1() {
        return user1;
    }

    public void setUser1(Users user1) {
        this.user1 = user1;
    }

    public Users getUser2() {
        return user2;
    }

    public void setUser2(Users user2) {
        this.user2 = user2;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Date getDoc() {
        return doc;
    }

    public void setDoc(Date doc) {
        this.doc = doc;
    }
}
