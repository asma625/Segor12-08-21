package com.segor.demontage.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.segor.demontage.controller.ConnexionLocalController;
import com.segor.demontage.models.AlesageCarter;

import java.util.ArrayList;
import java.util.List;

public class AlesagesCarterManager {



    Context context;
    BddLocale bddLocale;

    public AlesagesCarterManager(Context context) {
        this.context = context;
        this.bddLocale = ConnexionLocalController.getInstance(context);
    }
//insertion des informations liées aux AlesageCarter

    public  boolean insertionAlesageCarter(AlesageCarter alesageCarter){
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom_alesageCarter", alesageCarter.getNomAlesageCarter());
        cv.put("diametre_alesage_carter", alesageCarter.getDiametreAlesageCarter());
        cv.put("type", alesageCarter.getType());
        cv.put("norme", alesageCarter.getNorme());

        long result = sql.insert("AlesageCarter", null, cv);
        //sql.close();
        if( result == -1){
            return false;
        }else{
            return true;
        }

    }

    //modifier toutes les informations liées aux AlesageCarters
    public void updateAlesageCarter(AlesageCarter alesageCarter){
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom_alesageCarter", alesageCarter.getNomAlesageCarter());
        cv.put("diametre_alesage_carter", alesageCarter.getDiametreAlesageCarter());
        cv.put("type", alesageCarter.getType());
        cv.put("norme", alesageCarter.getNorme());
        sql.update("AlesageCarter", cv, "id = ?", new String[]{String.valueOf(alesageCarter.getId())});
    }


    public List<AlesageCarter> getAllAlesageCarters(){
        List<AlesageCarter> AlesageCarterList = new ArrayList<>();

        String sqlReq = "SELECT * FROM AlesageCarter";
        Cursor cursor = bddLocale.getReadableDatabase().rawQuery(sqlReq, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AlesageCarter AlesageCarter = new AlesageCarter();

            AlesageCarter.setId(cursor.getInt(0));
            AlesageCarter.setNomAlesageCarter(cursor.getString(1));
            AlesageCarter.setType(cursor.getString(2));
            AlesageCarter.setDiametreAlesageCarter(cursor.getString(3));
            AlesageCarter.setNorme(cursor.getString(4));

            AlesageCarterList.add(AlesageCarter);
            cursor.moveToNext();
        }
        return AlesageCarterList;
    }



    //supprimer un AlesageCarter de la base de donnée en passant son id en pramétre.
    public boolean deleteAlesageCarter(int id){
        // String sqlReq = "DELETE FROM AlesageCarter WHERE id = "+id;
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        sql.delete("AlesageCarter", "id = "+id, null);
        return true;
    }





}
