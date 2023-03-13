package com.example.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class OverlappingAppointments {
    public static boolean checkIfAvailableAddAppointment(String startTime, String endTime) throws SQLException {

        int year = Integer.parseInt(startTime.substring(0,4));
        int month = Integer.parseInt(startTime.substring(5,7));
        int day = Integer.parseInt(startTime.substring(8,10));
        int hour = Integer.parseInt(startTime.substring(11,13));
        int minute = Integer.parseInt(startTime.substring(14,16));

        int year2 = Integer.parseInt(endTime.substring(0,4));
        int month2 = Integer.parseInt(endTime.substring(5,7));
        int day2 = Integer.parseInt(endTime.substring(8,10));
        int hour2 = Integer.parseInt(endTime.substring(11,13));
        int minute2 = Integer.parseInt(endTime.substring(14,16));

        LocalDateTime addAppStart = LocalDateTime.of(year, month, day, hour, minute, 0);
        LocalDateTime addAppEnd = LocalDateTime.of(year2, month2, day2, hour2, minute2, 0);

        ResultSet rs = Query.queryDB("SELECT * FROM appointments");
        while (rs.next()) {
            String storedStartingTime = rs.getString("start");
            String storedEndingTime = rs.getString("end");
            int year3 = Integer.parseInt(storedStartingTime.substring(0,4));
            int month3 = Integer.parseInt(storedStartingTime.substring(5,7));
            int day3 = Integer.parseInt(storedStartingTime.substring(8,10));
            int hour3 = Integer.parseInt(storedStartingTime.substring(11,13));
            int minute3 = Integer.parseInt(storedStartingTime.substring(14,16));
            LocalDateTime storedAppStart = LocalDateTime.of(year3, month3, day3, hour3, minute3, 0);

            int year4 = Integer.parseInt(storedEndingTime.substring(0,4));
            int month4 = Integer.parseInt(storedEndingTime.substring(5,7));
            int day4 = Integer.parseInt(storedEndingTime.substring(8,10));
            int hour4 = Integer.parseInt(storedEndingTime.substring(11,13));
            int minute4 = Integer.parseInt(storedEndingTime.substring(14,16));
            LocalDateTime storedAppEnd = LocalDateTime.of(year4, month4, day4, hour4, minute4, 0);

            if ((addAppStart.isAfter(storedAppStart) || addAppStart.isEqual(storedAppStart)) && addAppStart.isBefore(storedAppEnd)){
                return true;
            }
            if ((addAppEnd.isAfter(storedAppStart)) && (addAppEnd.isBefore(storedAppEnd) || addAppEnd.isEqual(storedAppEnd))){
                return true;
            }
        }
        //Appointment can be added as no interferences were found
        return false;
    }

    public static boolean checkIfAvailableModifyAppointment(String startTime, String endTime, int id) throws SQLException {

        int year = Integer.parseInt(startTime.substring(0,4));
        int month = Integer.parseInt(startTime.substring(5,7));
        int day = Integer.parseInt(startTime.substring(8,10));
        int hour = Integer.parseInt(startTime.substring(11,13));
        int minute = Integer.parseInt(startTime.substring(14,16));

        int year2 = Integer.parseInt(endTime.substring(0,4));
        int month2 = Integer.parseInt(endTime.substring(5,7));
        int day2 = Integer.parseInt(endTime.substring(8,10));
        int hour2 = Integer.parseInt(endTime.substring(11,13));
        int minute2 = Integer.parseInt(endTime.substring(14,16));

        LocalDateTime addAppStart = LocalDateTime.of(year, month, day, hour, minute, 0);
        LocalDateTime addAppEnd = LocalDateTime.of(year2, month2, day2, hour2, minute2, 0);

        //====================================================================================================================================


        ResultSet rs = Query.queryDB("SELECT * FROM appointments");
        while (rs.next()) {

            String storedStartingTime = rs.getString("start");
            String storedEndingTime = rs.getString("end");
            int storedId = rs.getInt("appointment_id");

            //Only for the modify page, this code makes it so when modifying an appointment, the appointment won't interfere with itself
            if (id == storedId){
                continue;
            }
            //====================================================================================================================================


            int year3 = Integer.parseInt(storedStartingTime.substring(0,4));
            int month3 = Integer.parseInt(storedStartingTime.substring(5,7));
            int day3 = Integer.parseInt(storedStartingTime.substring(8,10));
            int hour3 = Integer.parseInt(storedStartingTime.substring(11,13));
            int minute3 = Integer.parseInt(storedStartingTime.substring(14,16));
            LocalDateTime storedAppStart = LocalDateTime.of(year3, month3, day3, hour3, minute3, 0);

            int year4 = Integer.parseInt(storedEndingTime.substring(0,4));
            int month4 = Integer.parseInt(storedEndingTime.substring(5,7));
            int day4 = Integer.parseInt(storedEndingTime.substring(8,10));
            int hour4 = Integer.parseInt(storedEndingTime.substring(11,13));
            int minute4 = Integer.parseInt(storedEndingTime.substring(14,16));
            LocalDateTime storedAppEnd = LocalDateTime.of(year4, month4, day4, hour4, minute4, 0);



            if ((addAppStart.isAfter(storedAppStart) || addAppStart.isEqual(storedAppStart)) && addAppStart.isBefore(storedAppEnd)){
                return true;
            }
            if ((addAppEnd.isAfter(storedAppStart)) && (addAppEnd.isBefore(storedAppEnd) || addAppEnd.isEqual(storedAppEnd))){
                return true;
            }
        }
        //Appointment can be added as no interferences were found
        return false;
    }
}
