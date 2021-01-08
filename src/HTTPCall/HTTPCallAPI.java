/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTTPCall;

import retrofit2.Call;
import retrofit2.http.Body;
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

}
