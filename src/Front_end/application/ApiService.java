package application;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

	@GET("/get-users")
    	Call<UserModel> getallusers();
	@POST("/send-message")
	    Call<Message> sendmessage(@Body Message message);
	@POST("/get-message")
		Call<Message> getmessage(@Body Message message);
	@POST("/search-user")
		Call<UserModel> searchuser(@Body String query);
	
}
