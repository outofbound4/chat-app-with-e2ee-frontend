/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author gaurav
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField userid;
    @FXML
    private JFXTextField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onSignUpLevelClicked(ActionEvent event) {
    }

    @FXML
    private void onKeepMeSignedClick(ActionEvent event) {
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) {
    }
    
}
