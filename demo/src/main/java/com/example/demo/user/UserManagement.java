package com.example.demo.user;

public class UserManagement {

    //Create user
    public int createUser(String username, String password, String role, User user){
        if(user.getRole().getUserManager()){
            //insert into db
            return 1;
        }
        return 0;
    }
    //lazm methode listing mn db

}
