package Front_end.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import login.LoginController;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class EditProfileController  implements Initializable{
	
	@FXML
	private Label username;
	
	@FXML
	private TextField fname;
	
	@FXML
	private TextField lname;
	
	@FXML
	private TextField interest;
	
	@FXML 
	private Button update;
	
	@FXML
	private ImageView back;
	
	@FXML
	private Label msg;
	
	//List<User> alluser=new ArrayList<>();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//currentuser
		
		Platform.runLater(()->{
		username.setText(LoginController.currentuser.getFirstname()+" "+LoginController.currentuser.getLastname());
		fname.setText(LoginController.currentuser.getFirstname());
		lname.setText(LoginController.currentuser.getLastname());
		interest.setText=LoginController.currentuser.getInterest();
		});
		
		
	}
        
        
	@FXML
	public void back(MouseEvent event){
		Parent root;
		//msg.setText("Back clicked");
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../resources/ShowProfile.fxml"));
            root=(Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("User_Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
            //it Hides the current window
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
   
	}
	
	@FXML
	public void onupdateButtonClick(javafx.event.ActionEvent event) throws IOException, Exception{
	  msg.setText("Button clicked");
         // String email = this.uname.getText();
          String firstname= this.fname.getText();
          String lastname=this.lname.getText();
          String interests=this.interest.getText();

//        empty field checking
        final String validationError = validate(email);
        if (isValidEmail(email)) {
            if (validationError.equals("validated")) {
                // getting retrofit service object
                RetrofitService retrofit = new RetrofitService();
                HTTPCallAPI service = retrofit.getService();
                // sending URL to server
                User log = new User(firstname,lastname,interests);
                final Call<Login> call = service.saveuser(log);
                // making Asynchronous 
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User apiResponse = response.body();
                           // User currentuser=apiResponse.getcurrentuser();
                        //User currentuser= response.body();
                            //API response
                            if (apiResponse.response.equals("Error")) {
                                msg.setText(apiResponse.message);
                            } else {
//                        here we will do some specific work.
                            	 msg.setText("Updated Succesfully");
                                 //here will do preference task;
                                 
                            }
                        }
                    }
          
          		@Override
			public void onFailure(Call<User> call, Throwable t) {
				// TODO Auto-generated method stub
                                msg.setText("Load Failure: "+ t.getMessage());
			}
		 });
          
 }
        }
        }
        
}

