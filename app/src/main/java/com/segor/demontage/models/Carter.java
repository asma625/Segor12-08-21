package com.segor.demontage.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carter  implements Serializable {

    private Integer id;
    private String longueur;
    private String largeur;
    private String hauteur;
    private String masse;
    private List<AlesageCarter> alesageCarterList = new ArrayList<>();



    public JSONObject toJson() throws JSONException {
        JSONObject JsonCarter = new JSONObject();
        //JsonCarter.put("id", this.getId());
        JsonCarter.put("largeur", this.getLargeur());
        JsonCarter.put("hauteur", this.getHauteur());
        JsonCarter.put("longueur", this.getLongueur());
        JsonCarter.put("masse", this.getMasse());
        JSONArray listeAlesageCarter = new JSONArray();
        for (AlesageCarter alesageCarter : this.alesageCarterList) {
            listeAlesageCarter.put(alesageCarter.toJson());
        }
        JsonCarter.put("alesagesCarter", listeAlesageCarter);

        return JsonCarter;
    }


    public String getLongueur() {
        return longueur;
    }

    public void setLongueur(String longueur) {
        this.longueur = longueur;
    }

    public String getLargeur() {
        return largeur;
    }

    public void setLargeur(String largeur) {
        this.largeur = largeur;
    }

    public String getHauteur() {
        return hauteur;
    }

    public void setHauteur(String hauteur) {
        this.hauteur = hauteur;
    }

    public String getMasse() {
        return masse;
    }

    public void setMasse(String masse) {
        this.masse = masse;
    }

    public List<AlesageCarter> getAlesageCarterList() {
        return alesageCarterList;
    }

    public void setAlesageCarterList(List<AlesageCarter> alesageCarterList) {
        this.alesageCarterList = alesageCarterList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
