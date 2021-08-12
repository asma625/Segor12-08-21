package com.segor.demontage.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.segor.demontage.R;
import com.segor.demontage.controller.ConnexionLocalController;
import com.segor.demontage.controller.ReducteurController;
import com.segor.demontage.dao.AccessoireManager;
import com.segor.demontage.dao.AlesagesCarterManager;
import com.segor.demontage.dao.BddLocale;
import com.segor.demontage.dao.CarterManger;
import com.segor.demontage.dao.ReducteurManager;
import com.segor.demontage.dao.VisserieManager;
import com.segor.demontage.models.Accessoire;
import com.segor.demontage.models.Reducteur;

import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class PdfFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    BddLocale bddLocale ;
    Bitmap bitmap,redimBitmap;
    public PdfFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static PdfFragment newInstance(String param1, String param2) {
        PdfFragment fragment = new PdfFragment();
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
        View  view = inflater.inflate(R.layout.fragment_pdf, container, false);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo_segor);
        redimBitmap = Bitmap.createScaledBitmap(bitmap,200,120,false);

        ReducteurManager reducteurManager = new ReducteurManager(this.getContext());
        Reducteur reducteur = reducteurManager.getReducteur();
        ImageButton button = view.findViewById(R.id.button_pdf);
        //Button buttonSynchro =  view.findViewById(R.id.button_synchro);
        //instance pour creer un pdf
        PdfDocument devisPdf = new PdfDocument();

        //creer et attribuer des valeurs
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(900,2200, 1).create();

        PdfDocument.Page myPage = devisPdf.startPage(myPageInfo);
        //objet qui permet de modifier les couleurs, les tailles, border........
        Paint myPaint = new Paint();
        //objet qui permet de modifier les formes, mettre du texte, dessiner, ajouter des images
        Canvas canvas = myPage.getCanvas();

        //affiche l'image du pdf
        canvas.drawBitmap(redimBitmap, canvas.getWidth()-220, 10,myPaint);

//-------------------------2e page-----------------------------------------



//--------------------------------------------------------------------------


        //crée les encadrés du pdf
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(2);
        myPaint.setColor(Color.BLUE);
        canvas.drawRoundRect(30f, 50f, 350f, 100f,5f,5f, myPaint);
        canvas.drawRoundRect(360f, 50f, 660f, 100f, 5,5,myPaint);

        canvas.drawRoundRect(30f, 140f, 470f, 460f,10f,10f, myPaint);
        canvas.drawRoundRect(480f, 140f, 890f, 460f,10f,10f, myPaint);

        canvas.drawRoundRect(30f, 470, 470f, 770,10f,10f, myPaint);
        canvas.drawRoundRect(480, 470, 890f, 770,10f,10f, myPaint);

        canvas.drawRoundRect(30f, 790, 890f, 1200,10f,10f, myPaint);

        //ajoute les champs de texte statiques
        myPaint.setStyle(Paint.Style.FILL);
        myPaint.setColor(Color.BLACK);
        myPaint.setTextSize(24);
        canvas.drawText("DEVIS DE REPARATION", 60, 85, myPaint);
        myPaint.setTextSize(20);
        canvas.drawText("OFFRE N° "+reducteur.getNumOffre(), 395, 85, myPaint);

        //affiche les titres des encadrés
        myPaint.setTextSize(20);
        myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        canvas.drawText("Plaque signalétique de réducteur", 80, 180, myPaint);
        canvas.drawText("Références SEGOR", 600,180, myPaint);
        canvas.drawText("Poids et encombrements", 120, 510, myPaint);
        canvas.drawText("Inspection de la lubrification", 560, 510, myPaint);

        canvas.drawText("Expertises des accessoires réalisées avant démontage du réducteur",120 , 820,myPaint );

        //ajouter les champs statique des encadrés
        myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
        myPaint.setTextSize(18);
        canvas.drawText("Constructeur", 50, 240, myPaint);
        canvas.drawText("Type", 50, 280, myPaint);
        canvas.drawText("Numéro de fabrication", 50, 320, myPaint);
        canvas.drawText("Rapport(i=)", 50, 360, myPaint);
        canvas.drawText("Puissance moteur", 50, 400, myPaint);
        canvas.drawText("Numéro de série", 50, 440, myPaint);

        canvas.drawText("Client", 500, 240, myPaint);
        canvas.drawText("Reçu", 500, 280, myPaint);
        canvas.drawText("Commande expertise", 500, 320, myPaint);
        canvas.drawText("Code d'expertise", 500, 360, myPaint);
        canvas.drawText("Commande SEGOR", 500, 400, myPaint);
        canvas.drawText("Nom démonteur", 500, 440, myPaint);

        canvas.drawText("Poids sans huile", 50, 600, myPaint);
        canvas.drawText("Encombrements", 50, 640, myPaint);
        canvas.drawText("Chassis à prévoir pour le transport ", 50, 680, myPaint);
        canvas.drawText("Réducteur livré", 50, 720, myPaint);
        myPaint.setTextSize(14);
        canvas.drawText("Zone a compléter par le magasinier.", 50, 540, myPaint);
        canvas.drawText("Si poids supérieur à 600 kg faire peser reducteur.", 50, 560, myPaint);
        myPaint.setTextSize(18);

        canvas.drawText("Type", 500, 560, myPaint);
        canvas.drawText("Quantité", 500, 600, myPaint);
        canvas.drawText("Viscosité", 500, 640, myPaint);
        canvas.drawText("Quantité graisse à injecter au remontage ", 500, 680, myPaint);
        canvas.drawText("----------------------------------------", 500, 720, myPaint);

        //ajouter les valeurs des champs reducteur depuis la base de donnée
        myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        myPaint.setColor(Color.BLUE);
        canvas.drawText(reducteur.getConstructeur(), 280, 240, myPaint);
        canvas.drawText(reducteur.getType_reducteur(), 280, 280, myPaint);
        canvas.drawText(reducteur.getAnnee_fab(), 280, 320, myPaint);
        canvas.drawText(reducteur.getRapport_i(), 280, 360, myPaint);
        canvas.drawText(reducteur.getType_moteur()+" KW", 280, 400, myPaint);
        canvas.drawText(reducteur.getN_Serie(), 280, 440, myPaint);

        canvas.drawText(reducteur.getClient(), 720, 240, myPaint);
        canvas.drawText(reducteur.getDate_recu(), 720, 280, myPaint);
        canvas.drawText(reducteur.getCde_expertise(), 720, 320, myPaint);
        canvas.drawText(reducteur.getCode_expertise(), 720, 360, myPaint);
        canvas.drawText(reducteur.getCde_segor(), 720, 400, myPaint);
        canvas.drawText(reducteur.getNom_demonteur(), 720, 440, myPaint);

        float y = 820;

        AccessoireManager accessoireManager = new AccessoireManager(this.getContext());

        myPaint.setColor(Color.BLACK);
        for (Accessoire accessoire : accessoireManager.getAllAccessoires()) {
            y = y + 40;
            canvas.drawText(accessoire.getNom_accessoire()+"/n"+accessoire.getMarque(), 50, y, myPaint);
        }


        devisPdf.finishPage(myPage);
        //button.setEnabled(true);
        button.setOnClickListener(v->{
            button.setImageResource(R.drawable.ic_baseline_check_24);
            button.setEnabled(false);
            File file = new File(this.getContext().getExternalFilesDir("/"),"reducteur_"+reducteur.getNumOffre()+".pdf");
            try {
                devisPdf.writeTo(new FileOutputStream(file));

                    Toast.makeText(this.getActivity(), "Le PDF du réducteur N° " + reducteur.getNumOffre() + " a bien été sauvgardé",Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this.getActivity(), "Une erreur a empêché la création du PDF ",Toast.LENGTH_LONG).show();
            }

            devisPdf.close();
        });



        return view;
    }
}