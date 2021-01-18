package Front_end.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.List;
import java.util.ArrayList;
import login.LoginController;

public class ShowProfile implements Initializable{
	
	@FXML
	private Label username;
	
	@FXML
	private Label fname;
	
	@FXML
	private Label lname;
	
	@FXML
	private Label interest;
	
	@FXML 
	private Button Edit;
	
	@FXML
	private ImageView back;
	
	@FXML
	private Label msg;
	
	//List<User> alluser=new ArrayList<>();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//currentuser
		
		Platform.runLater(()->{
		username.setText(LoginController.currentuser.getFirstname()+" "+LoginController.currentuser.getFirstname());
		fname.setText(LoginController.currentuser.getFirstname());
		lname.setText(LoginController.currentuser.getFirstname());
		//interest.setText=LoginController.currentuser.getInterest();
		});
		
		
	}	
	@FXML
	public void back(MouseEvent event){
		Parent root;
		//msg.setText("Back clicked");
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../resources/front_end.fxml"));
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
	public void onEditButtonClick(javafx.event.ActionEvent event) throws IOException, Exception{
	  msg.setText("Button clicked");
	  Parent root;
		//msg.setText("Back clicked");
      try {
          FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../resources/EditProfile.fxml"));
          root=(Parent) fxmlLoader.load();
          Stage stage = new Stage();
          stage.setTitle("Edit");
          stage.setScene(new Scene(root));
          stage.show();
          //it Hides the current window
          ((Node) (event.getSource())).getScene().getWindow().hide();
      } catch (IOException e) {
      }
 
	}
	 
	}
