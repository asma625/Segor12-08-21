package com.segor.demontage.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.segor.demontage.R;
import com.segor.demontage.dao.CarterManger;
import com.segor.demontage.models.Carter;
import com.segor.demontage.utils.AnnotationActivity;

public class CarterFragment extends Fragment {

    Carter carter = new Carter();
    private BottomAppBar bottomAppBarCarter;

    EditText editText_hauteur,
            editText_longueur,
            editText_largeur,
            editText_masse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_carter, container, false);
        editText_hauteur = rootView.findViewById(R.id.editText_hauteur);
        editText_longueur = rootView.findViewById(R.id.editText_longueur);
        editText_largeur = rootView.findViewById(R.id.editText_largeur);
        editText_masse = rootView.findViewById(R.id.editText_masse);
        bottomAppBarCarter = rootView.findViewById(R.id.bottomAppBar_carter);

        CarterManger carterManger =  new CarterManger(this.getContext());
        Carter carterRec = carterManger.getCarter();
        if(carterRec != null){
            editText_longueur.setText(carterRec.getLongueur());
            editText_largeur.setText(carterRec.getLargeur());
            editText_hauteur.setText(carterRec.getHauteur());
            editText_masse.setText(carterRec.getMasse());

        }

        afficherDial();


        bottomAppBarCarter.setOnMenuItemClickListener(itemMenu -> {
            if (itemMenu.getItemId() == R.id.item_save) {
                if(carter.getId() == null)
                    carter.setId(1);
                carter.setLongueur(editText_longueur.getText().toString());
                carter.setLargeur(editText_largeur.getText().toString());
                carter.setHauteur(editText_hauteur.getText().toString());
                carter.setMasse(editText_masse.getText().toString());

                if(carterManger.getCarter() == null){
                boolean insertData = carterManger.insertionCarter(carter);

                if(insertData == true){
                    Toast.makeText(this.getActivity(), "Successfully Entered Data",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this.getActivity(), "Erreur",Toast.LENGTH_LONG).show();
                }}
                else{boolean updateData = carterManger.updateCarter(carter);

                    if(updateData == true){
                        Toast.makeText(this.getActivity(), "Successfully update Data",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this.getActivity(), "Erreur",Toast.LENGTH_LONG).show();
                    }

                }


                return true;
            }if (itemMenu.getItemId() == R.id.item_photo){
                Intent intent = new Intent(this.getContext(), AnnotationActivity.class);
                startActivity(intent);
            }
            return false;
        });

        return rootView;
    }

    private void afficherDial() {
        Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.alerte_photo_alesages_carter);
        Button button = dialog.findViewById(R.id.button_photo_accessoire);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this.getContext(), AnnotationActivity.class);
            startActivity(intent);
            dialog.dismiss();
        });
        dialog.show();
    }

}
