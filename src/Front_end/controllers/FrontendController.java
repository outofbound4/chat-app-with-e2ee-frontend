package Front_end.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import HTTPCall.HTTPCallAPI;
import HTTPCall.RetrofitService;
import HTTPCall.User;
import HTTPCall.UserModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import login.LoginController;


public class FrontendController implements Initializable{
	
	@FXML
	private ImageView userProfile;
	
	@FXML
	private ImageView addFriend;
	
	@FXML
	private ImageView editProfile;
	
	@FXML
	private ImageView msgAny;
	
	@FXML
	private TextField searchText;
	
	@FXML
	private Button loadUser;
	
	
	//Display the hbox dynamically
	@FXML
	private VBox vbox;
	
	
	@FXML
	private TextArea messageBox;
	@FXML private ListView chatPane;
	@FXML private Button sendButton;
	
	List<User>alluser=new ArrayList<>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Step 1
		  //Node node = (Node) (event.getSource());
		  //Stage stage = (Stage) node.getScene().getWindow();
		  // Step 2
		  //User currentuser= (User) stage.getUserData();
		  // Step 3
		  //String firstname = currentuser.getFirstname();
		  //String lastname = currentuser.getLastname();
                  System.out.println(LoginController.currentuser.getFirstname());
                  System.out.println(LoginController.currentuser.getLastname());
		  RetrofitService retrofit=new RetrofitService();
		  HTTPCallAPI getuser = retrofit.getService();
		  Call<UserModel>call=getuser.getallusers();
		 
		 call.enqueue(new Callback<UserModel>() {
			 @Override
			 public void onResponse(Call<UserModel> call,Response<UserModel> response) {
				 if(!response.isSuccessful()) {
					 searchText.setText("code: "+ response.code());
					 return;
				 }
				else {
					System.out.println("Successful!!");
					UserModel Umodel=response.body();
				
					 alluser = Umodel.getUsers();
					 if(alluser!=null) {
							
					 //updating UI other than application so given under this lemda expression
					 Platform.runLater(()->{
						 //int i=1;
					for(User ulist:alluser) {
						if(ulist.getUsername()==LoginController.currentuser.getUsername()) {
							continue;
						}
						HBox root= new HBox();
						Label lblUserDp = new Label(ulist.firstname.substring(0, 2));
						lblUserDp.setAlignment(Pos.CENTER);
						lblUserDp.setFont(Font.font("Arial", 24));
						lblUserDp.setStyle("-fx-border-radius:50;padding: 20px;margin:20px;-fx-border-color:black");
						//lblUserDp.getStyleClass().add("hello-border-radius");
						lblUserDp.setTextFill(Color.web("#a11313"));
						lblUserDp.setWrapText(false);
						lblUserDp.setPrefHeight(45);
						lblUserDp.setPrefWidth(45);
						lblUserDp.setMaxHeight(Region.USE_COMPUTED_SIZE);
						lblUserDp.setMinHeight(Region.USE_COMPUTED_SIZE);
						lblUserDp.setMaxWidth(Region.USE_COMPUTED_SIZE);
						lblUserDp.setMinWidth(Region.USE_COMPUTED_SIZE);
						
						Label lblUserName=new Label(ulist.firstname +' '+ ulist.lastname);
						lblUserName.setFont(Font.font("Arial",FontWeight.BOLD, 14));
						
						root.getChildren().addAll(lblUserDp, lblUserName);
						root.setSpacing(20);
						root.setAlignment(Pos.CENTER_LEFT);
						//HBox.setMargin(root, new Insets(50, 50, 50, 50));
						
						//setGraphic(root);
						vbox.getChildren().add(root);
						
					}
					 });
					}
				
				}
			 }

			@Override
			public void onFailure(Call<UserModel> call, Throwable t) {
				// TODO Auto-generated method stub
			 searchText.setText("Load Failure: "+ t.getMessage());
			}
		 });

		
	}
	
  
        
	//onclick user profile image
	public void ShowProfile(MouseEvent event){
	Parent root;
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../resources/ShowProfile.fxml"));
            root=(Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("User_Profile");
            stage.setScene(new Scene(root));
            stage.show();
            //it Hides the current window
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
   
	}
	
	
	//On Search textbox press enter
	
	public void Search(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
        	String s=searchText.getText();
        	System.out.println(s);
          	RetrofitService retrofit=new RetrofitService();
   		HTTPCallAPI getuser = retrofit.getService();
            Call<UserModel> call=getuser.searchuser(s);
            
            call.enqueue(new Callback<UserModel>() {

				@Override
				public void onFailure(Call<UserModel> arg0, Throwable arg1) {
					System.out.println("No user!!!");
					
				}

				@Override
				public void onResponse(Call<UserModel> call, Response<UserModel> response) {
					if(!response.isSuccessful()) {
						 searchText.setText("code: "+ response.code());
						 return;
					 }
					else {
						System.out.println("Successful!!");
						UserModel Umodel=response.body();
					
						 alluser = Umodel.getUsers();
						 if(alluser!=null) {
							 //if( vbox.numElements > 0 )
							   //     myContent.removeElementAt( myContent.numElements - 1 );
								
						 //updating UI other than application so given under this lemda expression
						 Platform.runLater(()->{
							 int i=1;
						for(User ulist:alluser) {
							HBox root= new HBox();
							Label lblUserDp = new Label(ulist.firstname.substring(0, 2));
							Label lblUserName=new Label(ulist.firstname +' '+ ulist.lastname);
							root.getChildren().addAll(lblUserDp, lblUserName);
							//setGraphic(root);
							vbox.getChildren().add(root);
						}
						});
						 
					}
					
				}
				
				}
            	
            });
        }
        	
    }
	
	//
	
	
	
	/*//message Controller
	@FXML
	public void onsendbuttonclick(ActionEvent e) {
		RetrofitService retrofit=new RetrofitService();
		 ApiService getuser = retrofit.getService();
		 
		
	}*/
	


	public void onloadusersclick(ActionEvent event){
		searchText.setText("Button clicked");	
	}
}

	