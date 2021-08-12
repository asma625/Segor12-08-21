package com.segor.demontage.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.segor.demontage.R;
import com.segor.demontage.dao.AlesagesCarterManager;
import com.segor.demontage.models.AlesageCarter;
import com.segor.demontage.view.adapter.ListeAlesageCarterAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpertiseCarterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpertiseCarterFragment extends Fragment {

    private RecyclerView recyclerView;
    private AlesageCarter alesageCarter = new AlesageCarter();
    private List<AlesageCarter> alesageCarterList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExpertiseCarterFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expertise_carter, container, false);


        recyclerView = rootView.findViewById(R.id.recyclerView_alesage_carter);
        AlesagesCarterManager alesagesCarterManager =  new AlesagesCarterManager(this.getContext());
        alesageCarterList = alesagesCarterManager.getAllAlesageCarters();
        System.out.println(alesageCarterList.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new ListeAlesageCarterAdapter(alesageCarterList,this.getContext()));

        return rootView;
    }
}