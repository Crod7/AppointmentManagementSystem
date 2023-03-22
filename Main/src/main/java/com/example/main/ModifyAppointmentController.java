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
/** This class controls the modify appointment page.
 */
public class ModifyAppointmentController implements Initializable{
    /*Manages the buttons/ labels on screen-------------------------------------------------------------------------------------------*/
    /** This is the cancel button.
     */
    @FXML
    private Button buttonCancel;
    /** This is the save button.
     */
    @FXML
    private Button buttonSave;

    /** This list holds the options of the choice boxes that allow you to select which hour the appointment will take place.
     */
    private String[] hourOptions = {"00","01","02","03","04","05","06","07","08","09","10","11"
            ,"12","13","14","15","16","17","18","19","20","21","22","23"};
    /** This list holds the options of the choice boxes that allow you to select which minute the appointment will take place.
     */
    private String[] minuteOptions = {"00","15","30","45"};
    /** This holds the FXML combo box for the End Time Hour of an appointment.
     */
    @FXML
    private ChoiceBox<String> choiceBoxEndTimeHour;
    /** This holds the FXML combo box for the End Time Minute of an appointment.
     */
    @FXML
    private ChoiceBox<String> choiceBoxEndTimeMinute;
    /** This holds the FXML combo box for the Start Time Hour of an appointment.
     */
    @FXML
    private ChoiceBox<String> choiceBoxStartTimeHour;
    /** This holds the FXML combo box for the Start Time Minute of an appointment.
     */
    @FXML
    private ChoiceBox<String> choiceBoxStartTimeMinute;
    /** This class handles the Day on which the appointment will end.
     */
    @FXML
    private DatePicker datePickerEndDate;
    /** This class handles the Day on which the appointment will begin.
     */
    @FXML
    private DatePicker datePickerStartDate;
    /** This is a label that holds text and manages translation of the Appointment ID text.
     */
    @FXML
    private Label labelAppointmentId;
    /** This is a label that holds text and manages translation of the Contact ID text.
     */
    @FXML
    private Label labelContactId;
    /** This is a label that holds text and manages translation of the Customer ID text.
     */
    @FXML
    private Label labelCustomerId;
    /** This is a label that holds text and manages translation of the Description.
     */
    @FXML
    private Label labelDescription;
    /** This is a label that holds text and manages translation of the End Date label.
     */
    @FXML
    private Label labelEndDate;
    /** This is a label that holds text and manages translation of the End Time label.
     */
    @FXML
    private Label labelEndTime;
    /** This is a label that holds text and manages translation of the Header label.
     */
    @FXML
    private Label labelHeader;
    /** This is a label that holds text and manages translation of the Location text field label.
     */
    @FXML
    private Label labelLocation;
    /** This is a label that holds text and manages translation of the Start Date label.
     */
    @FXML
    private Label labelStartDate;
    /** This is a label that holds text and manages translation of the Start Time label.
     */
    @FXML
    private Label labelStartTime;
    /** This is a label that holds text and manages translation of the Title text field label.
     */
    @FXML
    private Label labelTitle;
    /** This is a label that holds text and manages translation of the Type text field label.
     */
    @FXML
    private Label labelType;
    /** This is a Combo Box that lets the user select a Contact ID.
     */
    @FXML
    private ComboBox menuButtonContactId;
    /** This variable represents which Contact was selected.
     */
    private int selectedContact;
    /** This is a menu button that holds Customer ID information.
     */
    @FXML
    private MenuButton menuButtonCustomerId;
    /** This holds int data for the appointment ID.
     */
    public static int appointmentIdData;
    /** This is a text field that holds the Appointment ID.
     */
    @FXML
    private TextField textFieldAppointmentId;
    /** This holds string data for the description.
     */
    public static String descriptionData;
    /** This is a text field that holds the Description.
     */
    @FXML
    private TextField textFieldDescription;
    /** This holds string data for the location.
     */
    public static String locationData;
    /** This is a text field that holds the Location string.
     */
    @FXML
    private TextField textFieldLocation;
    /** This holds string data for the title.
     */
    public static String titleData;
    /** This is a text field that holds the Title.
     */
    @FXML
    private TextField textFieldTitle;
    /** This holds string data for the type.
     */
    public static String typeData;
    /** This is a text field that holds the Type string.
     */
    @FXML
    private TextField textFieldType;
    /** This holds string data for the start date.
     */
    public static String startData;
    /** This holds string data for the end date.
     */
    public static String endData;
    /** This holds string data for the created date.
     */
    public static String createDateData;
    /** This holds string data for the created by.
     */
    public static String createdByData;
    /** This holds int data for the customer ID.
     */
    public static int customerIdData;
    /** This holds int data for the contact name.
     */
    public static String contactNameData;
    /** This is the selected appointment to be modified.
     */
    public static Appointment selectedApp;

    /** This variable holds which contact the customer may contact for information about their appointment.
     */
    private String contact_id;
    /** This variable holds which customer this appointment will belong to.
     */
    private Customer selectedCustomer;

    // Manages the table of customers, the user can select which customer this appointment is for ------------------
    /** This is a column for the customer ID.
     */
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;
    /** This is a column for the customer name.
     */
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    /** This is a table to represent the customers.
     */
    @FXML
    private TableView<Customer> tableViewCustomerTable;
    /** When the Modify Appointment page is initialized this method will run. It fills columns of the table and
     * other data to combo boxes and text fields. It will also translate any text to either French or English depending on
     * system default.
     */
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
        datePickerEndDate.setValue(endDateVal);
        choiceBoxEndTimeHour.setValue(endData.substring(11,13));
        choiceBoxEndTimeMinute.setValue(endData.substring(14,16));

    }

    /** Uses the current contact selected to set the selectedContact variable.
     */
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
    /** This method will save the appointment to the database. The method will go through a series of error checks to make
     * sure that the appointment being added will cause no issues. If any check is failed the appointment will not be added and the user will get
     * a message informing them why it failed to add.
     */
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

        String startTimeCheckIfOpen = TimeConversion.ConvertToUtc(datePickerStartDate.getValue(), parseInt(choiceBoxStartTimeHour.getValue()), parseInt(choiceBoxStartTimeMinute.getValue()));
        String endTimeCheckIfOpen = TimeConversion.ConvertToUtc(datePickerEndDate.getValue(), parseInt(choiceBoxEndTimeHour.getValue()), parseInt(choiceBoxEndTimeMinute.getValue()));

        LocalDateTime startTime = TimeConversion.ConvertToLocalDateTime(datePickerStartDate.getValue(), Integer.parseInt(choiceBoxStartTimeHour.getValue()), Integer.parseInt(choiceBoxStartTimeMinute.getValue()));
        LocalDateTime endTime = TimeConversion.ConvertToLocalDateTime(datePickerEndDate.getValue(), Integer.parseInt(choiceBoxEndTimeHour.getValue()), Integer.parseInt(choiceBoxEndTimeMinute.getValue()));




        if (BusinessHours.checkIfOpen(startTimeCheckIfOpen, endTimeCheckIfOpen)){
            ErrorMessage.msg(Lang.print("Store")+" "+Lang.print("hours")+" "+Lang.print("between")+" 8:00am - 10:00pm EST" );
            return;
        }
        //startTime = TimeConversion.ConvertToLocal(startTime);
        //endTime = TimeConversion.ConvertToLocal(endTime);
        //System.out.println(startTime);


        //This will make sure that the beginning of an appointment starts before the end of the appointment-------------------------------------------------
        //If hour the same, minute must be less
        LocalDateTime startCheck = TimeConversion.ConvertToTimeObj(startTimeCheckIfOpen);
        LocalDateTime endCheck = TimeConversion.ConvertToTimeObj(endTimeCheckIfOpen);
        if (startCheck.isAfter(endCheck) || startCheck.isEqual(endCheck)){
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
        if (OverlappingAppointments.checkIfAvailableModifyAppointment(startTimeCheckIfOpen, endTimeCheckIfOpen, appointmentIdData)){
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
    /** This method is assigned to a button and will take the user back to the main menu page.
     */
    public void cancelButtonClick(ActionEvent e) throws IOException{
        Form.changePageTo(e, "mainMenuViewAll.fxml");
    }
    /** This method is assigned to a button and will allow the user to select a customer to assign to an appointment.
     */
    public int selectCustomer(ActionEvent e) throws IOException {
        selectedCustomer = tableViewCustomerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            int result = selectedCustomer.getCustomerId();
            return result;
        }
        return 0;
    }
}
