package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.ApiService;
import application.RetrofitService;
import application.User;
import application.UserModel;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.ArrayList;


public class FriendListController implements Initializable{
	
	@FXML
	private Label uname;
	
	@FXML
	private Label fname;
	
	@FXML
	private Label lname;
	
	@FXML
	private Label msg;
	
	@FXML 
	private Button GetMore;
	
	@FXML
	private ImageView back;
	
	List<User> alluser=new ArrayList<>();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		//Label uname=new Label();
		//Label fname=new Label();
		//Label lname=new Label();
		
		// TODO Auto-generated method stub
	}
	
	@FXML
	public void back(MouseEvent event){
		Parent root;
		msg.setText("Back clicked");
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
	public void ongetmoreButtonClick(javafx.event.ActionEvent event) throws IOException, Exception{
	msg.setText("Button clicked");	
	 RetrofitService retrofit=new RetrofitService();
	 ApiService getuser = retrofit.getService();
	 Call<UserModel>call=getuser.getallusers();
	 
	 call.enqueue(new Callback<UserModel>() {
		 @Override
		 public void onResponse(Call<UserModel> call,Response<UserModel> response) {
			 if(!response.isSuccessful()) {
				 msg.setText("code: "+ response.code());
				 return;
			 }
			else {
				UserModel Umodel=response.body();
				
				 alluser=Umodel.getUsers();
				 //updating UI other than application so given under this lemda expression
				 Platform.runLater(()->{
				for(User ulist:alluser) {
					 uname.setText(ulist.username+',');
					 lname.setText(ulist.firstname+',');
					 System.out.println(ulist.lastname+',');
				}
				 });
				}
			
			}

		@Override
		public void onFailure(Call<UserModel> call, Throwable t) {
			// TODO Auto-generated method stub
			msg.setText("Load Failure: "+ t.getMessage());

		}
			

 });
	}
}
