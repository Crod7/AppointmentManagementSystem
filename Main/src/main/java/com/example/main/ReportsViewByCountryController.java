package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/** This class controls the Reports Contact View By Country Page.
 */
public class ReportsViewByCountryController implements Initializable {
    /*Manages the buttons/ labels on screen-------------------------------------------------------------------------------------------*/
    /** This variable manages the button so, it can be referenced when selecting this button to change the page.
     */
    @FXML
    private Button buttonCancel;
    /** This variable manages the button so, it can be referenced when selecting this button to change the page.
     */
    @FXML
    private Button buttonLogout;
    /** This variable manages text of the header.
     */
    @FXML
    private Label labelHeader;
    /** This variable manages text to tell the user what the combo box is used for.
     */
    @FXML
    private Label labelSortedBy;

    /** This variable manages a radio button that when pressed will change the table view to filter Appointments by Contact.
     */
    @FXML
    private RadioButton radioButtonSortByContact;
    /** This variable manages a radio button that when pressed will change the table view to filter Appointments by Country.
     */
    @FXML
    private RadioButton radioButtonSortByCountry;
    /** This variable manages a radio button that when pressed will change the table view to filter Appointments by Month and Type
     */
    @FXML
    private RadioButton radioButtonSortByMonthAndType;
    /** This variable manages which radio button is selected.
     */
    @FXML
    private ToggleGroup radioGroup;
    /** This variable manages the Table View on the page.
     */
    @FXML
    private TableView<CustomerTotal> tableViewMainTable;
    /** This variable manages the Column holding the country data.
     */
    @FXML
    private TableColumn<CustomerTotal, String> countryColumn;
    /** This variable manages the Column holding the quantity data.
     */
    @FXML
    private TableColumn<CustomerTotal, Integer> quantityColumn;
    /** This variable manages the ComboBox that decides what contact will be used to filter the table.
     */
    @FXML
    private ComboBox menuButtonCountry;

    /** When the page is initialized, the text will be converted to the correct language (Either english or french).
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {


        countryColumn.setText(Lang.print("Country"));
        quantityColumn.setText(Lang.print("Quantity"));
        buttonCancel.setText(Lang.print("Cancel"));
        buttonLogout.setText(Lang.print("Logout"));
        labelHeader.setText(Lang.print("Reports"));
        radioButtonSortByContact.setText(Lang.print("Contact")+" "+Lang.print("Schedule"));
        radioButtonSortByCountry.setText(Lang.print("Total")+" "+Lang.print("Customers")+" "+Lang.print("by")+" "+Lang.print("Country"));
        radioButtonSortByMonthAndType.setText(Lang.print("Total")+" "+Lang.print("Customers")+" "+Lang.print("by")+" "+Lang.print("Month")+" "+Lang.print("And")+" "+Lang.print("Type"));
        //This label changes depending on the Form selected by the radio buttons
        labelSortedBy.setText(Lang.print("Country"));

        //Sets all the countries the usr can select
        menuButtonCountry.getItems().addAll(Countries.countryOptions);
    }
    /** When a contact is selected, the table view will display all appointments associated with the contact.
     */
    public void sortByCountrySelect(ActionEvent e) throws IOException{
        //sets the table view-----------------------------------------------------------------------------------------
        CustomerTotal.populateList(String.valueOf(menuButtonCountry.getValue()));
        countryColumn.setCellValueFactory(new PropertyValueFactory<CustomerTotal, String>("country"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<CustomerTotal ,Integer>("quantity"));
        tableViewMainTable.setItems(CustomerTotal.getAllCustomers());
    }
    /** This button will log out the user and bring them back to the login screen.
     */
    public void logoutButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "login.fxml");
    }
    /** This button will return the user back to the main menu page.
     */
    public void cancelButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "mainMenuViewAll.fxml");
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the Month And Type Reports form.
     */
    public void monthAndTypeButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "reportsViewByMonthAndType.fxml");
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the Country Reports form.
     */
    public void contactButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "reportsContactSchedule.fxml");
    }
}