package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String role;
    public User(){}
    public User(String firstName,String lastName,String username,String password,String role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
}
    @Override
    public String toString(){
        return "Username : " + username + ", Role : " + role ;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {return username;}

    public void setUsername(String Username) {this.username = Username;}

    public String getPassword() {return password;}

    public void setPassword(String Password) {this.password = Password;}

    public String getRole() {return role;}

    public void setRole(String Role) {this.role = Role;}
}
