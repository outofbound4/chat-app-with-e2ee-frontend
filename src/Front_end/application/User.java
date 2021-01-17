package application;

import com.google.gson.annotations.SerializedName;

public class User {
	@SerializedName("id")
	public String id;
	
	@SerializedName("username")
	public String username;

	@SerializedName("firstname")
	public String firstname;

	@SerializedName("lastname")
	public String lastname;
	

	public String getUsername() {
		return username;
	}


	public String getFirstname() {
		return firstname;
	}


	public String getLastname() {
		return lastname;
	}
	
	
	
}
