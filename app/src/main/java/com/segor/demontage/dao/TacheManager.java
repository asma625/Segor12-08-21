package com.segor.demontage.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.segor.demontage.controller.ConnexionLocalController;
import com.segor.demontage.models.Tache;

import java.util.ArrayList;
import java.util.List;

public class TacheManager {




    Context context;
    BddLocale bddLocale;

    public TacheManager(Context context) {
        this.context = context;
        this.bddLocale = ConnexionLocalController.getInstance(context);
    }

    public  boolean insertionTache(Tache tache){
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",tache.getId());
        cv.put("temps",tache.getTemps());
        cv.put("designation",tache.getDesignation());
        long result = sql.insert("Tache", null, cv);
        if( result == -1){
            return false;
        }else{
            return true;
        }
    }
    public  boolean updateTache(Tache tache){
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",tache.getId());
        cv.put("temps",tache.getTemps());
        cv.put("designation",tache.getDesignation());
        int result = sql.update("Tache", cv, "id = ?", new String[]{String.valueOf(tache.getId())});
        if( result == -1){
            return false;
        }else{
            return true;
        }
    }


    public Tache getTache(){
        Tache tache = new Tache();
        String sqlReq = "SELECT * FROM Tache";
        Cursor cursor = bddLocale.getReadableDatabase().rawQuery(sqlReq, null);
        if(cursor.moveToFirst()) {
            tache.setId(cursor.getInt(0));
            tache.setDesignation(cursor.getString(1));
            tache.setTemps(cursor.getString(2));
            return tache;
        }
        return null;
    }

    public List<Tache> getAllTache(){
        List<Tache> tacheliste = new ArrayList<>();
        String sqlReq = "SELECT * FROM Tache";
        Cursor cursor = bddLocale.getReadableDatabase().rawQuery(sqlReq, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tache tache = new Tache();
            tache.setId(cursor.getInt(0));
            tache.setDesignation(cursor.getString(1));
            tache.setTemps(cursor.getString(2));

            tacheliste.add(tache);
            System.out.println(tache.getDesignation());
            cursor.moveToNext();
        }
        return tacheliste;
    }


}
