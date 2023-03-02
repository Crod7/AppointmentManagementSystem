package com.example.main;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuViewAllController implements Initializable {
    /*Manages the variables of the buttons and labels on screen-------------------------------------------------------------------------------------------*/
    @FXML
    private Button buttonAddAppointment;

    @FXML
    private Button buttonDeleteAppointment;

    @FXML
    private Button buttonLogout;

    @FXML
    private Button buttonModifyAppointment;

    @FXML
    private Button buttonReports;

    @FXML
    private RadioButton buttonViewAll;

    @FXML
    private RadioButton buttonViewByMonth;

    @FXML
    private RadioButton buttonViewByWeek;

    @FXML
    private RadioButton buttonViewCustomers;

    @FXML
    private DatePicker datePickerViewByDate;

    @FXML
    private Label labelAppointmentSchedule;

    /*Manages what columns go into table------------------------------------------------------------------------------------------------------------------------*/
    /** This is the JavaFX ID for the main table displayed on screen.
     */
    @FXML
    private TableView<Appointment> tableviewMainMenuTable;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;
    @FXML
    private TableColumn<Appointment, String> titleColumn;
    @FXML
    private TableColumn<Appointment, String> descriptionColumn;
    @FXML
    private TableColumn<Appointment, String> locationColumn;
    @FXML
    private TableColumn<Appointment, String> typeColumn;
    @FXML
    private TableColumn<Appointment, String> startColumn;
    @FXML
    private TableColumn<Appointment, String> endColumn;
    @FXML
    private TableColumn<Appointment, String> createDateColumn;
    @FXML
    private TableColumn<Appointment, String> createdByColumn;
    @FXML
    private TableColumn<Appointment, String> lastUpdateColumn;
    @FXML
    private TableColumn<Appointment, String> lastUpdatedByColumn;
    @FXML
    private TableColumn<Appointment, Integer> customerIdColumn;
    @FXML
    private TableColumn<Appointment, Integer> userIdColumn;
    @FXML
    private TableColumn<Appointment, Integer> contactIdColumn;

    @FXML
    private ToggleGroup viewGroup;

    /*Manages the table view------------------------------------------------------------------------------------------------------------------------*/
    /** This method sets up the two tables on the mainForm page.
     On initialization the method will set up the Part table and Product table.
     It will also handle the resource bundle that controls the language settings.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //sets the table view-----------------------------------------------------------------------------------------
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment ,Integer>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
        //createDateColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("createDate"));
        //createdByColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("createdBy"));
        //lastUpdateColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("lastUpdate"));
        //lastUpdatedByColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("lastUpdatedBy"));
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
        //createDateColumn.setText(Lang.print("Create")+" "+Lang.print("Date"));
        //createdByColumn.setText(Lang.print("Created")+" "+Lang.print("By"));
        //lastUpdateColumn.setText(Lang.print("Last")+" "+Lang.print("Update"));
        //lastUpdatedByColumn.setText(Lang.print("Last")+" "+Lang.print("Updated")+" "+Lang.print("By"));
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
    /** This function is linked to a button, and when the button is pressed will take the user to the ModifyAppointment form.
     */
    public void modifyAppointmentButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "modifyAppointment.fxml");
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
    // Controls the delete option
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
}
