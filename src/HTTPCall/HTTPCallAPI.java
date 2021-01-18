/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTTPCall;

import application.Message;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
/**
 *
 * @author gaurav
 */
public interface HTTPCallAPI {

    /**
     *
     * @param login
     * @return
     */
    @POST("/login")
    public Call<Login> login(@Body Login login);
    @POST("/register")
    public Call<Register> register(@Body Register register);
    @POST("/forgotPassword")
    public Call<ForgotPassword> forgotPassword(@Body ForgotPassword forgotPass);
    
    @GET("/get-users")
	public Call<UserModel> getallusers();
        
    @POST("/send-message")
	 Call<Message> sendmessage(@Body Message message);
    @POST("/get-message")
		Call<Message> getmessage(@Body Message message);
    @POST("/search-user")
	 Call<UserModel> searchuser(@Body String query);
    @POST("/save-user")
            Call<User> saveuser(@Body User user);
}
