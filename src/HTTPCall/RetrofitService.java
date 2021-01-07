/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTTPCall;

import config.Config;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author gaurav
 */
public class RetrofitService implements Config{

    private final Retrofit retrofit;
    private final HTTPCallAPI service;

    public RetrofitService() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.service = this.retrofit.create(HTTPCallAPI.class);
    }

    public HTTPCallAPI getService() {
        return this.service;
    }
}
