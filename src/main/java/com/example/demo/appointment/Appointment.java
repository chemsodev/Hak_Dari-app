package com.example.demo.appointment;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment {
    private SimpleIntegerProperty Id;
    private SimpleDateFormat date;
    private SimpleStringProperty description;
    private SimpleIntegerProperty IdClient;
    private SimpleIntegerProperty IdUser; // Agent za3ma

    public Appointment(int id, Date date, String description, int idClient, int idUser){
        this.Id = new SimpleIntegerProperty(id);
        this.description = new SimpleStringProperty(description);
        this.IdClient = new SimpleIntegerProperty(idClient);
        this.IdUser = new SimpleIntegerProperty(idUser);
        this.date = new SimpleDateFormat();
    }

    public Appointment() { }

    public int getId(){ return Id.get(); }
    public SimpleIntegerProperty IdProperty() { return Id; }
    public void setId(int id){ Id.set(id); }

    public String getDescription(){ return description.get();}
    public SimpleStringProperty DescriptionProperty() { return description; }
    public void setDescription(String description){ this.description.set(description); }

    public int getIdClient(){ return IdClient.get(); }
    public SimpleIntegerProperty IdClientProperty() { return IdClient; }
    public void setIdClient(int idClient){ IdClient.set(idClient); }

    public int getIdUser(){ return IdUser.get(); }
    public SimpleIntegerProperty IdUserProperty() { return IdUser; }
    public void setIdUser(int idUser){ IdUser.set(idUser); }

}
