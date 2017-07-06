package com.example.filote_cosmin.timemanager.Model;

import java.util.List;

/**
 * Created by Filote Cosmin on 7/5/2017.
 */




public class User {
    private String first_name;
    private String last_name;
    private int   role;
    private int id_company;
    private String email;
    private String address;
    private String password;
    private String phone;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getId_company() {
        return id_company;
    }

    public void setId_company(int id_company) {
        this.id_company = id_company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(String first_name, String last_name, int role, int id_company, String email, String address, String password, String phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
        this.id_company = id_company;
        this.email = email;
        this.address = address;
        this.password = password;
        this.phone = phone;
    }

    public User(String email, String password) {
        this.first_name = "";
        this.last_name = "";
        this.role = 0;
        this.id_company = 0;
        this.email = email;
        this.address = "";
        this.password = password;
        this.phone = "";
    }

}
