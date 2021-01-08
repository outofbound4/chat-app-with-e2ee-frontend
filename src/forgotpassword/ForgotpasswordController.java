/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgotpassword;

import JavaMail.JavaMail;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gaurav
 */
public class ForgotpasswordController implements Initializable {

    @FXML
    private JFXTextField email;
    @FXML
    private Text error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onGetNewPasswordClick(ActionEvent event) {
        JavaMail mail = new JavaMail();
        String resetEmail = email.getText();
        if (isValidEmail(resetEmail)) {
            String status = mail.sendMail(resetEmail, "testing", "testing");
            if (status.equals("Successful")) {
                error.setText("Email sent successfully..!!");
            } else {
                error.setText(status);
            }
        } else {
            error.setText("Invalid Email.");
        }
    }

    /**
     *
     * @param email
     * @return
     */
    //    method for email validation
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    @FXML
    private void onBackToLoginClick(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();
            //it Hides the current window
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }

    @FXML
    private void clearError(MouseEvent event) {
        error.setText("");
    }

}
