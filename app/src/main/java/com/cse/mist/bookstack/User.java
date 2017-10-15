package com.cse.mist.bookstack;

/**
 * Created by User on 8/18/2017.
 */

public class User {

    public String uname;
    public String uni;
    public String roll;
    public String email;
    public String password;
    public String number;
    // public String id;

    public User() {

    }

    public User(String uname, String uni, String roll, String email, String password, String number) {
        this.uname = uname;
        this.uni = uni;
        this.roll = roll;
        this.email = email;
        this.password = password;
        this.number = number;

    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

