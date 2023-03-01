package com.example.main;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyAppointmentController {
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
    private Button buttonSave;

    @FXML
    private ChoiceBox<?> choiceBoxEndTimeHour;

    @FXML
    private ChoiceBox<?> choiceBoxEndTimeMinute;

    @FXML
    private ChoiceBox<?> choiceBoxStartTimeHour;

    @FXML
    private ChoiceBox<?> choiceBoxStartTimeMinute;

    @FXML
    private DatePicker datePickerEndDate;

    @FXML
    private DatePicker datePickerStartDate;

    @FXML
    private Label labelAppointmentId;

    @FXML
    private Label labelContactId;

    @FXML
    private Label labelCustomerId;

    @FXML
    private Label labelDescription;

    @FXML
    private Label labelEndDate;

    @FXML
    private Label labelEndTime;

    @FXML
    private Label labelHeader;

    @FXML
    private Label labelLocation;

    @FXML
    private Label labelStartDate;

    @FXML
    private Label labelStartTime;

    @FXML
    private Label labelTitle;

    @FXML
    private Label labelType;

    @FXML
    private Label labelUserId;

    @FXML
    private MenuButton menuButtonContactId;

    @FXML
    private MenuButton menuButtonCustomerId;

    @FXML
    private MenuButton menuButtonUserId;

    @FXML
    private TextField textFieldAppointmentId;

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextField textFieldLocation;

    @FXML
    private TextField textFieldTitle;

    @FXML
    private TextField textFieldType;
}
