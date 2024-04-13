package com.example.demo;

public class User {

    private int ID;
    private String Name;
    private String password;
    private String Role;

    public int getID() {return ID; }
    public void setID(int ID) { this.ID = ID; }

    public String getName() { return Name; }
    public void setName(String Name) { this.Name = Name; }

    public String getPassword(){ return password; }
    public void setPassword (String password){ this.password = password; }

    public String getRole(){ return Role; }
    public void setRole(String Role) { this.Role = Role; }
}
