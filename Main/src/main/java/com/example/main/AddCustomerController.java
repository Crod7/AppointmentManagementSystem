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
import java.time.LocalDateTime;
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

    private int selectedDivision;

    public void initialize(URL url, ResourceBundle resourceBundle){
        // This loads up the options for the choice box---------------------------------------------------------
        Countries.populateList();
        menuButtonCountries.setItems(Countries.getAllCountries());

        // Changes the language of each column and label to default system language--------------------------------------------------------------------
        labelCustomerId.setText(Lang.print("Customer")+" "+Lang.print("ID"));
        labelHeader.setText(Lang.print("Add")+" "+Lang.print("Customer"));
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
            FirstLevelDivisions.populateList();
            FirstLevelDivisions.filteredList(selectedCountry);
            menuButtonDivisions.setItems(FirstLevelDivisions.getAllDivisionsFiltered());
        } catch (SQLException se){

        }
    }
    public void cancelButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "mainMenuViewCustomers.fxml");
    }
    public void saveButtonClick(ActionEvent e) throws IOException {
        // This will check to see if all fields hold data, as any empty text fields will throw an error----------------------
        if (textFieldCustomerName.getText().equals("")){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("enter")+" "+Lang.print("a")+" "+Lang.print("Name"));
            return;
        }
        if (textFieldAddress.getText().equals("")){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("enter")+" "+Lang.print("an")+" "+Lang.print("Address"));
            return;
        }
        if (textFieldPostalCode.getText().equals("")){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("enter")+" "+Lang.print("a")+" "+Lang.print("Postal")+" "+Lang.print("Code"));
            return;
        }
        if (textFieldPhoneNumber.getText().equals("")){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("enter")+" "+Lang.print("a")+" "+Lang.print("Phone")+" "+Lang.print("Number"));
            return;
        }
        if (menuButtonCountries.getValue() == null){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("a")+" "+Lang.print("Country"));
            return;
        }
        if (menuButtonDivisions.getValue() == null){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("a")+" "+Lang.print("Division"));
            return;
        }
        //These dates are converted to UTC so that when other users download the data it will be converted to their local time------------------
        LocalDateTime currentLocalTime = LocalDateTime.now();
        String createTime = TimeConversion.ConvertToUtc(currentLocalTime.toLocalDate(), currentLocalTime.getHour(), currentLocalTime.getMinute());
        //Finally this addToQueryDB method inserts a row into the database----------------------
        ResultSet rs = Query.addCustomerToQueryDB(Customer.generateCustomerId(), textFieldCustomerName.getText(), textFieldAddress.getText(), textFieldPostalCode.getText(), textFieldPhoneNumber.getText(),
                createTime, LoginController.username, createTime, LoginController.username, selectDivision(String.valueOf(menuButtonDivisions.getValue())));
        //The list is re-populated and makes sure no duplicates are added to same list----------------------
        Customer.populateList();
        Form.changePageTo(e, "mainMenuViewCustomers.fxml");

    }

    public int selectDivision(String division) throws IOException {
        int result = 0;
        for ( FirstLevelDivisions x: FirstLevelDivisions.getAllDivisions()){
            if (division.equals(x.getDivision())){
                result = x.getDivisionId();
            }
        }
        return result;
    }
}

