package com.segor.demontage.view;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;
import com.segor.demontage.R;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;
    private AccessoiresFragment accessoiresFragment;
    private ReducteurFragment reducteurFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        reducteurFragment = new ReducteurFragment();
        accessoiresFragment = new AccessoiresFragment();
        setSupportActionBar(toolbar);


       // getSupportFragmentManager().beginTransaction().replace(R.id.nav_reducteur , reducteurFragment).replace(R.id.nav_accessoires, accessoiresFragment).commit();
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_reducteur, R.id.nav_accessoires, R.id.nav_mobiles,R.id.nav_carter, R.id.nav_visserie ,R.id.nav_controles,
                R.id.nav_taches, R.id.nav_annotations,R.id.nav_pdf,R.id.nav_liste_accessoires, R.id.nav_expertise_carter, R.id.nav_synchro)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}