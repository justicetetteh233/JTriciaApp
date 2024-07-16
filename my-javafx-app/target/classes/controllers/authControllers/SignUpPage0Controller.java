package com.mycompany.app.controllers.authControllers;
// com.mycompany.app.controllers.authControllers.SignUpPage0Controller
import java.util.HashMap;
import com.mycompany.app.controllers.aControllerTraits.Navigator;
import com.mycompany.app.controllers.aControllerTraits.UserNotifierOfFailure;
import com.mycompany.app.controllers.aControllerTraits.UserNotifierOfSuccess;
import com.mycompany.app.controllers.aControllerTraits.Validator;
import com.mycompany.appBackend.models.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;




public class SignUpPage0Controller {
    @FXML
    private TextField userNameFeild;

    @FXML
    private Label userNameErrorFeild;

    @FXML
    private TextField emailFeild;

    @FXML
    private Label emailErrorFeild;

    @FXML
    private TextField phoneFeild;

    @FXML
    private Label phoneErrorFeild;

    @FXML
    private Label passwordErrorFeild;

    @FXML
    private Label confirmPasswordErrorFeild;

    @FXML
    private TextField passwordFeild;

    @FXML
    private TextField confirmPasswordFeild;

    @FXML
    private TextField secretQuestionFeild;

    @FXML
    private Label secretQuestionErrorFeild;

    @FXML
    private TextField secretAnswerFeild;

    @FXML
    private Label secretAnswerErrorFeild;

    @FXML
    private Button signUpBtn;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private Hyperlink loginInsteadlink;


    @FXML
    void signUp(ActionEvent event) {

        String[] validationFeilds = {                
            "[userNameFeild]{0}<"+userNameFeild.getText()+">(mustBeTextMoreThanCharacters@2)",
            "[emailFeild]{1}<"+emailFeild.getText()+">(mustBeTextMoreThanCharacters@8)",
            "[phoneFeild]{2}<"+phoneFeild.getText()+">(mustBeTextLessThanCharacters@11)(mustBeTextMoreThanCharacters@9)",
            "[passwordFeild]{3}<"+passwordFeild.getText()+">(mustBeTextMoreThanCharacters@4)",
            "[secretQuestionFeild]{4}<"+secretQuestionFeild.getText()+">(mustBeTextMoreThanCharacters@8)",
            "[secretAnswerFeild]{5}<"+secretAnswerFeild.getText()+">(mustBeTextMoreThanCharacters@3)"
        };

        Label[] errorFeilds ={
            userNameErrorFeild,
            emailErrorFeild,
            phoneErrorFeild,
            passwordErrorFeild,
            secretQuestionErrorFeild,
            secretAnswerErrorFeild
        };



        if(Validator.compareTwoFieldsAndClearFeild(passwordFeild.getText(), confirmPasswordFeild.getText(),passwordErrorFeild,errorFeilds)){
            
            HashMap<String, HashMap<String, Object>>  SerializedForm = Validator.validate(validationFeilds,errorFeilds);

            if((Boolean) SerializedForm.get("errorState").get("isvalid")){

                HashMap<String,Object> data = SerializedForm.get("validatedData");
                User newUser = new User();

                newUser.setName(data.get("userNameFeild").toString());
                newUser.setEmail(data.get("emailFeild").toString());
                newUser.setPhone(data.get("phoneFeild").toString());
                newUser.setPassword(data.get("passwordFeild").toString());
                newUser.setSecretQuestion(data.get("secretQuestionFeild").toString());
                newUser.setSecretAnswer(data.get("secretAnswerFeild").toString());

                Boolean isCreatedSuccessfully = newUser.create();

                if(isCreatedSuccessfully){
                    Navigator.navigate("/views/authViews/LogInPage1.fxml", event);
                }else{
                    System.out.println("now coming to load error animation");
                    if(UserNotifierOfFailure.notified(loadingIndicator))
                        return; 
                }

            }else{
                System.out.println("validations Error");
                return;
            }
        }else return;
    }



    @FXML
    void loginInsteadTask(ActionEvent event){
        if(UserNotifierOfSuccess.notified(loadingIndicator))
            Navigator.navigate("/views/authViews/LogInPage1.fxml", event);
    }

}

