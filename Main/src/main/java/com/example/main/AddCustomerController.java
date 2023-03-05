package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonSave;

    @FXML
    private Label labelAddress;

    @FXML
    private Label labelCountries;

    @FXML
    private Label labelCustomerId;

    @FXML
    private Label labelCustomerName;

    @FXML
    private Label labelDivisions;

    @FXML
    private Label labelHeader;

    @FXML
    private Label labelPhoneNumber;

    @FXML
    private Label labelPostalCode;

    @FXML
    private Label labelSubHeader;

    @FXML
    private ComboBox menuButtonCountries;

    @FXML
    private ComboBox menuButtonDivisions;

    @FXML
    private TextField textFieldAddress;

    @FXML
    private TextField textFieldCustomerId;

    @FXML
    private TextField textFieldCustomerName;

    @FXML
    private TextField textFieldPhoneNumber;

    @FXML
    private TextField textFieldPostalCode;

    public String country_id;
    private int selectedCountry;

    public void initialize(URL url, ResourceBundle resourceBundle){
        // This loads up the options for the choice box---------------------------------------------------------
        Countries.populateList();
        menuButtonCountries.setItems(Countries.getAllCountries());

        // This loads up the table view with Appointments (TO BE USED IN THE MODIFY CUSTOMERS PAGE)---------------------------------------------------------------
        /*Customer.populateList();
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<Customer ,Integer>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        tableViewCustomerTable.setItems(Customer.getAllCustomers());
        */

        // Changes the language of each column and label to default system language--------------------------------------------------------------------
        labelCustomerId.setText(Lang.print("Customer")+" "+Lang.print("ID"));
        labelHeader.setText(Lang.print("Add")+" "+Lang.print("Customer"));
        labelSubHeader.setText(Lang.print("Linked")+" "+Lang.print("Appointments"));
        labelCustomerName.setText(Lang.print("Customer")+" "+Lang.print("Name"));
        labelAddress.setText(Lang.print("Address"));
        labelPostalCode.setText(Lang.print("Postal")+" "+Lang.print("Code"));
        labelPhoneNumber.setText(Lang.print("Phone")+" "+Lang.print("Number"));
        labelCountries.setText(Lang.print("Countries"));
        labelDivisions.setText(Lang.print("Divisions"));
        buttonCancel.setText(Lang.print("Cancel"));
        buttonSave.setText(Lang.print("Save"));

        // This loads up what appointment ID will be assigned to potential appointment----------------------------------
        textFieldCustomerId.setText(String.valueOf(Customer.generateCustomerId()));
    }
    public void countrySelected(ActionEvent e) throws IOException {
        try {
            country_id = String.valueOf(menuButtonCountries.getSelectionModel().getSelectedItem());
            ResultSet rs = Query.queryDB("SELECT * FROM countries");
            while (rs.next()) {
                if (rs.getString("country").equals(country_id)){
                    selectedCountry =  rs.getInt("country_id");
                }
            }
            System.out.println(selectedCountry);
        } catch (SQLException se){

        }
    }
}

