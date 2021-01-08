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
public class Register {

    @SerializedName("firstname")
    public String firstname;
    @SerializedName("lastname")
    public String lastname;
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("response")
    public String response;
    @SerializedName("message")
    public String message;

    public Register(String firstName, String lastName, String email, String password) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.username = email;
        this.password = password;
    }
}
