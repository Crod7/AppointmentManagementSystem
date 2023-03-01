package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.main.JDBC.connection;

public class Appointment {
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private String contactName;

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

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    //The code below manages the methods used by this class --------------------------------------------------------
    public static ObservableList<Appointment> getAllAppointments(){
        return allAppointments;
    }
    public static void addAppointment(Appointment app){
        allAppointments.add(app);
    }
    public static boolean deleteAppointment(Appointment selectedAppointment){
        Query.deleteQueryDB("SELECT * FROM appointments WHERE appointment_id =" + selectedAppointment.getAppointmentId());
        allAppointments.remove(selectedAppointment);
        Appointment.populateList();
        return true;
    }
    public static void updateAppointment(Appointment selectedAppointment){
        for (Appointment x : allAppointments){
            if (x.getAppointmentId() == selectedAppointment.getAppointmentId()){

            }
        }
    }
    /*
    public static Appointment lookUpAppointment(Appointment app){
        ObservableList<Appointment> allAppointments = getAllAppointments();
        for (int i = 0; i < allAppointments.size(); i++){
            Appointment x = allAppointments.get(i);
            if (x.getAppointmentId() = app){
                return x;
            }
        }
        allAppointments.add(app);
    }
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
                        TimeConversion.ConvertToLocal(rs.getString("start")),
                        TimeConversion.ConvertToLocal(rs.getString("end")),
                        TimeConversion.ConvertToLocal(rs.getString("create_date")),
                        rs.getString("created_by"),
                        TimeConversion.ConvertToLocal(rs.getString("last_update")),
                        rs.getString("last_updated_by"),
                        rs.getInt("customer_id"),
                        rs.getInt("user_id"),
                        contactNameFound);
                addAppointment(appObject);
            }
        }catch (SQLException se){

        }
    }

    public static int generateAppointmentId(){
        try{
            ResultSet rs = Query.queryDB("SELECT appointment_id FROM appointments");
            int current_max = 0;
            while (rs.next()){
                if (rs.getInt("appointment_id") > current_max){
                    current_max = rs.getInt("appointment_id");
                }
            }
            //String resultString = String.valueOf(current_max);
            return current_max + 1;
        }catch (SQLException se){
            return 0;
        }
    }
    /** This method will search through the database and find the user_ID associated with the current user logged into the system and attach their respective ID to the appointment.
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
}

