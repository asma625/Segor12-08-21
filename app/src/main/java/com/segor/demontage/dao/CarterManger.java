package com.segor.demontage.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.segor.demontage.controller.ConnexionLocalController;
import com.segor.demontage.models.Carter;

public class CarterManger {


    Context context;
    BddLocale bddLocale;

    public CarterManger(Context context) {
        this.context = context;
        this.bddLocale = ConnexionLocalController.getInstance(context);
    }


    public  boolean insertionCarter(Carter carter){
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",carter.getId());
        cv.put("longueur",carter.getLongueur());
        cv.put("largeur",carter.getLargeur());
        cv.put("hauteur",carter.getHauteur());
        cv.put("masse",carter.getMasse());
        long result = sql.insert("Carter", null, cv);
        if( result == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean updateCarter(Carter carter){
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", carter.getId());
        cv.put("longueur",carter.getLongueur());
        cv.put("largeur",carter.getLargeur());
        cv.put("hauteur",carter.getHauteur());
        cv.put("masse",carter.getMasse());
        long result = sql.update("Carter", cv, "id = ?", new String[]{String.valueOf(carter.getId())});
        if( result == -1){
            return false;
        }else{
            return true;
    }}




    public Carter getCarter() {
        Carter carter = new Carter();
        String sqlReq = "SELECT * FROM Carter";
        Cursor cursor = bddLocale.getReadableDatabase().rawQuery(sqlReq, null);
        if(cursor.moveToFirst()) {
            carter.setId(cursor.getInt(0));
            carter.setHauteur(cursor.getString(3));
            carter.setLargeur(cursor.getString(2));
            carter.setLongueur(cursor.getString(1));
            carter.setMasse(cursor.getString(4));
            return carter;
        }
        return null;


    }
    public boolean deleteCarter(int id){
        // String sqlReq = "DELETE FROM Accessoire WHERE id = "+id;
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        sql.delete("Carter", "id = "+id, null);
        return true;
    }



}
