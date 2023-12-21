package com.example.main;

import javafx.fxml.FXMLLoader;

import java.sql.Connection;
import java.sql.DriverManager;
/** This class controls the connection between the client to the database.
 */
public class JDBC {
    /** This variable holds the protocol jdbc.
     */
    private static final String protocol = "jdbc";
    /** This variable holds the protocol jdbc.
     */
    private static final String vendor = ":mysql:";
    /** This variable holds the location of the connection, which is the local host.
     */
    private static final String location = "//localhost:3306/";
    /** This variable holds the name of the database, client_schedule.
     */
    private static final String databaseName = "client_schedule";
    /** This variable holds the string of the jdbc url.
     */
    public static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    /** This variable holds the string of the location of the connection driver.
     */
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    /** This variable holds the string of the username that will log in to the database.
     */
    public static final String userName = "root"; // Username
    /** This variable holds the string password for the connection.
     */
    public static String password = "Passw0rd!"; // Password
    /** This variable holds connection interface.
     */
    public static Connection connection;  // Connection Interface

    /** This method opens a connection with the database.
     */
    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
    /** This method closes a connection with the database.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
