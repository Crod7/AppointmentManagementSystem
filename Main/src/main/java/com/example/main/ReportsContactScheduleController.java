package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportsContactScheduleController implements Initializable {
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
    private TableView<Appointment> tableViewMainTable;
    /** This variable manages the Column holding the AppointmentID data.
     */
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;
    /** This variable manages the Column holding the Description data.
     */
    @FXML
    private TableColumn<Appointment, String> descriptionColumn;
    /** This variable manages the Column holding the End Time data, which holds the time the appointment ends.
     */
    @FXML
    private TableColumn<Appointment, String> endColumn;
    /** This variable manages the Column holding the Start Time data, which holds the time the appointment starts.
     */
    @FXML
    private TableColumn<Appointment, String> startColumn;
    /** This variable manages the Column holding the Title data.
     */
    @FXML
    private TableColumn<Appointment, String> titleColumn;
    /** This variable manages the Column holding the Type data.
     */
    @FXML
    private TableColumn<Appointment, String> typeColumn;
    /** This variable manages the Column holding the CustomerID data.
     */
    @FXML
    private TableColumn<Appointment, Integer> customerIdColumn;
    /** This variable manages the ComboBox that decides what contact will be used to filter the table.
     */
    @FXML
    private ComboBox menuButtonContactId;

    /** When the page is initialized, the text will be converted to the correct language (Either english or french).
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentIdColumn.setText(Lang.print("Appointment")+" "+Lang.print("ID"));
        titleColumn.setText(Lang.print("Title"));
        typeColumn.setText(Lang.print("Type"));
        descriptionColumn.setText(Lang.print("Description"));
        startColumn.setText(Lang.print("Start"));
        endColumn.setText(Lang.print("End"));
        customerIdColumn.setText(Lang.print("Customer")+" "+Lang.print("ID"));
        buttonCancel.setText(Lang.print("Cancel"));
        buttonLogout.setText(Lang.print("Logout"));
        labelHeader.setText(Lang.print("Reports"));
        radioButtonSortByContact.setText(Lang.print("Contact")+" "+Lang.print("Schedule"));
        radioButtonSortByCountry.setText(Lang.print("Total")+" "+Lang.print("Customers")+" "+Lang.print("by")+" "+Lang.print("Country"));
        radioButtonSortByMonthAndType.setText(Lang.print("Total")+" "+Lang.print("Customers")+" "+Lang.print("by")+" "+Lang.print("Month")+" "+Lang.print("And")+" "+Lang.print("Type"));
        //This label changes depending on the Form selected by the radio buttons
        labelSortedBy.setText(Lang.print("Contact"));

        //
        menuButtonContactId.setItems(Contact.getAllContacts());
    }
    /** When a contact is selected, the table view will display all appointments associated with the contact.
     */
    public void sortByContactSelect(ActionEvent e) throws IOException{
        //sets the table view-----------------------------------------------------------------------------------------
        Appointment.filterByContact((Contact) menuButtonContactId.getSelectionModel().getSelectedItem());
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment ,Integer>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment ,Integer>("customerId"));
        tableViewMainTable.setItems(Appointment.getAllAppointmentsFiltered());
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
}