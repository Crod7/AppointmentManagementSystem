package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** This class controls how many customers belong to a certain group.
 */
public class CustomerTotal {
    /** This var is the Country.
     */
    private String country;
    /** This var is the Quantity.
     */
    private int quantity;
    /** This is an Observable List that holds all the customer totals that meet the condition of the group.
     */
    private static ObservableList<CustomerTotal> allCustomers = FXCollections.observableArrayList();
    /** This is the Constructor.
     * @param country The country name.
     * @param quantity How many in this condition.
     */
    public CustomerTotal(String country, int quantity) {
        this.country = country;
        this.quantity = quantity;
    }
    /** This method gets the Country string.
     * @return The country name.
     */
    public String getCountry() {
        return country;
    }
    /** This method sets the country string.
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /** This method gets the getAllCustomers Observable list.
     * @return The getAllCustomers list.
     */
    public static ObservableList<CustomerTotal> getAllCustomers(){
        return allCustomers;
    }

    /** This will keep the customer totals up to date with the information gathered from the
     * database. It will display the number of customers in a country.
     * @param country This is the country to search for when finding the number of customers in this country.
     */
    public static void populateList(String country) {
            int count = 0;
            int selection = 0;
            if (country.equals( "U.S.")){
                selection = 1;
            } else if (country.equals("UK")){
                selection = 2;
            } else {
                selection = 3;
            }
            allCustomers.clear();
            Customer.populateList();
            FirstLevelDivisions.populateList();

            for (Customer cust : Customer.getAllCustomers()) {
                //This takes the division from the customer to check their country
                for (FirstLevelDivisions y: FirstLevelDivisions.getAllDivisions()){
                    if (y.getDivisionId() == cust.getDivisionId()){
                        if (selection == y.getCountryId()){
                            count = count + 1;
                        }
                    }
                }
            }
            CustomerTotal appObject = new CustomerTotal(country, count);
            addCustomer(appObject);
    }
    /** This method will add a CustomerTotal object to the allCustomers list.
     * @param app The customer total object to be added.
     */
    public static void addCustomer(CustomerTotal app){
        allCustomers.add(app);
    }
}
