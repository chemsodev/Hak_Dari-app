package com.example.demo.user;

public class Role {
    private boolean userManager;
    private boolean realEstateManager;
    private boolean clientManager;

    public Role (String roleName){
        switch (roleName) {
            case "admin" -> {
                userManager = true;
                realEstateManager = true;
                clientManager = true;
            }
            case "agent" -> {
                userManager = false;
                realEstateManager = true;
                clientManager = false;
            }
            case "user" -> {
                userManager = false;
                realEstateManager = false;
                clientManager = false;
            }
        }
    }
    public Role(){}

    public boolean getUserManager() { return userManager; }
    public boolean getRealEstateManager() { return realEstateManager; }
    public boolean getClientManager() { return clientManager; }

}
