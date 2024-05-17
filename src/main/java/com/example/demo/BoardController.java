package com.example.demo;

import com.example.demo.appointment.Appointment;
import com.example.demo.charge.Charge;
import com.example.demo.charge.ChargeManagement;
import com.example.demo.client.Client;
import com.example.demo.client.ClientManagement;
import com.example.demo.realEstate.RealEstate;
import com.example.demo.realEstate.RealEstateManagement;
import com.example.demo.transaction.Transaction;
import com.example.demo.transaction.TransactionManagement;
import com.example.demo.user.Role;
import com.example.demo.user.User;
import com.example.demo.user.UserManagement;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
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
    @FXML
    private AnchorPane appointment_form;

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
    private Button appointment_btn;
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
            appointment_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:transparent");
            appointment_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == client_btn) {
            show_clients();
            home_form.setVisible(false);
            clientManag_form.setVisible(true);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(false);
            charge_form.setVisible(false);
            userManag_form.setVisible(false);
            appointment_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:transparent");
            appointment_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == realEstate_btn) {
            show_realestates();
            show_realEstate_clients();
            home_form.setVisible(false);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(true);
            transaction_form.setVisible(false);
            charge_form.setVisible(false);
            userManag_form.setVisible(false);
            appointment_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:transparent");
            appointment_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == transaction_btn) {
            //Affichage des table
            displayTransactionForm();


            home_form.setVisible(false);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(true);
            charge_form.setVisible(false);
            userManag_form.setVisible(false);
            appointment_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:transparent");
            appointment_btn.setStyle("-fx-background-color:transparent");


        } else if (event.getSource() == charge_btn) {
            show_charges();
            home_form.setVisible(false);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(false);
            charge_form.setVisible(true);
            userManag_form.setVisible(false);
            appointment_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            user_btn.setStyle("-fx-background-color:transparent");
            appointment_btn.setStyle("-fx-background-color:transparent");


        } else if (event.getSource() == user_btn) {
            if(user.getRole().getUserManager()) {

                home_form.setVisible(false);
                clientManag_form.setVisible(false);
                realEstate_form.setVisible(false);
                transaction_form.setVisible(false);
                charge_form.setVisible(false);
                userManag_form.setVisible(true);
                appointment_form.setVisible(false);

                show_user();

                home_btn.setStyle("-fx-background-color:transparent");
                client_btn.setStyle("-fx-background-color:transparent");
                realEstate_btn.setStyle("-fx-background-color:transparent");
                transaction_btn.setStyle("-fx-background-color:transparent");
                charge_btn.setStyle("-fx-background-color:transparent");
                user_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
                appointment_btn.setStyle("-fx-background-color:transparent");

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Permision Error");
                alert.setHeaderText(null);
                alert.setContentText("You don't have permission to Manage Users.");
                alert.showAndWait();
            }
        }else if(event.getSource() == appointment_btn){
            home_form.setVisible(false);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(false);
            charge_form.setVisible(false);
            userManag_form.setVisible(false);
            appointment_form.setVisible(true);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:transparent");
            appointment_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");

        }

    }

    User user = new User();

    public void displayInfo(User user) {
        usernamelabel.setText("Welcome " + user.getUsername());
        this.user = user;
    }

    public  void logout(ActionEvent event) throws IOException {
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
    public Label client_id;;

    @FXML
    public void getclient_Item(){
        int index = client_table.getSelectionModel().getSelectedIndex();
        if(index != -1){
            client_id.setText(col_clientID.getCellData(index).toString());
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
            //Clear Item
            client_clearBtn_Clicked();
        }
    }

    public void client_updateBtn_Clicked() throws SQLException{
        if (client_id.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Some text fields are empty.Please make sure to select the client that u want to delete and to fill all the text fields..");
            alert.showAndWait();
        } else{
            int id = Integer.parseInt(client_id.getText());
            String nom=client_firstname.getText();
            String prenom=client_lastname.getText() ;
            String email=client_email.getText();
            String phone=client_phone.getText();
            Client client = new Client(id,nom,prenom,email,phone);
            ClientManagement.updateClient(user,client);
            show_clients();
            //Clear Item
            client_clearBtn_Clicked();
        }

    }

    public void client_deleteBtn_Clicked() throws SQLException{
        if (client_id.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure to select the client that u want to delete first.");
            alert.showAndWait();
        } else{
            int id = Integer.parseInt(client_id.getText());
            ClientManagement.deleteClient(id, user);
            show_clients();
            //Clear item
            client_clearBtn_Clicked();
        }
    }

    public void client_clearBtn_Clicked() {
        client_id.setText("");
        client_firstname.setText("");
        client_lastname.setText("");
        client_email.setText("");
        client_phone.setText("");
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
    private  TextField realEstate_title;
    @FXML
    private  TextField realEstate_description;
    @FXML
    private  TextField realEstate_price;
    @FXML
    private  TextField realEstate_address;
    @FXML
    private TextField realEstate_area;
    @FXML
    private Label realEstate_ownerFullname;
    @FXML
    private Label realEstate_ownerId;

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
            realEstate_type.setValue(col_type.getCellData(index).toString());
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
                realEstate_ownerId.setText(col_ownerID.getCellData(index).toString());
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
                realEstate_address.getText().isEmpty() || realEstate_area.getText().isEmpty() || realEstate_ownerFullname.getText().isEmpty()
                || realEstate_ownerId.getText().isEmpty() || realEstate_type.getValue().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Some text fields are empty. Please make sure to fill all the text fields.");
            alert.showAndWait();

        }else{
            String title = realEstate_title.getText();
            String description = realEstate_description.getText();
            double price = Double.parseDouble(realEstate_price.getText());
            double area = Double.parseDouble(realEstate_area.getText());
            String address = realEstate_address.getText();
            String type = realEstate_type.getValue();
            int OwnerId = Integer.parseInt(realEstate_ownerId.getText());
            RealEstate realEstate = new RealEstate( title, type, description,price, area,address,OwnerId);
            RealEstateManagement.createRealEstate(realEstate,user);
            show_realestates();
            realEstate_clearBtn_Clicked();
        }
    }

    public void realEstate_deleteBtn_Clicked() throws SQLException {
        if (realEstate_Id.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure to select the real estate that u want to delete first.");
            alert.showAndWait();
        }else{
            int id = Integer.parseInt(realEstate_Id.getText());
            RealEstateManagement.deleteRealEstate(id,user);
            show_realestates();
            realEstate_clearBtn_Clicked();
        }
    }

    public void realEstate_updateBtn_Clicked() throws SQLException {
        if (realEstate_Id.getText().isEmpty() || realEstate_title.getText().isEmpty() || realEstate_description.getText().isEmpty() || realEstate_price.getText().isEmpty() ||
                realEstate_address.getText().isEmpty() || realEstate_area.getText().isEmpty() || realEstate_ownerFullname.getText().isEmpty()
                || realEstate_ownerId.getText().isEmpty() || realEstate_type.getValue().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Some text fields are empty.Please make sure to select the real estate that u want to update and to fill all the text fields.");
            alert.showAndWait();

        }else{
            int id = Integer.parseInt(realEstate_Id.getText());
            String title = realEstate_title.getText();
            String description = realEstate_description.getText();
            double price = Double.parseDouble(realEstate_price.getText());
            double area = Double.parseDouble(realEstate_area.getText());
            String address = realEstate_address.getText();
            String type = realEstate_type.getValue();
            int OwnerId = Integer.parseInt(realEstate_ownerId.getText());
            RealEstate realEstate = new RealEstate( id,title, type, description,price, area,address,OwnerId);
            RealEstateManagement.updateRealEstate(realEstate,user);
            show_realestates();
            //Clear Item
            realEstate_clearBtn_Clicked();
        }
    }

    public void realEstate_clearBtn_Clicked(){
        realEstate_Id.setText("");
        realEstate_title.setText("");
        realEstate_description.setText("");
        realEstate_price.setText("");
        realEstate_address.setText("");
        realEstate_area.setText("");
        realEstate_ownerFullname.setText("");
        realEstate_ownerId.setText("");
        realEstate_type.setValue("");
    }


//  -------------------------------------------------------------------------------------------------------
//                                           Transaction Form
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

    @FXML
    private Label transaction_idLabel;
    @FXML
    private TextField transaction_NoteInput;
    @FXML
    private Label transaction_priceLabel;
    @FXML
    private TextField transaction_fraisInput;
    @FXML
    private Label transaction_ClientIdLabel;
    @FXML
    private Label transaction_ClientLastnameLabel;
    @FXML
    private Label transaction_ClientPhoneLabel;
    @FXML
    private Label transaction_realEstateIdLabel;

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

    public void getTransaction_Item(){
        int index = transaction_tableView.getSelectionModel().getSelectedIndex();
        if(index != -1){
            transaction_idLabel.setText(String.valueOf(transaction_col_id.getCellData(index)));
            transaction_TypeBtn.setValue(transaction_col_type.getCellData(index));
            transaction_priceLabel.setText(String.valueOf(transaction_col_prix.getCellData(index)));
            transaction_fraisInput.setText(String.valueOf(transaction_col_frais.getCellData(index)));
            transaction_NoteInput.setText(String.valueOf(transaction_col_note.getCellData(index)));
            transaction_PaiementBtn.setValue(String.valueOf(transaction_col_paiement.getCellData(index)));
            transaction_StatutBtn.setValue(String.valueOf(transaction_col_statut.getCellData(index)));
        }
    }

    public void getTransaction_RealEstateItem(){
        int index = transaction_realEstateTable.getSelectionModel().getSelectedIndex();
        if(index != -1){
            transaction_realEstateIdLabel.setText(String.valueOf(transaction_col_realEstateId.getCellData(index)));
        }
    }

    public void getTransaction_ClientItem(){
        int index = transaction_clientTable.getSelectionModel().getSelectedIndex();
        if(index != -1){
            transaction_ClientIdLabel.setText(String.valueOf(transaction_col_clientId.getCellData(index)));
            transaction_ClientLastnameLabel.setText(transaction_col_clientLastname.getCellData(index));
            transaction_ClientPhoneLabel.setText(transaction_col_clientPhone.getCellData(index));
        }

    }

    public void transaction_addBtn_Clicked() throws SQLException {
        int index=-1;
         index = transaction_clientTable.getSelectionModel().getSelectedIndex();
        if ( transaction_PaiementBtn.getValue()==null || transaction_TypeBtn.getValue().isEmpty() || transaction_priceLabel.getText().isEmpty()|| transaction_fraisInput.getText().isEmpty()
                || transaction_NoteInput.getText().isEmpty()|| transaction_StatutBtn.getValue().isEmpty()|| transaction_realEstateIdLabel.getText().isEmpty()
                || transaction_ClientIdLabel.getText().isEmpty()|| transaction_ClientLastnameLabel.getText().isEmpty() || transaction_ClientPhoneLabel.getText().isEmpty()|| index == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Some text fields are empty. Please make sure to fill all the text fields.");
            alert.showAndWait();

        }else{
             int id=transaction_col_id.getCellData(index);
             String type=transaction_TypeBtn.getValue();
             double prix= Double.parseDouble(transaction_priceLabel.getText());
             double frais= Double.parseDouble(transaction_fraisInput.getText());
             String methodePaiement=transaction_PaiementBtn.getValue();
             String statut=transaction_StatutBtn.getValue();
             String note=transaction_NoteInput.getText();
             int id_Client= Integer.parseInt(transaction_ClientIdLabel.getText());
             int id_Propriete= Integer.parseInt(transaction_realEstateIdLabel.getText());
            Transaction transaction = new Transaction(id,type,prix,frais,methodePaiement,statut,note,id_Client,id_Propriete);
            TransactionManagement.addTransaction(user,transaction);
            show_transaction();
            //Clear Item
            transaction_clearBtn_Clicked();
        }
    }

    public void transaction_deleteBtn_Clicked() throws SQLException {
        if (transaction_idLabel.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Some text fields are empty. Please make sure to select the Transaction that u want to delete first.");
            alert.showAndWait();
        }else{
            int id = Integer.parseInt(transaction_idLabel.getText());
            TransactionManagement.deleteTransaction(user,id);
            show_charges();
            transaction_clearBtn_Clicked();
        }
    }

    public void transaction_clearBtn_Clicked(){
        transaction_idLabel.setText("");
        transaction_PaiementBtn.setValue("");
        transaction_TypeBtn.setValue("");
        transaction_priceLabel.setText("");
        transaction_fraisInput.setText("");
        transaction_NoteInput.setText("");
        transaction_PaiementBtn.setValue("");
        transaction_StatutBtn.setValue("");
        transaction_realEstateIdLabel.setText("");
        transaction_ClientIdLabel.setText("");
        transaction_ClientLastnameLabel.setText("");
        transaction_ClientPhoneLabel.setText("");
    }

    @FXML
    private  ChoiceBox<String> transaction_PaiementBtn;
    @FXML
    private ChoiceBox<String> transaction_StatutBtn;
    @FXML
    private ChoiceBox<String> transaction_TypeBtn;
    @FXML
    private  ChoiceBox<String> realEstate_type;
    private String[] real_estate_types = {"Vente","Location"};
    private String[] transaction_Paiement_types= {"cash","bank change"};
    private String[] transaction_Statut_types = {"En Cours","Termine","Annuler"};
    private String[] transaction_Type_types = {"Vente","Location"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        realEstate_type.getItems().addAll(real_estate_types);
        transaction_PaiementBtn.getItems().addAll(transaction_Paiement_types);
        transaction_StatutBtn.getItems().addAll(transaction_Statut_types);
        transaction_TypeBtn.getItems().addAll(transaction_Type_types);
    }

//  -------------------------------------------------------------------------------------------------------
//                                           Charge Form
//  -------------------------------------------------------------------------------------------------------

    @FXML
    private TableView<Charge> charge_table;
    @FXML
    private TableColumn<Charge,String> charge_col_title;
    @FXML
    private TableColumn<Charge, String> charge_col_description;
    @FXML
    private TableColumn<Charge, String> charge_col_total;
    @FXML
    private TableColumn<Charge, String> charge_col_id;

    @FXML
    private Label charge_id;
    @FXML
    private TextField charge_total;
    @FXML
    private TextArea charge_description;
    @FXML
    private TextField charge_title;

    public void show_charges() throws SQLException {
        String query = "SELECT * FROM charge";
        try (Connection connection = Database.connect()) {
            assert connection != null;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                ObservableList<Charge>  chargeList = FXCollections.observableArrayList();

                while (resultSet.next()) {
                    int charge_id= Integer.parseInt(resultSet.getString("charge_id"));
                    String title = resultSet.getString("Title");
                    String description = resultSet.getString("Description");
                    Double total = resultSet.getDouble("Total");
                    Charge charge= new Charge(charge_id,description,title,total);
                    chargeList.add(charge);
                }

                // Set cell value factories for table columns
                charge_col_id.setCellValueFactory(new PropertyValueFactory<>("charge_id"));
                charge_col_title.setCellValueFactory(new PropertyValueFactory<>("Title"));
                charge_col_description.setCellValueFactory(new PropertyValueFactory<>("Description"));
                charge_col_total.setCellValueFactory(new PropertyValueFactory<>("Total"));
                charge_table.setItems(chargeList);
            }
        }
    }

    public void getcharge_Item(){
        int index = charge_table.getSelectionModel().getSelectedIndex();
        if(index != -1){
            charge_id.setText(String.valueOf(charge_col_id.getCellData(index)));
            charge_total.setText(String.valueOf(charge_col_total.getCellData(index)));
            charge_description.setText(charge_col_description.getCellData(index));
            charge_title.setText(charge_col_title.getCellData(index));
        }
    }

    public void charge_addBtn_clicked() throws SQLException {

       if (charge_title.getText().isEmpty() || charge_description.getText().isEmpty() || charge_total.getText().isEmpty()) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error Message");
           alert.setHeaderText(null);
           alert.setContentText("Some text fields are empty. Please make sure to fill all the text fields.");
           alert.showAndWait();
       } else {
           String Title = charge_title.getText();
           String description = charge_description.getText();
           double total = Double.parseDouble(charge_total.getText());
           Charge charge=new Charge(Title,description,total);
           ChargeManagement.createCharge(charge,user);
           show_charges();
           //clear item
           charge_clearBtn_Clicked();
       }
    }

    public void charge_clearBtn_Clicked() {
        charge_id.setText("");
        charge_total.setText("");
        charge_description.setText("");
        charge_title.setText("");
    }

    public void charge_deleteBtn_clicked() throws SQLException{
        if (charge_id.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Some text fields are empty. Please make sure to select the charge that u want to delete first.");
            alert.showAndWait();
        }else{
            String id = charge_id.getText();
            ChargeManagement.deleteClient(id, user);
            show_charges();
            //clear item
            charge_clearBtn_Clicked();
        }
    }

//  -------------------------------------------------------------------------------------------------------
//                                           User Form
//  -------------------------------------------------------------------------------------------------------

    @FXML
    private Label user_userIdLabel;
    @FXML
    private TextField user_username;
    @FXML
    private TextField user_password;
    @FXML
    private RadioButton user_clientManag;
    @FXML
    private RadioButton user_realEstateManag;
    @FXML
    private RadioButton user_transactionManag;
    @FXML
    private RadioButton user_chargeManag;
    @FXML
    private RadioButton user_userManag;


    @FXML
    private TableView<User> user_tableView;
    @FXML
    private TableColumn<User,Integer> user_col_id;
    @FXML
    private TableColumn<User,String> user_col_username;
    @FXML
    private TableColumn<User,String> user_col_password;
    @FXML
    private TableColumn<User,Boolean> user_col_clientManag;
    @FXML
    private TableColumn<User,Boolean> user_col_realEstateManag;
    @FXML
    private TableColumn<User,Boolean> user_col_transactionManag;
    @FXML
    private TableColumn<User,Boolean> user_col_chargeManag;
    @FXML
    private TableColumn<User,Boolean> user_col_userManag;

    public  void show_user() throws SQLException {
        String query = "SELECT * FROM users";
        try (Connection connection = Database.connect()) {
            assert connection != null;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                ObservableList<User>  userList = FXCollections.observableArrayList();

                while (resultSet.next()) {
                    int id= Integer.parseInt(resultSet.getString("Id"));
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    boolean userManag = resultSet.getBoolean("userManag");
                    boolean clientManag = resultSet.getBoolean("clientManag");
                    boolean realEstateManag = resultSet.getBoolean("realEstateManag");
                    boolean transactionManag = resultSet.getBoolean("transactionManag");
                    boolean chargeManag = resultSet.getBoolean("chargeManag");

                    Role role = new Role(userManag,realEstateManag,clientManag,transactionManag,chargeManag);
                    User user= new User(id,username,password,role);
                    userList.add(user);
                }

                // Set cell value factories for table columns
                user_col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
                user_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
                user_col_password.setCellValueFactory(new PropertyValueFactory<>("password"));

                user_col_clientManag.setCellValueFactory(new PropertyValueFactory<>("clientManager"));
                user_col_realEstateManag.setCellValueFactory(new PropertyValueFactory<>("realEstateManager"));
                user_col_transactionManag.setCellValueFactory(new PropertyValueFactory<>("transactionManager"));
                user_col_chargeManag.setCellValueFactory(new PropertyValueFactory<>("chargeManager"));
                user_col_userManag.setCellValueFactory(new PropertyValueFactory<>("userManager"));

                user_tableView.setItems(userList);

            }catch (SQLException e){
                System.err.println("SQL exeption In User Form");
            }catch (Exception e){
                System.err.println("Exeption In User Form");
            }
        }
    }

    public void getUser_item(){
        int index = user_tableView.getSelectionModel().getSelectedIndex();
        if(index != -1){

            user_userIdLabel.setText(user_col_id.getCellData(index).toString());
            user_username.setText(user_col_username.getCellData(index));
            user_password.setText(user_col_password.getCellData(index));


            if(user_col_clientManag.getCellData(index).toString().equals("true")) user_clientManag.setSelected(true);
            else user_clientManag.setSelected(false);

            if(user_col_realEstateManag.getCellData(index).toString().equals("true")) user_realEstateManag.setSelected(true);
            else user_realEstateManag.setSelected(false);

            if(user_col_transactionManag.getCellData(index).toString().equals("true")) user_transactionManag.setSelected(true);
            else user_transactionManag.setSelected(false);

            if(user_col_chargeManag.getCellData(index).toString().equals("true")) user_chargeManag.setSelected(true);
            else user_chargeManag.setSelected(false);

            if(user_col_userManag.getCellData(index).toString().equals("true")) user_userManag.setSelected(true);
            else user_userManag.setSelected(false);


        }
    }


    public void user_addBtn_clicked() throws SQLException {
     if (user_username.getText().isEmpty() || user_password.getText().isEmpty() || (!user_clientManag.isSelected() && !user_realEstateManag.isSelected() && !user_transactionManag.isSelected() && !user_chargeManag.isSelected() && !user_userManag.isSelected()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Some text fields are empty. Please make sure to fill all the text fields.");
            alert.showAndWait();
        }else{
         String u_username = user_username.getText();
         String u_password = user_password.getText();
         boolean user_clientManagement =false;
         boolean user_realEstateManagement =false;
         boolean user_transactionManagement =false;
         boolean user_chargeManagement =false;
         boolean user_userManagement =false;
         if (user_clientManag.isSelected()) user_clientManagement = true;
         if (user_realEstateManag.isSelected()) user_realEstateManagement = true;
         if (user_transactionManag.isSelected()) user_transactionManagement = true;
         if (user_chargeManag.isSelected()) user_chargeManagement = true;
         if (user_userManag.isSelected()) user_userManagement = true;
         Role role=new Role(user_userManagement,user_realEstateManagement,user_clientManagement,user_transactionManagement,user_chargeManagement);
          User user1=new User(u_username,u_password,role);
         UserManagement.createUser(user1);
         show_user();
     }
    }

    public void user_updateBtn_clicked() throws SQLException {
        if(user_userIdLabel.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Some text fields are empty. Please make sure to select the client that u want to delete first.");
            alert.showAndWait();
        } else{
            int id = Integer.parseInt(user_userIdLabel.getText());
            String u_username = user_username.getText();
            String u_password = user_password.getText();
            boolean user_clientManagement =false;
            boolean user_realEstateManagement =false;
            boolean user_transactionManagement =false;
            boolean user_chargeManagement =false;
            boolean user_userManagement =false;
            if (user_clientManag.isSelected()) user_clientManagement = true;
            if (user_realEstateManag.isSelected()) user_realEstateManagement = true;
            if (user_transactionManag.isSelected()) user_transactionManagement = true;
            if (user_chargeManag.isSelected()) user_chargeManagement = true;
            if (user_userManag.isSelected()) user_userManagement = true;
            Role role=new Role(user_userManagement,user_realEstateManagement,user_clientManagement,user_transactionManagement,user_chargeManagement);
            User user1=new User(id,u_username,u_password,role);
            UserManagement.updateUser(user1);
            show_user();
        }
    }

    public void user_deleteBtn_clicked() throws SQLException {
        if(user_userIdLabel.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Some text fields are empty. Please make sure to select the client that u want to delete first.");
            alert.showAndWait();
        } else{
            int id = Integer.parseInt(user_userIdLabel.getText());
            UserManagement.deleteUser(user,id);
            if(user.getId()==id){logout_btn.fire();}
            else{show_user();}
        }
    }

//  -------------------------------------------------------------------------------------------------------
//                                           Appointment Form
//  -------------------------------------------------------------------------------------------------------

    @FXML
    private TableView<Appointment> appointment_table;
    @FXML
    private TableColumn<Appointment, Integer> col_appointmentId;
    @FXML
    private TableColumn<Appointment, String> col_appointmentDate;
    @FXML
    private TableColumn<Appointment, String> col_appointmentDescription;
    @FXML
    private TableColumn<Appointment, String> col_appointmentClientFullname;
    @FXML
    private TableColumn<Appointment, String> col_appointmentClientPhone;
    @FXML
    private TableColumn<Appointment, Integer> col_appointmentRealEstateId;
    @FXML
    private TableColumn<Appointment, Integer> col_appointmentUserId;

    @FXML
    private TableView<RealEstate> appointment_realEstateTable;
    @FXML
    private TableColumn<RealEstate, Integer> appointment_col_realEstateId;
    @FXML
    private TableColumn<RealEstate, String> appointment_col_realEstateTitle;
    @FXML
    private TableColumn<RealEstate, String> appointment_col_realEstateDescription;

    @FXML
    private Label appointment_id;
    @FXML
    private TextField appointment_description;
    @FXML
    private DatePicker appointment_date;
    @FXML
    private TextField appointment_clientFullname;
    @FXML
    private TextField appointment_clientPhone;


    public void appointment_addBtn_clicked() throws SQLException {}

    public void appointment_updateBtn_clicked() throws SQLException {}

    public void appointment_deleteBtn_clicked() throws SQLException {}

    public void appointment_clearBtn_clicked(){}


}