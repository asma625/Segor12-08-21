package com.segor.demontage.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.segor.demontage.models.Controle;
import com.segor.demontage.models.Portee;
import com.segor.demontage.models.Tache;

import java.util.ArrayList;
import java.util.List;


public class BddLocale extends SQLiteOpenHelper {

    SQLiteOpenHelper sql ;
    private static final String DATA_BASE_NAME ="BasedeDonneesSegor1";
    private static final int DATA_BASE_VERSION =3;


    public BddLocale(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }


    public static String getDataBaseName() {
        return DATA_BASE_NAME;
    }

    public static int getDataBaseVersion() {
        return DATA_BASE_VERSION;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableReducteur ="CREATE TABLE Reducteur(id INTEGER PRIMARY KEY ,constructeur TEXT, type_reducteur TEXT, N_Serie TEXT, annee_fab TEXT," +
                "rapport_i TEXT , type_moteur TEXT  , client TEXT, date_recu TEXT, cde_expertise TEXT, code_expertise TEXT, cde_segor TEXT," +
                "nom_demonteur TEXT, poid TEXT, encombrement TEXT, chassis TEXT, reducteur_livre TEXT, type_lubrification TEXT, quantite TEXT," +
                "viscosite TEXT,  quantite_graisse TEXT,num_offre TEXT)";

        String tableAccessoire = "CREATE TABLE Accessoire(id INTEGER  PRIMARY KEY AUTOINCREMENT, caracteristique TEXT,autre_caracteristique TEXT,marque TEXT ,type TEXT  , etat TEXT, nom_accessoire TEXT, commentaire TEXT )";


        String tableFourniture =" CREATE TABLE Fourniture(id INTEGER PRIMARY KEY AUTOINCREMENT , nom_fourniture TEXT , quantite TEXT , reference TEXT , portee_id Text, FOREIGN KEY (portee_id)  REFERENCES portee(id) )";

        String tablePortee = "CREATE TABLE portee(id  INTEGER PRIMARY KEY AUTOINCREMENT, nom_portee TEXT, diametre_portee TEXT, norme TEXT , type_portee  TEXT ,mobile_id Text , FOREIGN KEY (mobile_id)  REFERENCES Mobile(id))";

        String tableAlesageCarter =  "CREATE  TABLE AlesageCarter(id INTEGER  PRIMARY KEY  AUTOINCREMENT, nom_alesageCarter TEXT, type TEXT, diametre_alesage_carter TEXT,norme TEXT) ";

        String tableMobile = "CREATE  TABLE Mobile(id INTEGER  PRIMARY KEY  AUTOINCREMENT, nom TEXTE , durete TEXT, type TEXT,nombreDentRoue TEXT, nombreDentPignon TEXT ,  moduleRoue TEXT," +
                "  modulePignon TEXT, inclinaison TEXT)";

        String tablePetitesFournitures ="CREATE TABLE PetitesFournitures( id INTEGER  PRIMARY KEY  AUTOINCREMENT, nom_petite_fourniture TEXT, matiere TEXT,quantite Text, reference TEXT)";

        String tableTache ="CREATE TABLE Tache( id INTEGER  PRIMARY KEY  , designation TEXT, temps TEXT )";

        String tableControle ="CREATE TABLE Controle( id INTEGER  PRIMARY KEY  , denomination TEXT , realise TEXT, aRealise TEXT)";

        String tableCarter =  "CREATE TABLE Carter(id INTEGER  PRIMARY KEY,longueur TEXT, largeur TEXT, hauteur TEXT ,masse TEXT)";

        db.execSQL(tableReducteur);
        db.execSQL(tableAccessoire);
        db.execSQL(tableControle);
        db.execSQL(tableFourniture);
        db.execSQL(tablePortee);
        db.execSQL(tableAlesageCarter);
        db.execSQL(tableCarter);
        db.execSQL(tablePetitesFournitures);
        db.execSQL(tableMobile);
        db.execSQL(tableTache);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlDropAccessoire = "DROP TABLE IF EXISTS Accessoire";
        String sqlDropReducteur = "DROP TABLE IF EXISTS Reducteur";

        String sqlDropControle = "DROP TABLE IF EXISTS Controle";


        String sqlDropPortee = "DROP TABLE IF EXISTS Portee";
        String sqlDropFourniture = "DROP TABLE IF EXISTS Fourniture";

        String sqlDropAlesageCarter = "DROP TABLE IF EXISTS AlesageCarter";
        String sqlDropMobile = "DROP TABLE IF EXISTS Mobile";
        String sqlDropPetitesFournitures = "DROP TABLE IF EXISTS PetitesFournitures";
        String sqlDropTache = "DROP TABLE IF EXISTS Tache";
        String sqlDropCarter = "DROP TABLE IF EXISTS Carter";

        db.execSQL(sqlDropAccessoire);
        db.execSQL(sqlDropReducteur);
        db.execSQL(sqlDropControle);
        db.execSQL(sqlDropPortee);
        db.execSQL(sqlDropFourniture);
        db.execSQL(sqlDropAlesageCarter);
        db.execSQL(sqlDropMobile);
        db.execSQL(sqlDropPetitesFournitures);
        db.execSQL(sqlDropTache);
        db.execSQL(sqlDropCarter);


        this.onCreate(db);

    }


    public  void insertionPortee(Portee portee){
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("diametre_portee",portee.getDiametrePortee());
        cv.put("type_portee",portee.getTypePortee());
        sql.insert("Portee", null, cv);
        sql.close();
    }





}
