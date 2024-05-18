package com.example.demo.appointment;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Appointment {
    private SimpleIntegerProperty Id;
    private SimpleObjectProperty<LocalDate> date;
    private SimpleStringProperty description;
    private SimpleStringProperty clientFullname;
    private SimpleStringProperty clientPhone;
    private SimpleIntegerProperty IdRealEstate;
    private SimpleIntegerProperty IdUser; // Agent za3ma

    public Appointment(int id, LocalDate date, String description, String clientFullname, String clientPhone, int IdRealEstate, int IdUser){
        this.Id = new SimpleIntegerProperty(id);
        this.description = new SimpleStringProperty(description);
        this.date = new SimpleObjectProperty<>(date);
        this.clientFullname = new SimpleStringProperty(clientFullname);
        this.clientPhone = new SimpleStringProperty(clientPhone);
        this.IdRealEstate = new SimpleIntegerProperty(IdRealEstate);
        this.IdUser = new SimpleIntegerProperty(IdUser);
    }

    public Appointment(LocalDate date, String description, String clientFullname, String clientPhone, int IdRealEstate, int IdUser){
        this.date = new SimpleObjectProperty<>(date);
        this.description = new SimpleStringProperty(description);
        this.clientFullname = new SimpleStringProperty(clientFullname);
        this.clientPhone = new SimpleStringProperty(clientPhone);
        this.IdRealEstate = new SimpleIntegerProperty(IdRealEstate);
        this.IdUser = new SimpleIntegerProperty(IdUser);
    }

    public Appointment() { }

    public int getId(){ return Id.get(); }
    public SimpleIntegerProperty IdProperty() { return Id; }
    public void setId(int id){ Id.set(id); }

    public LocalDate getDate() { return date.get(); }
    public SimpleObjectProperty<LocalDate> dateProperty() { return date; }
    public void setDate(LocalDate date) { this.date.set(date); }

    public String getDescription(){ return description.get();}
    public SimpleStringProperty DescriptionProperty() { return description; }
    public void setDescription(String description){ this.description.set(description); }

    public String getClientFullname(){ return clientFullname.get(); }
    public SimpleStringProperty clientFullnameProperty() { return clientFullname; }
    public void setClientFullname(String clientFullname){ this.clientFullname.set(clientFullname); }

    public String getClientPhone(){ return clientPhone.get(); }
    public SimpleStringProperty clientPhoneProperty() { return clientPhone; }
    public void setClientPhone(String clientPhone){ this.clientPhone.set(clientPhone); }

    public int getIdRealEstate(){ return IdRealEstate.get(); }
    public SimpleIntegerProperty IdRealEstateProperty() { return IdRealEstate; }
    public void setIdRealEstate(int idRealEstate){ IdRealEstate.set(idRealEstate); }

    public int getIdUser(){ return IdUser.get(); }
    public SimpleIntegerProperty IdUserProperty() { return IdUser; }
    public void setIdUser(int idUser){ IdUser.set(idUser); }

}
