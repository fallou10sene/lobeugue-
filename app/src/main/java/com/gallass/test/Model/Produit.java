package com.gallass.test.Model;

import android.support.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Produit extends RealmObject {

    @PrimaryKey
    private String id;
   // public int productImage;
    private String nomProduit;
    private int quantite;
    private int prix;


    /*public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "nomProduit='" + nomProduit + '\'' +
                ", quantite=" + quantite +
                ", prix=" + prix +
                '}';
    }


    public Produit(){
    }
}
