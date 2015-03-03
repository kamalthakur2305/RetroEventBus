/*package com.retroeventbus.web;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.OkClient;
import retrofit.client.Response;

import com.retroeventbus.events.ApiErrorEvent;
import com.retroeventbus.pojos.LoginData;
import com.retroeventbus.utils.Utilities;
import com.retroeventbus.web.UrlApi.LoginRestApi;
import com.squareup.okhttp.OkHttpClient;

import de.greenrobot.event.EventBus;

import android.app.Activity;


public class CallApi<T> {
	
	Activity activity;
	Object param_object;
	String method;
	Class<T> api_interface;
	String HOST_ADDRESS_URL;
	
	EventBus bus = EventBus.getDefault();
	
	public CallApi(Activity activity,Object param_object,String method, 
			Class<T> api_interface,String HOST_ADDRESS_URL){
		
		this.param_object = param_object;
		this.activity = activity;
		this.method = method;
		this.api_interface = api_interface;
		this.HOST_ADDRESS_URL = HOST_ADDRESS_URL;
		
		initRestAdapter();
		
	}

	*//**
	 * Initialize rest adapter.
	 *//*
	private void initRestAdapter() {
		OkHttpClient okHttpClient = new OkHttpClient();
		RestAdapter restAdapter = new RestAdapter.Builder().
				setClient(new OkClient(okHttpClient)).setEndpoint(HOST_ADDRESS_URL)
				.setLogLevel(LogLevel.BASIC).build();
		restAdapter.create(api_interface);
		
		loginRequest();
	}
	
	public interface  GetResult<ReturnType, ServiceClass>  {
	      ReturnType getResult(ServiceClass service);
	    }
	
	
	*//**
	 * Create login request.
	 * 
	 * @param loginData
	 * @param resultListner
	 * @param retrofitListner
	 *//*
	public void loginRequest() {
		
		
		if(!Utilities.checkInternet(activity)){
			resultListner.loginResult(""+0, null);
			return;
		}
		api_interface
		
		getApi().PostServiceInterface(param_object, new Callback<Response>() {

			@Override
			public void failure(RetrofitError error) {
				ApiErrorEvent event=new ApiErrorEvent(error);
		        // Post the event
		        bus.post(event);
			}

			@Override
			public void success(Response response, Response arg1) {
				if (response != null){
					
				}
					//handleLoginResponse(response);
			}
		});
	}
	
	private Class<T> getApi() {
		if (api_interface == null)
			initRestAdapter();
		return api_interface;
	}
}
*/