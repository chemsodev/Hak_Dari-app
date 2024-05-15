package com.example.demo.user;

import javafx.beans.property.SimpleBooleanProperty;

public class Role {

    private SimpleBooleanProperty userManager;
    private SimpleBooleanProperty realEstateManager;
    private SimpleBooleanProperty clientManager;
    private SimpleBooleanProperty transactionManager;
    private SimpleBooleanProperty chargeManager;


    public Role(boolean userManager, boolean realEstateManager, boolean clientManager, boolean TransactionManager, boolean chargeManager){
        this.userManager = new SimpleBooleanProperty(userManager);
        this.realEstateManager = new SimpleBooleanProperty(realEstateManager);
        this.clientManager = new SimpleBooleanProperty(clientManager);
        this.chargeManager = new SimpleBooleanProperty(chargeManager);
        this.transactionManager = new SimpleBooleanProperty(TransactionManager);
    }

    public Role(){}

    public boolean getUserManager() { return userManager.get(); }
    public void setUserManager(boolean userManager) { this.userManager.set(userManager); }
    public SimpleBooleanProperty getUserManagerProperty() { return userManager; }

    public boolean getRealEstateManager() { return realEstateManager.get(); }
    public void setRealEstateManager(boolean realEstateManager) { this.realEstateManager.set(realEstateManager); }
    public SimpleBooleanProperty getRealEstateManagerProperty() { return realEstateManager; }

    public boolean getClientManager() { return clientManager.get(); }
    public void setClientManager(boolean clientManager) { this.clientManager.set(clientManager); }
    public SimpleBooleanProperty getClientManagerProperty() { return clientManager; }

    public boolean getTransactionManager() { return transactionManager.get(); }
    public void setTransactionManager(boolean TransactionManager) { this.transactionManager.set(TransactionManager); }
    public SimpleBooleanProperty getTransactionManagerProperty() { return transactionManager; }

    public boolean getChargeManager() {return chargeManager.get();}
    public void setChargeManager(boolean chargeManager) { this.chargeManager.set(chargeManager);}
    public SimpleBooleanProperty getChargeManagerProperty() { return chargeManager; }
}
