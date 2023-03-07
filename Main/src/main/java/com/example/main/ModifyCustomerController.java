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

public class ModifyCustomerController implements Initializable {

    @FXML
    private TableView<Appointment> tableViewAppointment;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;

    @FXML
    private TableColumn<Appointment, String> appointmentStartColumn;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonDelete;

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
    Customer selectedCustomerToUpdateAppointmentList;

    //All data preloaded from the view customers page-------------
    public static int customerIdData;
    public static String customerNameData;
    public static String addressData;
    public static String postalCodeData;
    public static String phoneData;
    public static String createDateData;
    public static String createdByData;
    public static int divisionIdData;
    public static Customer selectedCus;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        // This loads up the options for the choice box---------------------------------------------------------
        // This sets the country from the current customer
        Countries.populateList();
        menuButtonCountries.setItems(Countries.getAllCountries());
        menuButtonCountries.getSelectionModel().select(FirstLevelDivisions.getCountryId(divisionIdData));
        // This sets the division from the current customer
        countrySelectedStart();
        try {
            menuButtonDivisions.getSelectionModel().select(selectDivisionStart(divisionIdData));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Changes the language of each column and label to default system language--------------------------------------------------------------------
        labelCustomerId.setText(Lang.print("Customer") + " " + Lang.print("ID"));
        labelHeader.setText(Lang.print("Modify") + " " + Lang.print("Customer"));
        labelCustomerName.setText(Lang.print("Customer") + " " + Lang.print("Name"));
        labelAddress.setText(Lang.print("Address"));
        labelPostalCode.setText(Lang.print("Postal") + " " + Lang.print("Code"));
        labelPhoneNumber.setText(Lang.print("Phone") + " " + Lang.print("Number"));
        labelCountries.setText(Lang.print("Countries"));
        labelDivisions.setText(Lang.print("Divisions"));
        labelSubHeader.setText(Lang.print("Linked")+" "+Lang.print("Appointments"));
        buttonDelete.setText(Lang.print("Delete"));
        buttonCancel.setText(Lang.print("Cancel"));
        buttonSave.setText(Lang.print("Save"));
        appointmentIdColumn.setText(Lang.print("Appointment")+" "+Lang.print("ID"));
        appointmentStartColumn.setText(Lang.print("Appointment")+" "+Lang.print("Start")+" "+Lang.print("Time"));

        // This loads up selected customer data from previous page----------------------------------
        textFieldCustomerId.setText(String.valueOf(customerIdData));
        textFieldCustomerName.setText(customerNameData);
        textFieldAddress.setText(addressData);
        textFieldPostalCode.setText(postalCodeData);
        textFieldPhoneNumber.setText(phoneData);

        // This loads up apartments associated with the customer the user is modifying-----------
        Appointment.filterByCustomer(selectedCus);
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentId"));
        appointmentStartColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
        tableViewAppointment.setItems(Appointment.getAllAppointmentsFiltered());
    }
    public void countrySelectedStart() {
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
    public void countrySelected(ActionEvent e) throws IOException {
        try {
            country_id = String.valueOf(menuButtonCountries.getSelectionModel().getSelectedItem());
            ResultSet rs = Query.queryDB("SELECT * FROM countries");
            while (rs.next()) {
                if (rs.getString("country").equals(country_id)){
                    selectedCountry =  rs.getInt("country_id");
                }
            }
            menuButtonDivisions.getSelectionModel().select(null);
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
        Query.modifyCustomerToQueryDB(customerIdData,
                textFieldCustomerName.getText(),
                textFieldAddress.getText(),
                textFieldPostalCode.getText(),
                textFieldPhoneNumber.getText(),
                createDateData,
                createdByData,
                createTime,
                LoginController.username,
                selectDivision(String.valueOf(menuButtonDivisions.getValue())));
        Appointment.populateList();

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
    public String selectDivisionStart(int division) throws IOException {
        String result = "";
            for ( FirstLevelDivisions x: FirstLevelDivisions.getAllDivisions()){
                if (division == (x.getDivisionId())){
                    result = x.getDivision();
                }
            }
            return result;
    }
    public void deleteAppointmentButtonClick(ActionEvent e) throws IOException {
        Appointment selectedAppointment = tableViewAppointment.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(Lang.print("Appointments"));
            alert.setHeaderText(Lang.print("Delete"));
            alert.setContentText(Lang.print("Do")+" "+Lang.print("you")+" "+Lang.print("want")+" "+Lang.print("to")+" "+Lang.print("delete")
                    +" "+Lang.print("this")+" "+Lang.print("Appointment")+"?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Appointment.deleteAppointment(selectedAppointment);
                Appointment.filterByCustomer(selectedCus);
                appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentId"));
                appointmentStartColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
                tableViewAppointment.setItems(Appointment.getAllAppointmentsFiltered());
            }
        } else {
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("an")+" "+Lang.print("Appointment")+" "+Lang.print("to")+" "+Lang.print("delete")+".");
        }
    }
}

