package com.gallass.test;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.gallass.test.Model.Produit;


import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RedirectActivity extends AppCompatActivity {
    // Array of strings...
    ArrayList<Produit> produitlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProduitAdapter mAdapter;
    private static final String TAG = "RedirectActivity";
    private String nomProduit;
    private int prix;
    private int quantite;
    private Button btnSuppr, btnMod;
    private ClipData.Item btnmybutton;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
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

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Produit> produitRealmResults = realm.where(Produit.class).findAll();
        for (Produit p:produitRealmResults){

            produitlist.add(p);
        }

        recyclerView = (RecyclerView) findViewById(R.id.idRecyclerView);
        mAdapter = new ProduitAdapter(produitlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // do something here
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }







}