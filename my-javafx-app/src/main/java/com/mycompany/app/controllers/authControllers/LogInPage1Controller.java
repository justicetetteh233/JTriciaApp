package com.mycompany.app.controllers.authControllers;
import com.mycompany.app.controllers.aControllerTraits.Navigator;
import com.mycompany.app.controllers.aControllerTraits.UserNotifierOfSuccess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class LogInPage1Controller {
    @FXML
    private TextField userNameFeild;

    @FXML
    private Label userNameErrorFeild;

    @FXML
    private TextField passwordFeild;

    @FXML
    private Label passwordErrorFeild;

    @FXML
    private Button signUpBtn;

    @FXML
    private Hyperlink signUpInstead;

    @FXML
    private ProgressIndicator loadingIndicator;


    @FXML
    void SignInFunction(ActionEvent event) {

    }

    @FXML
    void SignUpInsteadFunction(ActionEvent event) {
        if(UserNotifierOfSuccess.notified(loadingIndicator))
            Navigator.navigate("/views/authViews/SignUpPage0.fxml", event);
    }

}

