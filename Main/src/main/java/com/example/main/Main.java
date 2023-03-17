package com.example.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/** This class is the main file. The program begins here.
 */
public class Main extends Application {
    /** This will load up the initial UI page. The page is the login form.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 300);
        stage.setTitle(Lang.print("Appointment")+" "+Lang.print("Management")+" "+Lang.print("System"));
        stage.setScene(scene);
        stage.show();
    }
    /** This is the Main method.
     */
    public static void main(String[] args) {
        launch();
    }
}