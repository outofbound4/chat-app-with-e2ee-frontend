package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import application.ApiService;
import application.Message;
import application.RetrofitService;
import application.User;
import application.UserModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
import java.util.HashMap;

import application.MessageModel;

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
	private Button logout;
	
	@FXML
	private Button Loadmessage;
	
	
	//Display the hbox dynamically
	@FXML
	private VBox vbox;
	
	
	@FXML
	private TextArea messageBox;
	//@FXML private ListView chatPane;
	@FXML private Button sendButton;
	@FXML private ScrollPane scrollpane;
	List<User>alluser=new ArrayList<>();
	//public static User currentuser;
	//currentuser.setFirstname("Trisha");
	//currentuser.setLastname("Rani");
	//currentuser.setUsername("trisharani2210@gmail.com");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		RetrofitService retrofit=new RetrofitService();
		 ApiService getuser = retrofit.getService();
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
						 int i=1;
					for(User ulist:alluser) {
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
						root.setId("hbox"+i);
						lblUserName.setId("uname"+i);
						root.getStyleClass().add("friendbox");
						root.getChildren().addAll(lblUserDp, lblUserName);
						root.setSpacing(20);
						root.setAlignment(Pos.CENTER_LEFT);
						//HBox.setMargin(root, new Insets(50, 50, 50, 50));
						vbox.getChildren().add(root);
						
						//friendBox1 fb=new friendBox1();
					
						/*if(i==1) {
						 System.out.println(friendName1);
						 friendName1.setText(ulist.firstname +' '+ ulist.lastname);
						 String s=ulist.firstname.substring(0, 2);
						 //System.out.println(s);
						 friendDp1.setText(s);
						}
						
						if(i==2) {
							 friendName2.setText(ulist.firstname +' '+ ulist.lastname);
							 String s=ulist.firstname.substring(0, 2);
							 //System.out.println(s);
							 friendDp2.setText(s);
							}
						
						if(i==3) {
							 friendName3.setText(ulist.firstname +' '+ ulist.lastname);
							 String s=ulist.firstname.substring(0, 2);
							 //System.out.println(s);
							 friendDp3.setText(s);
							}
						i++;*/
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
	
	@FXML
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
	@FXML
	public void Search(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
        	String s=searchText.getText();
        	System.out.println(s);
          	RetrofitService retrofit=new RetrofitService();
   		 	ApiService getuser = retrofit.getService();
            Call<UserModel> call=getuser.searchuser(s);
            
            call.enqueue(new Callback<UserModel>() {

				

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

				@Override
				public void onFailure(Call<UserModel> arg0, Throwable arg1) {
					// TODO Auto-generated method stub
					
				}
            	
            });
        }	
	}

        	
	@FXML
	public void onloadmessageclick(javafx.event.ActionEvent event) {
		   
		   String id1="1";
		   String id2="2";
		   RetrofitService retrofit=new RetrofitService();
			 ApiService getmessage = retrofit.getService();
			// HashMap<String, String> log= new HashMap<String, String>();
			 //log(Id1,Id2);
			 Map<String,String> meta = new HashMap<>();
			 meta.put("sender",id1);
			 meta.put("recipient",id2);
			 Call<MessageModel>call=getmessage.getallmessage(meta);
			 call.enqueue(new Callback<MessageModel>() {
				 @Override
				 public void onResponse(Call<MessageModel> call,Response<MessageModel> response) {
					 if(!response.isSuccessful()) {
						 searchText.setText("code: "+ response.code());
						 return;
					 }
					else {
						System.out.println("Successful!!");
						MessageModel messages=response.body();
						List<Message> messagelist= messages.getallmessage();
						 if(messagelist!=null) {
								
						 //updating UI other than application so given under this lemda expression
						 Platform.runLater(()->{
							 int i=1;
							 VBox chatPane=new VBox();
							 VBox.setVgrow(scrollpane, Priority.ALWAYS);
						for(Message mlist:messagelist) {
							Label root= new Label();
							//put preference id here
							if(mlist.getSender()==1) {
								root.setText("You: "+mlist.getMessage());
								root.setFont(Font.font("Arial", 14));
								root.setStyle("padding:10px;margin:20px;-fx-border-color:light gray;-fx-background-color:#7bf007");
								root.setWrapText(true);
								root.setAlignment(Pos.CENTER_LEFT);
								root.setPrefHeight(40);
								root.setPrefWidth(200);
								root.setMaxHeight(Region.USE_COMPUTED_SIZE);
								root.setMinHeight(Region.USE_COMPUTED_SIZE);
								root.setMaxWidth(Region.USE_COMPUTED_SIZE);
								root.setMinWidth(Region.USE_COMPUTED_SIZE);
								chatPane.getChildren().add(root);
								chatPane.setMargin(root, new Insets(0, 0, 0, 200));
							}
								else {
								root.setText(mlist.getMessage());
								root.setFont(Font.font("Arial", 14));
								root.setStyle("padding:10px;margin:20px;-fx-border-color:light gray;-fx-background-color:white");
								root.setWrapText(true);
								root.setAlignment(Pos.CENTER_LEFT);
								root.setPrefHeight(40);
								root.setPrefWidth(200);
								root.setMaxHeight(Region.USE_COMPUTED_SIZE);
								root.setMinHeight(Region.USE_PREF_SIZE);
								root.setMaxWidth(Region.USE_PREF_SIZE);
								root.setMinWidth(Region.USE_COMPUTED_SIZE);
								chatPane.getChildren().add(root);
								chatPane.setMargin(root, new Insets(0, 0, 0,0));
								
								}
							chatPane.setSpacing(10);
						 }
						scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
						scrollpane.setPrefHeight(472);
						scrollpane.setPrefWidth(393);
						scrollpane.setMaxHeight(Region.USE_COMPUTED_SIZE);
						scrollpane.setMinHeight(Region.USE_COMPUTED_SIZE);
						scrollpane.setMaxWidth(Region.USE_COMPUTED_SIZE);
						scrollpane.setMinWidth(Region.USE_COMPUTED_SIZE);
						scrollpane.setContent(chatPane);
						});
						 
			}
						 }
					 }
				 @Override
					public void onFailure(Call<MessageModel> arg0, Throwable arg1) {
						System.out.println("No Messages");
						
					}
			 });
	}
	
	/*//message Controller
	@FXML
	public void onsendbuttonclick(ActionEvent e) {
		RetrofitService retrofit=new RetrofitService();
		 ApiService getuser = retrofit.getService();
		 
		
	}*/
	
	public void EditProfile(MouseEvent event){
		Parent root;
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../resources/EditProfile.fxml"));
            root=(Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit Profile");
            stage.setScene(new Scene(root));
            stage.show();
            //it Hides the current window
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
   
	}
	
	public void myfunction(String str) {
		System.out.println(str);
	}

	public void onloadusersclick(ActionEvent event){
		searchText.setText("Button clicked");	
	}
	
	
	public void HboxClick(MouseEvent event) {
		
	}
}

	