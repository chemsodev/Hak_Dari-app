package com.example.demo;

import com.example.demo.client.Client;
import com.example.demo.client.ClientManagement;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

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
            //Affichage des table
            displayTransactionForm();


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

//---------------------------------------------------------------------------------------------------------------
//                                                Client Form
//---------------------------------------------------------------------------------------------------------------

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

    @FXML
    public Button client_addBtn;
    @FXML
    public Button client_updateBtn;
    @FXML
    public Button client_deleteBtn;
    //Text Field
    @FXML
    public TextField client_phone;
    @FXML
    public TextField client_firstname;
    @FXML
    public TextField client_email;
    @FXML
    public TextField client_lastname;

    @FXML
    public void getclient_Item(){
        int index = client_table.getSelectionModel().getSelectedIndex();
        if(index != -1){
            client_firstname.setText(col_firstname.getCellData(index).toString());
            client_lastname.setText(col_lastname.getCellData(index).toString());
            client_email.setText(col_email.getCellData(index).toString());
            client_phone.setText(col_phone.getCellData(index).toString());
        }
    }

    public void client_addBtn_Clicked() throws SQLException {
        String nom = client_firstname.getText();
        String prenom = client_lastname.getText();
        String email = client_email.getText();
        String phone = client_phone.getText();


        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Some text fields are empty. Please make sure to fill all the text fields.");
            alert.showAndWait();
        } else {
            Client client = new Client(nom,prenom,email,phone);
            ClientManagement.createClient(client,user);
            show_clients();
        }
    }

    public void client_updateBtn_Clicked() throws SQLException{
        int index=client_table.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        if(index != -1) {
            int id = col_clientID.getCellData(index);
            String nom=client_firstname.getText();
            String prenom=client_lastname.getText() ;
            String email=client_email.getText();
            String phone=client_phone.getText();
            ClientManagement.updateClient(user,id,nom,prenom,email,phone);
            show_clients();
        }
    }

    public void client_deleteBtn_Clicked() throws SQLException{
        int index=client_table.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        if(index != -1) {
            int id = col_clientID.getCellData(index);
            ClientManagement.deleteClient(id, user);
            show_clients();
        }
    }


 //-------------------------------------------------------------------------------------------------------------
 //                                               RealEstate Form
 //-------------------------------------------------------------------------------------------------------------
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
    private TableColumn<RealEstate, String> col_type;
    @FXML
    private TableColumn<RealEstate, Integer> col_ownerID;

    // Client Table -> real estate form
    @FXML
    private TableView<Client> realEstate_clientTableView;
    @FXML
    private TableColumn<Client, Integer> realEstate_col_ClientId;
    @FXML
    private TableColumn<Client, String> realEstate_col_ClientLastname;
    @FXML
    private TableColumn<Client, String> realEstate_col_clientPhone;

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
                realEstate_col_ClientId.setCellValueFactory(new PropertyValueFactory<>("id"));
                realEstate_col_ClientLastname.setCellValueFactory(new PropertyValueFactory<>("Nom"));
                realEstate_col_clientPhone.setCellValueFactory(new PropertyValueFactory<>("Email"));

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
                    String type = resultSet.getString("Type");
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


    @FXML
    private Label realEstate_Id;
    @FXML
    private TextField realEstate_title;
    @FXML
    private TextField realEstate_description;
    @FXML
    private TextField realEstate_price;
    @FXML
    private TextField realEstate_address;
    @FXML
    private TextField realEstate_area;
    @FXML
    private Label realEstate_ownerFullname;
    @FXML
    private Label realEstate_ownerId;
    @FXML
    private MenuButton realEstate_type;

    @FXML
    public void getRealEstate_Item() {
        int index = realestate_table.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            realEstate_Id.setText(col_realestateID.getCellData(index).toString());
            realEstate_title.setText(col_title.getCellData(index).toString());
            realEstate_area.setText(col_area.getCellData(index).toString());
            realEstate_description.setText(col_description.getCellData(index).toString());
            realEstate_price.setText(col_price.getCellData(index).toString());
            realEstate_address.setText(col_address.getCellData(index).toString());
            realEstate_type.setText(col_type.getCellData(index).toString());
            String query = "SELECT * FROM Client WHERE Id = (SELECT id_Owner FROM RealEstate WHERE Id = ?)";
            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, col_realestateID.getCellData(index).toString());
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            String firstName = resultSet.getString("Prenom");
                            String lastName = resultSet.getString("Nom");
                            realEstate_ownerFullname.setText(firstName + " " + lastName);
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    public void getRealEstate_ClientItem(){
        int index = realEstate_clientTableView.getSelectionModel().getSelectedIndex();
        if(index != -1){
            realEstate_ownerFullname.setText(realEstate_col_ClientLastname.getCellData(index).toString());
            realEstate_ownerId.setText(realEstate_col_ClientId.getCellData(index).toString());
        }
    }



    public void realEstate_addBtn_Clicked() throws SQLException {
        if (realEstate_title.getText().isEmpty() || realEstate_description.getText().isEmpty() || realEstate_price.getText().isEmpty() ||
                realEstate_address.getText().isEmpty() || realEstate_area.getText().isEmpty() || realEstate_ownerFullname.getText().equals("") ||
                realEstate_type.isShowing() ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Some text fields are empty. Please make sure to fill all the text fields.");
            alert.showAndWait();

        }else{
            String OwnerIdQuery = "SELECT Id FROM Client WHERE Email = ? ";

            String title = realEstate_title.getText();
            String description = realEstate_description.getText();
            double price = Double.parseDouble(realEstate_price.getText());
            double area = Double.parseDouble(realEstate_area.getText());
            String address = realEstate_address.getText();
            String type = realEstate_type.getText();


            int ownerId = 15;

            RealEstate realEstate = new RealEstate( title, type, description,price, area,address,ownerId);
        }
    }

    public void realEstate_deleteBtn_Clicked() throws SQLException {

    }

    public void realEstate_updateBtn_Clicked() throws SQLException {

    }



//  -------------------------------------------------------------------------------------------------------
//                                           Transaction Tables
//  -------------------------------------------------------------------------------------------------------
    @FXML
    private TableView<Transaction> transaction_tableView;
    @FXML
    private TableColumn<Transaction, Integer> transaction_col_id;
    @FXML
    private TableColumn<Transaction, String> transaction_col_type;
    @FXML
    private TableColumn<Transaction, Double> transaction_col_prix;
    @FXML
    private TableColumn<Transaction, Double> transaction_col_frais;
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
                    double prix = resultSet.getDouble("Prix");
                    double frais = resultSet.getDouble("Frais");
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
    private TableColumn<Client, Integer> transaction_col_clientId;
    @FXML
    private TableColumn<Client, String> transaction_col_clientLastname;
    @FXML
    private TableColumn<Client, String> transaction_col_clientPhone;

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

                transaction_col_clientId.setCellValueFactory(new PropertyValueFactory<>("Id"));
                transaction_col_clientLastname.setCellValueFactory(new PropertyValueFactory<>("Nom"));
                transaction_col_clientPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));

                transaction_clientTable.setItems(clientList);
            }
        }
    }

    @FXML
    private TableView<RealEstate> transaction_realEstateTable;
    @FXML
    private TableColumn<RealEstate, Integer> transaction_col_realEstateId;
    @FXML
    private TableColumn<RealEstate,String> transaction_col_realEstateTitle;
    @FXML
    private TableColumn<RealEstate,String> transaction_col_realEstateDescription;
    @FXML
    private TableColumn<RealEstate,Double> transaction_col_realEstatePrice;

    public void show_transactionRealEstate() throws SQLException {
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
                    String type = resultSet.getString("Type");
                    //date
                    int id_Owner = resultSet.getInt("Id_Owner");

                    RealEstate realEstate = new RealEstate(realestate_iD, title, type, description, price, area, address, id_Owner);

                    realEstateList.add(realEstate);
                }

                // Set cell value factories for table columns
                transaction_col_realEstateId.setCellValueFactory(new PropertyValueFactory<>("Id"));
                transaction_col_realEstateTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
                transaction_col_realEstateDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
                transaction_col_realEstatePrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

                transaction_realEstateTable.setItems(realEstateList);
            }
        }
    }

    public void displayTransactionForm() throws SQLException{
        show_transactionClient();
        show_transaction();
        show_transactionRealEstate();
    }

    @FXML
    private MenuButton transaction_TypeBtn;
    @FXML
    private Label transaction_priceLabel;
    @FXML
    private TextField transaction_fraisInput;
    @FXML
    private MenuButton transaction_PaiementBtn;
    @FXML
    private MenuButton transaction_StatutBtn;
    @FXML
    private TextField transaction_NoteInput;
    @FXML
    private Label transaction_ClientIdLabel;
    @FXML
    private Label transaction_ClientLastnameLabel;
    @FXML
    private Label transaction_ClientPhoneLabel;
    @FXML
    private Label transaction_realEstateIdLabel;


    @FXML
    public void getTransaction_Item(){
        int index = transaction_tableView.getSelectionModel().getSelectedIndex();
        if(index != -1){
            //realEstate_col_ClientFullname.getCellData(index).toString()
        }
    }
    @FXML
    public void getTransaction_RealEstateItem(){
        int index = transaction_realEstateTable.getSelectionModel().getSelectedIndex();
        if(index != -1){
            //transaction_realEstateIdLabel.setText(transaction_col_realEstateId.getCellData(index).toString());
        }
    }
    @FXML
    public void getTransaction_ClientItem(){
        int index = transaction_clientTable.getSelectionModel().getSelectedIndex();
        if(index != -1){
            //transaction_ClientIdLabel.setText(transaction_col_clientId.getCellData(index).toString());
            transaction_ClientLastnameLabel.setText(transaction_col_clientLastname.getCellData(index).toString());
            transaction_ClientPhoneLabel.setText(transaction_col_clientPhone.getCellData(index).toString());
        }

    }


}