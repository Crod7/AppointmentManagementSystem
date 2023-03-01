package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {

    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int divisionId;

    public Customer(int customerId, String customerName, String address, String postalCode, String phone, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    //The code below manages the methods used by this class --------------------------------------------------------
    public static ObservableList<Customer> getAllCustomers(){
        return allCustomers;
    }
    public static void addCustomer(Customer app){
        allCustomers.add(app);
    }
    public static boolean deleteCustomer(Customer selectedCustomer){
        Query.deleteQueryDB("SELECT * FROM customers WHERE customer_id =" + selectedCustomer.getCustomerId());
        allCustomers.remove(selectedCustomer);
        Appointment.populateList();
        return true;
    }
    public static void updateAppointment(Appointment selectedAppointment){
        for (Customer x : allCustomers){
            if (x.getCustomerId() == selectedAppointment.getAppointmentId()){

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
            allCustomers.clear();



            ResultSet rs = Query.queryDB("SELECT * FROM customers");

            while (rs.next()) {
                Customer appObject = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("address"),
                        rs.getString("postal_code"),
                        rs.getString("phone"),
                        TimeConversion.ConvertToLocal(rs.getString("create_date")),
                        rs.getString("created_by"),
                        TimeConversion.ConvertToLocal(rs.getString("last_update")),
                        rs.getString("last_updated_by"),
                        rs.getInt("division_id"));
                addCustomer(appObject);
            }
        }catch (SQLException se){

        }
    }

    public static int generateCustomerId(){
        try{
            ResultSet rs = Query.queryDB("SELECT customer_id FROM customers");
            int current_max = 0;
            while (rs.next()){
                if (rs.getInt("customer_id") > current_max){
                    current_max = rs.getInt("customer_id");
                }
            }
            return current_max + 1;
        }catch (SQLException se){
            return 0;
        }
    }
    /** This method will search through the database and find the user_ID associated with the current user logged into the system and attach their respective ID to the appointment.
     */
    public static int autoUserIdGenerator(){
        try {
            allCustomers.clear();
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
