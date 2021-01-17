/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTTPCall;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author gaurav
 */
public class ForgotPassword {

    @SerializedName("username")
    public String username;
    @SerializedName("response")
    public String response;
    @SerializedName("message")
    public String message;
    @SerializedName("token")
    public String token;

    public ForgotPassword(String email) {
        this.username = email;
    }
}
