package com.mycompany.app.controllers.SchoolPagesControllers;
import java.util.HashMap;

import com.mycompany.app.controllers.aControllerTraits.Navigator;
import com.mycompany.app.controllers.aControllerTraits.Validator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class CreateSchoolPage3Controller {

    @FXML
    private Label addressErrorField;

    @FXML
    private TextField addressField;

    @FXML
    private Button createSchoolButton;

    @FXML
    private Label emailErrorField;

    @FXML
    private TextField emailField;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private Label locationErrorField;

    @FXML
    private TextField locationField;

    @FXML
    private Label mottoErrorField;

    @FXML
    private TextField mottoField;

    @FXML
    private Label nameErrorField;

    @FXML
    private TextField nameField;

    @FXML
    private Label phoneErrorField;

    @FXML
    private TextField phoneField;

    @FXML
    void CreateSchool(ActionEvent event) {

        Label[] errorFields = {
            addressErrorField,
            emailErrorField,
            locationErrorField,
            phoneErrorField,
            nameErrorField,
            mottoErrorField
         };

        String[] validationDetails = {
            "[address]{0}<"+addressField.getText()+">(mustBeTextMoreThanCharacters@2)",
            "[email]{1}<"+emailField.getText()+">(mustBeTextMoreThanCharacters@8)",
            "[location]{2}<"+locationField.getText()+">(mustBeTextMoreThanCharacters@2)",
            "[phone]{3}<"+phoneField.getText()+">(mustBeTextLessThanCharacters@11)(mustBeTextMoreThanCharacters@9)",
            "[name]{4}<"+nameField.getText()+">(mustBeTextMoreThanCharacters@2)",
            "[motto]{5}<"+mottoField.getText()+">(mustBeTextMoreThanCharacters@2)"
        };

        HashMap<String, HashMap<String, Object>>  SerializedForm = Validator.validate(validationDetails,errorFields);
        if((Boolean) SerializedForm.get("errorState").get("isvalid")){
            Navigator.navigate("/views/schoolPages/SchoolLandingPage4.fxml", event);
        }
    }









}
