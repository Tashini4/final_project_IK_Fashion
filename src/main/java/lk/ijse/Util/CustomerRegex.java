package lk.ijse.Util;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegex {
    public static boolean isTextFieldValid(CustomerTextField textField , String text){
        String filed = "";

        switch (textField){
            case ID:
                filed = "^([A-Z][0-9]{4})$";
                break;
            case NAME:
                filed = "^[A-z|\\s]{3,}$";
                break;
            case ADDRESS:
                filed = "^([A-z0-9]|[-/,.@+]|\\s){4,}$";
                break;
            case CONTACT:
                filed = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
                break;
            case EMAIL:
                filed =  "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case PASSWORD:
                filed =".*";
                break;
            case NUMBER:
                filed ="^[0-9]+$";

        }
        Pattern pattern = Pattern.compile(filed);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }
    public static boolean setTextColor(CustomerTextField location, TextField textField){
        if (CustomerRegex.isTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-text-fill: green; -fx-border-color: green;");

            return true;
        } else {
            textField.setStyle("-fx-text-fill: red; -fx-border-color: red;");
            return false;
        }
    }
}


