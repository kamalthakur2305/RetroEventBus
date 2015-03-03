package com.retroeventbus.events;

/**
 * Reports exception during API calls.
 * 
 * @author kthakur
 * 
 * @version 0.1
 *
 */
public class ExceptionEvent {
	
	Exception exception;
    
    public ExceptionEvent(Exception exception){          
        this.exception = exception;
    }
     
    public Exception getException(){
        return exception;
    }

}
