package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
/** This class controls the MonthAndType object. It is used in the reports page.
 */
public class MonthAndType {
    /** This is the variable month and is a string.
     */
    private String month;
    /** This is the variable type and is a string.
     */
    private String type;
    /** This is the variable quantity and is a integer.
     */
    private int quantity;
    /** Observable List containing all MonthAndType objects.
     */
    private static ObservableList<MonthAndType> allAppointments = FXCollections.observableArrayList();
    /** This is an Observable List used for listing all different types.
     */
    public static ObservableList<String> typeOptions = FXCollections.observableArrayList();
    /** This is the constructor of the MonthAndType object.
     * @param month This is the month of the object.
     * @param type This is the type of appointment.
     * @param quantity The number of appointments with a certain type and month.
     */
    public MonthAndType(String month, String type, int quantity) {
        this.month = month;
        this.type = type;
        this.quantity = quantity;
    }
    /** This gets the Month.
     * @return the month.
     */
    public String getMonth() {
        return month;
    }
    /** This sets the month.
     * @param month The month to be set.
     */
    public void setMonth(String month) {
        this.month = month;
    }
    /** This gets the Type.
     * @return the type.
     */
    public String getType() {
        return type;
    }
    /** This sets the type.
     * @param type The type to be set.
     */
    public void setType(String type) {
        this.type = type;
    }
    /** This gets the Observable List allAppointments.
     * @return the observable list allAppointments.
     */
    public static ObservableList<MonthAndType> getAllAppointments(){
        return allAppointments;
    }
    /** This is called to refresh the database reports with the UI Observable List. Calling this will
     * keep the List up to date. This is used for keeping table views up to date and accurate. If this is not called,
     * the UI and database will not have matching data.
     */
    public static void populateList(String month, String type) {
            int count = 0;
            allAppointments.clear();
            Appointment.populateList();

            for (Appointment x : Appointment.getAllAppointments()) {
                //This takes the month of the given date the appointment will take place
                String appDate = x.getStart().substring(5, 7);
                String appType = x.getType();
                //This compares the month with the selected month the user is filtering for
                if (month.substring(0,2).equals(appDate)) {
                    if (type.equals(appType)) {
                        count = count + 1;
                    }
                }
            }
            MonthAndType appObject = new MonthAndType(month, type, count);
            addAppointment(appObject);
    }
    /** This adds MonthAndType object to allAppointments list.
     * @param app The monthAndType being added.
     */
    public static void addAppointment(MonthAndType app){
        allAppointments.add(app);
    }
    /** This will populate the Combo Box on the reports page when searching for
     * number of month and type appointments.
     */
    public static void populateTypeComboBox(){
        for (Appointment x : Appointment.getAllAppointments()){
            String option = x.getType();
            if (!typeOptions.contains(option)){
                typeOptions.add(option);
            }
        }
    }
}
