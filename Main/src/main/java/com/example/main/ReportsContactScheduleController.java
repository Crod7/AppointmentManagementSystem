package com.example.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportsContactScheduleController implements Initializable {
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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonCancel.setText(Lang.print("Cancel"));
        buttonLogout.setText(Lang.print("Logout"));
        labelHeader.setText(Lang.print("Reports"));
        radioButtonSortByContact.setText(Lang.print("Contact")+" "+Lang.print("Schedule"));
        radioButtonSortByCountry.setText(Lang.print("Total")+" "+Lang.print("Customers")+" "+Lang.print("by")+" "+Lang.print("Country"));
        radioButtonSortByMonth.setText(Lang.print("Total")+" "+Lang.print("Customers")+" "+Lang.print("by")+" "+Lang.print("Month"));
        radioButtonSortByType.setText(Lang.print("Total")+" "+Lang.print("Customers")+" "+Lang.print("by")+" "+Lang.print("Type"));
        //This label changes depending on the Form selected by the radio buttons
        labelSortedBy.setText(Lang.print("Contact"));
    }
}