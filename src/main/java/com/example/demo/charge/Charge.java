package com.example.demo.charge;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Charge {
    private  SimpleIntegerProperty charge_id;
    private  SimpleStringProperty Title;
    private SimpleStringProperty Description;
    private  SimpleDoubleProperty Total;

    public Charge(String title, String description, double total) {
        Title = new SimpleStringProperty(title);
        Description = new SimpleStringProperty(description);
        Total = new SimpleDoubleProperty(total);
    }

    public Charge(int charge_id, String description, String title, Double total) {
        this.charge_id = new SimpleIntegerProperty(charge_id);
        Description =new SimpleStringProperty(description);
        Title =new SimpleStringProperty(title);
        Total = new SimpleDoubleProperty(total);
    }


    public String getTitle() {
        return Title.get();
    }

    public SimpleStringProperty titleProperty() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title.set(title);
    }

    public String getDescription() {
        return Description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description.set(description);
    }

    public double getTotal() {
        return Total.get();
    }

    public SimpleDoubleProperty totalProperty() {
        return Total;
    }

    public void setTotal(Double total) {
        this.Total.set(total);
    }

    public int getCharge_id() {
        return charge_id.get();
    }

    public SimpleIntegerProperty charge_idProperty() {
        return charge_id;
    }

    public void setCharge_id(int charge_id) {
        this.charge_id.set(charge_id);
    }

    public SimpleStringProperty DescriptionProperty() {
        return Description;
    }
}
