package com.example.main;

import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UpcomingAppointmentAlert {
    public static boolean upcomingAppointment() throws SQLException {
        LocalDateTime currentLocalTime = LocalDateTime.now();
        String currentTimeUtc = TimeConversion.ConvertToUtc(currentLocalTime.toLocalDate(), currentLocalTime.getHour(), currentLocalTime.getMinute());
        String localCurrentTime = TimeConversion.ConvertToLocal(currentTimeUtc);
        int year = Integer.parseInt(localCurrentTime.substring(0,4));
        int month = Integer.parseInt(localCurrentTime.substring(5,7));
        int day = Integer.parseInt(localCurrentTime.substring(8,10));
        int hour = Integer.parseInt(localCurrentTime.substring(11,13));
        int minute = Integer.parseInt(localCurrentTime.substring(14,16));
        LocalDateTime time = LocalDateTime.of(year, month, day, hour, minute);




        ResultSet rs = Query.queryDB("SELECT * FROM appointments");
        while (rs.next()){
            int id = rs.getInt("appointment_id");
            String date = rs.getString("start");
            String localCheckTime = TimeConversion.ConvertToLocal(date);
            int yearDb = Integer.parseInt(localCheckTime.substring(0,4));
            int monthDb = Integer.parseInt(localCheckTime.substring(5,7));
            int dayDb = Integer.parseInt(localCheckTime.substring(8,10));
            int hourDb = Integer.parseInt(localCheckTime.substring(11,13));
            int minuteDb = Integer.parseInt(localCheckTime.substring(14,16));
            LocalDateTime timeDb= LocalDateTime.of(yearDb, monthDb, dayDb, hourDb, minuteDb);


            if (time.isAfter(timeDb.minusMinutes(15)) && time.isBefore(timeDb)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Upcoming Appointment");
                alert.setHeaderText("Appointment ID: "+id+"   Date/Time: "+timeDb);
                alert.setContentText("This appointment will begin soon!");
                alert.showAndWait();
                return true;
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Upcoming Appointment");
        alert.setHeaderText("No Appointments Soon");
        alert.setContentText("There are no appointments upcoming within the next 15 minutes");
        alert.showAndWait();
        return true;
    }
}
