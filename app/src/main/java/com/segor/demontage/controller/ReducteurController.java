package com.segor.demontage.controller;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.segor.demontage.R;
import com.segor.demontage.dao.BddLocale;
import com.segor.demontage.models.Reducteur;
import com.segor.demontage.utils.RequestManger;
import com.segor.demontage.utils.StringRequestWithToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class ReducteurController  {

     Context context;
    private static ReducteurController instance=  null;
     BddLocale bddLocale = ConnexionLocalController.getInstance(context);


    public interface Save{
        void OnSave(String urlReducteur);
    }

    public static  ReducteurController getInstance(){
        if(instance == null){
            instance = new ReducteurController();
        }
        return instance;

    }

    public ReducteurController(){}


    public ReducteurController(Context context) {
        this.context = context;
    }

    public void save(Context context, Reducteur reducteur, Save saveListener) throws JSONException {
        String url = "reducteur/nouveau";


        JSONObject jsonBody = reducteur.toJson();

        StringRequestWithToken request = new StringRequestWithToken(
                context,
                Request.Method.POST,
                context.getResources().getString(R.string.url_spring)+url,
                urlReducteur ->{
                    saveListener.OnSave(urlReducteur);
                },
                messageErreur ->{
                    System.out.println(messageErreur);
                }

        ){
            @Override
            public byte[] getBody() throws AuthFailureError {
                return jsonBody.toString().getBytes(StandardCharsets.UTF_8);
            }
        };
          RequestManger.getInstance(context).addToRequestQueur(request);
    }

}
