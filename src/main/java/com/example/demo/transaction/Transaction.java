package com.example.demo.transaction;

import com.example.demo.Database;
import com.example.demo.client.Client;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Transaction {

    private SimpleIntegerProperty id;
    private SimpleStringProperty type;
    private SimpleObjectProperty<LocalDate> date;
    private SimpleDoubleProperty prix;
    private SimpleDoubleProperty frais;
    private SimpleStringProperty methodePaiement;
    private SimpleStringProperty statut;
    private SimpleStringProperty note;
    private SimpleIntegerProperty id_Client;
    private SimpleIntegerProperty id_Propriete;

    public Transaction(int id,LocalDate date,String type,double prix,double frais,String methodePaiement,String statut,String note,int id_Client,int id_Propriete) {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleObjectProperty<>(date);
        this.type = new SimpleStringProperty(type);
        this.prix = new SimpleDoubleProperty(prix);
        this.frais = new SimpleDoubleProperty(frais);
        this.methodePaiement = new SimpleStringProperty(methodePaiement);
        this.statut = new SimpleStringProperty(statut);
        this.note = new SimpleStringProperty(note);
        this.id_Client = new SimpleIntegerProperty(id_Client);
        this.id_Propriete = new SimpleIntegerProperty(id_Propriete);
    }

    public Transaction(int id,String type,double prix,double frais,String methodePaiement,String statut,String note,int id_Client,int id_Propriete) {
        this.id = new SimpleIntegerProperty(id);
        this.type = new SimpleStringProperty(type);
        this.prix = new SimpleDoubleProperty(prix);
        this.frais = new SimpleDoubleProperty(frais);
        this.methodePaiement = new SimpleStringProperty(methodePaiement);
        this.statut = new SimpleStringProperty(statut);
        this.note = new SimpleStringProperty(note);
        this.id_Client = new SimpleIntegerProperty(id_Client);
        this.id_Propriete = new SimpleIntegerProperty(id_Propriete);
    }

    public void setId(int id) {
        this.id.set(id);
    }
    public int getId() {
        return id.get();
    }
    public SimpleIntegerProperty idTransaction() {
        return id;
    }

    public String getType() {
        return type.get();
    }
    public void setType(String type) {
        this.type.set(type);
    }
    public SimpleStringProperty typeTransaction() {
        return type;
    }

    public double getPrix() {
        return prix.get();
    }
    public void setPrix(double prix) {
        this.prix.set(prix);
    }
    public SimpleDoubleProperty prixTransaction() {
        return prix;
    }

    public double getFrais() {
        return frais.get();
    }
    public void setFrais(double frais) {
        this.frais.set(frais);
    }
    public SimpleDoubleProperty fraisTransaction() {
        return frais;
    }

    public String getMethodePaiement() {
        return methodePaiement.get();
    }
    public void setMethodePaiement(String methodePaiement) {
        this.methodePaiement.set(methodePaiement);
    }
    public SimpleStringProperty methodePaiementTransaction() {
        return methodePaiement;
    }

    public String getStatut() {
        return statut.get();
    }
    public void setStatut(String statut) {
        this.statut.set(statut);
    }
    public SimpleStringProperty statutTransaction() {
        return statut;
    }

    public String getNote() {
        return note.get();
    }
    public void setNote(String note) {
        this.note.set(note);
    }
    public SimpleStringProperty noteTransaction() {
        return note;
    }

    public LocalDate getDate() { return date.get(); }
    public SimpleObjectProperty<LocalDate> dateProperty() { return date; }
    public void setDate(LocalDate date) { this.date.set(date); }

    public int getId_Client() {
        return id_Client.get();
    }
    public void setId_Client(int id_Client) {
        this.id_Client.set(id_Client);
    }
    public SimpleIntegerProperty id_ClientTransaction() {
        return id_Client;
    }

    public int getId_Propriete() {
        return id_Propriete.get();
    }
    public void setId_Propriete(int id_Propriete) {
        this.id_Propriete.set(id_Propriete);
    }
    public SimpleIntegerProperty id_ProprieteTransaction() {
        return id_Propriete;
    }
}
