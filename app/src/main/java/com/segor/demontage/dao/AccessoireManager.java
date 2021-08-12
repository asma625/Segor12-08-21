package com.segor.demontage.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.segor.demontage.controller.ConnexionLocalController;
import com.segor.demontage.models.Accessoire;

import java.util.ArrayList;
import java.util.List;

public class AccessoireManager {

    Context context;
    BddLocale bddLocale;

    public AccessoireManager(Context context) {
        this.context = context;
        this.bddLocale = ConnexionLocalController.getInstance(context);
    }
//insertion des informations liées aux accessoire

    public  boolean insertionAccessoire(Accessoire accessoire){
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",accessoire.getId());
        cv.put("nom_accessoire", accessoire.getNom_accessoire());
        cv.put("commentaire" , accessoire.getCommentaire());
        cv.put("caracteristique", accessoire.getCaracteristique());
        cv.put("autre_caracteristique" , accessoire.getAutreCaracteristique());
        cv.put("marque", accessoire.getMarque());
        cv.put("type" , accessoire.getType());
        cv.put("etat" , accessoire.getEtat());
        long result = sql.insert("Accessoire", null, cv);
        //sql.close();
        if( result == -1){
            return false;
        }else{
            return true;
        }

    }

    //modifier toutes les informations liées aux accessoires
    public void updateAccessoire(Accessoire accessoire){
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",accessoire.getId());
        cv.put("nom_accessoire", accessoire.getNom_accessoire());
        cv.put("commentaire" , accessoire.getCommentaire());
        cv.put("caracteristique", accessoire.getCaracteristique());
        cv.put("autre_caracteristique" , accessoire.getAutreCaracteristique());
        cv.put("marque", accessoire.getMarque());
        cv.put("type" , accessoire.getType());
        cv.put("etat" , accessoire.getEtat());
        sql.update("Accessoire", cv, "id = ?", new String[]{String.valueOf(accessoire.getId())});
    }


    public List<Accessoire> getAllAccessoires(){
        List<Accessoire> accessoireList = new ArrayList<>();

        String sqlReq = "SELECT * FROM Accessoire";
        Cursor cursor = bddLocale.getReadableDatabase().rawQuery(sqlReq, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Accessoire accessoire = new Accessoire();

            accessoire.setId(cursor.getInt(0));
            accessoire.setCaracteristique(cursor.getString(1));
            accessoire.setAutreCaracteristique(cursor.getString(2));
            accessoire.setMarque(cursor.getString(3));
            accessoire.setType(cursor.getString(4));
            accessoire.setEtat(cursor.getString(5));
            accessoire.setNom_accessoire(cursor.getString(6));
            accessoire.setCommentaire(cursor.getString(7));

            accessoireList.add(accessoire);
            cursor.moveToNext();
        }

        return accessoireList;
    }






    //supprimer un accessoire de la base de donnée en passant son id en pramétre.
    public boolean deleteAccessoire(int id){
        // String sqlReq = "DELETE FROM Accessoire WHERE id = "+id;
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        sql.delete("Accessoire", "id = "+id, null);
        return true;
    }












}
