package com.realtion.human.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String image;

    @Column(unique = true)
    private String mobile;

    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @NotEmpty
    @Column
    private String password;

    @Column
    private String status;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String gender;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date dob;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String country;

    @Column
    private Long pin;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "user_relation")
    private List<UserRelation> userRelation;

    @OneToMany(mappedBy = "users2", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "user_relation2")
    private List<UserRelation> userRelation2;

    public List<UserRelation> getUserRelation2() {
        return userRelation2;
    }

    public void setUserRelation2(List<UserRelation> userRelation2) {
        this.userRelation2 = userRelation2;
    }

    public List<UserRelation> getUserRelation() {
        return userRelation;
    }

    public void setUserRelation(List<UserRelation> userRelation) {
        this.userRelation = userRelation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
        this.pin = pin;
    }

}
