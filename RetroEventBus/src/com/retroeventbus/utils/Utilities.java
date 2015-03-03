package com.retroeventbus.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.mime.TypedInput;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This class define common utils which are used throughout the 
 * Application like Dialogs, Toasts, Internet checks etc.
 *  
 * @author kthakur
 * 
 * @version 0.1
 *
 */
public class Utilities {
	
	 /**
     * This method checks if the device has an active internet 
     * connection or not.
     * 
     * @param activity
     *          - Activity where this dialog will be shown
     * @return
     *      Returns true if there is internet connectivity
     */
    public static Boolean checkInternet(Context activity){
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }
        else if (netInfo != null && (netInfo.getState() == NetworkInfo.State.DISCONNECTED || netInfo.getState() == NetworkInfo.State.DISCONNECTING || netInfo.getState() == NetworkInfo.State.SUSPENDED || netInfo.getState() == NetworkInfo.State.UNKNOWN)){
            return false;
        }
        else{
            return false;
        }
    }
    
    /**
	 * Parse Retrofit TypedInput
	 * 
	 * @param body
	 *            TypedInput
	 * 
	 * @return parsed string or null
	 * */
	public static String parseTypedInput(TypedInput body) {
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();

		if (body == null)
			return null;

		try {
			reader = new BufferedReader(new InputStreamReader(body.in()));

			String line;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
}
