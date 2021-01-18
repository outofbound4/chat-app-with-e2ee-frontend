/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgotpassword;

import HTTPCall.ForgotPassword;
import HTTPCall.HTTPCallAPI;
import HTTPCall.RetrofitService;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        String username = email.getText();
        if (isValidEmail(username)) {
            // getting retrofit service object
            RetrofitService retrofit = new RetrofitService();
            // getting retrofit service object RetrofitService retrofit = new RetrofitService();
            HTTPCallAPI service = retrofit.getService();
            // sending URL to server
            ForgotPassword forgotPass = new ForgotPassword(username);
            final Call<ForgotPassword> call = service.forgotPassword(forgotPass);
            // making Asynchronous call
            call.enqueue(new Callback<ForgotPassword>() {
                @Override
                public void onResponse(Call<ForgotPassword> call, Response<ForgotPassword> response) {
                    if (response.isSuccessful()) {
                        ForgotPassword apiResponse = response.body();
                        //API response
                        if (apiResponse.response.equals("Error")) {
                            error.setText(apiResponse.message);
                        } else {
                            error.setText(apiResponse.message);
                        }
                    } else {
                        error.setText("Request Error :: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<ForgotPassword> call, Throwable t) {
                    error.setText("Network Error :: " + t.getLocalizedMessage());
//                System.out.println("Network Error :: " + t.getLocalizedMessage());
                }
            });

        } else {
            error.setText("Invalid Email.");
        }
    }


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
