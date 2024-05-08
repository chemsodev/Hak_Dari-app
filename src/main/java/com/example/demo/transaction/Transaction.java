package com.example.demo.transaction;

import com.example.demo.Database;
import com.example.demo.client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction {
    private int id;
    private String type;
    //date
    private long prix;
    private long frais;
    private String methodePaiement;
    private String statut;
    private String note;
    private int id_Client;
    private int id_Propriete;

    public Transaction(int id,String type,long prix,long frais,String methodePaiement,String statut,String note,int id_Client,int id_Propriete) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.frais = frais;
        this.methodePaiement = methodePaiement;
        this.statut = statut;
        this.note = note;
        this.id_Client = id_Client;
        this.id_Propriete = id_Propriete;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public long getPrix() {
        return prix;
    }
    public void setPrix(long prix) {
        this.prix = prix;
    }

    public long getFrais() {
        return frais;
    }
    public void setFrais(long frais) {
        this.frais = frais;
    }

    public String getMethodePaiement() {
        return methodePaiement;
    }
    public void setMethodePaiement(String methodePaiement) {
        this.methodePaiement = methodePaiement;
    }

    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public int getId_Client() {
        return id_Client;
    }
    public void setId_Client(int id_Client) {
        this.id_Client = id_Client;
    }

    public int getId_Propriete() {
        return id_Propriete;
    }
    public void setId_Propriete(int id_Propriete) {
        this.id_Propriete = id_Propriete;
    }
}
