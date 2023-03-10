package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerTotal {
    private String country;
    private int quantity;
    private static ObservableList<CustomerTotal> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<CustomerTotal> allCustomersFiltered = FXCollections.observableArrayList();

    public CustomerTotal(String country, int quantity) {
        this.country = country;
        this.quantity = quantity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public static ObservableList<CustomerTotal> getAllCustomers(){
        return allCustomers;
    }
    public static ObservableList<CustomerTotal> getAllCustomersFiltered(){
        return allCustomersFiltered;
    }


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
    public static void addCustomer(CustomerTotal app){
        allCustomers.add(app);
    }
}
