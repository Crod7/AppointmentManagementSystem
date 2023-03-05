package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Contact {
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    private int contactId;
    private String contactName;
    private String email;

    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    @Override
    public String toString() {
        return getContactName();
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }

    public static void addContact(Contact contact) {
        allContacts.add(contact);
    }

    public static boolean deleteContact(Contact selectedContact) {
        if (selectedContact == null) {
            return false;
        }
        allContacts.remove(selectedContact);
        return true;
    }

    public static void updateContact(Contact selectedContact) {
        for (Contact x : allContacts) {
            if (x.getContactId() == selectedContact.getContactId()) {

            }
        }
    }






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

