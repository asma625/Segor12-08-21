package com.segor.demontage.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Tache  implements Serializable {

    private Integer id;
    private String designation;
    private String temps;
    //Integer id_reducteur;//
    private Reducteur reducteur = new Reducteur();



    public JSONObject toJson() throws JSONException {
        JSONObject JsonTache = new JSONObject();
        JsonTache.put("nomTache", this.getDesignation());
        JsonTache.put("tempsTache", this.getTemps());

        return JsonTache;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public Reducteur getReducteur() {
        return reducteur;
    }

    public void setReducteur(Reducteur reducteur) {
        this.reducteur = reducteur;
    }
}
