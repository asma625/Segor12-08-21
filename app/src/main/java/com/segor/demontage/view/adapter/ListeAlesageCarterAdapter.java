package com.segor.demontage.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.segor.demontage.R;
import com.segor.demontage.dao.AlesagesCarterManager;
import com.segor.demontage.models.AlesageCarter;

import java.util.List;

public class ListeAlesageCarterAdapter extends    RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AlesageCarter> alesageCarterList;
    private Context context;


    public ListeAlesageCarterAdapter(List<AlesageCarter> alesageCarterList, Context context) {
        this.alesageCarterList = alesageCarterList;
        this.context = context;
    }



    static class AlesageCarterViewHolder extends RecyclerView.ViewHolder{

                ImageButton buttonSupprimerAlesageCarter, buttonSaveAlesageCarter;
                EditText editTextNomeAlesageCarter, editTextTypeAlesageCarter, editTextDiametreAlesageCarter;
                RadioGroup radioGroupNormeAlesageCarter;
                RadioButton radioButtonConforme, radioButtonNonConforme;

        public AlesageCarterViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonSupprimerAlesageCarter = itemView.findViewById(R.id.button_supprimer_alesage_carter);
            editTextNomeAlesageCarter = itemView.findViewById(R.id.editText_nome_alesage_carter);
            editTextTypeAlesageCarter = itemView.findViewById(R.id.editText_type_alesage_carter);
            editTextDiametreAlesageCarter = itemView.findViewById(R.id.editText_diametre_alesage_carter);
            radioGroupNormeAlesageCarter =  itemView.findViewById(R.id.RadioGroup_norme_alesage_carter);
            radioButtonConforme =  itemView.findViewById(R.id.radioButton_norme_conforme);
            radioButtonNonConforme =  itemView.findViewById(R.id.radioButton_norme_non_conforme);

        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alesage_carter, parent,false);
        return new AlesageCarterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            AlesageCarter alesageCarter =  alesageCarterList.get(position);
            AlesageCarterViewHolder alesageCarterViewHolder = (AlesageCarterViewHolder) holder;


            alesageCarterViewHolder.editTextNomeAlesageCarter.setText(alesageCarter.getNomAlesageCarter());
            alesageCarterViewHolder.editTextTypeAlesageCarter.setText(alesageCarter.getType());
            alesageCarterViewHolder.editTextDiametreAlesageCarter.setText(alesageCarter.getDiametreAlesageCarter());
           try {
               if(alesageCarter.getNorme().equals("Conforme") ){
                   alesageCarterViewHolder.radioButtonConforme.setChecked(true);

               }else if(alesageCarter.getNorme().equals("Non conforme")){
                   alesageCarterViewHolder.radioButtonNonConforme.setChecked(true);

               }
           }catch (NullPointerException e){

           }



          alesageCarterViewHolder.radioGroupNormeAlesageCarter.setOnCheckedChangeListener((radio, id) ->
             {
            switch (id) {

                case R.id.radioButton_norme_conforme:

                    alesageCarter.setNorme("Conforme");
                    break;
                case R.id.radioButton_norme_non_conforme:
                    alesageCarter.setNorme("Non conforme");
                    break;
            }    AlesagesCarterManager alesagesCarterManager =  new AlesagesCarterManager(context);
                 alesageCarter.setNomAlesageCarter(alesageCarterViewHolder.editTextNomeAlesageCarter.getText().toString());
                 alesageCarter.setType(alesageCarterViewHolder.editTextTypeAlesageCarter.getText().toString());
                 alesageCarter.setDiametreAlesageCarter(alesageCarterViewHolder.editTextDiametreAlesageCarter.getText().toString());
                 alesagesCarterManager.updateAlesageCarter(alesageCarter);
             });


           /*alesageCarterViewHolder.buttonSaveAlesageCarter.setOnClickListener(v-> {
                AlesagesCarterManager alesagesCarterManager =  new AlesagesCarterManager(context);
                alesageCarter.setNomAlesageCarter(alesageCarterViewHolder.editTextNomeAlesageCarter.getText().toString());
                alesageCarter.setType(alesageCarterViewHolder.editTextTypeAlesageCarter.getText().toString());
                alesageCarter.setDiametreAlesageCarter(alesageCarterViewHolder.editTextDiametreAlesageCarter.getText().toString());
                alesagesCarterManager.updateAlesageCarter(alesageCarter);



            });*/


            alesageCarterViewHolder.buttonSupprimerAlesageCarter.setOnClickListener(v -> {
                alesageCarterList.remove(position);
                AlesagesCarterManager alesagesCarterManager =  new AlesagesCarterManager(context);
                alesagesCarterManager.deleteAlesageCarter(alesageCarter.getId());
                notifyItemRemoved(position);
            });

        }

    @Override
    public int getItemCount() {
        return (alesageCarterList.size());
    }

}
