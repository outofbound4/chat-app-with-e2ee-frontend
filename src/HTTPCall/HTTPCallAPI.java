/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTTPCall;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *
 * @author gaurav
 */
public interface HTTPCallAPI {

    /**
     *
     * @param user
     * @param userid
     * @param password
     * @return
     */
//    @POST("/login")
//    Call<String> login(@Query("userid") String userid,
//            @Query("password") String password);
    @POST("login")
    public Call<User> login(@Body User user);

}
