package com.example.main;

import javafx.scene.control.Alert;
/** This class manages the methods used for handling errors that occur in the program.
 */
public class ErrorMessage {

    /** This method when called will display an error message telling the user what they need to correct in order for the form to accept their input.
     */
    public static void msg(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(Lang.print("Attention"));
        alert.setHeaderText(Lang.print("Invalid")+" "+ Lang.print("Entries")+" "+Lang.print("Detected"));
        alert.setContentText(message);
        alert.showAndWait();
    }
    /** This method when called will display an error message telling the user what they need to correct in order for the form to accept their input.
     */
    public static void msg2(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(Lang.print("Attention"));
        alert.setHeaderText(Lang.print("Deletion")+" "+ Lang.print("Failed"));
        alert.setContentText(message);
        alert.showAndWait();
    }
}
