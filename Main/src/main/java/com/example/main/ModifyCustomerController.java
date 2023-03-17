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
/** When the Modify Customer page is initialized this method will run. It fills columns of the table and
 * other data to combo boxes and text fields. It will also translate any text to either French or English depending on
 * system default.
 */
public class ModifyCustomerController implements Initializable {
    /** This represents the table of appointments associated with the customer being modified.
     */
    @FXML
    private TableView<Appointment> tableViewAppointment;
    /** This represents the column appointment ID.
     */
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;
    /** This represents the column start date of the appointment.
     */
    @FXML
    private TableColumn<Appointment, String> appointmentStartColumn;
    /** This button holds the cancel button.
     */
    @FXML
    private Button buttonCancel;
    /** This button holds the save button.
     */
    @FXML
    private Button buttonSave;
    /** This button holds the delete button.
     */
    @FXML
    private Button buttonDelete;
    /** This label holds the address text.
     */
    @FXML
    private Label labelAddress;
    /** This label holds the countries text.
     */
    @FXML
    private Label labelCountries;
    /** This label holds the customer ID text.
     */
    @FXML
    private Label labelCustomerId;
    /** This label holds the customer name text.
     */
    @FXML
    private Label labelCustomerName;
    /** This label holds the divisions text.
     */
    @FXML
    private Label labelDivisions;
    /** This label holds the header text.
     */
    @FXML
    private Label labelHeader;
    /** This label holds the phone number text.
     */
    @FXML
    private Label labelPhoneNumber;
    /** This label holds the postal code text.
     */
    @FXML
    private Label labelPostalCode;
    /** This label holds the Sub-Header text.
     */
    @FXML
    private Label labelSubHeader;
    /** This is a combo box for countries.
     */
    @FXML
    private ComboBox menuButtonCountries;
    /** This is a combo box for divisions.
     */
    @FXML
    private ComboBox menuButtonDivisions;
    /** This is a text field for the address input.
     */
    @FXML
    private TextField textFieldAddress;
    /** This is a text field for the customer id input.
     */
    @FXML
    private TextField textFieldCustomerId;
    /** This is a text field for the customer name input.
     */
    @FXML
    private TextField textFieldCustomerName;
    /** This is a text field for the phone number input.
     */
    @FXML
    private TextField textFieldPhoneNumber;
    /** This is a text field for the postal code input.
     */
    @FXML
    private TextField textFieldPostalCode;
    /** This is a string holding the customer ID to later be parsed into an integer.
     */
    public String country_id;
    /** This is the selected country.
     */
    private int selectedCountry;

    //All data preloaded from the view customers page-------------
    /** This variable holds the Customer ID data which was passed down from the View Customer Page.
     */
    public static int customerIdData;
    /** This variable holds the Customer Name data which was passed down from the View Customer Page.
     */
    public static String customerNameData;
    /** This variable holds the Address data which was passed down from the View Customer Page.
     */
    public static String addressData;
    /** This variable holds the Postal Code data which was passed down from the View Customer Page.
     */
    public static String postalCodeData;
    /** This variable holds the Phone Number data which was passed down from the View Customer Page.
     */
    public static String phoneData;
    /** This variable holds the Created Date data which was passed down from the View Customer Page.
     */
    public static String createDateData;
    /** This variable holds the Created By data which was passed down from the View Customer Page.
     */
    public static String createdByData;
    /** This variable holds the Division ID data which was passed down from the View Customer Page.
     */
    public static int divisionIdData;
    /** This is the selected customer that is being modified currently.
     */
    public static Customer selectedCus;

    /** When the Add Appointment page is initialized this method will run. It fills columns of the table and
     * other data to combo boxes and text fields. It will also translate any text to either French or English depending on
     * system default.
     */
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
    /** Using the country_id, we get the int associated with the string matching the country name.
     */
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
    /** This method determines which country was selected.
     */
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
    /** This method is assigned to a button and will take the user back to the main menu page.
     */
    public void cancelButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "mainMenuViewCustomers.fxml");
    }
    /** This method is assigned to a button and will save the customer information if valid.
     */
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
    /** This method will determine which division ID is selected.
     * @param division The name of the division.
     * @return the id associated with the division name.
     */
    public int selectDivision(String division) throws IOException {
        int result = 0;
        for ( FirstLevelDivisions x: FirstLevelDivisions.getAllDivisions()){
            if (division.equals(x.getDivision())){
                result = x.getDivisionId();
            }
        }
        return result;
    }
    /** This will assign a default selection to the division button.
     * @param division this is the division id int.
     * @return the name of the division.
     */
    public String selectDivisionStart(int division) throws IOException {
        String result = "";
            for ( FirstLevelDivisions x: FirstLevelDivisions.getAllDivisions()){
                if (division == (x.getDivisionId())){
                    result = x.getDivision();
                }
            }
            return result;
    }
    /** This will delete an appointment.
     */
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

