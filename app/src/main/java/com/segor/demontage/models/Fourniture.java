package com.segor.demontage.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Fourniture {

    private Integer id;
    private String nom_fourniture;
    private Integer quantite;
    private String reference;
    private Portee portee = new Portee();


    public String getNom_fourniture() {
        return nom_fourniture;
    }

    public void setNom_fourniture(String nom_fourniture) {
        this.nom_fourniture = nom_fourniture;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Integer getId() {
        return id;
    }


    public JSONObject toJson() throws JSONException {

        JSONObject JsonFourniture = new JSONObject();

        JsonFourniture.put("id", this.getId());
        JsonFourniture.put("nom_fourniture", this.getNom_fourniture());
        JsonFourniture.put("quantite", this.getQuantite());
        JsonFourniture.put("reference", this.getReference());
        JsonFourniture.put("portee",portee.toJson());
        return JsonFourniture;
    }
}
