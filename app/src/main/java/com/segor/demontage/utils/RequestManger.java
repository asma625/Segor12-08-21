package com.segor.demontage.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public final class RequestManger {

    private static RequestManger instance = null;
    private Context context;
    private RequestQueue requestQueue;


    private RequestManger(Context context){
        this.context = context;
        this.requestQueue = getRequestQueue();

    }
    private  RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }


    public static RequestManger getInstance(Context context){
        if(instance == null){
            instance = new RequestManger(context);
        }
        return instance;

    }

    public void addToRequestQueur(Request request){
        getRequestQueue().add(request);
    }


}
