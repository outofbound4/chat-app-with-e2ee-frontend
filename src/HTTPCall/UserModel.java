package HTTPCall;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class UserModel {
	
@SerializedName("users")	
public List<User> users=null;

@SerializedName("response")
public String response;

@SerializedName("message")
public String message;

public List<User> getUsers() {
	return users;
}

public String getResponse() {
	return response;
}

public String getMessage() {
	return message;
}



	

}
