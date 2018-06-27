
package com.gallass.test;


import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;


import com.gallass.test.Model.Produit;

import java.util.UUID;

import io.realm.Realm;


public class MainActivity extends AppCompatActivity {

    private Button enregistrer;
    private Realm realm;
    private String nomproduit;
    private int prix;
    private int quantite;
    private EditText editQuantite;
    private EditText editPrix;
    private EditText editNomProduit;
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private DrawerLayout.DrawerListener drawerToggle;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);

        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);
        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        editNomProduit = (EditText) findViewById(R.id.nomproduit);
        editQuantite = (EditText) findViewById(R.id.quantite);
        editPrix = (EditText) findViewById(R.id.prix);
        realm = Realm.getDefaultInstance();

        enregistrer = (Button) findViewById(R.id.enregistrer);
       enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save_into_database(
                editNomProduit.getText().toString().trim(),
                Integer.parseInt(editQuantite.getText().toString().trim()),
                Integer.parseInt(editPrix.getText().toString().trim()));

                Intent intent = new Intent(MainActivity.this, RedirectActivity.class);
                startActivity(intent);
                /*realm.beginTransaction();
                Produit produit = realm.createObject(Produit.class);
                produit.setNomProduit(nomproduit);
                produit.setPrix(quantite);
                produit.setQuantite(prix);
                realm.commitTransaction();
                */
            }
        });
    }
    private int getNextId() {
        int id = 1;
        try {
            id = (realm.where(Produit.class).max("id").intValue() + 1);
        }
        catch (NullPointerException e){

        }
        return id;
    }
    private void save_into_database(final String nomproduit, final int quantite, final int prix) {
        //realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Produit produit = bgRealm.createObject(Produit.class, UUID.randomUUID().toString());
                produit.setNomProduit(nomproduit);
                produit.setQuantite(quantite);
                produit.setPrix(prix);

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Log.v("database","les données ont étés enregistrés avec success");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.e("database",error.getMessage());
            }
        });
    }

    @Override protected void onDestroy() {

        super.onDestroy();
        realm.close();
    }

   /* public void Enregistre(View view) {

        Button btn1 = (Button) findViewById(R.id.enregistrer);
        AlphaAnimation alpha = new AlphaAnimation(0f, 1f);
        alpha.setDuration(500);
        btn1.startAnimation(alpha);

        //Recuperation donnee

        realm = Realm.getDefaultInstance();


        EditText editText1 = (EditText) findViewById(R.id.et1);
        String str = editText1.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.et2);
        int quantite = Integer.parseInt(editText2.getText().toString());

        EditText editText3 = (EditText) findViewById(R.id.et3);
        int prix = Integer.parseInt(editText3.getText().toString());
        //Fin recuperation
        Intent intent = new Intent(this, RedirectActivity.class);
        //Envoie donnee
        intent.putExtra("nomproduit", str);
        intent.putExtra("quantite", quantite);
        intent.putExtra("prix", prix);
        //Fin

        startActivity(intent);

    }
    public void Enregistre() {
        realm.close();
    }*/
}

