package com.realtion.human.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserRelation {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String relation;

    @Column
    private Date doc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_1")
    @JsonBackReference(value = "user_relation")
    private Users users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_2")
    @JsonBackReference(value = "user_relation2")
    private Users users2;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Users getUsers2() {
        return users2;
    }

    public void setUsers2(Users users2) {
        this.users2 = users2;
    }
}
