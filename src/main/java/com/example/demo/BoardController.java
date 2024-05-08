package com.example.demo;

import com.example.demo.client.Client;
import com.example.demo.realEstate.RealEstate;
import com.example.demo.transaction.Transaction;
import com.example.demo.user.User;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class BoardController {
    private Parent root;
    private Stage stage;
    private Scene scene;


    @FXML
    private AnchorPane home_form;
    @FXML
    private AnchorPane clientManag_form;
    @FXML
    private AnchorPane realEstate_form;
    @FXML
    private AnchorPane transaction_form;
    @FXML
    private AnchorPane charge_form;
    @FXML
    private AnchorPane userManag_form;

    //Side Bar
    @FXML
    private Label usernamelabel;
    @FXML
    private Button home_btn;
    @FXML
    private Button client_btn;
    @FXML
    private Button realEstate_btn;
    @FXML
    private Button transaction_btn;
    @FXML
    private Button charge_btn;
    @FXML
    private Button user_btn;
    @FXML
    private Button logout_btn;

    //Home
    @FXML
    private Label home_totalUser_label;
    @FXML
    private Label home_totalRealEstate_label;
    @FXML
    private Label home_totalTransaction_label;


    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    public void switchForm(ActionEvent event) throws SQLException {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(false);
            charge_form.setVisible(false);
            userManag_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:transparent");
        } else if (event.getSource() == client_btn) {
            show_clients();
            home_form.setVisible(false);
            clientManag_form.setVisible(true);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(false);
            charge_form.setVisible(false);
            userManag_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == realEstate_btn) {
            show_realestates();
            show_realEstate_clients();
            home_form.setVisible(false);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(true);
            transaction_form.setVisible(false);
            charge_form.setVisible(false);
            userManag_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == transaction_btn) {
            show_transactionClient();
            show_transaction();
            home_form.setVisible(false);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(true);
            charge_form.setVisible(false);
            userManag_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:transparent");


        } else if (event.getSource() == charge_btn) {
            home_form.setVisible(false);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(false);
            charge_form.setVisible(true);
            userManag_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            user_btn.setStyle("-fx-background-color:transparent");


        } else if (event.getSource() == user_btn) {
            home_form.setVisible(false);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(false);
            charge_form.setVisible(false);
            userManag_form.setVisible(true);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");


        }


    }

    User user = new User();

    public void displayInfo(User user) {
        usernamelabel.setText("Welcome " + user.getUsername());
        this.user = user;
    }

    public void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void home_totalUser() {

        String sql = "SELECT COUNT(id) FROM users";

        connect = Database.connect();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            home_totalUser_label.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void home_totalRealEstate(){

        String sql = "SELECT COUNT(id) FROM RealEstate";

        connect = Database.connect();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            home_totalRealEstate_label.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void home_totalTransaction(){

        String sql = "SELECT COUNT(id) FROM Transaction";

        connect = Database.connect();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            home_totalTransaction_label.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initialze(){
        home_totalRealEstate();
        home_totalUser();
        home_totalTransaction();
    }

    @FXML
    private TableView<Client> client_table;
    @FXML
    private TableColumn<Client, Integer> col_clientID;
    @FXML
    private TableColumn<Client, String> col_firstname;
    @FXML
    private TableColumn<Client, String> col_lastname;
    @FXML
    private TableColumn<Client, String> col_email;
    @FXML
    private TableColumn<Client, String> col_phone;

    public void show_clients() throws SQLException {
        String query = "SELECT * FROM Client";
        try (Connection connection = Database.connect()) {
            assert connection != null;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                ObservableList<Client> clientList = FXCollections.observableArrayList();

                while (resultSet.next()) {
                    int clientID = resultSet.getInt("Id");
                    String firstName = resultSet.getString("Nom");
                    String lastName = resultSet.getString("Prenom");
                    String email = resultSet.getString("Email");
                    String phone = resultSet.getString("Phone");

                    Client client = new Client(clientID, firstName, lastName, email, phone);
                    clientList.add(client);
                }

                col_clientID.setCellValueFactory(new PropertyValueFactory<>("id"));
                col_firstname.setCellValueFactory(new PropertyValueFactory<>("nom"));
                col_lastname.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
                col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

                client_table.setItems(clientList);
            }
        }
    }

    //  Real Estate Form Table IDs
    // Real estate Table
    @FXML
    private TableView<RealEstate> realestate_table;
    @FXML
    private TableColumn<RealEstate, Integer> col_realestateID;
    @FXML
    private TableColumn<RealEstate, String> col_title;
    @FXML
    private TableColumn<RealEstate, String> col_description;
    @FXML
    private TableColumn<RealEstate, Double> col_price;
    @FXML
    private TableColumn<RealEstate, Double> col_area;
    @FXML
    private TableColumn<RealEstate, String> col_address;
    @FXML
    private TableColumn<RealEstate, Integer> col_type;
    @FXML
    private TableColumn<RealEstate, Integer> col_ownerID;

    // Client Table -> real estate form
    @FXML
    private TableView<Client> realEstate_clientTableView;
    @FXML
    private TableColumn<Client, String> realEstate_col_ClientFullname;
    @FXML
    private TableColumn<Client, String> realEstate_col_clientEmail;

    public void show_realEstate_clients() throws SQLException {
        String query = "SELECT * FROM Client";
        try (Connection connection = Database.connect()) {
            assert connection != null;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                ObservableList<Client> clientList = FXCollections.observableArrayList();

                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String Nom = resultSet.getString("Nom");
                    String Prenom = resultSet.getString("Prenom");
                    String Email = resultSet.getString("Email");
                    String Phone = resultSet.getString("Phone");


                    Client client = new Client(id, Nom, Prenom, Email, Phone);
                    clientList.add(client);
                }

                // Set cell value factories for table columns
                realEstate_col_ClientFullname.setCellValueFactory(new PropertyValueFactory<>("Nom"));
                realEstate_col_clientEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));

                realEstate_clientTableView.setItems(clientList);
            }
        }
    }

    public void show_realestates() throws SQLException {
        String query = "SELECT * FROM RealEstate";
        try (Connection connection = Database.connect()) {
            assert connection != null;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                ObservableList<RealEstate> realEstateList = FXCollections.observableArrayList();

                while (resultSet.next()) {
                    int realestate_iD = resultSet.getInt("Id");
                    String title = resultSet.getString("Title");
                    String description = resultSet.getString("Description");
                    Double price = resultSet.getDouble("Price");
                    Double area = resultSet.getDouble("Area");
                    String address = resultSet.getString("Address");
                    int type = resultSet.getInt("Type");
                    int ownerId = resultSet.getInt("id_Owner");

                    RealEstate realEstate = new RealEstate(realestate_iD, title, type, description, price, area, address,  ownerId);
                    realEstateList.add(realEstate);
                }

                // Set cell value factories for table columns
                col_realestateID.setCellValueFactory(new PropertyValueFactory<>("id"));
                col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
                col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
                col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
                col_area.setCellValueFactory(new PropertyValueFactory<>("area"));
                col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
                col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
                col_ownerID.setCellValueFactory(new PropertyValueFactory<>("ownerId"));

                realestate_table.setItems(realEstateList);
            }
        }
    }




    //  Transaction Tables ---------------------------------------------
    @FXML
    private TableView<Transaction> transaction_tableView;
    @FXML
    private TableColumn<Transaction, Integer> transaction_col_id;
    @FXML
    private TableColumn<Transaction, String> transaction_col_type;
    @FXML
    private TableColumn<Transaction, Long> transaction_col_prix;
    @FXML
    private TableColumn<Transaction, Long> transaction_col_frais;
    @FXML
    private TableColumn<Transaction, String> transaction_col_paiement;
    @FXML
    private TableColumn<Transaction, String> transaction_col_statut;
    @FXML
    private TableColumn<Transaction, String> transaction_col_note;

    public void show_transaction() throws SQLException {
        String query = "SELECT * FROM Transaction";
        try (Connection connection = Database.connect()) {
            assert connection != null;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                ObservableList<Transaction> transactionList = FXCollections.observableArrayList();

                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String type = resultSet.getString("Type");
                    long prix = resultSet.getLong("Prix");
                    long frais = resultSet.getLong("Frais");
                    String methodePaiement = resultSet.getString("MethodePaiement");
                    String statut = resultSet.getString("Statut");
                    String note = resultSet.getString("Note");
                    int id_Client = resultSet.getInt("Id_Client");
                    int id_Propriete = resultSet.getInt("Id_Propriete");

                    Transaction transaction = new Transaction(id,type,prix,frais,methodePaiement,statut,note,id_Client,id_Propriete);
                    transactionList.add(transaction);
                }

                // Set cell value factories for table columns
                transaction_col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
                transaction_col_type.setCellValueFactory(new PropertyValueFactory<>("Type"));
                transaction_col_prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
                transaction_col_frais.setCellValueFactory(new PropertyValueFactory<>("Frais"));
                transaction_col_paiement.setCellValueFactory(new PropertyValueFactory<>("MethodePaiement"));
                transaction_col_statut.setCellValueFactory(new PropertyValueFactory<>("Statut"));
                transaction_col_note.setCellValueFactory(new PropertyValueFactory<>("Note"));

                transaction_tableView.setItems(transactionList);
            }
        }
    }

    //Client table -> transaction form
    @FXML
    private TableView<Client> transaction_clientTable;
    @FXML
    private TableColumn<Client, String> transaction_col_clientFullname;
    @FXML
    private TableColumn<Client, String> transaction_col_clientIEmail;

    public void show_transactionClient() throws SQLException {
        String query = "SELECT * FROM Client";
        try (Connection connection = Database.connect()) {
            assert connection != null;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                ObservableList<Client> clientList = FXCollections.observableArrayList();

                while (resultSet.next()) {
                    int clientID = resultSet.getInt("Id");
                    String firstName = resultSet.getString("Nom");
                    String lastName = resultSet.getString("Prenom");
                    String email = resultSet.getString("Email");
                    String phone = resultSet.getString("Phone");

                    Client client = new Client(clientID, firstName, lastName, email, phone);
                    clientList.add(client);
                }

                // Set cell value factories for table columns
                transaction_col_clientFullname.setCellValueFactory(new PropertyValueFactory<>("Nom"));
                transaction_col_clientIEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));

                transaction_clientTable.setItems(clientList);
            }
        }
    }

}