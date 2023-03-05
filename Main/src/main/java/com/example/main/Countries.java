package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Countries {
    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();
    private int countryId;
    private String country;

    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }
    public String toString(){return getCountry();}

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static ObservableList<Countries> getAllCountries(){
        return allCountries;
    }
    public static void addCountries(Countries app){
        allCountries.add(app);
    }

    public static void populateList(){
        try {
            allCountries.clear();

            ResultSet rs = Query.queryDB("SELECT * FROM countries");

            while (rs.next()) {
                Countries object = new Countries(
                        rs.getInt("country_id"),
                        rs.getString("country"));
                addCountries(object);
            }
        }catch (SQLException se){

        }
    }
}
