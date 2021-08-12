package com.segor.demontage.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.segor.demontage.controller.ConnexionLocalController;
import com.segor.demontage.models.PetitesFournitures;

import java.util.ArrayList;
import java.util.List;

public class VisserieManager {

    Context context;
    BddLocale bddLocale;

    public VisserieManager(Context context) {
        this.context = context;
        this.bddLocale = ConnexionLocalController.getInstance(context);
    }

//insertion des informations liées aux Petites fournitures
      public  boolean insertionPetitesFourniture(PetitesFournitures petitesFournitures){
                SQLiteDatabase sql = bddLocale.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("id",petitesFournitures.getId());
                cv.put("nom_petite_fourniture",petitesFournitures.getNomPetiteFourniture());
                cv.put("quantite",petitesFournitures.getQuantite());
                cv.put("reference",petitesFournitures.getReference());
                cv.put("matiere",petitesFournitures.getMatiere());
                long result = sql.insert("PetitesFournitures", null, cv);
                return( result == -1);

    }
    public  boolean updatePetitesFourniture(PetitesFournitures petitesFournitures){
                SQLiteDatabase sql = bddLocale.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put("id",petitesFournitures.getId());
                cv.put("nom_petite_fourniture",petitesFournitures.getNomPetiteFourniture());
                cv.put("quantite",petitesFournitures.getQuantite());
                cv.put("reference",petitesFournitures.getReference());
                cv.put("matiere",petitesFournitures.getMatiere());
                long result = sql.update("PetitesFournitures",  cv ,"id = ?", new String[]{String.valueOf(petitesFournitures.getId())});
                System.out.println(petitesFournitures.getMatiere());
                return( result == -1);
    }



    public boolean deletePetitesFournitures(Integer id) {
        SQLiteDatabase sql =  bddLocale.getWritableDatabase();
        sql.delete("PetitesFournitures", "id = "+id, null);
        return true;
    }

    public List<PetitesFournitures> getAllPetitesFournitures() {
        List<PetitesFournitures> petitesFournituresList = new ArrayList<>();

        String sqlReq = "SELECT * FROM PetitesFournitures";
        Cursor cursor = bddLocale.getReadableDatabase().rawQuery(sqlReq, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PetitesFournitures petitesFournitures = new PetitesFournitures();

            petitesFournitures.setId(cursor.getInt(0));
            petitesFournitures.setNomPetiteFourniture(cursor.getString(1));
            petitesFournitures.setMatiere(cursor.getString(2));
            petitesFournitures.setQuantite(cursor.getString(3));
            petitesFournitures.setReference(cursor.getString(4));

            petitesFournituresList.add(petitesFournitures);
            cursor.moveToNext();
        }

        return petitesFournituresList;



    }




}
