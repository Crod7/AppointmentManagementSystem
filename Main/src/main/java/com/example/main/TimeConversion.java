package com.example.main;

import java.time.*;
import java.time.format.DateTimeFormatter;
/** This class controls the time conversions in the program.
 */
public class TimeConversion {
    /** This method converts a string to the user's local time zone.
     * @param date this is the date string that will be converted.
     * @return the new date as a string.
     */
    public static String ConvertToLocal(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));
        int hour = Integer.parseInt(date.substring(11,13));
        int minute = Integer.parseInt(date.substring(14,16));
        LocalDateTime myLDT = LocalDateTime.of(year,month,day,hour,minute,0);
        ZoneId myZoneId = ZoneId.of("UTC");
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        ZoneId utcZoneId = ZoneId.systemDefault();
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);
        String result = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(utcZDT);
        return  result;
    }
    /** This method converts a date string to  the eastern time zone.
     * @param date this is the date string that will be converted.
     * @return the new date as a string.
     */
    public static String ConvertToESTFromLocal(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));
        int hour = Integer.parseInt(date.substring(11,13));
        int minute = Integer.parseInt(date.substring(14,16));
        LocalDateTime myLDT = LocalDateTime.of(year,month,day,hour,minute,0);
        ZoneId myZoneId = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        ZoneId utcZoneId = ZoneId.of("America/New_York");
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);
        String result = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(utcZDT);
        return  result;
    }
    /** This method converts LocalDate and time to UTC timezone.
     * @param date this is the LocalDate to be converted.
     * @param hour this integer will be the hour in the new time.
     * @param minute this integer will be the minute in the new time.
     * @return the new date as a string.
     */
    public static String ConvertToUtc(LocalDate date, int hour, int minute) {
        LocalDateTime myLDT = LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(),hour,minute,0);
        ZoneId myZoneId = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);
        String result = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(utcZDT);
        return  result;

    }
    /** This method converts LocalDate and time to UTC timezone.
     * @param date this is the LocalDate to be converted.
     * @param hour this integer will be the hour in the new time.
     * @param minute this integer will be the minute in the new time.
     * @param seconds this integer will be the seconds in the new time.
     * @return the new date as a string.
     */
    public static String ConvertToUtcWithSeconds(LocalDate date, int hour, int minute, int seconds) {
        LocalDateTime myLDT = LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(),hour,minute,seconds);
        ZoneId myZoneId = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);
        String result = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(utcZDT);
        return  result;

    }
    /** This method converts a date in string format to a LocalDateTime object.
     * @param date this is the string to be converted
     * @return the new LocalDateTime.
     */
    public static LocalDateTime ConvertToTimeObj(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));
        int hour = Integer.parseInt(date.substring(11,13));
        int minute = Integer.parseInt(date.substring(14,16));
        LocalDateTime myLDT = LocalDateTime.of(year,month,day,hour,minute,0);
        return  myLDT;
    }

}
