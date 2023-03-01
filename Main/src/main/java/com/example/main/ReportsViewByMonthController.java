package com.example.main;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ReportsViewByMonthController {
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
    private Button buttonCancel;

    @FXML
    private Button buttonLogout;

    @FXML
    private Label labelHeader;

    @FXML
    private Label labelSortedBy;

    @FXML
    private MenuButton menuButtonSortBy;

    @FXML
    private RadioButton radioButtonSortByContact;

    @FXML
    private RadioButton radioButtonSortByCountry;

    @FXML
    private RadioButton radioButtonSortByMonth;

    @FXML
    private RadioButton radioButtonSortByType;

    @FXML
    private ToggleGroup radioGroup;

    @FXML
    private TableView<?> tableViewMainTable;

}
