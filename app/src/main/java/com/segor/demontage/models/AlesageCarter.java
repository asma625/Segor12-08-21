package com.segor.demontage.models;

import org.json.JSONException;
import org.json.JSONObject;

public class AlesageCarter {

    private Integer id;
    private String diametreAlesageCarter;
    private String nomAlesageCarter;
    private String type;
    private String norme;
    private Carter carter = new Carter();


    public JSONObject toJson() throws JSONException {

        JSONObject JsonAlesageCarter = new JSONObject();
        //JsonAlesageCarter.put("id", this.getId());
        JsonAlesageCarter.put("nomAlesage",this.getNomAlesageCarter());
        JsonAlesageCarter.put("diametre", this.getDiametreAlesageCarter());
        JsonAlesageCarter.put("type",this.getType());
        JsonAlesageCarter.put("norme",this.getNorme());
        JsonAlesageCarter.put("carter",carter.toJson());
        return JsonAlesageCarter;
    }
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

    public Carter getCarter() {
        return carter;
    }

    public void setCarter(Carter carter) {
        this.carter = carter;
    }

    public String getDiametreAlesageCarter() {
        return diametreAlesageCarter;
    }

    public void setDiametreAlesageCarter(String diametreAlesageCarter) {
        this.diametreAlesageCarter = diametreAlesageCarter;
    }

    public String getNorme() {
        return norme;
    }

    public void setNorme(String norme) {
        this.norme = norme;
    }

    public String getNomAlesageCarter() {
        return nomAlesageCarter;
    }

    public void setNomAlesageCarter(String nomAlesageCarter) {
        this.nomAlesageCarter = nomAlesageCarter;
    }

}
