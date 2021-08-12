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
import com.segor.demontage.dao.MobileManager;
import com.segor.demontage.models.Mobile;

import java.util.List;

public class ListeMobilesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
private List<Mobile> listeMobiles;
    private Context context;

    public ListeMobilesAdapter(List<Mobile> listeMobiles, Context context) {
        this.listeMobiles = listeMobiles;
        this.context = context;
    }

    static class MobileViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewMobile;
        private TextView textViewMobileType;
        private ImageButton imageButtonRetirerMobile;

        public MobileViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMobile = itemView.findViewById(R.id.textView_nom_mobile);
            textViewMobileType = itemView.findViewById(R.id.textView_type_mobile);
            imageButtonRetirerMobile = itemView.findViewById(R.id.imageButton_retirer_mobile);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_mobile, parent, false);
            return new MobileViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            Mobile mobile =  listeMobiles.get(position);
            MobileViewHolder mobileViewHolder = (MobileViewHolder) holder;
            mobileViewHolder.textViewMobile.setText(mobile.getNom());
            mobileViewHolder.textViewMobileType.setText(mobile.getType());
            mobileViewHolder.imageButtonRetirerMobile.setOnClickListener(v -> {
                MobileManager  mobileManager =  new MobileManager(context);
                Integer id = listeMobiles.get(position).getId();
                mobileManager.deleteMobile( id);
                listeMobiles.remove(position);
                notifyDataSetChanged();
            });

        }






    @Override
    public int getItemCount() {
        return listeMobiles.size();
    }


}
