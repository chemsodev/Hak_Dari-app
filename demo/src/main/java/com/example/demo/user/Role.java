package com.example.demo.user;

public class Role {
    private boolean userManager;
    private boolean realEstateManager;
    private boolean clientManager;
    private boolean TransactionManager;

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
    public Role(boolean userManager, boolean realEstateManager, boolean clientManager){
        this.userManager = userManager;
        this.realEstateManager = realEstateManager;
        this.clientManager = clientManager;
    }
    public Role(){}

    public boolean getUserManager() { return userManager; }
    public void setUserManager(boolean userManager) { this.userManager = userManager; }

    public boolean getRealEstateManager() { return realEstateManager; }
    public void setRealEstateManager(boolean realEstateManager) { this.realEstateManager = realEstateManager; }

    public boolean getClientManager() { return clientManager; }
    public void setClientManager(boolean clientManager) { this.clientManager = clientManager; }

    public boolean getTransactionManager() { return TransactionManager; }
    public void setTransactionManager(boolean TransactionManager) { this.TransactionManager = TransactionManager; }
}
