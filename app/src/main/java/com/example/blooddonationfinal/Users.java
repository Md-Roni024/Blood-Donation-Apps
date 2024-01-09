package com.example.blooddonationfinal;

public class Users {
    String fullname,email,phonenumber,password;

    public Users() {

    }

    public Users(String fullname, String email, String phonenumber, String password) {
        this.fullname = fullname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
