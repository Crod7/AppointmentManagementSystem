package com.example.main;


import java.sql.*;
import java.time.LocalDateTime;

public class Query {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

    public static ResultSet queryDB(String queryCode){
        try {
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            Statement stmt = connection.createStatement();
            String query = queryCode;
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException se){
            ResultSet rs = null;
            return rs;
        }
    }
    public static ResultSet addToQueryDB(int id, String title, String desc, String locate, String type, String startDate, String endDate, String create_date, String created_by, String last_update, String last_updated_by, int customer_id, int user_id, int contact_id){
        try {
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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


    public static ResultSet deleteQueryDB(String queryCode){
        try {
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
