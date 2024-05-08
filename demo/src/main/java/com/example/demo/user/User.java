package com.example.demo.user;

public class User {
    private int id;
    private String username;
    private String password;
    private Role role;

    //constructor

    //Seter & Getter


    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setUsername(String username) { this.username = username;  }
    public String getUsername() { return username; }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }

    public void setRole(Role role) { this.role = role; }
    public Role getRole() { return role; }

}
