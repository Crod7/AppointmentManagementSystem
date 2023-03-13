package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class ModifyAppointmentController implements Initializable{
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

    public static int appointmentIdData;

    @FXML
    private TextField textFieldAppointmentId;

    public static String descriptionData;
    @FXML
    private TextField textFieldDescription;

    public static String locationData;
    @FXML
    private TextField textFieldLocation;

    public static String titleData;
    @FXML
    private TextField textFieldTitle;

    public static String typeData;
    @FXML
    private TextField textFieldType;
    public static String startData;
    public static String endData;
    public static String createDateData;
    public static String createdByData;
    public static int customerIdData;
    public static String contactNameData;
    public static Appointment selectedApp;

    /** This variable holds which contact the customer may contact for information about their appointment.
     */
    private String contact_id;
    /** This variable holds which customer this appointment will belong to.
     */
    private Customer selectedCustomer;

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
        //labelEndDate.setText(Lang.print("End")+" "+Lang.print("Date"));
        labelEndTime.setText(Lang.print("End")+" "+Lang.print("Time"));
        labelHeader.setText(Lang.print("Modify")+" "+Lang.print("Appointments"));
        labelLocation.setText(Lang.print("Location"));
        labelStartDate.setText(Lang.print("Start")+" "+Lang.print("Date"));
        labelStartTime.setText(Lang.print("Start")+" "+Lang.print("Time"));
        labelTitle.setText(Lang.print("Title"));
        labelType.setText(Lang.print("Type"));
        buttonCancel.setText(Lang.print("Cancel"));
        buttonSave.setText(Lang.print("Save"));

        // This loads the data from the selected appointment you wish to modify---------------------------------------
        textFieldAppointmentId.setText(String.valueOf(appointmentIdData));
        textFieldTitle.setText(titleData);
        textFieldDescription.setText(descriptionData);
        textFieldLocation.setText(locationData);
        textFieldType.setText(typeData);
        tableViewCustomerTable.getSelectionModel().select(Customer.getCustomerId(customerIdData));
        // The following code below grabs the contact_Id from the appointment being modified------------------------
        menuButtonContactId.getSelectionModel().select(Contact.getContactId(contactNameData));
        contact_id = String.valueOf(menuButtonContactId.getSelectionModel().getSelectedItem());
        try {
            ResultSet rs = Query.queryDB("SELECT * FROM contacts");
            while (rs.next()) {
                if (rs.getString("contact_name").equals(contact_id)) {
                    selectedContact = rs.getInt("contact_id");
                }
            }
        } catch (SQLException se){

        }

        // The following code will load date data options with the current times set on selected appointment--------------------
        LocalDate startDateVal = LocalDate.parse(startData.substring(0,10));
        datePickerStartDate.setValue(startDateVal);
        choiceBoxStartTimeHour.setValue(startData.substring(11,13));
        choiceBoxStartTimeMinute.setValue(startData.substring(14,16));

        LocalDate endDateVal = LocalDate.parse(endData.substring(0,10));
        datePickerStartDate.setValue(endDateVal);
        choiceBoxEndTimeHour.setValue(endData.substring(11,13));
        choiceBoxEndTimeMinute.setValue(endData.substring(14,16));

    }


    public void selectContact(ActionEvent e){
        try {
            contact_id = String.valueOf(menuButtonContactId.getSelectionModel().getSelectedItem());
            ResultSet rs = Query.queryDB("SELECT * FROM contacts");
            while (rs.next()) {
                if (rs.getString("contact_name").equals(contact_id)){
                    selectedContact =  rs.getInt("contact_id");
                }
            }
        } catch (SQLException se){

        }
    }
    public void saveButtonClick(ActionEvent e) throws IOException, SQLException {
        // This will check to see if all fields hold data, as any empty text fields will throw an error----------------------
        if (textFieldTitle.getText().equals("")){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("enter")+" "+Lang.print("a")+" "+Lang.print("Title"));
            return;
        }
        if (textFieldDescription.getText().equals("")){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("enter")+" "+Lang.print("a")+" "+Lang.print("Description"));
            return;
        }
        if (textFieldLocation.getText().equals("")){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("enter")+" "+Lang.print("a")+" "+Lang.print("Location"));
            return;
        }
        if (textFieldType.getText().equals("")){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("enter")+" "+Lang.print("a")+" "+Lang.print("Type"));
            return;
        }
        if (datePickerStartDate.getValue() == null || choiceBoxStartTimeHour.getValue() == null || choiceBoxStartTimeMinute.getValue() == null){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("a")+" "+Lang.print("Start")+" "+Lang.print("Date"));
            return;
        }
        if (choiceBoxEndTimeHour.getValue() == null || choiceBoxEndTimeMinute.getValue() == null){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("a")+" "+Lang.print("End")+" "+Lang.print("Date"));
            return;
        }
        /** This variable will hold which customer is selected for the appointment. If no customer is selected, this variable will hold a value of 0 and fail the error handling. Only when a customer is selected will this variable hold a value other than 0 and thus pass the error handling.
         */
        int selectedCustomer = selectCustomer(e);
        if (selectedCustomer == 0){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("a")+" "+Lang.print("Customer"));
            return;
        }
        if (contact_id == null ){
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("a")+" "+Lang.print("Contact"));
            return;
        }

        //WILL WORK WITH THE TIME OF THE APPOINTMENT============================================================================================================================
        //These dates are converted to UTC so that when other users download the data it will be converted to their local time-----------------------------------
        LocalDateTime currentLocalTime = LocalDateTime.now();
        String createTime = TimeConversion.ConvertToUtc(currentLocalTime.toLocalDate(), currentLocalTime.getHour(), currentLocalTime.getMinute());
        String startTime = TimeConversion.ConvertToUtc(datePickerStartDate.getValue(), parseInt(choiceBoxStartTimeHour.getValue()), parseInt(choiceBoxStartTimeMinute.getValue()));
        String endTime = TimeConversion.ConvertToUtc(datePickerStartDate.getValue(), parseInt(choiceBoxEndTimeHour.getValue()), parseInt(choiceBoxEndTimeMinute.getValue()));
        //This will check to see if the appointment starts and ends during business hours, 8am to 10pm EST----------------------------------------------------
        if (TimeConversion.checkIfOpen(startTime) || TimeConversion.checkIfOpen(endTime)){
            ErrorMessage.msg(Lang.print("Store")+" "+Lang.print("hours")+" "+Lang.print("between")+" 8:00am - 10:00pm EST" );
            return;
        }
        //This will make sure that the beginning of an appointment starts before the end of the appointment--------------------------------------------------
        //If hour the same, minute must be less
        if ((Integer.parseInt(choiceBoxStartTimeHour.getValue()) == Integer.parseInt(choiceBoxEndTimeHour.getValue())) &&
                (Integer.parseInt(choiceBoxStartTimeMinute.getValue()) >= Integer.parseInt(choiceBoxEndTimeMinute.getValue()))){
            ErrorMessage.msg(Lang.print("Appointment")+" "+Lang.print("must")+" "+Lang.print("start")+" "+Lang.print("before")+" "+Lang.print("End")+" "+Lang.print("of")+" "+Lang.print("Appointment")+".");
            return;
        }
        //If hour starts after the end of appointment, also calls error
        if ((Integer.parseInt(choiceBoxStartTimeHour.getValue()) > Integer.parseInt(choiceBoxEndTimeHour.getValue()))){
            ErrorMessage.msg(Lang.print("Appointment")+" "+Lang.print("must")+" "+Lang.print("start")+" "+Lang.print("before")+" "+Lang.print("End")+" "+Lang.print("of")+" "+Lang.print("Appointment")+".");
            return;
        }
        //This makes sure that the appointment is between monday through friday, any weekend days are not allowed-----------------------------------------
        LocalDate day = datePickerStartDate.getValue();
        if (String.valueOf(day.getDayOfWeek()).equals("SATURDAY") || String.valueOf(day.getDayOfWeek()).equals("SUNDAY")){
            ErrorMessage.msg("Appointments can only be made during Monday - Friday");
            return;
        }
        //We check to see if any other appointment is taking up this time, as we cannot have overlapping appointments-----------------------------------------
        if (OverlappingAppointments.checkIfAvailableModifyAppointment(startTime, endTime, appointmentIdData)){
            ErrorMessage.msg("Appointment is overlapping with another Appointment.");
            return;
        }

        //====================================================================================================================================================================


        //Before an appointment can be replaced, we must first update the appointment from the database and reinsert it to take it's place---------------
        Appointment.updateAppointment(selectedApp);
        //Finally this addToQueryDB method inserts a row into the database----------------------
        ResultSet rs = Query.addToQueryDB(appointmentIdData, textFieldTitle.getText(), textFieldDescription.getText(), textFieldLocation.getText(), textFieldType.getText(), startTime,
                endTime, createDateData, createdByData, createTime/*UpdateTime*/, LoginController.username/*LastUpdatedBy*/, /*selectCustomer(e)*/selectedCustomer, Appointment.autoUserIdGenerator(), selectedContact);
        //The list is re-populated and makes sure no duplicates are added to same list----------------------
        Appointment.populateList();
        Form.changePageTo(e, "mainMenuViewAll.fxml");
    }

    public void cancelButtonClick(ActionEvent e) throws IOException{
        Form.changePageTo(e, "mainMenuViewAll.fxml");
    }

    public int selectCustomer(ActionEvent e) throws IOException {
        selectedCustomer = tableViewCustomerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            int result = selectedCustomer.getCustomerId();
            return result;
        }
        return 0;
    }
}
