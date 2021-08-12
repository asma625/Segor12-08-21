package com.segor.demontage.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.segor.demontage.controller.ConnexionLocalController;
import com.segor.demontage.models.Reducteur;

public class ReducteurManager {

    Context context;
    BddLocale bddLocale;

    public ReducteurManager(Context context) {
        this.context = context;
        this.bddLocale = ConnexionLocalController.getInstance(context);
    }


//insertion des informations liées aux réducteur
    public boolean insertInformationReducteur(Reducteur reducteur) {
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", reducteur.getId());
        cv.put("num_offre", reducteur.getNumOffre());
        cv.put("constructeur", reducteur.getConstructeur());
        cv.put("type_reducteur", reducteur.getType_reducteur());
        cv.put("N_Serie", reducteur.getN_Serie());
        cv.put("annee_fab", reducteur.getAnnee_fab());
        cv.put("rapport_i", reducteur.getRapport_i());
        cv.put("type_moteur", reducteur.getType_moteur());
        cv.put("client", reducteur.getClient());
        cv.put("date_recu", reducteur.getDate_recu());
        cv.put("cde_expertise", reducteur.getCde_expertise());
        cv.put("code_expertise", reducteur.getCode_expertise());
        cv.put("cde_segor", reducteur.getCde_segor());
        cv.put("nom_demonteur", reducteur.getNom_demonteur());
        cv.put("poid", reducteur.getPoids());
        cv.put("encombrement", reducteur.getEncombrement());
        cv.put("chassis", reducteur.getChassis());
        cv.put("reducteur_livre", reducteur.getReducteur_livre());
        cv.put("type_lubrification", reducteur.getType_lubrification());
        cv.put("quantite", reducteur.getQuantite());
        cv.put("viscosite", reducteur.getViscosite());
        cv.put("quantite_graisse", reducteur.getQuantite_graisse());
        long result = sql.insert("Reducteur", null, cv);
        //sql.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }
    //récupérer des informations liées à un  réducteur spécifique.
    public Reducteur getReducteurById(int id) {
        Reducteur reducteur = new Reducteur();
        String sqlReq = "SELECT * FROM Reducteur where id = " + id;
       // BddLocale bddLocale = ConnexionLocalController.getInstance(context);
        Cursor cursor = bddLocale.getReadableDatabase().rawQuery(sqlReq, null);

        if (cursor.moveToFirst()) {
            reducteur.setId(cursor.getInt(0));
            reducteur.setConstructeur(cursor.getString(1));
            reducteur.setType_reducteur(cursor.getString(2));
            reducteur.setN_Serie(cursor.getString(3));
            reducteur.setAnnee_fab(cursor.getString(4));
            reducteur.setRapport_i(cursor.getString(5));
            reducteur.setType_moteur(cursor.getString(6));
            reducteur.setClient(cursor.getString(7));
            reducteur.setDate_recu(cursor.getString(8));
            reducteur.setCde_expertise(cursor.getString(9));
            reducteur.setCode_expertise(cursor.getString(10));
            reducteur.setCde_segor(cursor.getString(11));
            reducteur.setNom_demonteur(cursor.getString(12));
            reducteur.setPoids(cursor.getString(13));
            reducteur.setEncombrement(cursor.getString(14));
            reducteur.setChassis(cursor.getString(15));
            reducteur.setReducteur_livre(cursor.getString(16));
            reducteur.setType_lubrification(cursor.getString(17));
            reducteur.setQuantite(cursor.getString(18));
            reducteur.setViscosite(cursor.getString(19));
            reducteur.setQuantite_graisse(cursor.getString(20));
            reducteur.setNumOffre(cursor.getString(21));
            return reducteur;
        }
        return null;
    }



//modifier toutes les informations liées aux réducteur
    public boolean updateInformationReducteur(Reducteur reducteur) {
        //BddLocale bddLocale = ConnexionLocalController.getInstance(context);
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", reducteur.getId());
        cv.put("num_offre", reducteur.getNumOffre());
        cv.put("constructeur", reducteur.getConstructeur());
        cv.put("type_reducteur", reducteur.getType_reducteur());
        cv.put("N_Serie", reducteur.getN_Serie());
        cv.put("annee_fab", reducteur.getAnnee_fab());
        cv.put("rapport_i", reducteur.getRapport_i());
        cv.put("type_moteur", reducteur.getType_moteur());
        cv.put("client", reducteur.getClient());
        cv.put("date_recu", reducteur.getDate_recu());
        cv.put("cde_expertise", reducteur.getCde_expertise());
        cv.put("code_expertise", reducteur.getCode_expertise());
        cv.put("cde_segor", reducteur.getCde_segor());
        cv.put("nom_demonteur", reducteur.getNom_demonteur());
        cv.put("poid", reducteur.getPoids());
        cv.put("encombrement", reducteur.getEncombrement());
        cv.put("chassis", reducteur.getChassis());
        cv.put("reducteur_livre", reducteur.getReducteur_livre());
        cv.put("type_lubrification", reducteur.getType_lubrification());
        cv.put("quantite", reducteur.getQuantite());
        cv.put("viscosite", reducteur.getViscosite());
        cv.put("quantite_graisse", reducteur.getQuantite_graisse());
        int result = sql.update("Reducteur", cv, "id = ?", new String[]{String.valueOf(reducteur.getId())});
        sql.close();
        return  (result != -1) ;
    }


//récuperer toutes les réducteurs de la base de données.
    public Reducteur getReducteur(){
        Reducteur reducteur = new Reducteur();
        String sqlReq = "SELECT * FROM Reducteur";
        //BddLocale bddLocale = ConnexionLocalController.getInstance(context);
        Cursor cursor = bddLocale.getReadableDatabase().rawQuery(sqlReq, null);
        if(cursor.moveToFirst()) {
            reducteur.setId(cursor.getInt(0));
            reducteur.setConstructeur(cursor.getString(1));
            reducteur.setType_reducteur(cursor.getString(2));
            reducteur.setN_Serie(cursor.getString(3));
            reducteur.setAnnee_fab(cursor.getString(4));
            reducteur.setRapport_i(cursor.getString(5));
            reducteur.setType_moteur(cursor.getString(6));
            reducteur.setClient(cursor.getString(7));
            reducteur.setDate_recu(cursor.getString(8));
            reducteur.setCde_expertise(cursor.getString(9));
            reducteur.setCode_expertise(cursor.getString(10));
            reducteur.setCde_segor(cursor.getString(11));
            reducteur.setNom_demonteur(cursor.getString(12));
            reducteur.setPoids(cursor.getString(13));
            reducteur.setEncombrement(cursor.getString(14));
            reducteur.setChassis(cursor.getString(15));
            reducteur.setReducteur_livre(cursor.getString(16));
            reducteur.setType_lubrification(cursor.getString(17));
            reducteur.setQuantite(cursor.getString(18));
            reducteur.setViscosite(cursor.getString(19));
            reducteur.setQuantite_graisse(cursor.getString(20));
            reducteur.setNumOffre(cursor.getString(21));
            return reducteur;
        }
        return null;
    }

//supprimer un réducteur de la base de donnée en passant son id en pramétre.
    public boolean deleteInformationReducteur(int id){
        //BddLocale bddLocale = ConnexionLocalController.getInstance(context);
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        sql.delete("Reducteur", "id = "+id, null);
        return true;
    }







}
