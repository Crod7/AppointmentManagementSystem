package com.example.main;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyCustomerController {
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
    private Label labelAddress;

    @FXML
    private Label labelCountries;

    @FXML
    private Label labelCustomerId;

    @FXML
    private Label labelCustomerName;

    @FXML
    private Label labelDivisions;

    @FXML
    private Label labelHeader;

    @FXML
    private Label labelPhoneNumber;

    @FXML
    private Label labelPostalCode;

    @FXML
    private MenuButton menuButtonCountries;

    @FXML
    private MenuButton menuButtonDivisions;

    @FXML
    private TextField textFieldAddress;

    @FXML
    private TextField textFieldCustomerId;

    @FXML
    private TextField textFieldCustomerName;

    @FXML
    private TextField textFieldPhoneNumber;

    @FXML
    private TextField textFieldPostalCode;

}