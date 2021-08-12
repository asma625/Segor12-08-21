package com.segor.demontage.view;


import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.segor.demontage.R;

import com.segor.demontage.dao.MobileManager;
import com.segor.demontage.models.Mobile;
import com.segor.demontage.view.adapter.ListeMobilesAdapter;

import java.util.ArrayList;
import java.util.List;

public class MobilesFragment extends Fragment implements View.OnClickListener {

    private ImageButton imageButtonPlanetaire, imageButtonCylDroit, imageButtonCylHel,
            imageButtonSpiroDroit, imageButtonSpiroConique, imageButtonVis,
            imageButtonMobile, imageButtonChevron,imageButtonAutreEngrenage;
    private List<Mobile> listeMobiles = new ArrayList<>();
    RecyclerView recyclerView;
    MobileManager mobileManager = new MobileManager(this.getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mobiles, container, false);
        afficherDial();
        imageButtonPlanetaire = root.findViewById(R.id.imageButton_planetaire);
        imageButtonCylDroit = root.findViewById(R.id.imageButton_cyl_droit);
        imageButtonCylHel= root.findViewById(R.id.imageButton_cyl_helicoidal);
        imageButtonAutreEngrenage = root.findViewById(R.id.imageButton_autre_engrenage);

        imageButtonVis = root.findViewById(R.id.imageButton_vis_sans_fin);
        imageButtonMobile = root.findViewById(R.id.imageButton_arbre);

        imageButtonSpiroDroit = root.findViewById(R.id.imageButton_spiro_droit);
        imageButtonSpiroConique = root.findViewById(R.id.imageButton_spiro_conique);

        imageButtonChevron = root.findViewById(R.id.imageButton_engrenage_chevron);



        recyclerView = root.findViewById(R.id.recyclerView_mobiles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listeMobiles =  mobileManager.getAllMobile();
        recyclerView.setAdapter(new ListeMobilesAdapter(listeMobiles,this.getContext()));


        imageButtonPlanetaire.setOnClickListener(this::onClick);
        imageButtonCylDroit.setOnClickListener(this::onClick);
        imageButtonCylHel.setOnClickListener(this::onClick);
        imageButtonSpiroDroit.setOnClickListener(this::onClick);
        imageButtonSpiroConique.setOnClickListener(this::onClick);
        imageButtonVis.setOnClickListener(this::onClick);
        imageButtonMobile.setOnClickListener(this::onClick);
        imageButtonChevron.setOnClickListener(this::onClick);
        imageButtonAutreEngrenage.setOnClickListener(this::onClick);

        return root;
    }

    @Override
    public void onClick(View v) {

        Mobile mobile = new Mobile();
        mobile.setType("");



        Dialog alerteAutreEngrenage = new Dialog(this.getContext());
        alerteAutreEngrenage.setContentView(R.layout.alerte_autre_engrenage);
        Button buttonAjouterEngrenage = alerteAutreEngrenage.findViewById(R.id.button_ajouter_autre_engrenage);
        EditText editTextAjouterNomEngrenage  = alerteAutreEngrenage.findViewById(R.id.editText_ajouter_nom_autre_engrenage);
        RadioGroup radioGroupTypeEngrenage  = alerteAutreEngrenage.findViewById(R.id.radioGroup_editer_type_autre_engrenage);
        EditText editTextNumeroTypeEngrenage = alerteAutreEngrenage.findViewById(R.id.editText_numero_autre_engrenage);

        EditText editTextModulePignon  = alerteAutreEngrenage.findViewById(R.id.editText_module_pignon_autre);
        EditText editTextDentsPignon  = alerteAutreEngrenage.findViewById(R.id.editText_dents_pignon_autre);
        EditText editTextModuleRoue  = alerteAutreEngrenage.findViewById(R.id.editText_module_roue_autre);
        EditText editTextDentsRoue = alerteAutreEngrenage.findViewById(R.id.editText_dents_roue_autre);








        Dialog alerteMobile = new Dialog(this.getContext());
        alerteMobile.setContentView(R.layout.alerte_editer_type_arbre);

        Dialog alerteEngrenage = new Dialog(this.getContext());
        alerteEngrenage.setContentView(R.layout.alerte_editer_type_engrenage);





        Button buttonAjouterTypeMobile = alerteMobile.findViewById(R.id.button_ajouter_type_arbre);
        Button buttonAjouterTypeEngrenage= alerteEngrenage.findViewById(R.id.button_ajouter_type_engrenage);




        RadioGroup radioGroupMobile = alerteMobile.findViewById(R.id.radioGroup_editer_type_arbre);
        EditText editTextNumeroMobile = alerteMobile.findViewById(R.id.editText_numero_arbre);
        EditText editTextNumeroEngrenage = alerteEngrenage.findViewById(R.id.editText_numero_engrenage);

        RadioGroup radioGroupEngrenage = alerteEngrenage.findViewById(R.id.radioGroup_editer_type_engrenage);


        radioGroupMobile.setOnCheckedChangeListener((radio, id) ->
        {
            switch (id) {

                case R.id.radioButton_pv:
                    mobile.setNom("ARBRE");
                    mobile.setType("PV");
                    break;
                case R.id.radioButton_mv:
                    mobile.setNom("ARBRE");
                    mobile.setType("MV");
                    break;
                case R.id.radioButton_gv:
                    mobile.setNom("ARBRE");
                    mobile.setType("GV");
                    break;
            }});

        radioGroupEngrenage.setOnCheckedChangeListener((radio, id) ->
        {
            switch (id) {

                case R.id.radioButton_pv:

                    mobile.setType("PV");
                    break;
                case R.id.radioButton_mv:

                    mobile.setType("MV");
                    break;
                case R.id.radioButton_gv:

                    mobile.setType("GV");
                    break;
            }});




        switch (v.getId()){

            case R.id.imageButton_arbre:

                alerteMobile.show();
                buttonAjouterTypeMobile.setOnClickListener(v1 -> {
                    mobile.setNom("ARBRE");
                    mobile.setType(mobile.getType()+editTextNumeroMobile.getText());
                    mobileManager.insertionMobile(mobile);
                    listeMobiles = mobileManager.getAllMobile();
                    recyclerView.setAdapter(new ListeMobilesAdapter(listeMobiles,this.getContext()));
                    alerteMobile.dismiss();
                });


                break;

            case R.id.imageButton_planetaire:
                alerteEngrenage.show();
                buttonAjouterTypeEngrenage.setOnClickListener(v1 -> {
                    mobile.setNom("ENGRENAGE PLANETAIRE");
                    mobile.setType(mobile.getType()+editTextNumeroEngrenage.getText());
                    mobileManager.insertionMobile(mobile);
                    listeMobiles = mobileManager.getAllMobile();
                    recyclerView.setAdapter(new ListeMobilesAdapter(listeMobiles,this.getContext()));
                    alerteEngrenage.dismiss();
                });
                break;

            case R.id.imageButton_cyl_droit:
                alerteEngrenage.show();
                buttonAjouterTypeEngrenage.setOnClickListener(v1 -> {
                    mobile.setNom("ENGRENAGE CYLINDRIQUE DROIT");
                    mobile.setType(mobile.getType()+editTextNumeroEngrenage.getText());
                    mobileManager.insertionMobile(mobile);
                    listeMobiles = mobileManager.getAllMobile();
                    recyclerView.setAdapter(new ListeMobilesAdapter(listeMobiles,this.getContext()));
                    alerteEngrenage.dismiss();
                });
                break;

            case R.id.imageButton_cyl_helicoidal:
                alerteEngrenage.show();

                buttonAjouterTypeEngrenage.setOnClickListener(v1 -> {
                    mobile.setNom("ENGRENAGE CYLINDRIQUE HELICOIDAL");
                    mobile.setType(mobile.getType()+editTextNumeroEngrenage.getText());
                    mobileManager.insertionMobile(mobile);
                    listeMobiles = mobileManager.getAllMobile();
                    recyclerView.setAdapter(new ListeMobilesAdapter(listeMobiles,this.getContext()));
                    alerteEngrenage.dismiss();
                });
                break;

            case R.id.imageButton_vis_sans_fin:
                alerteEngrenage.show();

                buttonAjouterTypeEngrenage.setOnClickListener(v1 -> {
                    mobile.setNom("ENGRENAGE VIS SANS FIN");
                    mobile.setType(mobile.getType()+editTextNumeroEngrenage.getText());
                    mobileManager.insertionMobile(mobile);
                    listeMobiles = mobileManager.getAllMobile();
                    recyclerView.setAdapter(new ListeMobilesAdapter(listeMobiles,this.getContext()));
                    alerteEngrenage.dismiss();
                });
                break;

            case R.id.imageButton_spiro_droit:
                alerteEngrenage.show();

                buttonAjouterTypeEngrenage.setOnClickListener(v1 -> {
                    mobile.setNom("ENGRENAGE CONIQUE DROIT");
                    mobile.setType(mobile.getType()+editTextNumeroEngrenage.getText());
                    mobileManager.insertionMobile(mobile);
                    listeMobiles = mobileManager.getAllMobile();
                    recyclerView.setAdapter(new ListeMobilesAdapter(listeMobiles,this.getContext()));
                    alerteEngrenage.dismiss();
                });
                break;

            case R.id.imageButton_spiro_conique:
                alerteEngrenage.show();

                buttonAjouterTypeEngrenage.setOnClickListener(v1 -> {
                    mobile.setNom("ENGRENAGE SPIRO CONIQUE");
                    mobile.setType(mobile.getType()+editTextNumeroEngrenage.getText());
                    mobileManager.insertionMobile(mobile);
                    listeMobiles = mobileManager.getAllMobile();
                    recyclerView.setAdapter(new ListeMobilesAdapter(listeMobiles,this.getContext()));
                    alerteEngrenage.dismiss();
                });
                break;

            case R.id.imageButton_engrenage_chevron:
                alerteEngrenage.show();

                buttonAjouterTypeEngrenage.setOnClickListener(v1 -> {
                    mobile.setNom("ENGRENAGE CHEVRON");
                    mobile.setType(mobile.getType()+editTextNumeroEngrenage.getText());
                    mobileManager.insertionMobile(mobile);
                    listeMobiles = mobileManager.getAllMobile();
                    recyclerView.setAdapter(new ListeMobilesAdapter(listeMobiles,this.getContext()));
                    alerteEngrenage.dismiss();
                });
                break;


            case R.id.imageButton_autre_engrenage:


                buttonAjouterEngrenage.setOnClickListener(v1 -> {
                    if(!editTextAjouterNomEngrenage.getText().toString().equals("")) {

                        mobile.setNom(editTextAjouterNomEngrenage.getText().toString());
                        mobile.setModulePignon(editTextModulePignon.getText().toString());
                        mobile.setModuleRoue(editTextModuleRoue.getText().toString());
                        mobile.setNombreDentPignon(editTextDentsPignon.getText().toString());
                        mobile.setNombreDentRoue(editTextDentsRoue.getText().toString());
                        mobile.setType(mobile.getType() + editTextNumeroTypeEngrenage.getText().toString());

                        mobileManager.insertionMobile(mobile);
                        listeMobiles = mobileManager.getAllMobile();
                        recyclerView.setAdapter(new ListeMobilesAdapter(listeMobiles,this.getContext()));
                        alerteAutreEngrenage.dismiss();
                    }
                });
                radioGroupTypeEngrenage.setOnCheckedChangeListener((radio, id) ->
                {
                    switch (id) {
                        case R.id.radioButton_autre_pv:

                            mobile.setType("PV ");
                            break;
                        case R.id.radioButton_autre_mv:

                            mobile.setType("MV ");
                            break;
                        case R.id.radioButton_autre_gv:

                            mobile.setType("GV ");
                            break;
                    }});
                alerteAutreEngrenage.show();

                break;

        }
    }
    private void afficherDial() {
        Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.alerte_photo_mobile);
        Button button = dialog.findViewById(R.id.button_photo_mobile);
        button.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }
}
