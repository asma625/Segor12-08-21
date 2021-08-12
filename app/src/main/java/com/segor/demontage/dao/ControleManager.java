package com.segor.demontage.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.segor.demontage.controller.ConnexionLocalController;
import com.segor.demontage.models.Controle;

import java.util.ArrayList;
import java.util.List;

public class ControleManager {




    Context context;
    BddLocale bddLocale;

    public ControleManager(Context context) {
        this.context = context;
        this.bddLocale = ConnexionLocalController.getInstance(context);
    }


    public  void insertionControle(Controle controle){
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",controle.getId());
        cv.put("denomination",controle.getDenomination());
        cv.put("realise",controle.getRealise());
        cv.put("aRealise",controle.getaRealiser());
        sql.insert("Controle", null, cv);
        sql.close();
    }


    public  boolean updateControle(Controle controle){
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",controle.getId());
        cv.put("denomination",controle.getDenomination());
        cv.put("realise",controle.getRealise());
        cv.put("aRealise",controle.getaRealiser());
        int result = sql.update("Controle", cv, "id = ?", new String[]{String.valueOf(controle.getId())});
        if( result == -1){
            return false;
        }else{
            return true;
        }
    }


    public Controle getControle(){
        Controle controle = new Controle();
        String sqlReq = "SELECT * FROM Controle";
        Cursor cursor = bddLocale.getReadableDatabase().rawQuery(sqlReq, null);
        if(cursor.moveToFirst()) {
            controle.setId(cursor.getInt(0));
            controle.setDenomination(cursor.getString(1));
            controle.setRealise(cursor.getString(2));
            controle.setaRealiser(cursor.getString(3));
            return controle;
        }
        return null;
    }

    public List<Controle> getAllControle(){
        List<Controle> controleListliste = new ArrayList<>();
        String sqlReq = "SELECT * FROM Controle";
        Cursor cursor = bddLocale.getReadableDatabase().rawQuery(sqlReq, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Controle controle = new Controle();
            controle.setId(cursor.getInt(0));
            controle.setDenomination(cursor.getString(1));
            controle.setRealise(cursor.getString(2));
            controle.setaRealiser(cursor.getString(3));

            controleListliste.add(controle);

            cursor.moveToNext();
        }
        return controleListliste;
    }
}
