package com.example.demo.user;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {

    private SimpleIntegerProperty id;
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private Role role;

    //constructor
    public User(int id , String username, String password, Role role) {
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.role = role;
    }

    public User() {
    }

    public User(String username, String password, Role role) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.role = role;
    }
    //Seter & Getter

    public void setId(int id) { this.id.set(id); }
    public int getId() { return id.get(); }
    public SimpleIntegerProperty idProperty() { return id; }

    public void setUsername(String username) { this.username.set(username);  }
    public String getUsername() { return username.get(); }
    public SimpleStringProperty usernameProperty() { return username; }

    public void setPassword(String password) { this.password.set(password); }
    public String getPassword() { return password.get(); }
    public SimpleStringProperty passwordProperty() { return password; }

    public void setRole(Role role) { this.role = role; }
    public Role getRole() { return role; }

    public boolean isUserManager(){ return role.getUserManager();}
    public SimpleBooleanProperty userManager(){ return role.getUserManagerProperty();}

    public boolean isClientManager(){ return role.getClientManager();}
    public SimpleBooleanProperty clientManager(){ return role.getClientManagerProperty();}

    public boolean isRealEstateManager(){ return role.getRealEstateManager();}
    public SimpleBooleanProperty realEstateManager(){ return role.getRealEstateManagerProperty();}

    public boolean isChargeManager(){ return role.getChargeManager();}
    public SimpleBooleanProperty chargeManager(){ return role.getChargeManagerProperty();}

    public boolean isTransactionManager(){ return role.getTransactionManager();}
    public SimpleBooleanProperty transactionManager(){ return role.getTransactionManagerProperty();}

}
