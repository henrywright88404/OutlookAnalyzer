package outlookanalyzer.scene.elements;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;

import static outlookanalyzer.scene.Layouts.HoizontalBoxHelper.createHBox;


public class PasswordFields {
    public static javafx.scene.control.PasswordField createPasswordField(){
        return new PasswordField();
    }

    public static javafx.scene.control.PasswordField createPasswordField(double minWidth){
        PasswordField passwordField = createPasswordField();
        passwordField.setMinWidth(minWidth);
        return new PasswordField();
    }

    public static javafx.scene.control.PasswordField createPasswordField(double minWidth, double minHeight) {
        PasswordField passwordField = createPasswordField();


        if (minWidth != 0 && minHeight == 0) {
            passwordField.setMinWidth(minWidth);
        } else {
            passwordField.setMinSize(minWidth, minHeight);
        }
        return new PasswordField();
    }

    public static HBox createLabelwithPasswordField(String label, double minWidth, double minHeight){


        PasswordField passwordField = createPasswordField(minWidth, minHeight);
        Label label1 = new Label(label);

        return createHBox(11,label1, passwordField);

    }


}
