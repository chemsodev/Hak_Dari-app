package com.example.demo.historique;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HistoriqueClient {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleObjectProperty<LocalDateTime> dateTime;
    private SimpleIntegerProperty userId = new SimpleIntegerProperty();
    private SimpleIntegerProperty clientId = new SimpleIntegerProperty();

    public HistoriqueClient(int id, String description, LocalDateTime date, int userId, int clientId) {
        this.id = new SimpleIntegerProperty(id);
        this.description = new SimpleStringProperty(description);
        this.dateTime = new SimpleObjectProperty<>(date);
        this.userId = new SimpleIntegerProperty(userId);
        this.clientId = new SimpleIntegerProperty(clientId);
    }
    public HistoriqueClient(){}

    // Getter and Setter for id
    public int getId() {return id.get();}
    public void setId(int id) {this.id.set(id);}
    public SimpleIntegerProperty idProperty() {return id;}

    // Getter and Setter for description
    public String getDescription() {return description.get();}
    public void setDescription(String description) {this.description.set(description);}
    public SimpleStringProperty descriptionProperty() {return description;}

    // Getter and Setter for dateTime
    public LocalDateTime getDateTime() {return dateTime.get();}
    public void setDateTime(LocalDateTime dateTime) {this.dateTime.set(dateTime);}
    public SimpleObjectProperty<LocalDateTime> dateTimeProperty() {return dateTime;}

    // Getter and Setter for userId
    public int getUserId() {return userId.get();}
    public void setUserId(int userId) {this.userId.set(userId);}
    public SimpleIntegerProperty userIdProperty() {return userId;}

    // Getter and Setter for clientId
    public int getClientId() {return clientId.get();}
    public void setClientId(int clientId) {this.clientId.set(clientId);}
    public SimpleIntegerProperty clientIdProperty() {return clientId; }

}
