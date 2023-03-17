package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/** This class manages the Contact object.
 */
public class Contact {
    /** An observable list of all contacts.
     */
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    /** This variable holds the Contact ID as integer.
     */
    private int contactId;
    /** This variable holds the Contact Name as string.
     */
    private String contactName;
    /** This variable holds the Email as string.
     */
    private String email;
    /** The constructor for the Contact object.
     * @param contactName This is the name of the contact.
     * @param contactId This is the unique ID of the customer.
     * @param email This is the email of the customer.
     */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }
    /** We override the toString method to return the name of the contact.
     */
    @Override
    public String toString() {
        return getContactName();
    }
    /** This method gets the contact id.
     * @return the contact id.
     */
    public int getContactId() {
        return contactId;
    }
    /** This method sets the contact id.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /** This method gets the contact name.
     * @return the contact name.
     */
    public String getContactName() {
        return contactName;
    }
    /** This method sets the contact name.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /** This method gets the contact email.
     * @return the contact email.
     */
    public String getEmail() {
        return email;
    }
    /** This method sets the email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /** This method gets the Observable List of allContacts.
     * @return allContacts.
     */
    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }
    /** This method adds a Contact to the allContacts Observable List.
     * @param contact The object to be added to the list.
     */
    public static void addContact(Contact contact) {
        allContacts.add(contact);
    }
    /** This method is run to populate a list of available contacts so the user can make a selection.
     */
    public static void populateList(){
        try {
            allContacts.clear();


            ResultSet rs = Query.queryDB("SELECT * FROM contacts");


            // This code below will fill the list with contact objects
            while (rs.next()) {
                Contact conObject = new Contact(
                        rs.getInt("contact_id"),
                        rs.getString("contact_name"),
                        rs.getString("email"));
                addContact(conObject);
            }
        }catch (SQLException se){

        }
    }
    /** This takes a name and matches that name with the database and returns it's corresponding ID.
     * @param name This is the name that will be used to compare to the database.
     * @return the ID that matched the name.
     */
    public static int getContactId(String name){
        try{
            ResultSet rs = Query.queryDB("SELECT * FROM contacts");
            while (rs.next()){
                if (rs.getString("contact_name").equals(name)){
                    return rs.getInt("contact_id") - 1;
                }
            }
            return 0;
        }catch (SQLException se){

        }
        return 0;
    }
}

