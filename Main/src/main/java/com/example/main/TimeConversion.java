package com.example.main;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeConversion {
    public static String ConvertToLocal(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));
        int hour = Integer.parseInt(date.substring(11,13));
        int minute = Integer.parseInt(date.substring(14,16));
        LocalDateTime myLDT = LocalDateTime.of(year,month,day,hour,minute,0);
        ZoneId myZoneId = ZoneId.of("UTC");
        System.out.println(myZoneId);
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        ZoneId utcZoneId = ZoneId.systemDefault();
        System.out.println(utcZoneId);
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);
        String result = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(utcZDT);
        return  result;
    }
    public static String ConvertToESTFromLocal(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));
        int hour = Integer.parseInt(date.substring(11,13));
        int minute = Integer.parseInt(date.substring(14,16));
        LocalDateTime myLDT = LocalDateTime.of(year,month,day,hour,minute,0);
        ZoneId myZoneId = ZoneId.systemDefault();
        System.out.println(myZoneId);
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        ZoneId utcZoneId = ZoneId.of("America/New_York");
        System.out.println(utcZoneId);
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);
        String result = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(utcZDT);
        return  result;
    }
    public static String ConvertToUtc(LocalDate date, int hour, int minute) {
        LocalDateTime myLDT = LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(),hour,minute,0);
        ZoneId myZoneId = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);
        String result = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(utcZDT);
        return  result;

    }
    public static String ConvertToUtcWithSeconds(LocalDate date, int hour, int minute, int seconds) {
        LocalDateTime myLDT = LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(),hour,minute,seconds);
        ZoneId myZoneId = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);
        String result = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(utcZDT);
        return  result;

    }
    public static LocalDateTime ConvertToTimeObj(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));
        int hour = Integer.parseInt(date.substring(11,13));
        int minute = Integer.parseInt(date.substring(14,16));
        LocalDateTime myLDT = LocalDateTime.of(year,month,day,hour,minute,0);
        return  myLDT;
    }
    public static boolean checkIfOpen(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));
        int hour = Integer.parseInt(date.substring(11,13));
        int minute = Integer.parseInt(date.substring(14,16));



        LocalDate myLD = LocalDate.of(year , month, day);
        LocalTime myLT = LocalTime.of(hour, minute, 0);
        LocalDateTime myLDT = LocalDateTime.of(myLD, myLT);
        ZoneId myZoneId = ZoneId.of("America/New_York");
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        ZoneId estZoneId = ZoneId.systemDefault();
        ZonedDateTime estZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), estZoneId);
        String result = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(estZDT);

        int checkTime = (Integer.parseInt(result.substring(11,13)));
        int checkTimeMin = Integer.parseInt(result.substring(14,16));

        //System.out.println(checkTime);
        if ((checkTime >= 9 || checkTime == 23) && ((checkTime == 23 && checkTimeMin == 0) || (checkTime < 23 && checkTime >= 9))){
            //Appointment canceled because location is not open
            return false;
        } else{
            //Appointment accepted because location is open
            return true;
        }
    }

}
