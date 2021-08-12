package com.segor.demontage.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Mobile {

    private Integer id;
    private String type;
    private String durete;
    private String nom;
    private String nombreDentPignon;
    private String modulePignon;
    private String nombreDentRoue;
    private String moduleRoue;
    private String inclinaison;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDurete() {
        return durete;
    }

    public void setDurete(String durete) {
        this.durete = durete;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNombreDentPignon() {
        return nombreDentPignon;
    }

    public void setNombreDentPignon(String nombreDentPignon) {
        this.nombreDentPignon = nombreDentPignon;
    }

    public String getModulePignon() {
        return modulePignon;
    }

    public void setModulePignon(String modulePignon) {
        this.modulePignon = modulePignon;
    }

    public String getNombreDentRoue() {
        return nombreDentRoue;
    }

    public void setNombreDentRoue(String nombreDentRoue) {
        this.nombreDentRoue = nombreDentRoue;
    }

    public String getModuleRoue() {
        return moduleRoue;
    }

    public void setModuleRoue(String moduleRoue) {
        this.moduleRoue = moduleRoue;
    }

    public String getInclinaison() {
        return inclinaison;
    }

    public void setInclinaison(String inclinaison) {
        this.inclinaison = inclinaison;
    }

    public JSONObject toJson() throws JSONException {

        JSONObject JsonMobile = new JSONObject();

        JsonMobile.put("id", this.getId());
        JsonMobile.put("nomMobile", this.getNom());
        JsonMobile.put("type", this.getType());
        JsonMobile.put("durete", this.getDurete());

        return JsonMobile;
    }
}
