package com.retroeventbus.events;

/**
 * Reports Internet connectivity status.
 * 
 * @author kthakur
 * 
 * @version 0.1
 *
 */
public class NoInternetEvent {

	boolean value;

	public NoInternetEvent(boolean value) {
		this.value = value;
	}

	public boolean getException() {
		return value;
	}
}
