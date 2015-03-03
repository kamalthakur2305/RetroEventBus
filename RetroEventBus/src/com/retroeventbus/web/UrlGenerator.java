package com.retroeventbus.web;


import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

import com.retroeventbus.EventBusCallback;
import com.retroeventbus.pojos.LoginData;
import com.squareup.okhttp.Response;

/**
 * API calls.
 * 
 * @author kthakur
 * @version 0.1
 *
 */
public class UrlGenerator {
	
	interface LoginRestApi {
		@POST("/Login.svc/UserLogin")
		void login(@Body Object param_object, Callback<retrofit.client.Response> eventBusCallback);
	}

	interface GetCityRestApi {
		@GET("/Common.svc/GetCity")
		void getCity(Callback<Response> cb);
	}
	
	
	/*public static interface RecentGetsServiceInterface {
	    @GET("/post/{param1}/{param2}")
	    void go(@Path("param1") String param1, @Path("param2") String param2);
	}
	
	public static interface PostServiceInterface<T> {
	    @POST("/{param1}/{param2}")
	    void go(@Body Object param_object, Callback<retrofit.client.Response> eventBusCallback,
	    		@Path("param1") String param1, @Path("param2") String param2);
	}*/
}
