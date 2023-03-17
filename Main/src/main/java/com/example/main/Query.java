package com.example.main;


import java.sql.*;
import java.time.LocalDateTime;
/** This class controls the queries made to the database.
 */
public class Query {
    /** This method takes a query in the form of a string and uses it to query the database.
     * @param queryCode This is the string that will be used to query the database.
     */
    public static ResultSet queryDB(String queryCode){
        try {
            JDBC.connection = DriverManager.getConnection(JDBC.jdbcUrl, JDBC.userName, JDBC.password);
            Statement stmt = JDBC.connection.createStatement();
            String query = queryCode;
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException se){
            ResultSet rs = null;
            return rs;
        }
    }
    /** This method takes arguments and uses them to add a new appointment row in the database. This method is also
     * used when modifying appointments.
     * @param id the appointment_id to be added.
     * @param title the title of the appointment.
     * @param desc the description of the appointment.
     * @param locate the location of the appointment.
     * @param type the type of appointment being added.
     * @param startDate the date and time the appointment will begin.
     * @param endDate the date and time the appointment will end.
     * @param create_date the date and time the appointment was created.
     * @param created_by the user that created the appointment initially.
     * @param last_update the last time this appointment has been updated.
     * @param last_updated_by the last user to have updated this appointment.
     * @param customer_id the customer ID linked to this appointment.
     * @param contact_id the contact that can be called for this appointment.
     * @param user_id the user ID that created this appointment.
     */
    public static ResultSet addToQueryDB(int id, String title, String desc, String locate, String type, String startDate, String endDate, String create_date, String created_by, String last_update, String last_updated_by, int customer_id, int user_id, int contact_id){
        try {
            JDBC.connection = DriverManager.getConnection(JDBC.jdbcUrl, JDBC.userName, JDBC.password);
            Statement stmt = JDBC.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM appointments";
            ResultSet rs = stmt.executeQuery(query);
            rs.last();
            //int id = Appointment.generateAppointmentId();
            rs.moveToInsertRow();
            rs.updateInt("Appointment_ID", id);
            rs.updateString("Title", title);
            rs.updateString("Description", desc);
            rs.updateString("Location", locate);
            rs.updateString("Type", type);
            rs.updateTimestamp("Start", Timestamp.valueOf(startDate));
            rs.updateTimestamp("End", Timestamp.valueOf(endDate));
            rs.updateTimestamp("Create_Date", Timestamp.valueOf(create_date));
            rs.updateTimestamp("Last_Update", Timestamp.valueOf(last_update));
            rs.updateString("Created_By", created_by);
            rs.updateString("Last_Updated_By", last_updated_by);
            rs.updateInt("Customer_ID", customer_id);
            rs.updateInt("User_ID", user_id);
            rs.updateInt("Contact_ID", contact_id);
            rs.insertRow();
            return rs;
        } catch (SQLException se){
            ResultSet rs = null;
            return rs;
        }
    }
    /** This method takes arguments and uses them to add a new customer row in the database.
     * @param id the customer_id to be added.
     * @param name the customer name to be added.
     * @param phone the customer phone number.
     * @param address the customer address to be added.
     * @param division_id the division_id linked to the customer.
     * @param postalCode the postal code linked to the customer.
     * @param create_date the date and time the customer was created.
     * @param created_by the user that created the customer initially.
     * @param last_update the last time this customer has been updated.
     * @param last_updated_by the last user to have updated this customer.
     */
    public static ResultSet addCustomerToQueryDB(int id, String name, String address, String postalCode, String phone, String create_date, String created_by, String last_update, String last_updated_by, int division_id){
        try {
            JDBC.connection = DriverManager.getConnection(JDBC.jdbcUrl, JDBC.userName, JDBC.password);
            Statement stmt = JDBC.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM customers";
            ResultSet rs = stmt.executeQuery(query);
            rs.last();
            //int id = Appointment.generateAppointmentId();
            rs.moveToInsertRow();
            rs.updateInt("Customer_ID", id);
            rs.updateString("Customer_Name", name);
            rs.updateString("Address", address);
            rs.updateString("Postal_Code", postalCode);
            rs.updateString("Phone", phone);
            rs.updateTimestamp("Create_Date", Timestamp.valueOf(create_date));
            rs.updateString("Created_By", created_by);
            rs.updateTimestamp("Last_Update", Timestamp.valueOf(last_update));
            rs.updateString("Last_Updated_By", last_updated_by);
            rs.updateInt("Division_ID", division_id);
            rs.insertRow();
            return rs;
        } catch (SQLException se){
            ResultSet rs = null;
            return rs;
        }
    }
    /** This method takes arguments and uses them to modify the matching customer row in the database.
     * @param id the customer_id to be added.
     * @param name the customer name to be added.
     * @param phone the customer phone number.
     * @param address the customer address to be added.
     * @param divisionId the division_id linked to the customer.
     * @param postal the postal code linked to the customer.
     * @param createDate the date and time the customer was created.
     * @param createdBy the user that created the customer initially.
     * @param update the last time this customer has been updated.
     * @param updatedBy the last user to have updated this customer.
     */
    public static void modifyCustomerToQueryDB(int id, String name, String address, String postal, String phone, String createDate, String createdBy, String update, String updatedBy, int divisionId){
        try {
            JDBC.connection = DriverManager.getConnection(JDBC.jdbcUrl, JDBC.userName, JDBC.password);
            Statement stmt = JDBC.connection.createStatement();
            String sql ="UPDATE customers SET customer_name = '" + name + "' WHERE customer_id = " + (id);
            int rowsAffected = stmt.executeUpdate(sql);
            sql ="UPDATE customers SET address = '" + address + "' WHERE customer_id = " + (id);
            rowsAffected = stmt.executeUpdate(sql);
            sql ="UPDATE customers SET postal_code = '" + postal + "' WHERE customer_id = " + (id);
            rowsAffected = stmt.executeUpdate(sql);
            sql ="UPDATE customers SET phone = '" + phone + "' WHERE customer_id = " + (id);
            rowsAffected = stmt.executeUpdate(sql);
            sql ="UPDATE customers SET create_date = '" + createDate + "' WHERE customer_id = " + (id);
            rowsAffected = stmt.executeUpdate(sql);
            sql ="UPDATE customers SET created_by = '" + createdBy + "' WHERE customer_id = " + (id);
            rowsAffected = stmt.executeUpdate(sql);
            sql ="UPDATE customers SET last_update = '" + update + "' WHERE customer_id = " + (id);
            rowsAffected = stmt.executeUpdate(sql);
            sql ="UPDATE customers SET last_updated_by = '" + updatedBy + "' WHERE customer_id = " + (id);
            rowsAffected = stmt.executeUpdate(sql);
            sql ="UPDATE customers SET division_id = '" + divisionId + "' WHERE customer_id = " + (id);
            rowsAffected = stmt.executeUpdate(sql);


        } catch (SQLException se){
        }
    }
    /** This method is used when the user needs to delete a row from the database.
     * @param queryCode This is the string that will be used to query the database.
     */
    public static ResultSet deleteQueryDB(String queryCode){
        try {
            JDBC.connection = DriverManager.getConnection(JDBC.jdbcUrl, JDBC.userName, JDBC.password);
            Statement stmt = JDBC.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(queryCode);
            rs.absolute(1);
            rs.deleteRow();
            return rs;
        } catch (SQLException se){
            ResultSet rs = null;
            return rs;
        }
    }
}
