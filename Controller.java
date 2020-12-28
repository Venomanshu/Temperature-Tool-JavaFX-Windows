package com.gtmzoid.javafx;

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

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
	@FXML
	public Label welcomeLable;
	@FXML
	public ChoiceBox <String>choiceBox;
	@FXML
	public TextField userInputField;
	@FXML
	public Button convertButton;

	private static final String C_TO_F_TEXT = "Celsius to Farhrenheit";
	private static final String F_TO_C_TEXT = "Farhrenheit to Celsius";

	private boolean isC_TO_F = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

			choiceBox.getItems().add(C_TO_F_TEXT);
			choiceBox.getItems().add(F_TO_C_TEXT);

			choiceBox.setValue(C_TO_F_TEXT);

			choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				System.out.println(newValue);
				if(newValue.equals(C_TO_F_TEXT)){
					isC_TO_F=true;
				}else
				{
					isC_TO_F=false;
				}


			});

			convertButton.setOnAction(event -> {
				convert();});

	}

	private void convert() {
		String input = userInputField.getText();
		Float enteredTemprature= 0.0f;
		try{
			enteredTemprature = Float.parseFloat(input);

		}catch(Exception exception)
		{
			warnUser();
			return;
			// no code executed......
		}

		float newTemprature = 0.0f;
		if(isC_TO_F)
		{
			newTemprature = (enteredTemprature*9/5)+32;
		}
		else{
			newTemprature = (enteredTemprature-32)*5/9;
		}
		display(newTemprature);
	}

	private void warnUser() {

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("ERROR OCCURED");
		alert.setContentText("INVALID TEMPRATURE OCCURRED");
		alert.setContentText("Please Enter Valid Temprature");
		alert.show();
		}

	private void display(float newTemprature)
	{
		String unit = isC_TO_F? "F":"C";
		System.out.println("The new Temprature is "+ newTemprature + unit);

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("The new Temprature is:" + newTemprature + unit);
		alert.show();
	}
}
