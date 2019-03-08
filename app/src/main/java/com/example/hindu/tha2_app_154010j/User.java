package com.example.hindu.tha2_app_154010j;

public class User {
    String name,index,mail,mobile,gpa,password;

    public User(String name, String index, String mail, String mobile, String gpa, String password) {
        this.name = name;
        this.index = index;
        this.mail = mail;
        this.mobile = mobile;
        this.gpa = gpa;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
