/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import HTTPCall.HTTPCallAPI;
import HTTPCall.Register;
import HTTPCall.RetrofitService;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * FXML Controller class
 *
 * @author gaurav
 */
public class RegisterController implements Initializable {

    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField confirmPassword;
    @FXML
    private Text error;
    @FXML
    private JFXCheckBox termCondition;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onRegisterButtonClick(ActionEvent event) throws Exception {
        //        input values
        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String email = this.email.getText();
        String password = this.password.getText();
        String confirmPassword = this.confirmPassword.getText();

//        here first checking empty input and criterial password
        final String validationError = validate(firstName, lastName, password, confirmPassword);
        if (isValidEmail(email)) {
            if (validationError.equals("validated")) {
                // getting retrofit service object
                RetrofitService retrofit = new RetrofitService();
                HTTPCallAPI service = retrofit.getService();
                // sending URL to server
                Register reg = new Register(firstName, lastName, email, password);
                final Call<Register> call = service.register(reg);
                // making Asynchronous call 
                call.enqueue(new Callback<Register>() {
                    @Override
                    public void onResponse(Call<Register> call, Response<Register> response) {
                        if (response.isSuccessful()) {
                            Register apiResponse = response.body();
                            //API response
                            if (apiResponse.response.equals("Error")) {
                                error.setText(apiResponse.message);
                            } else {
// here we will do some specific work.
                                error.setText(apiResponse.message);
                            }
                        } else {
                            error.setText("Request Error :: " + response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {
                        error.setText("Network Error :: " + t.getLocalizedMessage());
//                System.out.println("Network Error :: " + t.getLocalizedMessage());
                    }
                });

            } else {
                error.setText(validationError);
            }
        } else {
            error.setText("Invalid Email.");
        }
    }
//    it validates empty input fields

    private String validate(String firstName, String lastName, String password, String confirmPassword) throws Exception {

        if (firstName == null || firstName.isEmpty()) {
            return "First Name missing.";
        }

        if (password.length() > 15 || password.length() < 8) {
            return "Password must be less than 16 and more than 7 characters in length.";
        }

        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars)) {
            return "Password must contain atleast one upper case alphabet.";
        }

        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars)) {
            return "Password must contain atleast one lower case alphabet.";
        }

        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers)) {
            return "Password must contain atleast one number.";
        }

        String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
        if (!password.matches(specialChars)) {
            return "Password should contain atleast one special character.";
        }
//        confirm password matching
        if (!password.equals(confirmPassword)) {
            return "Confirm password mismatch";
        }
        
        if (!termCondition.isSelected()) {
            return "Please accept the Terms of Use and Privacy Policy.";
        }
        
        return "validated";
    }

    //    method for email validation
    public static boolean isValidEmail(String email) {
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
    private void onLoginClick(ActionEvent event) {
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

}
