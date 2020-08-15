package outlookanalyzer.scene.elements;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import static outlookanalyzer.scene.Layouts.HoizontalBoxHelper.createHBox;

public class TextFields {

    public static TextField createTextField( ){
        return new TextField();
    }

    public static TextField createTextField(double minWidth ){
        TextField textField = createTextField();
        textField.setMinWidth(minWidth);
        return textField;
    }

    public static TextField createTextField(double minWidth, double minHeight ){
        TextField textField = textField = createTextField();

        if(minWidth != 0 && minHeight==0){
            textField.setMinWidth(minWidth);
        } else {
            textField.setMinSize(minWidth, minHeight);
        }

        return textField;
    }

    public static TextField createTextField(double minWidth, double minHeight , double maxWidth, double maxHeight  ){
        TextField textField = createTextField(minWidth,minHeight);

        if(maxWidth != 0 && maxHeight==0){
            textField.setMaxWidth(maxWidth);
        } else {
            textField.setMaxSize(maxWidth, maxHeight);
        }

        return textField;
    }

    public static HBox createLabelWithTextField(String labelString, double minWidth, double minHeight, double maxWidth, double maxHeight){


        TextField textField = createTextField(minWidth, minHeight,maxWidth,maxHeight);
        Label label = new Label(labelString);

        return createHBox(10,label, textField);

    }

}
