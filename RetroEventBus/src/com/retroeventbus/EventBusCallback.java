package com.retroeventbus;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Override "onSuccess" to get Results instead the success of interface.
 * Correspondingly when need to handle error override "onFailure".
 **/
public abstract class EventBusCallback<T> implements Callback<T> {

    private EventBus mBus;

    /**
     *  Default constructor
     *  uses the bus from API.netBus or it could be EventBus.getDefault()
     */
    public EventBusCallback()
    {
       this(EventBus.getDefault());
    }

    /**
     * Call this if you want to have a reference to the eventbus
     * @param _bus
     */
    public EventBusCallback(EventBus _bus)
    {
        mBus = _bus;
        mBus.register(this);
    }


    /** must override */
    abstract public void onSuccess(T obj);

    /** override in case we want to handle a api failure */
    public void onFailure(RetrofitError error){};


    /**
     * The event that gets called on success
     * @param obj
     */
    public void onEvent (T obj){
        onSuccess(obj);
        // And we unregister
        mBus.unregister(this);
        mBus = null;
    };

    /**
     * The event that gets called on failure
     * @param error
     */
    public void onEvent (RetrofitError error) {
        onFailure(error);
        // And we unregister
        mBus.unregister(this);
        mBus = null;
    }


    /** execute common functionality for all api calls */
    @Override
    final public void success(T obj, Response response) {
        /**
         * You can do here whatever you want with the response object
         */

        // Here we send the event
        mBus.post(obj);
    }
    
    /** execute common functionality for handling error from a api call */
    @Override
    final public void failure(RetrofitError error) {
        /**
         * You can do here whatever you want with the Retrofit error
         */

        // Here we send the event
        mBus.post(error);
    }

}