package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
/** This class manages the Countries objects.
 */
public class Countries {
    /** This array holds the options available for countries.
     */
    public static String[] countryOptions = {"U.S.","UK","Canada"};
    /** This Observable List holds the countries as an Observable List.
     */
    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();
    /** This variable is the Country ID.
     */
    private int countryId;
    /** This variable is the Country string.
     */
    private String country;
    /** This Constructor represents the country.
     * @param countryId This is the ID of the country.
     * @param country This is the name of the country.
     */
    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }
    /** This method will return the name of the country.
     * @return the name of the country as a string.
     */
    public String toString(){return getCountry();}
    /** This gets the country.
     * @return the country name.
     */
    public String getCountry() {
        return country;
    }
    /** This sets the country name.
     * @param country The name.
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /** This returns the Observable List of allCountries.
     * @return the allCountries Observable List.
     */
    public static ObservableList<Countries> getAllCountries(){
        return allCountries;
    }
    /** This adds a Country to the allCountries list.
     * @param app The country being added.
     */
    public static void addCountries(Countries app){
        allCountries.add(app);
    }
    /** This method will keep the table view in the program and the data from the database consistent with each other.
     */
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
