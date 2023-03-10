package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MonthAndType {
    private String month;
    private String type;
    private int quantity;
    private static ObservableList<MonthAndType> allAppointments = FXCollections.observableArrayList();

    //public static String[] typeOptions = {};
    public static ObservableList<String> typeOptions = FXCollections.observableArrayList();
    private static ObservableList<MonthAndType> allAppointmentsFiltered = FXCollections.observableArrayList();

    public MonthAndType(String month, String type, int quantity) {
        this.month = month;
        this.type = type;
        this.quantity = quantity;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public static ObservableList<MonthAndType> getAllAppointments(){
        return allAppointments;
    }
    public static ObservableList<String> getAllTypeOptions(){
        return typeOptions;
    }
    public static ObservableList<MonthAndType> getAllAppointmentsFiltered(){
        return allAppointmentsFiltered;
    }


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
    public static void addAppointment(MonthAndType app){
        allAppointments.add(app);
    }
    public static void populateTypeComboBox(){
        for (Appointment x : Appointment.getAllAppointments()){
            String option = x.getType();
            if (!typeOptions.contains(option)){
                typeOptions.add(option);
            }
        }
    }
}
