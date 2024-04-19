package com.example.demo.client;

import com.example.demo.user.User;

public class Client {
    private int id;
    private String fullname;
    private String email;
    private String  phone;

    public Client(int id, String fullname, String email, String phone) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

}
