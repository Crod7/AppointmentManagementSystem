package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuViewByWeekController implements Initializable {
    /*Manages the variables of the buttons and labels on screen-------------------------------------------------------------------------------------------*/
    /** This menu button will allow the user to select which week to view in the given month.
     */
    @FXML
    private ComboBox menuButtonWeek;
    /** This menu button will allow the user to select which month to view.
     */
    @FXML
    private ComboBox menuButtonMonth;
    /** This button will search for a specific week and return the appointments found in that week.
     */
    @FXML
    private Button buttonSearch;
    /** This button will take the user to the add appointment page.
     */
    @FXML
    private Button buttonAddAppointment;
    /** This button will delete a selected appointment. If no appointment is selected, will display an error message instructing the user to make a selection first.
     */
    @FXML
    private Button buttonDeleteAppointment;
    /** This button will take the user to the login page.
     */
    @FXML
    private Button buttonLogout;
    /** This button will take the user to the modify appointment page.
     */
    @FXML
    private Button buttonModifyAppointment;
    /** This button will take the user to the reports page.
     */
    @FXML
    private Button buttonReports;
    /** This variable holds a radio button, and is used to retrieve data about UI interactions with the button. This button will show all appointments.
     */
    @FXML
    private RadioButton buttonViewAll;
    /** This variable holds a radio button, and is used to retrieve data about UI interactions with the button. This button will sort appointments by month.
     */
    @FXML
    private RadioButton buttonViewByMonth;
    /** This variable holds a radio button, and is used to retrieve data about UI interactions with the button. This button will sort appointments by week.
     */
    @FXML
    private RadioButton buttonViewByWeek;
    /** This variable holds a radio button, and is used to retrieve data about UI interactions with the button. This button will sort appointments by customers.
     */
    @FXML
    private RadioButton buttonViewCustomers;
    /** This variable holds the date picker button, and is used to retrieve data about UI interactions with the button.
     */
    @FXML
    private DatePicker datePickerViewByDate;
    /** This variable holds the label for title of the page.
     */
    @FXML
    private Label labelAppointmentSchedule;

    /*Manages what columns go into table------------------------------------------------------------------------------------------------------------------------*/
    /** This variable holds the table view that will represent the appointment data and it's columns on the main page.
     */
    @FXML
    public TableView<Appointment> tableviewMainMenuTable;
    /** This variable holds the column data for an Appointment Object with Integer data type representing the appointment ID.
     */
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;
    /** This variable holds the column data for an Appointment Object with String data type representing the title of the appointment.
     */
    @FXML
    private TableColumn<Appointment, String> titleColumn;
    /** This variable holds the column data for an Appointment Object with String data type describing the appointment.
     */
    @FXML
    private TableColumn<Appointment, String> descriptionColumn;
    /** This variable holds the column data for an Appointment Object with String data type representing the location of the appointment.
     */
    @FXML
    private TableColumn<Appointment, String> locationColumn;
    /** This variable holds the column data for an Appointment Object with String data type representing the type of the appointment taking place.
     */
    @FXML
    private TableColumn<Appointment, String> typeColumn;
    /** This variable holds the column data for an Appointment Object with String data type representing the start time of the appointment.
     */
    @FXML
    private TableColumn<Appointment, String> startColumn;
    /** This variable holds the column data for an Appointment Object with String data type representing the end time of the appointment.
     */
    @FXML
    private TableColumn<Appointment, String> endColumn;
    /** This variable holds the column data for an Appointment Object with Integer data type representing the customer ID.
     */
    @FXML
    private TableColumn<Appointment, Integer> customerIdColumn;
    /** This variable holds the column data for an Appointment Object with Integer data type representing the user ID.
     */
    @FXML
    private TableColumn<Appointment, Integer> userIdColumn;
    /** This variable holds the column data for an Appointment Object with Integer ata type representing the contact ID.
     */
    @FXML
    private TableColumn<Appointment, Integer> contactIdColumn;

    /** This variable holds the toggle group for the radio buttons on the form.
     */
    @FXML
    private ToggleGroup viewGroup;

    /*Manages the table view------------------------------------------------------------------------------------------------------------------------*/
    /** This method sets up the two tables on the mainForm page.
     On initialization the method will set up the Part table and Product table.
     It will also handle the resource bundle that controls the language settings.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuButtonWeek.getItems().addAll(DateFilter.weekOptions);
        menuButtonMonth.getItems().addAll(DateFilter.monthOptions);
        //sets the table view-----------------------------------------------------------------------------------------
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment ,Integer>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment ,Integer>("customerId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment ,Integer>("userId"));
        contactIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment ,Integer>("contactName"));
        tableviewMainMenuTable.setItems(Appointment.getAllAppointments());
        //changes the language of each column-------------------------------------------------------------------
        appointmentIdColumn.setText(Lang.print("Appointment")+" "+Lang.print("ID"));
        titleColumn.setText(Lang.print("Title"));
        descriptionColumn.setText(Lang.print("Description"));
        locationColumn.setText(Lang.print("Location"));
        typeColumn.setText(Lang.print("Type"));
        startColumn.setText(Lang.print("Start"));
        endColumn.setText(Lang.print("End"));
        customerIdColumn.setText(Lang.print("Customer")+" "+Lang.print("ID"));
        userIdColumn.setText(Lang.print("User")+" "+Lang.print("ID"));
        contactIdColumn.setText(Lang.print("Contact")+" "+Lang.print("ID"));
        //changes the language of each label--------------------------------------------------------------------
        labelAppointmentSchedule.setText(Lang.print("Appointment")+" "+Lang.print("Schedule"));
        buttonAddAppointment.setText(Lang.print("Add")+" "+Lang.print("Appointment"));
        buttonDeleteAppointment.setText(Lang.print("Delete")+" "+Lang.print("Appointment"));
        buttonLogout.setText(Lang.print("Logout"));
        buttonModifyAppointment.setText(Lang.print("Modify")+" "+Lang.print("Appointment"));
        buttonReports.setText(Lang.print("Reports"));
        buttonViewAll.setText(Lang.print("View")+" "+Lang.print("All"));
        buttonViewByMonth.setText(Lang.print("View")+" "+Lang.print("by")+" "+Lang.print("Month"));
        buttonViewByWeek.setText(Lang.print("View")+" "+Lang.print("by")+" "+Lang.print("Week"));
        buttonViewCustomers.setText(Lang.print("View")+" "+Lang.print("Customers"));
    }

    /*Manages all the methods that control navigation between different forms------------------------------------------------------------------------*/
    /** This function is linked to a button, and when the button is pressed will take the user to the MainMenuViewAll form.
     */
    public void viewAllButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "mainMenuViewAll.fxml");
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the MainMenuViewMonth form.
     */
    public void viewByMonthButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "mainMenuViewByMonth.fxml");
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the MainMenuViewWeek form.
     */
    public void viewByWeekButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "mainMenuViewByWeek.fxml");
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the MainMenuViewCustomers form.
     */
    public void viewCustomersButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "mainMenuViewCustomers.fxml");
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the AddAppointment form.
     */
    public void addAppointmentButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "addAppointment.fxml");
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the ModifyAppointment form with all the data passed through it from the selected appointment to be modified.
     */
    public void modifyAppointmentButtonClick(ActionEvent e) throws IOException {
        /** This will hold the object selected on the main menu and pass the data into the text fields of the modify appointment form.
         */
        Appointment selectedAppointment = tableviewMainMenuTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            ModifyAppointmentController.appointmentIdData = selectedAppointment.getAppointmentId();
            ModifyAppointmentController.titleData = selectedAppointment.getTitle();
            ModifyAppointmentController.descriptionData = selectedAppointment.getDescription();
            ModifyAppointmentController.locationData = selectedAppointment.getLocation();
            ModifyAppointmentController.typeData = selectedAppointment.getType();
            ModifyAppointmentController.createDateData = selectedAppointment.getCreateDate();
            ModifyAppointmentController.createdByData = selectedAppointment.getCreatedBy();
            ModifyAppointmentController.startData = selectedAppointment.getStart();
            ModifyAppointmentController.endData = selectedAppointment.getEnd();
            ModifyAppointmentController.contactNameData = selectedAppointment.getContactName();
            ModifyAppointmentController.customerIdData = selectedAppointment.getCustomerId();
            ModifyAppointmentController.selectedApp = selectedAppointment;

            Form.changePageTo(e, "modifyAppointment.fxml");
        } else {
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("an")+" "+Lang.print("Appointment")+" "+Lang.print("to")+" "+Lang.print("modify")+".");
        }
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the ReportsContactSchedule form.
     */
    public void reportsButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "reportsContactSchedule.fxml");
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the Login form.
     */
    public void logoutButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "login.fxml");
    }
    /** This function is linked to a button, and when the button is will delete the currently selected appointment.
     */
    public void deleteAppointmentButtonClick(ActionEvent e) throws IOException {
        Appointment selectedAppointment = tableviewMainMenuTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(Lang.print("Appointments"));
            alert.setHeaderText(Lang.print("Delete"));
            alert.setContentText(Lang.print("Do")+" "+Lang.print("you")+" "+Lang.print("want")+" "+Lang.print("to")+" "+Lang.print("delete")
                    +" "+Lang.print("this")+" "+Lang.print("Appointment")+"?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Appointment.deleteAppointment(selectedAppointment);
            }
        } else {
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("an")+" "+Lang.print("Appointment")+" "+Lang.print("to")+" "+Lang.print("delete")+".");
        }
    }
    /** This function will read what month and week the user wants to use to filter the table view, and it will return the new table on the screen.
     */
    public void weekSelect(ActionEvent e) throws IOException {
        if (menuButtonWeek.getSelectionModel().getSelectedItem() != null && menuButtonMonth.getSelectionModel().getSelectedItem() != null){
            Appointment.filterByWeek(String.valueOf(menuButtonMonth.getSelectionModel().getSelectedItem()), (String.valueOf(menuButtonWeek.getSelectionModel().getSelectedItem()).substring(7)));
            appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment ,Integer>("appointmentId"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
            startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
            endColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
            customerIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment ,Integer>("customerId"));
            userIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment ,Integer>("userId"));
            contactIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment ,Integer>("contactName"));
            tableviewMainMenuTable.setItems(Appointment.getAllAppointmentsFiltered());
        } else {
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("a")+" "+Lang.print("month")+" "+Lang.print("and")+" "+Lang.print("week")+".");
        }
    }
}
