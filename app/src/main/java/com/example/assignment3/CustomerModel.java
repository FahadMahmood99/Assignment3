package com.example.assignment3;

import java.io.Serializable;

public class CustomerModel implements Serializable {

    int id;
    String email;
    String password;

    public CustomerModel(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

//    @Override
//    public String toString() {
//        return "customerModel{" +
//                "id=" + id +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
