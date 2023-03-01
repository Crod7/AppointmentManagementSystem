package com.example.main;

import java.util.Locale;
import java.util.ResourceBundle;
/** This class holds the method for translating words to different languages.
 */
public class Lang {


    /** The method accepts a string and translates it to the current system language. It is done by returning the new value as a string.
     * @param word This is the string that will be translated and returned.
     */
    public static String print(String word){
        ResourceBundle rb = ResourceBundle.getBundle(".Nat", Locale.getDefault());
        return rb.getString(word);
    }
}
