package com.retroeventbus.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Common class for handling Progress Dialog tasks(i.e. Showing, dismissing,
 * currently showing)
 * 
 * @author kthakur
 * 
 * @version 0.1
 */
public class ProgressUtility {
	public static ProgressDialog progress_Dialog;
	static String progress_title = "drivermatics";
    public static AlertDialog.Builder alertDialogBuilder = null;
    public static AlertDialog alertDialog = null;
	/**
	 * A dialog showing a progress indicator and an optional text message or
	 * view. Only a text message or a view can be used at the same time.
	 * 
	 * The dialog can be made cancelable on back key press.
	 * 
	 * @param context
	 *            - context of the activity on which Progress dialog need to be
	 *            shown
	 * @param title
	 *            - title to be shown on Progress Dialog
	 * @param message
	 *            - message to shown in progress Dialog
	 */
	public static void showProgressCancelable(Context activity,String message) { 
		progress_Dialog = ProgressDialog.show(activity, progress_title, message, true, true);
	}
	
	
	
	/**
	 * A dialog showing a progress indicator and an optional text message or
	 * view. Only a text message or a view can be used at the same time.
	 * 
	 * The dialog can't be  cancelable on back key press.
	 * 
	 * @param context
	 *            - context of the activity on which Progress dialog need to be
	 *            shown
	 * @param title
	 *            - title to be shown on Progress Dialog
	 * @param message
	 *            - message to shown in progress Dialog
	 * @param Iscacelable
	 *            - If true the dialog can be made cancelable on back key press.
	 */
	public static void showProgress(Context activity,String message) {
		progress_Dialog = ProgressDialog.show(activity, progress_title, message, true);
	}
	/*public static void showProgress(Context context,String message, boolean Iscacelable) {
		try {
			progress_Dialog = ProgressDialog.show(context, progress_title, message, true,Iscacelable);
		} catch (Exception e) {
		}
		
	}*/

	/**
	 * Dismiss this dialog, removing it from the screen. This method can be
	 * invoked safely from any thread. Note that you should not override this
	 * method to do cleanup when the dialog is dismissed
	 */
	public static void dismissProgress() {
		try {
			progress_Dialog.dismiss();
		} catch (Exception e) {
		}
		
	}

	/**
	 *@return - 
	 *		Returns Whether the dialog is currently showing.
	 */
	public static boolean isShowingProgress() {
		if(progress_Dialog != null)
		return progress_Dialog.isShowing();
		else
	     return false;
	}
}
