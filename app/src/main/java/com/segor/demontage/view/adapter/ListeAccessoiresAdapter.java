package com.segor.demontage.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.segor.demontage.R;
import com.segor.demontage.dao.AccessoireManager;
import com.segor.demontage.models.Accessoire;

import java.util.List;

public class ListeAccessoiresAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Accessoire> listeAccessoires;
    private Context context;
    AccessoireManager accessoireManager;

    public ListeAccessoiresAdapter(List<Accessoire> listeAccessoires,Context context) {
        this.listeAccessoires = listeAccessoires;
        this.context = context;
    }

    static class AccessoiresViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewAccessoire;
        private ImageButton imageButtonRetirer;

        public AccessoiresViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAccessoire = itemView.findViewById(R.id.textView_nom_accessoire);
            imageButtonRetirer = itemView.findViewById(R.id.imageButton_retirer_accessoire);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accessoires, parent,false);
        return new AccessoiresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Accessoire accessoire = listeAccessoires.get(position);
        AccessoiresViewHolder accessoiresViewHolder = (AccessoiresViewHolder) holder;
        accessoiresViewHolder.textViewAccessoire.setText(accessoire.getNom_accessoire() );

        accessoiresViewHolder.imageButtonRetirer.setOnClickListener(v -> {
            accessoireManager = new AccessoireManager(this.context);
            Integer id = listeAccessoires.get(position).getId();
            accessoireManager.deleteAccessoire(id);
            listeAccessoires.remove(position);
            notifyDataSetChanged();
        });


    }

    @Override
    public int getItemCount() {
        return listeAccessoires.size();
    }
}
