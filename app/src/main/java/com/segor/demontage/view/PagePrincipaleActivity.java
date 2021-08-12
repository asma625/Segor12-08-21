package com.segor.demontage.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.segor.demontage.R;
import com.segor.demontage.controller.ConnexionLocalController;
import com.segor.demontage.dao.BddLocale;

public class PagePrincipaleActivity extends AppCompatActivity {

    private Button btnNouveauDevis;
    private Button btnDevisEnCours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_principale);
        init();

    }


    public void init() {
        btnNouveauDevis = findViewById(R.id.button_nouveau_devis_demontage);
        btnDevisEnCours = findViewById(R.id.button_devis_demontage_en_cours);
        btnNouveauDevis.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.alerte_nouveau_devis_reparation);
            Button buttonConfirmer =  dialog.findViewById(R.id.button_photo_accessoire);
            Button buttonAnnuler =  dialog.findViewById(R.id.button_annule_alerte_nouveau_devis_reducteur);
            dialog.show();
            buttonAnnuler.setOnClickListener(v1 -> dialog.dismiss());

            buttonConfirmer.setOnClickListener(v2 ->
            {
                BddLocale bddLocale  = ConnexionLocalController.getInstance(this);
                this.deleteDatabase(bddLocale.getDatabaseName());
                bddLocale.close();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            });






        });
        btnDevisEnCours.setOnClickListener(v3-> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);});
      /*  Reducteur reducteur =  new Reducteur();
        reducteur.setConstructeur("Test");
        reducteur.setType_reducteur("BT 125");
        reducteur.setN_Serie("5187982");
        reducteur.setAnnee_fab("2018");
        reducteur.setRapport_i(86.5f);
        reducteur.setType_moteur("cc");


        Accessoire accessoire = new Accessoire();
        accessoire.setNom_accessoire("Moteur");
        BddLocale bddLocale =  new BddLocale(this);
        bddLocale.insertInformationReducteur(reducteur);
        bddLocale.insertionAccessoir(accessoire);

        AlesageArbre alesage_arbre =  new AlesageArbre();
        alesage_arbre.setDiametre_alesage_arbre(78.5f);
        bddLocale.insertionAlesage_arbre(alesage_arbre);

        Mobile mobile = new Mobile();
        mobile.setType("GV");
        bddLocale.insertionMobile(mobile);



        AlesageCarter alesage_carter =  new AlesageCarter();
        alesage_carter.setDiametre_alesage_carter(45.1f);
        bddLocale.insertionAlesage_carter(alesage_carter);


        Controle controle = new Controle();
        controle.setDenomination("Magnéto");
        bddLocale.insertionControle(controle);

        Etat etat = new Etat();
        etat.setId(1);
        etat.setDesignation("Bon");
        bddLocale.insertionEtat(etat);

        Engrenage engrenage = new Engrenage();

        engrenage.setDurete(15);
        engrenage.setFonctionnement("pignon");
        engrenage.setModule(158);
        engrenage.setNombre_dent(158);
        bddLocale.insertionEngrenage(engrenage);

        Arbre arbre = new Arbre();
        arbre.setNom_arbre("arbre creux");
        bddLocale.insertionArbre(arbre);


        Fourniture fourniture =  new Fourniture();
        fourniture.setNom_fourniture("piéce");
        fourniture.setQuantite(150);
        fourniture.setReference("acbd185");
        bddLocale.insertionFourniture(fourniture);


        Portee portee = new Portee();
        portee.setDiametre_portee(15.5f);
        portee.setType_portee("portée d'accouplement");
        bddLocale.insertionPortee(portee);

        PetitesFournitures petitesFournitures =  new PetitesFournitures();

        petitesFournitures.setMatiere("acier");
        bddLocale.insertionPetites_fourniture(petitesFournitures);



        Tache tache = new Tache();
        tache.setDesignation("Demontage");
        tache.setTemps("4H");
        bddLocale.insertionTache(tache);

*/




    }

}