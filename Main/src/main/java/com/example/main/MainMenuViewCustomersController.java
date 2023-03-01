package com.example.main;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class MainMenuViewCustomersController {
    /*Manages the scene-------------------------------------------------------------------------------------------*/
    /** This sets the stage.
     */
    private Stage stage;
    /** This sets the scene.
     */
    private Scene scene;
    /** This sets the root.
     */
    private Parent root;
    /*Manages the buttons/ labels on screen-------------------------------------------------------------------------------------------*/

    @FXML
    private Button buttonAddCustomer;

    @FXML
    private Button buttonDeleteCustomer;

    @FXML
    private Button buttonLogout;

    @FXML
    private Button buttonModifyCustomer;

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

    @FXML
    private TableView<?> tableviewMainMenuTable;

    @FXML
    private ToggleGroup viewGroup;

}