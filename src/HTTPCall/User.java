package HTTPCall;

import com.google.gson.annotations.SerializedName;

public class User {
	@SerializedName("id")
	public Long id;
	
	@SerializedName("username")
	public String username;

	@SerializedName("firstname")
	public String firstname;

	@SerializedName("lastname")
	public String lastname;
	
        @SerializedName("interests")
        public String interests;
        
        @SerializedName("response")
            public String response;
        @SerializedName("message")
            public String message;
        
        public User(){
            
        }
        public User(String firstname,String lastname,String interests){
            this.firstname=firstname;
            this.lastname=lastname;
            this.interests=interests;
        }
	public String getUsername() {
		return username;
	}


	public String getFirstname() {
		return firstname;
	}


	public String getLastname() {
		return lastname;
	}
        
        public String getInterests() {
		return interests;
	}
        
       	
	
}
