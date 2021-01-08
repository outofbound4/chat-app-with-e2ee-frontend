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
public class Login {

    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("response")
    public String response;
    @SerializedName("message")
    public String message;

    public Login(String email, String Password) {
        this.username = email;
        this.password = Password;
    }

    public String getEmail() {
        return username;
    }
}
