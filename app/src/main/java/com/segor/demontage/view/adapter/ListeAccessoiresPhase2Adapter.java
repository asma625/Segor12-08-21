package com.segor.demontage.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.segor.demontage.R;
import com.segor.demontage.dao.AccessoireManager;
import com.segor.demontage.models.Accessoire;

import java.util.List;


public class ListeAccessoiresPhase2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Accessoire> listeAccessoires;
    private Context context;
    private  String photoPath=null;
    private AccessoireManager accessoireManager;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public ListeAccessoiresPhase2Adapter(List<Accessoire> listeAccessoires, Context context) {
        this.listeAccessoires = listeAccessoires;
        this.context = context;
    }

    static class AccessoiresViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewAccessoire;
        private EditText editTextMarque;
        private EditText editTextType;
        private EditText editTextcaracteristique;
        private EditText editTextAutreCaracteristique;
        private RadioGroup radioGroupEtat;
        private EditText editTextCause;
        private Button button;
        private Button buttonPhoto;
        private LinearLayout layout;
        private RadioButton radioButtonConserver ,radioButtonReparer, radioButtonRemplacer;




        public AccessoiresViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAccessoire = itemView.findViewById(R.id.textView_nom_accessoire_p2);
            editTextMarque = itemView.findViewById(R.id.editText_marque_accessoire_p2);
            editTextType = itemView.findViewById(R.id.editText_type_accessoire_p2);
            editTextcaracteristique = itemView.findViewById(R.id.editText_caracteristique_accessoire_p2);
            radioButtonConserver = itemView.findViewById(R.id.radioButton_conserver_p2);
            radioButtonReparer = itemView.findViewById(R.id.radioButton_reparer_p2);
            radioButtonRemplacer  = itemView.findViewById(R.id.radioButton_remplacer_p2);
            radioGroupEtat = itemView.findViewById(R.id.radioGroup_etat_accessoire_p2);
            editTextCause = itemView.findViewById(R.id.editTextMultiLine_cause_p2);
            button = itemView.findViewById(R.id.button_ajouter_champs_accessoire);
            layout = itemView.findViewById(R.id.layout_accessoire);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accessoires_phase2, parent,false);
        return new AccessoiresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Accessoire accessoire = listeAccessoires.get(position);
        AccessoiresViewHolder accessoiresViewHolder = (AccessoiresViewHolder) holder;
        accessoiresViewHolder.textViewAccessoire.setText(accessoire.getNom_accessoire());
        accessoiresViewHolder.editTextMarque.setText(accessoire.getMarque());
        accessoiresViewHolder.editTextType.setText(accessoire.getType());
        accessoiresViewHolder.editTextcaracteristique.setText(accessoire.getCaracteristique());
        accessoiresViewHolder.editTextCause.setText(accessoire.getCommentaire());

        if (accessoire.getEtat() != null) {
            if (accessoire.getEtat().equals("A conserver en l'état")) {
                accessoiresViewHolder.radioButtonConserver.setChecked(true);

            } else if (accessoire.getEtat().equals("A réparer")) {
                accessoiresViewHolder.radioButtonReparer.setChecked(true);

            } else if (accessoire.getEtat().equals("A remplacer")) {
                accessoiresViewHolder.radioButtonRemplacer.setChecked(true);

            }
        }


        accessoiresViewHolder.button.setOnClickListener(v -> {
            accessoiresViewHolder.textViewAccessoire.setBackgroundColor(Color.GREEN);
            accessoire.setMarque(accessoiresViewHolder.editTextMarque.getText().toString());
            accessoire.setType(accessoiresViewHolder.editTextType.getText().toString());
            accessoire.setCaracteristique(accessoiresViewHolder.editTextcaracteristique.getText().toString());
            accessoire.setAutreCaracteristique(accessoiresViewHolder.editTextAutreCaracteristique.getText().toString());
            accessoire.setCommentaire(accessoiresViewHolder.editTextCause.getText().toString());
            //faut pas mettre les RadioButton à l'intérieur de lambda
            accessoireManager = new AccessoireManager(context);
            //BddLocale bddLocale = ConnexionLocalController.getInstance(context);
            //bddLocale.updateAccessoire(accessoire);
            accessoireManager.updateAccessoire(accessoire);

            accessoiresViewHolder.layout.setVisibility(View.GONE);
            notifyDataSetChanged();
        });

        accessoiresViewHolder.radioGroupEtat.setOnCheckedChangeListener((radio, id) ->
        {
            switch (id) {

                case R.id.radioButton_conserver_p2:
                    accessoire.setEtat("A conserver en l'état");
                    break;
                case R.id.radioButton_reparer_p2:
                    accessoire.setEtat("A réparer");
                    break;
                case R.id.radioButton_remplacer_p2:
                    accessoire.setEtat("A remplacer");
                    break;
            }

        });

    }

    @Override
    public int getItemCount() {
        return listeAccessoires.size();
    }

}
