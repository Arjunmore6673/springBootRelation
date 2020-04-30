package com.realtion.human.model;

import java.util.Date;
import java.util.Objects;

public class UsersModel {

    private Long id;
    private String image;
    private String mobile;
    private String email;
    private String status;
    private Date dob;
    private String name;
    private String gender;
    private String relation;
    private String doc;
    private String code;
    private String address;
    private Object hisRelations;
    private String firebaseId;
    private String firebaseToken;


    public Object getHisRelations() {
        return hisRelations;
    }

    public void setHisRelations(Object hisRelations) {
        this.hisRelations = hisRelations;
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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersModel)) return false;
        UsersModel that = (UsersModel) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getImage(), that.getImage()) &&
                Objects.equals(getMobile(), that.getMobile()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getDob(), that.getDob()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getGender(), that.getGender()) &&
                Objects.equals(getRelation(), that.getRelation()) &&
                Objects.equals(getDoc(), that.getDoc()) &&
                Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getHisRelations(), that.getHisRelations()) &&
                Objects.equals(getFirebaseId(), that.getFirebaseId()) &&
                Objects.equals(getFirebaseToken(), that.getFirebaseToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getImage(), getMobile(), getEmail(), getStatus(), getDob(), getName(), getGender(), getRelation(), getDoc(), getCode(), getAddress(), getHisRelations(), getFirebaseId(), getFirebaseToken());
    }
}
