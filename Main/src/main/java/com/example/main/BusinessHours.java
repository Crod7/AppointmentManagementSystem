package com.example.main;

import java.time.*;

public class BusinessHours {
    public static boolean checkIfOpen(String startApp2, String endApp2){
        String startApp3 = TimeConversion.ConvertToLocal(startApp2);
        String startApp = TimeConversion.ConvertToESTFromLocal(startApp3);
        String endApp3 = TimeConversion.ConvertToLocal(endApp2);
        String endApp = TimeConversion.ConvertToESTFromLocal(endApp3);



        int year = Integer.parseInt(startApp.substring(0,4));
        int month = Integer.parseInt(startApp.substring(5,7));
        int day = Integer.parseInt(startApp.substring(8,10));
        int hour = Integer.parseInt(startApp.substring(11,13));
        int minute = Integer.parseInt(startApp.substring(14,16));
        LocalDateTime timeStart2 = LocalDateTime.of(year, month, day, hour, minute);
        //---
        ZoneId x = ZoneId.of("America/New_York");
        ZonedDateTime m = ZonedDateTime.of(timeStart2, x);
        LocalDateTime timeStart = LocalDateTime.of(m.getYear(),m.getMonth(),m.getDayOfMonth(),m.getHour(),m.getMinute(),0);

        int year2 = Integer.parseInt(endApp.substring(0,4));
        int month2 = Integer.parseInt(endApp.substring(5,7));
        int day2 = Integer.parseInt(endApp.substring(8,10));
        int hour2 = Integer.parseInt(endApp.substring(11,13));
        int minute2 = Integer.parseInt(endApp.substring(14,16));
        LocalDateTime timeEnd2 = LocalDateTime.of(year2, month2, day2, hour2, minute2);
        //---
        ZoneId y = ZoneId.of("America/New_York");
        ZonedDateTime n = ZonedDateTime.of(timeEnd2, y);
        LocalDateTime timeEnd = LocalDateTime.of(n.getYear(),n.getMonth(),n.getDayOfMonth(),n.getHour(),n.getMinute(),0);

        /*
        LocalDateTime myLDT = LocalDateTime.of(year,month,day, 12, 0);
        ZoneId myZoneId = ZoneId.of("UTC");
        ZonedDateTime myZDTStart = ZonedDateTime.of(myLDT, myZoneId);
        LocalDateTime newStart = LocalDateTime.of(myZDTStart.getYear(),myZDTStart.getMonth(),myZDTStart.getDayOfMonth(),myZDTStart.getHour(),myZDTStart.getMinute(),0);

        LocalDateTime myLDT2 = LocalDateTime.of(year,month,day+1, 2, 0);
        ZoneId myZoneId2 = ZoneId.of("UTC");
        ZonedDateTime myZDTEnd = ZonedDateTime.of(myLDT2, myZoneId2);
        LocalDateTime newEnd = LocalDateTime.of(myZDTEnd.getYear(),myZDTEnd.getMonth(),myZDTEnd.getDayOfMonth(),myZDTEnd.getHour(),myZDTEnd.getMinute(),0);

        System.out.println("New Start: "+ timeStart + " New End: "+ timeEnd);
        System.out.println("Cutoff Start: " + newStart + " Cutoff End: "+newEnd);
        */
        LocalDateTime myLDT = LocalDateTime.of(year,month,day, 8, 0);
        ZoneId myZoneId = ZoneId.of("America/New_York");
        ZonedDateTime myZDTStart = ZonedDateTime.of(myLDT, myZoneId);
        LocalDateTime newStart = LocalDateTime.of(myZDTStart.getYear(),myZDTStart.getMonth(),myZDTStart.getDayOfMonth(),myZDTStart.getHour(),myZDTStart.getMinute(),0);

        LocalDateTime myLDT2 = LocalDateTime.of(year,month,day, 22, 0);
        ZoneId myZoneId2 = ZoneId.of("America/New_York");
        ZonedDateTime myZDTEnd = ZonedDateTime.of(myLDT2, myZoneId2);
        LocalDateTime newEnd = LocalDateTime.of(myZDTEnd.getYear(),myZDTEnd.getMonth(),myZDTEnd.getDayOfMonth(),myZDTEnd.getHour(),myZDTEnd.getMinute(),0);

        System.out.println("New Start: "+ timeStart + " New End: "+ timeEnd);
        System.out.println("Cutoff Start: " + newStart + " Cutoff End: "+newEnd);



        if ((timeStart.isAfter(newStart) || timeStart.isEqual(newStart)) //time starts at or after 8am EST
            && (timeStart.isBefore(newEnd))                              //but starts before 10pm EST
            && ((timeEnd.isAfter(newStart)))                            //time ends after 8am EST
            && (((timeEnd.isBefore(newEnd)) || timeEnd.isEqual(newEnd)))){ //but finished at or before 10pm EST
                //no interferences found, so return false
                return false;
        }
        /*
        if ((timeStart.isAfter(newStart) || timeStart.isEqual(newStart) || timeStart.getHour() == 1 || timeStart.getHour() == 0 || timeStart.getHour() == 2) //time starts at or after 8am EST
                && (timeStart.isBefore(newEnd))                              //but starts before 10pm EST
                && ((timeEnd.isAfter(newStart) || timeEnd.getHour() == 1 || timeEnd.getHour() == 2|| timeEnd.getHour() == 0 || timeEnd.getHour() <= 5)   )                            //time ends after 8am EST
                && (((timeEnd.isBefore(newEnd)) || timeEnd.isEqual(newEnd)) || timeEnd.getHour() == 1 || (timeEnd.getHour() == 2 && timeEnd.getMinute() == 0))){ //but finished at or before 10pm EST
            //no interferences found, so return false
            return false;
        }
        */






    //There was an interference so return true and deny appointment
    return true;
    }
}
