package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
/** This class controls the FirstLevelDivisions.
 */
public class FirstLevelDivisions {
    /** An Observable List of all divisions.
     */
    private static ObservableList<FirstLevelDivisions> allDivisions = FXCollections.observableArrayList();
    /** An Observable List of filtered divisions.
     */
    private static ObservableList<FirstLevelDivisions> allDivisionsFiltered = FXCollections.observableArrayList();
    /** This is the division id.
     */
    private int divisionId;
    /** This is the division name.
     */
    private String division;
    /** This is the country ID, where the division belongs to.
     */
    private int countryId;
    /** This is the constructor.
     * @param countryId The country it belongs to.
     * @param division The state/province it belongs to.
     * @param divisionId The ID of the state/province.
     */
    public FirstLevelDivisions(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }
    /** This gets the Division ID.
     * @return the division id.
     */
    public int getDivisionId() {
        return divisionId;
    }
    /** This gets the Division.
     * @return the division name.
     */
    public String getDivision() {
        return division;
    }
    /** This gets the Country ID.
     * @return the country id.
     */
    public int getCountryId() {
        return countryId;
    }
    /** This changes the toString method to print the name of the division.
     * @return the division name.
     */
    public String toString(){return getDivision();}
    /** This gets the Observable list for getAllDivisions.
     * @return the allDivisions list.
     */
    public static ObservableList<FirstLevelDivisions> getAllDivisions(){
        return allDivisions;
    }
    /** This adds a First Level Division to the Observable List allDivisions.
     * @param app This is the division to be added to the list.
     */
    public static void addDivisions(FirstLevelDivisions app){
        allDivisions.add(app);
    }
    /** This gets the Observable List for getAllDivisionsFiltered.
     * @return The allDivsionsFiltered list.
     */
    public static ObservableList<FirstLevelDivisions> getAllDivisionsFiltered(){
        return allDivisionsFiltered;
    }
    /** This adds to the allDivisionsFiltered list.
     * @param app The First Level Division to be added.
     */
    public static void addDivisionsFiltered(FirstLevelDivisions app){
        allDivisionsFiltered.add(app);
    }
    /** This keeps the FirstLevelDivision up to date with the database.
     */
    public static void populateList(){
        try {
            allDivisions.clear();

            ResultSet rs = Query.queryDB("SELECT * FROM first_level_divisions");

            while (rs.next()) {
                FirstLevelDivisions object = new FirstLevelDivisions(
                        rs.getInt("division_id"),
                        rs.getString("division"),
                        rs.getInt("country_id"));
                addDivisions(object);
            }
        }catch (SQLException se){

        }
    }
    /** This filters the allDivisionsFiltered list.
     * @param cId The list will be filtered by Country ID.
     */
    public static void filteredList(int cId){
        allDivisionsFiltered.clear();
        for (FirstLevelDivisions x: allDivisions){
            if (cId == x.getCountryId()){
                addDivisionsFiltered(x);
            }
        }
    }
    /** This will use the division id to get the country it belongs to.
     * @param id This is the division id.
     */
    public static int getCountryId(int id){
        try{
            ResultSet rs = Query.queryDB("SELECT * FROM first_level_divisions");
            while (rs.next()){
                if (rs.getInt("division_id") == (id)){
                    return rs.getInt("country_id")-1;
                }
            }
            return 0;
        }catch (SQLException se){

        }
        return 0;
    }
}
