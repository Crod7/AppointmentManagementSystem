package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static com.example.main.JDBC.connection;
/** This class manages the Appointment object.
 */
public class Appointment {
    /** This ObservableList holds all appointments in the database currently.
     */
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    /** This ObservableList holds appointments that have been filtered with certain conditions. These condition vary
     * depending on how and where that method is called.
     */
    private static ObservableList<Appointment> allAppointmentsFiltered = FXCollections.observableArrayList();
    /** This variable holds the Appointment ID integer.
     */
    private int appointmentId;
    /** This variable holds the Title string.
     */
    private String title;
    /** This variable holds the Description string.
     */
    private String description;
    /** This variable holds the Location string.
     */
    private String location;
    /** This variable holds the Type string.
     */
    private String type;
    /** This variable holds the Start date string.
     */
    private String start;
    /** This variable holds the End date string.
     */
    private String end;
    /** This variable holds the Created date string.
     */
    private String createDate;
    /** This variable holds the Created by string.
     */
    private String createdBy;
    /** This variable holds the Last Updated string.
     */
    private String lastUpdate;
    /** This variable holds the Last Updated by string.
     */
    private String lastUpdatedBy;
    /** This variable holds the Customer ID integer.
     */
    private int customerId;
    /** This variable holds the User ID integer.
     */
    private int userId;
    /** This variable holds the Contact Name string.
     */
    private String contactName;
    /** This is the constructor for the Appointment objects.
     * @param appointmentId A unique Identifier integer.
     * @param contactName Holds the name of the contact who is responsible for this appointment.
     * @param createDate The exact time the appointment was first created.
     * @param createdBy Which user created this appointment.
     * @param customerId A unique Identifier for which customer is assigned to this appointment.
     * @param description A short collection of text to describe the appointment.
     * @param lastUpdate A date when this appointment was last updated.
     * @param end When this appointment will end.
     * @param start When this appointment will begin.
     * @param location The address where this appointment will take place.
     * @param title The title of this appointment.
     * @param type What category of appointment this falls into.
     * @param lastUpdatedBy The last user to update this appointment, not the user who created it.
     * @param userId The user associated with the appointment.
     */
    public Appointment(int appointmentId, String title, String description, String location, String type, String start, String end, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy, int customerId, int userId, String contactName) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactName = contactName;
    }
    /** This method is a get method.
     * @return This appointment's ID.
     */
    public int getAppointmentId() {
        return appointmentId;
    }
    /** This method sets the Appointment ID.
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    /** This method is a get method.
     * @return This appointment's Title.
     */

    public String getTitle() {
        return title;
    }
    /** This method sets the Title.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /** This method is a get method.
     * @return This appointment's Description.
     */
    public String getDescription() {
        return description;
    }
    /** This method sets the Description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /** This method is a get method.
     * @return This appointment's Location.
     */
    public String getLocation() {
        return location;
    }
    /** This method sets the Location.
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /** This method is a get method.
     * @return This appointment's Type.
     */
    public String getType() {
        return type;
    }
    /** This method sets the Type.
     */
    public void setType(String type) {
        this.type = type;
    }
    /** This method is a get method.
     * @return This appointment's Start Date.
     */
    public String getStart() {
        return start;
    }
    /** This method sets the Start Date.
     */
    public void setStart(String start) {
        this.start = start;
    }
    /** This method is a get method.
     * @return This appointment's End Date.
     */
    public String getEnd() {
        return end;
    }
    /** This method sets the End Date.
     */
    public void setEnd(String end) {
        this.end = end;
    }
    /** This method is a get method.
     * @return This appointment's Create Date.
     */
    public String getCreateDate() {
        return createDate;
    }
    /** This method sets the Create Date.
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    /** This method is a get method.
     * @return Who created this appointment.
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /** This method sets the Created by string.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /** This method is a get method.
     * @return When this was last updated.
     */
    public String getLastUpdate() {
        return lastUpdate;
    }
    /** This method sets the Last Updated string.
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /** This method is a get method.
     * @return Last user to update this appointment.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /** This method sets the Last Updated by string.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /** This method is a get method.
     * @return This appointment's Customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }
    /** This method sets the Customer ID.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /** This method is a get method.
     * @return This appointment's User ID.
     */
    public int getUserId() {
        return userId;
    }
    /** This method sets the User ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /** This method is a get method.
     * @return This appointment's Contact Name.
     */
    public String getContactName() {
        return contactName;
    }
    /** This method sets the Contact Name.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    //The code below manages the methods used by this class --------------------------------------------------------
    /** This returns the observable list.
     * @return all the appointments in the Observable List.
     */
    public static ObservableList<Appointment> getAllAppointments(){
        return allAppointments;
    }
    /** This returns the observable list.
     * @return all the filtered appointments in the Observable List.
     */
    public static ObservableList<Appointment> getAllAppointmentsFiltered(){
        return allAppointmentsFiltered;
    }
    /** Deletes an appointment from the Observable List.
     * @param selectedAppointment This is the appointment object to be deleted.
     */
    public static boolean deleteAppointment(Appointment selectedAppointment){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Lang.print("Deleted"));
        alert.setHeaderText(Lang.print("Appointment")+" "+Lang.print("ID")+": "+selectedAppointment.getAppointmentId()+"   "+Lang.print("Type")+": "+selectedAppointment.getType());
        alert.setContentText(Lang.print("This")+" "+Lang.print("Appointment")+" "+Lang.print("was")+" "+Lang.print("Deleted")+".");
        alert.showAndWait();

        Query.deleteQueryDB("SELECT * FROM appointments WHERE appointment_id =" + selectedAppointment.getAppointmentId());
        allAppointments.remove(selectedAppointment);
        Appointment.populateList();
        return true;
    }
    /** Updates an appointment on the Observable List.
     * @param selectedAppointment This is the appointment object to be updated.
     */
    public static boolean updateAppointment(Appointment selectedAppointment){
        Query.deleteQueryDB("SELECT * FROM appointments WHERE appointment_id =" + selectedAppointment.getAppointmentId());
        allAppointments.remove(selectedAppointment);
        return true;
    }
    /** Uses a LAMBDA expression to refresh the database appointments with the UI Observable List. Calling this will
     * keep the List up to date. This is used for keeping table views up to date and accurate. If this is not called,
     * the UI and database will not have matching data.
     */
    public static void populateList(){
        try {
            allAppointments.clear();

            Contact.populateList();


            ResultSet rs = Query.queryDB("SELECT * FROM appointments");

            while (rs.next()) {

                int contactId = rs.getInt("contact_id");
                ResultSet cs = Query.queryDB("SELECT * FROM contacts WHERE contact_id = " + String.valueOf(contactId));
                String contactNameFound = "empty";
                while (cs.next()){
                    contactNameFound = cs.getString("contact_name");
                }



                Appointment appObject = new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("location"),
                        rs.getString("type"),
                        //TimeConversion.ConvertToLocal(rs.getString("start")),
                        String.valueOf(rs.getTimestamp("start")),
                        //TimeConversion.ConvertToLocal(rs.getString("end")),
                        String.valueOf(rs.getTimestamp("end")),
                        TimeConversion.ConvertToLocal(rs.getString("create_date")),
                        rs.getString("created_by"),
                        TimeConversion.ConvertToLocal(rs.getString("last_update")),
                        rs.getString("last_updated_by"),
                        rs.getInt("customer_id"),
                        rs.getInt("user_id"),
                        contactNameFound);
                //THIS IS THE LAMBDA EXPRESSION
                MyInterface addAppointment = () -> allAppointments.add(appObject);
                addAppointment.run();
            }
        }catch (SQLException se){

        }
    }
    /** Uses a LAMBDA expression to refresh the database appointments with the UI Observable List. Calling this will
     * keep the List up to date. This is used for keeping table views up to date and accurate. If this is not called,
     * the UI and database will not have matching data.
     */
    public static void populateListFromModify(){
        try {
            allAppointments.clear();

            Contact.populateList();


            ResultSet rs = Query.queryDB("SELECT * FROM appointments");

            while (rs.next()) {

                int contactId = rs.getInt("contact_id");
                ResultSet cs = Query.queryDB("SELECT * FROM contacts WHERE contact_id = " + String.valueOf(contactId));
                String contactNameFound = "empty";
                while (cs.next()){
                    contactNameFound = cs.getString("contact_name");
                }



                Appointment appObject = new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("location"),
                        rs.getString("type"),
                        (rs.getString("start")),
                        (rs.getString("end")),
                        (rs.getString("create_date")),
                        rs.getString("created_by"),
                        (rs.getString("last_update")),
                        rs.getString("last_updated_by"),
                        rs.getInt("customer_id"),
                        rs.getInt("user_id"),
                        contactNameFound);
                //THIS IS THE LAMBDA EXPRESSION
                MyInterface addAppointment = () -> allAppointments.add(appObject);
                addAppointment.run();
            }
        }catch (SQLException se){

        }
    }
    /** Generates a unique Appointment ID.
     * @returns a unique number to be used as an Appointment ID.
     */
    public static int generateAppointmentId(){
        try{
            ResultSet rs = Query.queryDB("SELECT appointment_id FROM appointments");
            int current_max = 0;
            while (rs.next()){
                if (rs.getInt("appointment_id") > current_max){
                    current_max = rs.getInt("appointment_id");
                }
            }
            return current_max + 1;
        }catch (SQLException se){
            return 0;
        }
    }
    /** This method will search through the database and find the user_ID associated with the current user logged into the
     * system and attach their respective ID to the appointment.
     * @returns the current user's ID.
     */
    public static int autoUserIdGenerator(){
        try {
            allAppointments.clear();
            ResultSet rs = Query.queryDB("SELECT * FROM users");
            while (rs.next()) {
                if (rs.getString("user_name").equals(LoginController.username)){
                    return rs.getInt("user_id");
                }
            }
            return 0;
        }catch (SQLException se){
            return 0;
        }
    }
    /** This will create the filtered Observable List with the condition being which month is selected.
     * @param obj This obj represents a string that will determine what month to filter for.
     */
    public static void filterByMonth(String obj){
        allAppointmentsFiltered.clear();
        for (Appointment x : getAllAppointments()){
            //This takes the month of the given date the appointment will take place
            String strMonth = x.getStart().substring(5,7);
            //This compares the month with the selected month the user is filtering for
            if (strMonth.equals(obj.substring(0,2))) {
                allAppointmentsFiltered.add(x);
            }
        }
    }
    /** This will create the filtered Observable List with the condition being which month and week is selected.
     * @param month This month represents a string that will determine what month to filter for.
     * @param week This week will also be used in combination with month to determine which appointments to filter.
     */
    public static void filterByWeek(String month, String week){
        allAppointmentsFiltered.clear();
        for (Appointment x : getAllAppointments()){
            //This takes the month of the given date the appointment will take place
            String strMonth = x.getStart().substring(5,7);
            //This takes an int of the day to be used later to determine the week
            int intDay = Integer.parseInt(x.getStart().substring(8,10));
            //This compares the month with the selected month the user is filtering for
            if (strMonth.equals(month.substring(0,2))) {
                //Using the intDay we can find what # of week it belongs to in the month and use it to return it in a filtered list
                if (week.equals("01")) {
                    if (((intDay) > 0) && (intDay) <= 7) {
                        allAppointmentsFiltered.add(x);
                    }
                }
                if (week.equals("02")) {
                    if (((intDay) > 7) && (intDay) <= 14) {
                        allAppointmentsFiltered.add(x);
                    }
                }
                if (week.equals("03")) {
                    if (((intDay) > 14) && (intDay) <= 21) {
                        allAppointmentsFiltered.add(x);
                    }
                }
                if (week.equals("04")) {
                    if (((intDay) > 21) && (intDay) <= 28) {
                        allAppointmentsFiltered.add(x);
                    }
                }
                if (week.equals("05")) {
                    if (intDay > 28) {
                        allAppointmentsFiltered.add(x);
                    }
                }
            }
        }
    }
    /** This will create the filtered Observable List with the condition being which customer is selected.
     * @param obj This obj represents a string that will determine what customer to filter for.
     */
    public static void filterByCustomer(Customer obj){
        allAppointmentsFiltered.clear();
        for (Appointment x : getAllAppointments()){
            //This takes the month of the given date the appointment will take place
            int customerId = obj.getCustomerId();
            //This compares the month with the selected month the user is filtering for
            if (x.customerId == customerId) {
                allAppointmentsFiltered.add(x);
            }
        }
    }
    /** This will create the filtered Observable List with the condition being which contact is selected.
     * @param obj This obj represents a string that will determine what contact to filter for.
     */
    public static void filterByContact(Contact obj){
        allAppointmentsFiltered.clear();
        for (Appointment x : getAllAppointments()){
            if (x.getContactName().equals(obj.getContactName())) {
                allAppointmentsFiltered.add(x);
            }
        }
    }
    /** This will check to see if any appointment is linked to a selected customer.
     * @param obj This obj represents the customer we are checking to see if they have any appointments linked to them.
     */
    public static int checkIfCustomerIsEmpty(Customer obj){
        int result = 0;
        for (Appointment x : getAllAppointments()){
            if (obj.getCustomerId() == x.getCustomerId()){
                result = result + 1;
            }
        }
        return result;
    }
}

