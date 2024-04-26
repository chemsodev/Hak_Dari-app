package com.example.demo.realEstate;

import com.example.demo.user.User;

public class RealEstate {
    private int id;
    private String title;
    private String type; //on peut utiliser des entier 1 -> Residentiel ,2 -> commercial , 3 ....
    private String description;
    //photo
    // a vendre ou location
    private double price;
    private double area;//m2
    private String address;
    private String status;
    private int ownerId;

    //constructor


    //setter & getter
    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }

    public void setType(String type) { this.type = type; }
    public String getType() { return type; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public void setPrice(double price) { this.price = price; }
    public double getPrice() { return price; }

    public void setArea(double area) { this.area = area; }
    public double getArea() { return area; }

    public void setAddress(String address) { this.address = address; }
    public String getAddress() { return address; }

    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; }

    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }
    public int getOwnerId() { return ownerId; }

}
