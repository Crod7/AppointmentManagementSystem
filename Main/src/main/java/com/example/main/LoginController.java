package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
/** This class controls the Login page.
 */
public class LoginController implements Initializable {
    /*Manages the buttons/ labels on screen-------------------------------------------------------------------------------------------*/
    /** This button represents the login button.
     */
    @FXML
    private Button buttonLogin;
    /** This label displays text when an error occurs.
     */
    @FXML
    private Label labelError;
    /** This label displays text next to the password text field.
     */
    @FXML
    private Label labelPassword;
    /** This label displays text of current time zone.
     */
    @FXML
    private Label labelTimeZoneChangeable;
    /** This label displays text next to the time zone.
     */
    @FXML
    private Label labelTimeZoneNonChangeable;
    /** This label displays text.
     */
    @FXML
    private Label labelUsername;

    /** Manages the password entered in its text field and forwards it to another method to be used in authentication.
     */
    @FXML
    private TextField textFieldPassword;
    /** Manages the username entered in its text field and forwards it to another method to be used in authentication.
     */
    @FXML
    private TextField textFieldUsername;
    /** Sets the user for the current session
     */
    public static String username;

    /** This method is activated when pressing the login button on the login screen, it checks for authentication to make sure the USERID mataches its respective PASSWORD.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelUsername.setText(Lang.print("Username"));
        labelPassword.setText(Lang.print("Password"));
        labelTimeZoneChangeable.setText(String.valueOf(ZoneId.systemDefault()));
        labelTimeZoneNonChangeable.setText(Lang.print("Time")+" "+Lang.print("Zone"));
        buttonLogin.setText(Lang.print("Login"));
    }
    /** This method is activated when pressing the login button on the login screen, it checks for authentication to make sure the USERID mataches its respective PASSWORD.
     */
    public void loginButtonClick(ActionEvent e) throws IOException{
        int errorCheck = 0;
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);
        String loginTime = TimeConversion.ConvertToUtcWithSeconds(time.toLocalDate(), time.getHour(), time.getMinute(), time.getSecond());
        System.out.println(loginTime);
        try {
            ResultSet rs = Query.queryDB("SELECT * FROM users");
            while(rs.next()){
                if (textFieldUsername.getText().equals(rs.getString("User_Name"))) {
                    if (textFieldPassword.getText().equals(rs.getString("Password"))){
                        BufferedWriter writer = new BufferedWriter(new FileWriter("login_activity.txt",true));
                        writer.append("\nUser: " +textFieldUsername.getText()+" || Attempted login at " + loginTime +" [UTC] || Login Successful!");
                        writer.close();
                        errorCheck = errorCheck + 1;
                        username = rs.getString("User_Name");
                        JDBC.openConnection();
                        Appointment.populateList();
                        Customer.populateList();
                        Form.changePageTo(e, "mainMenuViewAll.fxml");
                        UpcomingAppointmentAlert.upcomingAppointment();
                    } else{
                        if (errorCheck == 0){
                            BufferedWriter writer = new BufferedWriter(new FileWriter("login_activity.txt",true));
                            writer.append("\nUser: " +textFieldUsername.getText()+" || Attempted login at " + loginTime +" [UTC] || Login Failed!");
                            writer.close();
                            errorCheck = errorCheck + 1;
                        }
                        labelError.setText(Lang.print("ERROR")+" "+Lang.print("Invalid")+" "+Lang.print("Password"));
                    }

                }else{
                    labelError.setText(Lang.print("ERROR")+" "+Lang.print("Invalid")+" "+Lang.print("Login"));
                }
            }
            if (errorCheck == 0){
                BufferedWriter writer = new BufferedWriter(new FileWriter("login_activity.txt",true));
                writer.append("\nUser: " +textFieldUsername.getText()+" || Attempted login at " + loginTime +" [UTC] || Login Failed!");
                writer.close();
                errorCheck = errorCheck + 1;
            }
        }catch(SQLException se){
        }
    }
}