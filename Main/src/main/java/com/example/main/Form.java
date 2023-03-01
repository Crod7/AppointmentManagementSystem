package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/** This class controls the form and any form changes throughout the program.
 */
public class Form {
    /*Manages the scene-------------------------------------------------------------------------------------------*/
    /** This sets the stage.
     */
    private static Stage stage;
    /** This sets the scene.
     */
    private static Scene scene;
    /** This sets the root.
     */
    private static Parent root;

    /** This method controls every form change throughout the program. This method takes a string and uses that string to locate which fxml file to switch to.
     * @param fxml This is the name of the fxml file to search for. For simplicity include .fxml extension in the parameter.
     */
    public static void changePageTo(ActionEvent e,String fxml) throws IOException {
        root = FXMLLoader.load(Form.class.getResource(fxml));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
