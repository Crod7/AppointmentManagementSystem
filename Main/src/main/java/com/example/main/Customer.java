package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
/** This class manages the Customer Object.
 */
public class Customer {
    /** This is an Observable List for allCustomers.
     */
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    /** This is an Observable List for allCustomersFiltered.
     */
    private static ObservableList<Customer> allCustomersFiltered = FXCollections.observableArrayList();
    /** This is the Customer ID.
     */
    private int customerId;
    /** This is the Customer name.
     */
    private String customerName;
    /** This is the Address.
     */
    private String address;
    /** This is the Postal Code.
     */
    private String postalCode;
    /** This is the Phone Number.
     */
    private String phone;
    /** This is the Create Date.
     */
    private String createDate;
    /** This is the Created By string.
     */
    private String createdBy;
    /** This is the Last Update string.
     */
    private String lastUpdate;
    /** This is the Last Updated by string.
     */
    private String lastUpdatedBy;
    /** This is the Division ID.
     */
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
    /** This gets the Customer ID.
     * @return the customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }
    /** This gets the Customer Name.
     * @return the customer Name.
     */
    public String getCustomerName() {
        return customerName;
    }
    /** This gets the Address.
     * @return the address.
     */
    public String getAddress() {
        return address;
    }
    /** This sets the address.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /** This gets the Postal Code.
     * @return the postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }
    /** This gets the Phone Number.
     * @return the phone number.
     */
    public String getPhone() {
        return phone;
    }
    /** This gets the Create Date.
     * @return the create date.
     */
    public String getCreateDate() {
        return createDate;
    }
    /** This gets the Created By string.
     * @return the created by string.
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /** This gets the Division ID.
     * @return the division id.
     */
    public int getDivisionId() {
        return divisionId;
    }


    //The code below manages the methods used by this class --------------------------------------------------------
    /** This gets the Observable List of allCustomers.
     * @return the Observable List.
     */
    public static ObservableList<Customer> getAllCustomers(){
        return allCustomers;
    }
    /** This adds a Customer to the allCustomers list.
     * @param app This is the customer to be added.
     */
    public static void addCustomer(Customer app){
        allCustomers.add(app);
    }
    /** This deletes a customer from the allCustomers list.
     * @param selectedCustomer This is the customer to be removed.
     */
    public static boolean deleteCustomer(Customer selectedCustomer){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Lang.print("Deleted"));
        alert.setHeaderText(Lang.print("Customer")+" "+Lang.print("ID")+": "+selectedCustomer.getCustomerId()+"   "+Lang.print("Name")+": "+selectedCustomer.getCustomerName());
        alert.setContentText(Lang.print("This")+" "+Lang.print("Customer")+" "+Lang.print("was")+" "+Lang.print("Deleted")+".");
        alert.showAndWait();

        Query.deleteQueryDB("SELECT * FROM customers WHERE customer_id =" + selectedCustomer.getCustomerId());
        allCustomers.remove(selectedCustomer);
        Appointment.populateList();
        return true;
    }
    /** This keeps the database and allcustomers Observable list up to date and in sync with their data.
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
    /** This generates a Customer ID unique to the new customer that was created.
     * @return A unique customer ID.
     */
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
    /** This method will search through the database and find the Customer ID associated with the current
     * Customer and attach their respective ID to the table View.
     */
    public static int getCustomerId(int id){
        try{
            ResultSet rs = Query.queryDB("SELECT * FROM customers");
            while (rs.next()){
                if (rs.getInt("customer_id") == id){
                    return rs.getInt("customer_id") - 1;
                }
            }
            return 0;
        }catch (SQLException se){

        }
        return 0;
    }
}
