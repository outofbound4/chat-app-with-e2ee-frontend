package application;
import config.Config;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService implements Config{
	
private final Retrofit retrofit;
private final ApiService getuser;

//public List<User> FetchUser() throws Exception{
	public RetrofitService() {
	this. retrofit=new Retrofit.Builder()
	.baseUrl(Config.BASE_URL)
	.addConverterFactory(GsonConverterFactory.create())
	.build();
	this.getuser=retrofit.create(ApiService.class);
}
//	Call<UserModel>alluser=getuser.getallusers();
	//alluser.execute();
//	Response<UserModel> execute=alluser.execute();
	//UserModel myuserlist=execute.body();
	
	//return myuserlist.getUsers();
	

public ApiService getService() {
    return this.getuser;
}

}
