package com.segor.demontage.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.segor.demontage.R;
import com.segor.demontage.controller.ConnexionLocalController;
import com.segor.demontage.dao.AccessoireManager;
import com.segor.demontage.dao.BddLocale;
import com.segor.demontage.models.Accessoire;
import com.segor.demontage.view.adapter.ListeAccessoiresPhase2Adapter;

import java.util.List;

public class ListeAccessoiresFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListeAccessoiresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListeAccessoiresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListeAccessoiresFragment newInstance(String param1, String param2) {
        ListeAccessoiresFragment fragment = new ListeAccessoiresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liste_accessoires, container, false);
//        TextView textView = view.findViewById(R.id.textView_accessoire_liste);
        //BddLocale bddLocale = ConnexionLocalController.getInstance(this.getContext());
        AccessoireManager accessoireManager = new AccessoireManager(this.getContext());

        List<Accessoire> accessoireList = accessoireManager.getAllAccessoires();
        //accessoireManager.close();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_accesoires);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new ListeAccessoiresPhase2Adapter(accessoireList,this.getContext()));


        return view;
    }
}