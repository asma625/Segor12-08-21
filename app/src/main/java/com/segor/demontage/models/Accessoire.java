package com.segor.demontage.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Accessoire  implements Serializable {

    private Integer id;
    private String nom_accessoire;
    private String etat;
    private Reducteur reducteur = new Reducteur();
    private String commentaire;
    private String type;
    private String marque;
    private String caracteristique;
    private String autreCaracteristique;


    public JSONObject toJson() throws JSONException {

        JSONObject jsonAccessoire = new JSONObject();

        //jsonAccessoire.put("id", this.getId());
        jsonAccessoire.put("nomAccessoire", this.getNom_accessoire());
        jsonAccessoire.put("commentaireEtat", this.getCommentaire());
        jsonAccessoire.put("type", this.getType());
        jsonAccessoire.put("marque", this.getMarque());
        jsonAccessoire.put("caracteristique", this.getCaracteristique());
        jsonAccessoire.put("etat", this.getEtat());
        return jsonAccessoire;
    }

    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom_accessoire() {
        return nom_accessoire;
    }

    public void setNom_accessoire(String nom_accessoire) {
        this.nom_accessoire = nom_accessoire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Reducteur getReducteur() {
        return reducteur;
    }

    public void setReducteur(Reducteur reducteur) {
        this.reducteur = reducteur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCaracteristique() {
        return caracteristique;
    }

    public void setCaracteristique(String caracteristique) {
        this.caracteristique = caracteristique;
    }

    public String getAutreCaracteristique() {
        return autreCaracteristique;
    }

    public void setAutreCaracteristique(String autreCaracteristique) {
        this.autreCaracteristique = autreCaracteristique;
    }
}
