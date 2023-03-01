package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable{
    private Parent root;
    /*Manages the buttons/ labels on screen-------------------------------------------------------------------------------------------*/
    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonSave;

    /** This list holds the options of the choice boxes that allow you to select which hour the appointment will take place.
     */
    private String[] hourOptions = {"00","01","02","03","04","05","06","07","08","09","10","11"
            ,"12","13","14","15","16","17","18","19","20","21","22","23"};
    /** This list holds the options of the choice boxes that allow you to select which minute the appointment will take place.
     */
    private String[] minuteOptions = {"00","15","30","45"};
    @FXML
    private ChoiceBox<String> choiceBoxEndTimeHour;

    @FXML
    private ChoiceBox<String> choiceBoxEndTimeMinute;

    @FXML
    private ChoiceBox<String> choiceBoxStartTimeHour;

    @FXML
    private ChoiceBox<String> choiceBoxStartTimeMinute;

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
    private ComboBox menuButtonContactId;
    private int selectedContact;

    @FXML
    private MenuButton menuButtonCustomerId;

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

    // Manages the table of customers, the user can select which customer this appointment is for ------------------
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    @FXML
    private TableView<Customer> tableViewCustomerTable;

    public void initialize(URL url, ResourceBundle resourceBundle){
        // This loads up the options for the choice box---------------------------------------------------------
        choiceBoxEndTimeHour.getItems().addAll(hourOptions);
        choiceBoxStartTimeHour.getItems().addAll(hourOptions);
        choiceBoxEndTimeMinute.getItems().addAll(minuteOptions);
        choiceBoxStartTimeMinute.getItems().addAll(minuteOptions);
        menuButtonContactId.setItems(Contact.getAllContacts());

        // This loads up the table view with customers---------------------------------------------------------------
        Customer.populateList();
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<Customer ,Integer>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        tableViewCustomerTable.setItems(Customer.getAllCustomers());

        // Changes the language of each column and label to default system language--------------------------------------------------------------------
        customerIdColumn.setText(Lang.print("Customer")+" "+Lang.print("ID"));
        customerNameColumn.setText(Lang.print("Customer")+" "+Lang.print("Name"));
        labelAppointmentId.setText(Lang.print("Appointment")+" "+Lang.print("ID"));
        labelContactId.setText(Lang.print("Contact"));
        labelCustomerId.setText(Lang.print("Customer"));
        labelDescription.setText(Lang.print("Description"));
        labelEndDate.setText(Lang.print("End")+" "+Lang.print("Date"));
        labelEndTime.setText(Lang.print("End")+" "+Lang.print("Time"));
        labelHeader.setText(Lang.print("Add")+" "+Lang.print("Appointments"));
        labelLocation.setText(Lang.print("Location"));
        labelStartDate.setText(Lang.print("Start")+" "+Lang.print("Date"));
        labelStartTime.setText(Lang.print("Start")+" "+Lang.print("Time"));
        labelTitle.setText(Lang.print("Title"));
        labelType.setText(Lang.print("Type"));
        buttonCancel.setText(Lang.print("Cancel"));
        buttonSave.setText(Lang.print("Save"));
    }


    public void selectContact(ActionEvent e){
        try {
            //String contact_id = "";
            String contact_id = String.valueOf(menuButtonContactId.getSelectionModel().getSelectedItem()/*.toString()*/);
            ResultSet rs = Query.queryDB("SELECT * FROM contacts");
            while (rs.next()) {
                if (rs.getString("contact_name").equals(contact_id)){
                    selectedContact =  rs.getInt("contact_id");
                }
            }
        } catch (SQLException se){

        }
    }
    public void saveButtonClick(ActionEvent e) throws IOException {
        //These dates are converted to UTC so that when other users download the data it will be converted to their local time
        LocalDateTime currentLocalTime = LocalDateTime.now();
        String createTime = TimeConversion.ConvertToUtc(currentLocalTime.toLocalDate(), currentLocalTime.getHour(), currentLocalTime.getMinute());
        String startTime = TimeConversion.ConvertToUtc(datePickerStartDate.getValue(), Integer.parseInt(choiceBoxStartTimeHour.getValue()), Integer.parseInt(choiceBoxStartTimeMinute.getValue()));
        String endTime = TimeConversion.ConvertToUtc(datePickerEndDate.getValue(), Integer.parseInt(choiceBoxEndTimeHour.getValue()), Integer.parseInt(choiceBoxEndTimeMinute.getValue()));


        //Finally this addToQueryDB method inserts a row into the database
        ResultSet rs = Query.addToQueryDB(textFieldTitle.getText(), textFieldDescription.getText(), textFieldLocation.getText(), textFieldType.getText(), startTime,
                endTime, createTime, LoginController.username, createTime, LoginController.username, selectCustomer(e), Appointment.autoUserIdGenerator(), selectedContact);
        //The list is re-populated and makes sure no duplicates are added to same list
        Appointment.populateList();
        Form.changePageTo(e, "mainMenuViewAll.fxml");
    }

    public void cancelButtonClick(ActionEvent e) throws IOException{
        Form.changePageTo(e, "mainMenuViewAll.fxml");
    }

    public int selectCustomer(ActionEvent e) throws IOException {
        Customer selected = tableViewCustomerTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            int result = selected.getCustomerId();
            return result;
        }
        return 0;
    }
}
