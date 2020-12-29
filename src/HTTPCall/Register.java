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

    @SerializedName("firstName")
    public String firstName;
    @SerializedName("lastName")
    public String lastName;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    @SerializedName("response")
    public String response;
    @SerializedName("message")
    public String message;

    public Register(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
