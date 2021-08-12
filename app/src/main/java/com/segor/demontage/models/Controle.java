package com.segor.demontage.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Controle implements Serializable {

    private Integer id;
    private String denomination;
    private Reducteur reducteur;
    private String realise;
    private String aRealiser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public JSONObject toJson() throws JSONException {

        JSONObject JsonControle = new JSONObject();
        JsonControle.put("nomControle", this.getDenomination());
        JsonControle.put("realise", this.getaRealiser());
        JsonControle.put("aRealiser", this.getRealise());

        return JsonControle;
    }

    public Reducteur getReducteur() {
        return reducteur;
    }

    public void setReducteur(Reducteur reducteur) {
        this.reducteur = reducteur;
    }

    public String getRealise() {
        return realise;
    }

    public void setRealise(String realise) {
        this.realise = realise;
    }

    public String getaRealiser() {
        return aRealiser;
    }

    public void setaRealiser(String aRealiser) {
        this.aRealiser = aRealiser;
    }
}
