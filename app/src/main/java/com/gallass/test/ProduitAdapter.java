package com.gallass.test;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gallass.test.Model.Produit;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class ProduitAdapter extends RecyclerView.Adapter<ProduitAdapter.MyViewHolder>{

    private ArrayList<Produit> produitList;
    private Realm realm;
    Context context;

    public ProduitAdapter(ArrayList<Produit> produitList) {
        this.produitList = produitList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageProductImage;
        public TextView tnomProduit, tquantite, tprix, tposition;
        private Button btnSuppr;


        public MyViewHolder(View itemView) {
            super(itemView);
            //imageProductImage= (ImageView) itemView.findViewById(R.id.idProductImage);
            tnomProduit = (TextView) itemView.findViewById(R.id.txtnomproduit);
            tquantite = (TextView) itemView.findViewById(R.id.txtquantite);
            tprix = (TextView) itemView.findViewById(R.id.txtprix);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        Produit produit = produitList.get(position);
       // holder.imageProductImage.setImageResource(produitList.get(position).getProductImage());
        holder.tnomProduit.setText(produit.getNomProduit());
        holder.tquantite.setText("Qt " + String.valueOf(produit.getQuantite()));
        holder.tprix.setText("Prix  " + String.valueOf(produit.getPrix()));


       /* holder.imageProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomProduit = produitList.get(position).getNomProduit().toString();
                Toast.makeText(context, nomProduit + " is selected", Toast.LENGTH_SHORT).show();
            }




        });*/

    }



    @Override
    public int getItemCount() {
        return produitList.size();
    }




}





