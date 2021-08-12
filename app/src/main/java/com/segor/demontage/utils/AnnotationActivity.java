package com.segor.demontage.utils;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.segor.demontage.R;
import com.segor.demontage.controller.ConnexionLocalController;
import com.segor.demontage.dao.AlesagesCarterManager;
import com.segor.demontage.dao.BddLocale;
import com.segor.demontage.models.AlesageCarter;
import com.segor.demontage.view.adapter.AnnotationAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnotationActivity extends AppCompatActivity {

    private ImageView imageView;
    private RadioButton numero,cercle,flecheD,texte,flecheG,flecheH,flecheB,note;
    private RadioGroup radioGroup;
    private RadioGroup radioGroupColor;
    private EditText editTextAnnotation;
    private EditText editTextDiametreAlesageCarter;
    private EditText editTextTypeAlesageCarter;
    private BottomAppBar bottomAppBar;
    private String rep = "value";
    private String repColor = "black";
    private RecyclerView recyclerViewAnnotation;
    private AnnotationAdapter annotationAdapter;
    private List<AlesageCarter> annotations = new ArrayList<>();
    private String photoPath=null;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private int cptNote = 0;
    private int cptNumero = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);

        imageView = findViewById(R.id.imageView);
        cercle = findViewById(R.id.radioButton_cercle);
        texte = findViewById(R.id.radioButton_texte);
        note = findViewById(R.id.radioButton_note);
        numero = findViewById(R.id.radioButton_numero);
        flecheB = findViewById(R.id.radioButton_fleche_bas);
        flecheH = findViewById(R.id.radioButton_fleche_haut);
        flecheG = findViewById(R.id.radioButton_fleche_gauche);
        flecheD = findViewById(R.id.radioButton_fleche_droite);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroupColor = findViewById(R.id.groupeButton_color);
        editTextAnnotation = findViewById(R.id.editText_alerte_nom_alesage);
        recyclerViewAnnotation= findViewById(R.id.recyclerView_annotation);
        bottomAppBar = findViewById(R.id.bottomAppBar_annotation);

        bottomAppBar.setOnMenuItemClickListener(menuItem -> {
            if(menuItem.getItemId() == R.id.item_prendre_photo){

                dispatchTakePictureIntent();
                return true;
            }

            if(menuItem.getItemId() == R.id.item_save_photo){
                save();
                Toast.makeText(this, "La photo a bien été enregistrée dans votre galerie", Toast.LENGTH_LONG).show();
                return true;
            }

            if(menuItem.getItemId() == R.id.item_annule_photo){

                cptNote = 0;
                cptNumero = 0;
                Bitmap imageBitmap = BitmapFactory.decodeFile(photoPath);

                DisplayMetrics metrics = new DisplayMetrics();
                this.getWindowManager().getDefaultDisplay().getMetrics(metrics);

                float h = metrics.heightPixels*1f;
                float w = metrics.widthPixels*1f;

                float rh=h/imageBitmap.getHeight();
                float rw = w/imageBitmap.getWidth();
                float r = Math.min(rh, rw);

                imageBitmap = Bitmap.createScaledBitmap(imageBitmap, (int)(imageBitmap.getWidth()*r) , (int)(imageBitmap.getHeight()*r), true);
                imageView.setImageBitmap(imageBitmap);
                annotations = new ArrayList<>();

                annotationAdapter = new AnnotationAdapter(annotations);
                recyclerViewAnnotation.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewAnnotation.setAdapter(annotationAdapter);
                return true;
            }

            return false;
        });



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.radioButton_cercle:
                        rep = "cercle";
                        break;
                    case R.id.radioButton_numero:
                        rep = "numero";
                        break;
                    case R.id.radioButton_fleche_bas:
                        rep = "flecheBas";
                        break;
                    case R.id.radioButton_fleche_haut:
                        rep = "flecheHaut";
                        break;
                    case R.id.radioButton_fleche_droite:
                        rep = "flecheDroite";
                        break;
                    case R.id.radioButton_fleche_gauche:
                        rep = "flecheGauche";
                        break;
                    case R.id.radioButton_texte:
                        rep = "texte";
                        break;
                    case R.id.radioButton_note:
                        rep = "note";
                        break;

                }

            }
        });


        radioGroupColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.radioButton_red:
                        repColor= "red";
                        break;
                    case R.id.radioButton_yellow:
                        repColor= "yellow";

                        break;
                    case R.id.radioButton_black:
                        repColor= "black";

                        break;
                    case R.id.radioButton_blue:
                        repColor= "blue";
                        break;
                    case R.id.radioButton_green:
                        repColor= "green";
                        break;

                }

            }
        });



        imageView.setOnTouchListener((v, event) -> {

            ajoutDessin(event.getX(),event.getY(),rep);

            return false;
        });

    }

    private void ajoutDessin(float x, float y,String rep) {

        Bitmap  bitmap =((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Bitmap drawableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(drawableBitmap);
        Paint paint = new Paint();

        switch (repColor){
            case "red":
                paint.setColor(Color.RED);
                break;
            case "yellow":
                paint.setColor(Color.YELLOW);

                break;
            case "black":
                paint.setColor(Color.BLACK);

                break;
            case "blue":
                paint.setColor(Color.BLUE);
                break;

            case "green":
                paint.setColor(Color.GREEN);
                break;
        }


        switch (rep){
            case "note" :
                Dialog dialog = new Dialog(AnnotationActivity.this);
                dialog.setContentView(R.layout.alerte_annotation_carter);
                Button buttonValide = dialog.findViewById(R.id.button_valide_alesage_carter);
                annotationAdapter = new AnnotationAdapter(annotations);
                recyclerViewAnnotation.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewAnnotation.setAdapter(annotationAdapter);

                buttonValide.setOnClickListener(v -> {
                    editTextAnnotation = dialog.findViewById(R.id.editText_alerte_nom_alesage);
                    editTextDiametreAlesageCarter = dialog.findViewById(R.id.editText_alerte_diametre_alesage_carter);
                    editTextTypeAlesageCarter = dialog.findViewById(R.id.editText_alerte_type_alesage_carter);


                    /*Portee portee = new Portee();
                    annotations.add(portee);*/
                    AlesageCarter alesageCarter = new AlesageCarter() ;
                    alesageCarter.setNomAlesageCarter(editTextAnnotation.getText().toString());
                    alesageCarter.setDiametreAlesageCarter(editTextDiametreAlesageCarter.getText().toString());
                    alesageCarter.setType(editTextTypeAlesageCarter.getText().toString());
                    AlesagesCarterManager  alesagesCarterManager = new AlesagesCarterManager(AnnotationActivity.this);
                    alesagesCarterManager.insertionAlesageCarter(alesageCarter);
                    annotations.add(alesageCarter);
                    //boolean insertData = bddLocale.insertionPetitesFourniture(petitesFournitures);
                    annotationAdapter.notifyDataSetChanged();


                    if(annotations != null) {
                        cptNote++;
                        paint.setColor(Color.RED);
                        paint.setTextSize(120);
                        canvas.drawText(cptNote + "", x, y, paint);
                        imageView.setImageBitmap(drawableBitmap);
                        annotationAdapter = new AnnotationAdapter(annotations);

                        recyclerViewAnnotation.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerViewAnnotation.setAdapter(annotationAdapter);
                        dialog.dismiss();
                    }else{
                        Toast.makeText(this, "Saisissez une valeur ou annulez", Toast.LENGTH_LONG).show();
                    }

                });
                dialog.show();
                break;

            case "flecheBas" :

                paint.setStrokeWidth(10);
                float[] pts ={x,y,x,y-150,x,y,x-30,y-40,x,y,x+30,y-40};
                canvas.drawLines(pts,paint);
                imageView.setImageBitmap(drawableBitmap);
                break;

            case "flecheHaut" :

                paint.setStrokeWidth(10);
                float[] pts1 ={x,y,x,y+150,x,y,x-30,y+40,x,y,x+30,y+40};
                canvas.drawLines(pts1,paint);
                imageView.setImageBitmap(drawableBitmap);
                break;

            case "flecheDroite" :

                paint.setStrokeWidth(10);
                float[] pts2 = {x,y,x-150,y,x,y,x-30,y-40,x,y,x-30,y+40};
                canvas.drawLines(pts2,paint);
                imageView.setImageBitmap(drawableBitmap);
                break;

            case "flecheGauche" :

                paint.setStrokeWidth(10);
                float[] pts3 ={x,y,x+150,y,x,y,x+30,y-40,x,y,x+30,y+40};
                canvas.drawLines(pts3,paint);
                imageView.setImageBitmap(drawableBitmap);
                break;

            case "numero" :
                cptNumero++;

                paint.setTextSize(120);
                canvas.drawText(cptNumero + "", x, y, paint);
                imageView.setImageBitmap(drawableBitmap);
                break;

            case "cercle" :

                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(10);
                canvas.drawCircle(x, y, 80, paint);
                imageView.setImageBitmap(drawableBitmap);
                break;

            case "texte" :
                Dialog dialog1 = new Dialog(AnnotationActivity.this);
                dialog1.setContentView(R.layout.alerte_annotation_carter);
                buttonValide = dialog1.findViewById(R.id.button_valide_alesage_carter);


                buttonValide.setOnClickListener(v -> {
                    editTextAnnotation = dialog1.findViewById(R.id.editText_alerte_nom_alesage);
                    String ann = editTextAnnotation.getText().toString();

                    if(ann != null) {


                        paint.setTextSize(200);
                        paint.setColor(Color.BLUE);
                        canvas.drawText(ann , x, y, paint);
                        imageView.setImageBitmap(drawableBitmap);

                        dialog1.dismiss();
                    }else{
                        Toast.makeText(this, "Saisissez une annotation", Toast.LENGTH_LONG).show();
                    }

                });
                dialog1.show();
                break;
        }


    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photoFile = null;
        try {
            photoFile = File.createTempFile("photo"+timeStamp, ".jpg",storageDir);
            photoPath = photoFile.getAbsolutePath();
            Uri photoURI = FileProvider.getUriForFile(AnnotationActivity.this,
                    AnnotationActivity.this.getApplicationContext().getPackageName()+".provider",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (IOException ex) {

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            DisplayMetrics metrics = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            float h = metrics.heightPixels*1f;
            float w = metrics.widthPixels*1f;

            Bitmap imageBitmap = BitmapFactory.decodeFile(photoPath);
            float rh=h/imageBitmap.getHeight();
            float rw = w/imageBitmap.getWidth();
            float r = Math.min(rh, rw);
            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, (int)(imageBitmap.getWidth()*r) , (int)(imageBitmap.getHeight()*r), true);
            imageView.setImageBitmap(imageBitmap);

        }
    }

    public void save(){

        BddLocale bddLocale = ConnexionLocalController.getInstance(this);


            Bitmap  bitmap =((BitmapDrawable) imageView.getDrawable()).getBitmap();
            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "nom image ", "photo avec annotation");

    }

}

