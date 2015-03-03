package com.retroeventbus.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.retroeventbus.R;
import com.retroeventbus.events.ApiErrorEvent;
import com.retroeventbus.events.ApiResultEvent;
import com.retroeventbus.events.ExceptionEvent;
import com.retroeventbus.events.NoInternetEvent;
import com.retroeventbus.listeners.ProgressListener;
import com.retroeventbus.pojos.LoginResponse;
import com.retroeventbus.utils.ProgressUtility;
import com.retroeventbus.web.LoginApi;

import de.greenrobot.event.EventBus;

/**
 * Defines the user interface for user login.
 * 
 * @author kthakur
 * 
 * @version 0.1
 *
 */
public class LoginActivity extends ActionBarActivity implements ProgressListener{

	EventBus bus = EventBus.getDefault();
	
	@InjectView(R.id.button1) Button start_button;
	@InjectView(R.id.textView1)TextView textView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ButterKnife.inject(this);
        
        //register EventBus
        bus.register(this);
    }
    
    @OnClick(R.id.button1)
    public void start() {
    	// just an example 
        new LoginApi(LoginActivity.this,LoginActivity.this);
    }

	@Override
	public void showProgress() {
		ProgressUtility.showProgress(LoginActivity.this, "Please wait..");
	}
	
	/**
	 * Called when the bus posts an event for no Internet connection.
	 * @param event
	 */
	public void onEvent(NoInternetEvent  event) {
		dismissProgress();
	}
	
	/**
	 * Called when the bus posts an event for successful result.
	 * 
	 * <br>Call onEvent: Called in the same thread (default)
	 * <br>Call onEventMainThread: Called in Android UI's main thread
	 * <br>Call onEventBackgroundThread: Called in the background thread
	 * <br>Call onEventAsync: Called in a separate thread
	 * 
	 * @param event
	 */
	public void onEvent(ApiResultEvent  event) {
		dismissProgress();
		LoginResponse response = (LoginResponse) event.getResponse();
		textView.setText("User Id: "+response.getUserLoginResult().getUserDetail()[0].getUserID());
	}
	
	/**
	 * Called when the bus posts an event for service failure.
	 * @param event
	 */
	public void onEvent(ApiErrorEvent event) {
		dismissProgress();
		
		Log.d("ApiErrorEvent", ""+event.getError());
	}
	
	/**
	 * Called when the bus posts an event for exception while GSON parsing.
	 * @param event
	 */
	public void onEvent(ExceptionEvent event) {
		dismissProgress();
	}
	
	/**
	 * Dismiss progress dialog
	 */
	public void dismissProgress(){
		if(ProgressUtility.isShowingProgress()){
			ProgressUtility.dismissProgress();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//unregister EventBus
		bus.unregister(this);
	}
	
}
