package com.segor.demontage.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.segor.demontage.controller.ConnexionLocalController;
import com.segor.demontage.models.Mobile;

import java.util.ArrayList;
import java.util.List;

public class MobileManager {

    Context context;
    BddLocale bddLocale;

    public MobileManager(Context context) {
        this.context = context;
        this.bddLocale = ConnexionLocalController.getInstance(context);
    }
//insertion des informations liées aux mobiles

    public  boolean insertionMobile(Mobile mobile){
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",mobile.getId());
        cv.put("nom",mobile.getNom());
        cv.put("durete",mobile.getDurete());
        cv.put("type",mobile.getType());
        cv.put("nombreDentRoue",mobile.getNombreDentRoue());
        cv.put("nombreDentPignon",mobile.getNombreDentPignon());
        cv.put("moduleRoue",mobile.getModuleRoue());
        cv.put("modulePignon",mobile.getModulePignon());
        cv.put("inclinaison",mobile.getInclinaison());

        long result = sql.insert("Mobile", null, cv);
        //sql.close();
        if( result == -1){
            return false;
        }else{
            return true;
        }

    }

    //modifier toutes les informations liées aux accessoires
    public void updateMobile(Mobile mobile){
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",mobile.getId());
        cv.put("nom",mobile.getNom());
        cv.put("durete",mobile.getDurete());
        cv.put("type",mobile.getType());
        cv.put("nombreDentRoue",mobile.getNombreDentRoue());
        cv.put("nombreDentPignon",mobile.getNombreDentPignon());
        cv.put("moduleRoue",mobile.getModuleRoue());
        cv.put("modulePignon",mobile.getModulePignon());
        cv.put("inclinaison",mobile.getInclinaison());

        sql.update("Mobile", cv, "id = ?", new String[]{String.valueOf(mobile.getId())});
    }


    public List<Mobile> getAllMobile(){
        List<Mobile> mobileList = new ArrayList<>();

        String sqlReq = "SELECT * FROM Mobile";
        Cursor cursor = bddLocale.getReadableDatabase().rawQuery(sqlReq, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mobile mobile = new Mobile();

            mobile.setId(cursor.getInt(0));
            mobile.setNom(cursor.getString(1));
            mobile.setDurete(cursor.getString(2));
            mobile.setType(cursor.getString(3));
            mobile.setNombreDentRoue(cursor.getString(4));
            mobile.setNombreDentPignon(cursor.getString(5));
            mobile.setModuleRoue(cursor.getString(6));
            mobile.setModulePignon(cursor.getString(7));
            mobile.setInclinaison(cursor.getString(8));
            mobileList.add(mobile);
            cursor.moveToNext();
        }

        return mobileList;
    }



    //supprimer un accessoire de la base de donnée en passant son id en pramétre.
    public boolean deleteMobile(int id){
        SQLiteDatabase sql = bddLocale.getWritableDatabase();
        sql.delete("Mobile", "id = "+id, null);
        return true;
    }


}
