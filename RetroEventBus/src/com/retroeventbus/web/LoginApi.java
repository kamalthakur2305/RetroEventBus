package com.retroeventbus.web;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import android.app.Activity;

import com.google.gson.Gson;
import com.retroeventbus.events.ApiErrorEvent;
import com.retroeventbus.events.ApiResultEvent;
import com.retroeventbus.events.ExceptionEvent;
import com.retroeventbus.events.NoInternetEvent;
import com.retroeventbus.listeners.ProgressListener;
import com.retroeventbus.pojos.LoginData;
import com.retroeventbus.pojos.LoginResponse;
import com.retroeventbus.utils.Utilities;
import com.retroeventbus.web.UrlGenerator.LoginRestApi;
import com.squareup.okhttp.OkHttpClient;

import de.greenrobot.event.EventBus;

/**
 * This class is used to authenticate user via web service and read 
 * the results returned in a web service and communicate it back to the Activity.
 * 
 * @author kthakur
 * 
 * @version 0.1
 *
 */
public class LoginApi implements UrlListener{
	
	Activity activity;
	LoginRestApi loginRestApi;
	ProgressListener progressListener;
	EventBus bus = EventBus.getDefault();
	
	/**
	 * Parameterized constructor.
	 * 
	 * @param activity: Context of the Activity from which it is called.
	 * @param progressListener: Progress listener
	 * @param loginData: Login data object
	 */
	public LoginApi(Activity activity,ProgressListener progressListener) {
		
		this.activity = activity;
		this.progressListener = progressListener;
		
		loginRequest();
	}
	
	/**
	 * Create login request.
	 * 
	 * @param loginData
	 * @param resultListner
	 * @param retrofitListner
	 */
	public void loginRequest() {
		
		//check for Internet
		if(!Utilities.checkInternet(activity)){
			//posts no Internet status back to the Activity 
			NoInternetEvent event = new NoInternetEvent(false);
	        bus.post(event);
	        return;
		}
		
		//start the progress bar
		progressListener.showProgress();
		
		LoginData loginData = new LoginData();
		loginData.setEmailID("abc@xyz.com");
		loginData.setPassword("aaaaaa");
		loginData.setFlag("2");
		loginData.setDeviceID("");
		loginData.setDeviceType("");
		
		getApi().login(loginData, new Callback<Response>() {
			
			@Override
			public void failure(RetrofitError error) {
				ApiErrorEvent event=new ApiErrorEvent(error);
		        // Post the event
		        bus.post(event);
			}

			@Override
			public void success(Response response, Response arg1) {
				handleResponse(response);
			}
		});
	}
	
	/**
	 * Handle login response, performs GSON parsing.
	 * @param response
	 */
	protected void handleResponse(Response response) {
		String jsonString = Utilities.parseTypedInput(response.getBody());
		if (jsonString == null)
			return;
		
		try {
			Gson gson = new Gson();
			LoginResponse loginResponse = gson.fromJson(jsonString, LoginResponse.class);
			ApiResultEvent event = new ApiResultEvent(loginResponse);
			String UserID = loginResponse.getUserLoginResult().getUserDetail()[0].getUserID();
			 // Post the event back to the Activity with login response.
			bus.post(event);
		}
		catch (Exception e) {
			ExceptionEvent event = new ExceptionEvent(e);
			// Post the event back to the activity with exception.
			bus.post(event);
		}
	}
	
	/**
	 * Create rest adapter.
	 * 
	 */
	private LoginRestApi getApi() {
		OkHttpClient okHttpClient = new OkHttpClient();
		RestAdapter restAdapter = new RestAdapter.Builder().
				setClient(new OkClient(okHttpClient)).setEndpoint(HOST_ADDRESS_URL)
				.setLogLevel(LogLevel.BASIC).build();
		return restAdapter.create(LoginRestApi.class);
	}
}
