package com.example.demo.realEstate;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RealEstate {
    private SimpleIntegerProperty id;
    private SimpleStringProperty title;
    private SimpleIntegerProperty type;
    private SimpleStringProperty description;
    private SimpleDoubleProperty price;
    private SimpleDoubleProperty area;
    private SimpleStringProperty address;
    private SimpleStringProperty status;
    private SimpleIntegerProperty ownerId;

    public RealEstate(int id, String title, int type, String description, double price, double area, String address, String status, int ownerId) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.type = new SimpleIntegerProperty(type);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price);
        this.area = new SimpleDoubleProperty(area);
        this.address = new SimpleStringProperty(address);
        this.status = new SimpleStringProperty(status);
        this.ownerId = new SimpleIntegerProperty(ownerId);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public int getType() {
        return type.get();
    }

    public void setType(int type) {
        this.type.set(type);
    }

    public SimpleIntegerProperty typeProperty() {
        return type;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public double getArea() {
        return area.get();
    }

    public void setArea(double area) {
        this.area.set(area);
    }

    public SimpleDoubleProperty areaProperty() {
        return area;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public int getOwnerId() {
        return ownerId.get();
    }

    public void setOwnerId(int ownerId) {
        this.ownerId.set(ownerId);
    }

    public SimpleIntegerProperty ownerIdProperty() {
        return ownerId;
    }
}