package com.segor.demontage.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Portee {

    Integer id;
    String nomPortee;//portee, alesage, rainure ....
    float diametrePortee;
    String typePortee;//portée de joint , de roulement....
    Mobile mobile = new Mobile();
    List<Fourniture> fournitureList =  new ArrayList<>();



    public JSONObject toJson() throws JSONException {

        JSONObject JsonPortee = new JSONObject();

        JsonPortee.put("id", this.getId());
        JsonPortee.put("diametre_portee", this.getDiametrePortee());
        JsonPortee.put("type_portee", this.getTypePortee());


        JSONArray listeFournitures = new JSONArray();
        for (Fourniture fourniture :this.fournitureList){
            listeFournitures.put(fourniture.toJson());
        }
        JsonPortee.put("listeFournitures", fournitureList);

        return JsonPortee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomPortee() {
        return nomPortee;
    }

    public void setNomPortee(String nomPortee) {
        this.nomPortee = nomPortee;
    }

    public float getDiametrePortee() {
        return diametrePortee;
    }

    public void setDiametrePortee(float diametrePortee) {
        this.diametrePortee = diametrePortee;
    }

    public String getTypePortee() {
        return typePortee;
    }

    public void setTypePortee(String typePortee) {
        this.typePortee = typePortee;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public List<Fourniture> getFournitureList() {
        return fournitureList;
    }

    public void setFournitureList(List<Fourniture> fournitureList) {
        this.fournitureList = fournitureList;
    }
}
