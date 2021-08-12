package com.segor.demontage.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.segor.demontage.R;
import com.segor.demontage.controller.ReducteurController;
import com.segor.demontage.dao.AccessoireManager;
import com.segor.demontage.dao.AlesagesCarterManager;
import com.segor.demontage.dao.CarterManger;
import com.segor.demontage.dao.ControleManager;
import com.segor.demontage.dao.MobileManager;
import com.segor.demontage.dao.ReducteurManager;
import com.segor.demontage.dao.TacheManager;
import com.segor.demontage.dao.VisserieManager;
import com.segor.demontage.models.Reducteur;

import org.json.JSONException;


public class SynchroFragment extends Fragment {

    ImageButton imageButtonSynchro;

    public SynchroFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_synchro, container, false);
        imageButtonSynchro = view.findViewById(R.id.button_synchro);
        imageButtonSynchro.setEnabled(true);
        imageButtonSynchro.setOnClickListener( v -> {
            imageButtonSynchro.setEnabled(false);
            imageButtonSynchro.setImageResource(R.drawable.ic_baseline_check_24);

            ReducteurManager reducteurManager =  new ReducteurManager(this.getContext());
            AlesagesCarterManager alesagesCarterManager = new AlesagesCarterManager(this.getContext());
            CarterManger carterManager = new CarterManger(this.getContext());
            AccessoireManager accessoireManagerSynchro =  new AccessoireManager(this.getContext());
            TacheManager tacheManager =  new TacheManager(this.getContext());
            VisserieManager visserieManager =  new VisserieManager(this.getContext());
            ControleManager controleManager = new ControleManager(this.getContext());
            MobileManager mobileManager = new MobileManager(this.getContext());

            try {
                Reducteur reducteur1 = reducteurManager.getReducteur();
                reducteur1.setCarter(carterManager.getCarter());
                reducteur1.getCarter().setAlesageCarterList(alesagesCarterManager.getAllAlesageCarters());
                reducteur1.setAccessoireList(accessoireManagerSynchro.getAllAccessoires());
                reducteur1.setPetitesFournituresList(visserieManager.getAllPetitesFournitures());
                reducteur1.setControleList(controleManager.getAllControle());
                reducteur1.setTacheList(tacheManager.getAllTache());
                reducteur1.setMobileList(mobileManager.getAllMobile());

                ReducteurController.getInstance().save(this.getContext(),reducteur1, urlReducteur -> {
                    Toast.makeText(this.getActivity(), "Synchronisation sur la base de données a réussi",Toast.LENGTH_LONG).show();
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });

        return view;
    }
}