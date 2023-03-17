package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
/** This class handles the Add Customer Page.
 */
public class AddCustomerController implements Initializable {
    /** This button manages the cancel button.
     */
    @FXML
    private Button buttonCancel;
    /** This button manages the cancel save.
     */
    @FXML
    private Button buttonSave;
    /** This is a label that holds text and manages translation of the Address text.
     */
    @FXML
    private Label labelAddress;
    /** This is a label that holds text and manages translation of the Countries text.
     */
    @FXML
    private Label labelCountries;
    /** This is a label that holds text and manages translation of the Customer ID text.
     */
    @FXML
    private Label labelCustomerId;
    /** This is a label that holds text and manages translation of the Customer Name text.
     */
    @FXML
    private Label labelCustomerName;
    /** This is a label that holds text and manages translation of the Divisions text.
     */
    @FXML
    private Label labelDivisions;
    /** This is a label that holds text and manages translation of the Header text.
     */
    @FXML
    private Label labelHeader;
    /** This is a label that holds text and manages translation of the Phone Number text.
     */
    @FXML
    private Label labelPhoneNumber;
    /** This is a label that holds text and manages translation of the Postal Code text.
     */
    @FXML
    private Label labelPostalCode;
    /** This is a combo box that holds the different options for Countries available.
     */
    @FXML
    private ComboBox menuButtonCountries;
    /** This is a combo box that holds the different options for divisions available.
     */
    @FXML
    private ComboBox menuButtonDivisions;
    /** This is a text field used for accepting data on the Address.
     */
    @FXML
    private TextField textFieldAddress;
    /** This is a text field used for accepting data on the Customer ID.
     */
    @FXML
    private TextField textFieldCustomerId;
    /** This is a text field used for accepting data on the Customer Name.
     */
    @FXML
    private TextField textFieldCustomerName;
    /** This is a text field used for accepting data on the Customer Phone Number.
     */
    @FXML
    private TextField textFieldPhoneNumber;
    /** This is a text field used for accepting data on the Customer Postal Code.
     */
    @FXML
    private TextField textFieldPostalCode;
    /** This variable holds the ID of the country.
     */
    public String country_id;
    /** This variable represents which country was selected.
     */
    private int selectedCountry;
    /** This variable represents which division was selected.
     */
    private int selectedDivision;
    /** When the Add Customer page is initialized this method will run. It fills columns of the table and
     * other data to combo boxes and text fields. It will also translate any text to either French or English depending on
     * system default.
     */
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
    /** This allows the Combo Boxes to change depending on which country is initially selected. US will make the
     * combo box have states, Canada and UK have provinces and divisions.
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
    /** This method will first check if all entries are valid and pass error checks,
     * if so the method will add the Customer to the database.
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
        ResultSet rs = Query.addCustomerToQueryDB(Customer.generateCustomerId(), textFieldCustomerName.getText(), textFieldAddress.getText(), textFieldPostalCode.getText(), textFieldPhoneNumber.getText(),
                createTime, LoginController.username, createTime, LoginController.username, selectDivision(String.valueOf(menuButtonDivisions.getValue())));
        //The list is re-populated and makes sure no duplicates are added to same list----------------------
        Customer.populateList();
        Form.changePageTo(e, "mainMenuViewCustomers.fxml");

    }
    /** This method will return which division was selected.
     * @param division This will be the name of the division that is retrieved from the combo box.
     * @return result Will return an int that resembles the Division ID related to the string division.
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
}

