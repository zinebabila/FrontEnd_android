package com.example.tpjsonimage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Livre {
    private String nom;
    private String image;

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public Livre(String nom, String image) {
        this.nom = nom;
        this.image = image;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
