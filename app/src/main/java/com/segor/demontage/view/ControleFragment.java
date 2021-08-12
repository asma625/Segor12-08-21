package com.segor.demontage.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.segor.demontage.R;

import com.segor.demontage.dao.ControleManager;
import com.segor.demontage.models.Controle;

import java.util.ArrayList;
import java.util.List;

public class ControleFragment extends Fragment {


    private BottomAppBar bottomAppBarControle;
    private List<Controle> controleList = new ArrayList<>();
    //controleManager controleManager;
    private CheckBox checkBoxMagnetoRealise,
            checkBoxAlesageCarterRealise,
            checkBoxGeometrieCarterRealise,
            checkBoxEtanchiteRealise,
            checkBoxCarterSablerRealise,
            checkBoxMagnetoArealise,
            checkBoxAlesageCarterArealise,
            checkBoxGeometrieCarterArealise,
            checkBoxEtanchiteArealise,
            checkBoxCarterSablerArealise;


    public ControleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_controle, container, false);
        init(view);

        bottomAppBarControle.setOnMenuItemClickListener(itemMenu -> {
            if (itemMenu.getItemId() == R.id.item_camera) {

                ControleManager controleManager =  new ControleManager(this.getContext());
                if (controleManager.getAllControle().size() == 0) {
                    String realise = checkBoxMagnetoRealise.isChecked()  ? "true" : "false";
                    String aRealiser = checkBoxMagnetoArealise.isChecked()  ? "true" : "false";
                    controleManager.insertionControle(ajouterControle(1, "Magnéto",realise  , aRealiser));


                    realise =checkBoxAlesageCarterRealise.isChecked()  ? "true" : "false";
                    aRealiser =checkBoxAlesageCarterArealise.isChecked()  ? "true" : "false";
                    controleManager.insertionControle(ajouterControle(2, "Alésages carter",realise , aRealiser));


                    realise =checkBoxGeometrieCarterRealise.isChecked()  ? "true" : "false";
                    aRealiser =checkBoxGeometrieCarterArealise.isChecked()  ? "true" : "false";
                    controleManager.insertionControle(ajouterControle(3, "Géométrie carter", realise , aRealiser));




                    realise =checkBoxEtanchiteRealise.isChecked()  ? "true" : "false";
                    aRealiser =checkBoxEtanchiteArealise.isChecked()  ? "true" : "false";
                    controleManager.insertionControle(ajouterControle(4, "Etanchéité circuit", realise , aRealiser));




                    realise =checkBoxCarterSablerRealise.isChecked()  ? "true" : "false";
                    aRealiser =checkBoxCarterSablerArealise.isChecked()  ? "true" : "false";
                    controleManager.insertionControle(ajouterControle(5, "Carter à sabler",realise , aRealiser));




                } else {
                    String aRealise =checkBoxMagnetoArealise.isChecked()  ? "true" : "false";
                    String realise =checkBoxMagnetoRealise.isChecked()  ? "true" : "false";
                    controleManager.updateControle(ajouterControle(1, "Magnéto",realise , aRealise));


                    aRealise =checkBoxAlesageCarterArealise.isChecked()  ? "true" : "false";
                    realise =checkBoxAlesageCarterRealise.isChecked()  ? "true" : "false";
                    controleManager.updateControle(ajouterControle(2, "Alésages carter",realise , aRealise));


                    aRealise =checkBoxGeometrieCarterArealise.isChecked()  ? "true" : "false";
                    realise =checkBoxGeometrieCarterRealise.isChecked()  ? "true" : "false";
                    controleManager.updateControle(ajouterControle(3, "Géométrie carter", realise , aRealise));


                    aRealise =checkBoxEtanchiteArealise.isChecked()  ? "true" : "false";
                    realise =checkBoxEtanchiteRealise.isChecked()  ? "true" : "false";
                    controleManager.updateControle(ajouterControle(4, "Etanchéité circuit", realise , aRealise));



                    aRealise =checkBoxCarterSablerArealise.isChecked()  ? "true" : "false";
                    realise =checkBoxCarterSablerRealise.isChecked()  ? "true" : "false";
                    controleManager.updateControle(ajouterControle(5, "Carter à sabler",realise , aRealise));



                }

                return true;
            }
            return false;
        });
        return view;
    }

    private Controle ajouterControle(Integer id, String denomination , String realise ,String aRealiser) {
        Controle controle =  new Controle();
        controle.setId(id);
        controle.setDenomination(denomination);
        controle.setRealise(realise);
        controle.setaRealiser(aRealiser);

        return controle;

    }

    private void init(View view) {

        ControleManager controleManager =  new ControleManager(this.getContext());
        bottomAppBarControle = view.findViewById(R.id.bottomAppBar_edition_controle);
        checkBoxMagnetoRealise = view.findViewById(R.id.checkBox_magneto_realise);
        checkBoxAlesageCarterRealise = view.findViewById(R.id.checkBox_alesage_carter_realise);
        checkBoxGeometrieCarterRealise = view.findViewById(R.id.checkBox_geometrie_carter_realise);
        checkBoxEtanchiteRealise = view.findViewById(R.id.checkBox_etancheite_realise);
        checkBoxCarterSablerRealise = view.findViewById(R.id.checkBox_carter_sabler_realise);
        checkBoxMagnetoArealise = view.findViewById(R.id.checkBox_magneto_a_realise);
        checkBoxAlesageCarterArealise = view.findViewById(R.id.checkBox_alesage_carter_a_realise);
        checkBoxGeometrieCarterArealise = view.findViewById(R.id.checkBox_geometrie_carter_a_realise);
        checkBoxEtanchiteArealise = view.findViewById(R.id.checkBox_etancheite_a_realise);
        checkBoxCarterSablerArealise = view.findViewById(R.id.checkBox_carter_sabler_a_realise);

        controleList = controleManager.getAllControle();

        if (controleList.size() != 0) {
            if(controleList.get(0).getRealise().equals("true") )
                checkBoxMagnetoRealise.setChecked(true);
            if(controleList.get(0).getaRealiser().equals("true") )
                checkBoxMagnetoArealise.setChecked(true);

            if(controleList.get(1).getRealise().equals("true"))
                checkBoxAlesageCarterRealise.setChecked(true);
            if(controleList.get(1).getaRealiser().equals("true") )
                checkBoxAlesageCarterArealise.setChecked(true);

            if(controleList.get(2).getRealise().equals("true") )
                checkBoxGeometrieCarterRealise.setChecked(true);
            if(controleList.get(2).getaRealiser().equals("true") )
                checkBoxGeometrieCarterArealise.setChecked(true);


            if(controleList.get(3).getRealise().equals("true"))
                checkBoxEtanchiteRealise.setChecked(true);
            if(controleList.get(3).getaRealiser().equals("true") )
                checkBoxEtanchiteArealise.setChecked(true);


            if(controleList.get(4).getRealise().equals("true"))
                checkBoxCarterSablerRealise.setChecked(true);
            if(controleList.get(4).getaRealiser().equals("true") )
                checkBoxCarterSablerArealise.setChecked(true);

        }

    }

    @Override
    public void onPause() {
        super.onPause();
        ControleManager controleManager = new ControleManager(this.getContext());
        if (controleManager.getAllControle().size() == 0) {
            String realise = checkBoxMagnetoRealise.isChecked()  ? "true" : "false";
            String aRealiser = checkBoxMagnetoArealise.isChecked()  ? "true" : "false";
            controleManager.insertionControle(ajouterControle(1, "Magnéto",realise  , aRealiser));


            realise = checkBoxAlesageCarterRealise.isChecked()  ? "true" : "false";
            aRealiser = checkBoxAlesageCarterArealise.isChecked()  ? "true" : "false";
            controleManager.insertionControle(ajouterControle(2, "Alésages carter",realise , aRealiser));


            realise = checkBoxGeometrieCarterRealise.isChecked()  ? "true" : "false";
            aRealiser = checkBoxGeometrieCarterArealise.isChecked()  ? "true" : "false";
            controleManager.insertionControle(ajouterControle(3, "Géométrie carter", realise , aRealiser));




            realise = checkBoxEtanchiteRealise.isChecked()  ? "true" : "false";
            aRealiser = checkBoxEtanchiteArealise.isChecked()  ? "true" : "false";
            controleManager.insertionControle(ajouterControle(4, "Etanchéité circuit", realise , aRealiser));




            realise = checkBoxCarterSablerRealise.isChecked()  ? "true" : "false";
            aRealiser = checkBoxCarterSablerArealise.isChecked()  ? "true" : "false";
            controleManager.insertionControle(ajouterControle(5, "Carter à sabler",realise , aRealiser));




        } else {
            String aRealiser =checkBoxMagnetoArealise.isChecked()  ? "true" : "false";
            String realise =checkBoxMagnetoRealise.isChecked()  ? "true" : "false";
            controleManager.updateControle(ajouterControle(1, "Magnéto",realise , aRealiser));


            aRealiser =checkBoxAlesageCarterArealise.isChecked()  ? "true" : "false";
            realise =checkBoxAlesageCarterRealise.isChecked()  ? "true" : "false";
            controleManager.updateControle(ajouterControle(2, "Alésages carter",realise , aRealiser));


            aRealiser =checkBoxGeometrieCarterArealise.isChecked()  ? "true" : "false";
            realise =checkBoxGeometrieCarterRealise.isChecked()  ? "true" : "false";
            controleManager.updateControle(ajouterControle(3, "Géométrie carter", realise , aRealiser));


            aRealiser =checkBoxEtanchiteArealise.isChecked()  ? "true" : "false";
            realise =checkBoxEtanchiteRealise.isChecked()  ? "true" : "false";
            controleManager.updateControle(ajouterControle(4, "Etanchéité circuit", realise , aRealiser));



            aRealiser =checkBoxCarterSablerArealise.isChecked()  ? "true" : "false";
            realise =checkBoxCarterSablerRealise.isChecked()  ? "true" : "false";
            controleManager.updateControle(ajouterControle(5, "Carter à sabler",realise , aRealiser));

        }
    }
}