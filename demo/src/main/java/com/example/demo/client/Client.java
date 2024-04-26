package com.example.demo.client;

import com.example.demo.user.User;

public class Client {
    private int id;
    private String fullname;
    private String email;
    private String password;
    private String  phone;

    public Client(int id, String fullname,String password, String email, String phone) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

}
