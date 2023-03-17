package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.Month;
import java.util.ResourceBundle;
/** This class controls the Reports Contact View By Month And Type Page.
 */
public class ReportsContactViewByMonthAndTypeController implements Initializable {
    /*Manages the buttons/ labels on screen-------------------------------------------------------------------------------------------*/
    @FXML
    private Button goButton;
    /** This variable manages the button so, it can be referenced when selecting this button to change the page.
     */
    @FXML
    private Button buttonCancel;
    /** This variable manages the button so, it can be referenced when selecting this button to change the page.
     */
    @FXML
    private Button buttonLogout;
    /** This variable manages text of the header.
     */
    @FXML
    private Label labelHeader;
    /** This variable manages text to tell the user what the combo box is used for.
     */
    @FXML
    private Label labelMonth;
    /** This variable manages text to tell the user what the combo box is used for.
     */
    @FXML
    private Label labelType;

    /** This variable manages a radio button that when pressed will change the table view to filter Appointments by Contact.
     */
    @FXML
    private RadioButton radioButtonSortByContact;
    /** This variable manages a radio button that when pressed will change the table view to filter Appointments by Country.
     */
    @FXML
    private RadioButton radioButtonSortByCountry;
    /** This variable manages a radio button that when pressed will change the table view to filter Appointments by Month and Type
     */
    @FXML
    private RadioButton radioButtonSortByMonthAndType;
    /** This variable manages which radio button is selected.
     */
    @FXML
    private ToggleGroup radioGroup;
    /** This variable manages the Table View on the page.
     */
    @FXML
    private TableView<MonthAndType> tableViewMainTable;
    /** This variable manages the Column holding the Type data.
     */
    @FXML
    private TableColumn<MonthAndType, String> typeColumn;
    /** This variable manages the Column holding the Month data.
     */
    @FXML
    private TableColumn<MonthAndType, String> monthColumn;
    /** This variable manages the Column holding the Quantity data.
     */
    @FXML
    private TableColumn<MonthAndType, Integer> quantityColumn;
    /** This variable manages the ComboBox that decides what month will be used to filter the table.
     */
    @FXML
    private ComboBox menuButtonMonth;
    /** This variable manages the ComboBox that decides what type will be used to filter the table.
     */
    @FXML
    private ComboBox menuButtonType;

    /** When the page is initialized, the text will be converted to the correct language (Either english or french).
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        monthColumn.setText(Lang.print("Month"));
        typeColumn.setText(Lang.print("Type"));
        quantityColumn.setText(Lang.print("Quantity"));
        buttonCancel.setText(Lang.print("Cancel"));
        buttonLogout.setText(Lang.print("Logout"));
        labelHeader.setText(Lang.print("Reports"));
        radioButtonSortByContact.setText(Lang.print("Contact")+" "+Lang.print("Schedule"));
        radioButtonSortByCountry.setText(Lang.print("Total")+" "+Lang.print("Customers")+" "+Lang.print("by")+" "+Lang.print("Country"));
        radioButtonSortByMonthAndType.setText(Lang.print("Total")+" "+Lang.print("Customers")+" "+Lang.print("by")+" "+Lang.print("Month")+" "+Lang.print("And")+" "+Lang.print("Type"));
        //This label changes depending on the Form selected by the radio buttons-----------------
        labelType.setText(Lang.print("Type"));
        labelMonth.setText(Lang.print("Month"));

        //This will fill the combo boxes with the available options------------------
        MonthAndType.populateTypeComboBox();
        menuButtonMonth.getItems().addAll(DateFilter.monthOptions);
        menuButtonType.getItems().addAll(MonthAndType.typeOptions);
    }
    /** This button will log out the user and bring them back to the login screen.
     */
    public void logoutButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "login.fxml");
    }
    /** This button will return the user back to the main menu page.
     */
    public void cancelButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "mainMenuViewAll.fxml");
    }
    /** When pressed, will display a report showing the number of appointments matching the filter settings of month and type set by the user. Also checks to make sure the user entered the both filters.
     */
    public void goButtonClick(){
        if (menuButtonMonth.getValue() != null && menuButtonType.getValue() != null){
            MonthAndType.populateList(String.valueOf(menuButtonMonth.getValue()), String.valueOf(menuButtonType.getValue()));
            monthColumn.setCellValueFactory(new PropertyValueFactory<MonthAndType, String>("month"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<MonthAndType, String>("type"));
            quantityColumn.setCellValueFactory(new PropertyValueFactory<MonthAndType ,Integer>("quantity"));
            tableViewMainTable.setItems(MonthAndType.getAllAppointments());
        } else {
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("Month")+" "+Lang.print("and")+" "+Lang.print("Type")+".");
        }
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the Month And Type Reports form.
     */
    public void contactButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "reportsContactSchedule.fxml");
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the Country Reports form.
     */
    public void countryButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "reportsViewByCountry.fxml");
    }
}