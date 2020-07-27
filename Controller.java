package com.internshala.javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Label welcome;
    @FXML
    public ChoiceBox<String> choice;
    @FXML
    public TextField field;
    @FXML
    public Button convert;

    private static final String C_F = "Celsius to Fahrenheit";
    private static final String F_C = "Fahrenheit to Celsius";
    boolean IsC_F= true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choice.getItems().add(C_F);
        choice.getItems().add(F_C);
        choice.setValue(C_F);
        choice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals(C_F))
            {
                IsC_F = true;
            }
            else
                IsC_F = false;
        });

        convert.setOnAction(event -> {button();});

    }

    private void button() {
        String inp =field.getText();
        float temp = 0.0f;
        try {
             temp = Float.parseFloat(inp);
        }
        catch(Exception ex)
        {
            warning();
            return;
        }
        float ntemp =0.0f;
        if(IsC_F)
        {
        ntemp= (temp*9/5)+32;
    }
        else
        {
            ntemp=(temp-32)*5/9;
        }
        display(ntemp);
}

    private void warning() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid temperature entered");
        alert.setContentText("Please enter a valid temperature");
        alert.show();

    }

    private void display(float ntemp) {
        String unit = IsC_F? "F":"C";
        System.out.println(ntemp+" "+unit);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText("The Temperature is :"+ntemp+" "+unit);
        alert.show();
    }
}
