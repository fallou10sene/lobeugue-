package com.gallass.test;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity1 extends AppCompatActivity {

        Toolbar toolbar;
        View button;
        private LinearLayout ListP;
        private LinearLayout SaveP;


        DrawerLayout drawerLayout;
        ActionBarDrawerToggle drawerToggle;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.tool_bar);



            ListP = (LinearLayout) findViewById(R.id.ListP);
            SaveP = (LinearLayout) findViewById(R.id.SaveP);

            ListP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity1.this, RedirectActivity.class);
                    startActivity(intent);
                }
            });

            SaveP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity1.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            this.toolbar = (Toolbar) findViewById(R.id.toolbar);

            //definir notre toolbar en tant qu'actionBar
            setSupportActionBar(toolbar);

            //afficher le bouton retour
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
            drawerToggle = new ActionBarDrawerToggle(this,this.drawerLayout,0,0);
            drawerLayout.addDrawerListener(this.drawerToggle);

        }

        //ne pas oublier de copier/coller ces 2 méthodes
        @Override
        protected void onPostCreate(Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
            // synchroniser le drawerToggle après la restauration via onRestoreInstanceState
            drawerToggle.syncState();
        }
        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            drawerToggle.onConfigurationChanged(newConfig);
        }

        private void afficherCacherToolbar() {
            if(toolbar.getAlpha() == 1){ //si alpha==1 alors elle est affichee

                //cacher
                toolbar.animate()
                        .alpha(0) //la rendre invisible
                        .translationY(-toolbar.getHeight()) //la déplacer vers le haut
                        .start();
            }
            else{ //si alpha==0 alors elle est cachee

                //afficher
                toolbar.animate()
                        .alpha(1) //la rendre visible
                        .translationY(0) //retour à la position d'origine
                        .start();
            }
        }

    }




