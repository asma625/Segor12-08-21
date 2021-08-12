package com.segor.demontage.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.segor.demontage.R;

import com.segor.demontage.dao.TacheManager;
import com.segor.demontage.models.Tache;

import java.util.ArrayList;
import java.util.List;


public class TacheFragment extends Fragment {

    private BottomAppBar bottomAppBarTache;
    private List<Tache> tacheList = new ArrayList<>();
    private Float somme = 0f;
    private EditText editTextDemontage,
            editTextNettoyage,
            editTextExpertise,
            editTextMontage,
            editTextEssai,
            editTextSablage,
            editTextcontrole,
            editTextcolisage,
            editTextPeinture,
            editTextTotal;


    public TacheFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tache, container, false);
        init(view);



        bottomAppBarTache.setOnMenuItemClickListener(itemMenu -> {
            if (itemMenu.getItemId() == R.id.item_camera) {

                TacheManager  tacheManager =  new TacheManager(this.getContext());
                if (tacheManager.getAllTache().size() == 0) {
                    tacheManager.insertionTache(ajouterTache(1, "Démontage", editTextDemontage.getText().toString()));
                    tacheManager.insertionTache(ajouterTache(2, "Nettoyage", editTextNettoyage.getText().toString()));
                    tacheManager.insertionTache(ajouterTache(3, "Expertise-Etudes", editTextExpertise.getText().toString()));
                    tacheManager.insertionTache(ajouterTache(4, "Montage",editTextMontage.getText().toString()));
                    tacheManager.insertionTache(ajouterTache(5, "Essai",editTextEssai.getText().toString()));
                    tacheManager.insertionTache(ajouterTache(6, "Sablage",  editTextSablage.getText().toString()));
                    tacheManager.insertionTache(ajouterTache(7, "Contrôle final",editTextcontrole.getText().toString()));
                    tacheManager.insertionTache(ajouterTache(8, "Peinture",  editTextPeinture.getText().toString()));
                    tacheManager.insertionTache(ajouterTache(9, "Colisage-Chassis", editTextcolisage.getText().toString()));
                    String [] tableauHeures = {editTextDemontage.getText().toString(),editTextNettoyage.getText().toString(),editTextExpertise.getText().toString(),
                            editTextEssai.getText().toString(),editTextSablage.getText().toString(),editTextcontrole.getText().toString()
                            ,editTextPeinture.getText().toString(), editTextcolisage.getText().toString(),editTextMontage.getText().toString()};
                    somme =  somme(tableauHeures);
                    editTextTotal.setText(""+somme);
                    tacheManager.insertionTache(ajouterTache(10, "Total", editTextTotal.getText().toString()));

                } else {

                    tacheManager.updateTache(ajouterTache(1, "Démontage", editTextDemontage.getText().toString()));
                    tacheManager.updateTache(ajouterTache(2, "Nettoyage",editTextNettoyage.getText().toString()));
                    tacheManager.updateTache(ajouterTache(3, "Expertise-Etudes", editTextExpertise.getText().toString()));
                    tacheManager.updateTache(ajouterTache(4, "Montage", editTextMontage.getText().toString()));
                    tacheManager.updateTache(ajouterTache(5, "Essai", editTextEssai.getText().toString()));
                    tacheManager.updateTache(ajouterTache(6, "Sablage-Peinture", editTextSablage.getText().toString()));
                    tacheManager.updateTache(ajouterTache(7, "Contrôle final", editTextcontrole.getText().toString()));
                    tacheManager.updateTache(ajouterTache(8, "Peinture",  editTextPeinture.getText().toString()));
                    tacheManager.updateTache(ajouterTache(9, "Colisage-Chassis", editTextcolisage.getText().toString()));
                    String [] tableauHeures = {editTextDemontage.getText().toString(),editTextNettoyage.getText().toString(),editTextExpertise.getText().toString(),
                            editTextEssai.getText().toString(),editTextSablage.getText().toString(),editTextcontrole.getText().toString()
                            ,editTextPeinture.getText().toString(), editTextcolisage.getText().toString(),editTextMontage.getText().toString()};
                    //editTextTotal.setText(""+somme(tableauHeures));
                    somme =  somme(tableauHeures);
                    editTextTotal.setText(""+somme);
                    tacheManager.updateTache(ajouterTache(10, "Total", editTextTotal.getText().toString()));

                }






                return true;
            }
            return false;
        });
        return view;
    }

    private Tache ajouterTache(Integer id, String nom, String temps) {
        Tache tache = new Tache();
        tache.setId(id);
        tache.setDesignation(nom);
        tache.setTemps(temps);
        return tache;

    }

    private void init(View view) {

        TacheManager  tacheManager =  new TacheManager(this.getContext());
        bottomAppBarTache = view.findViewById(R.id.bottomAppBar_edition_controle);
        editTextDemontage = view.findViewById(R.id.editText_tache_demontage);
        editTextNettoyage = view.findViewById(R.id.editText_tache_nettoyage);
        editTextExpertise = view.findViewById(R.id.editText_tache_expertise);
        editTextMontage = view.findViewById(R.id.editText_tache_montage);
        editTextEssai = view.findViewById(R.id.editText_tache_essai);
        editTextSablage = view.findViewById(R.id.editText_tache_sablage);
        editTextcontrole = view.findViewById(R.id.editText_tache_controle);
        editTextcolisage = view.findViewById(R.id.editText_tache_colisage);
        editTextPeinture = view.findViewById(R.id.editText_tache_peinture);
        editTextTotal = view.findViewById(R.id.editText_tache_total);

        tacheList = tacheManager.getAllTache();
        System.out.println(tacheList.size());
        if (tacheList.size() != 0) {

            editTextDemontage.setText(""+tacheList.get(0).getTemps());
            editTextNettoyage.setText(""+tacheList.get(1).getTemps());
            editTextExpertise.setText(""+tacheList.get(2).getTemps());
            editTextMontage.setText(""+tacheList.get(3).getTemps());
            editTextEssai.setText(""+tacheList.get(4).getTemps());
            editTextSablage.setText(""+tacheList.get(5).getTemps());
            editTextcontrole.setText(""+tacheList.get(6).getTemps());
            editTextPeinture.setText(""+tacheList.get(7).getTemps());
            editTextcolisage.setText(""+tacheList.get(8).getTemps());
            editTextTotal.setText(""+tacheList.get(9).getTemps());
        }


    }
    public  Float convert( String valeur){
        if(valeur.equals(""))
            return 0f;
        else
            return Float.valueOf(valeur);
    }

    public Float somme(String []tab){
        Float somme = 0f;
        for (String s : tab)
            somme = somme + convert(s);
        return  somme;
    }
}