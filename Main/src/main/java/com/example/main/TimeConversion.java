package com.example.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class TimeConversion {
    public static String ConvertToLocal(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));
        int hour = Integer.parseInt(date.substring(11,13));
        int minute = Integer.parseInt(date.substring(14,16));

        LocalDate myLD = LocalDate.of(year , month, day);
        LocalTime myLT = LocalTime.of(hour, minute, 0);
        LocalDateTime myLDT = LocalDateTime.of(myLD, myLT);
        ZoneId myZoneId = ZoneId.of("UTC");
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        ZoneId utcZoneId = ZoneId.systemDefault();
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);
        String result = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(utcZDT);
        return  result;
    }
    public static String ConvertToUtc(LocalDate date, int hour, int minute) {
        LocalDate myLD = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
        LocalTime myLT = LocalTime.of(hour, minute, 0);
        LocalDateTime myLDT = LocalDateTime.of(myLD, myLT);
        ZoneId myZoneId = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);
        String result = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(utcZDT);
        return  result;

    }
}
