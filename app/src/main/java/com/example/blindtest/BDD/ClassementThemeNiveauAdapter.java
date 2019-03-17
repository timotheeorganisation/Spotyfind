package com.example.blindtest.BDD;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.blindtest.Classes.Partie;
import com.example.blindtest.R;

import java.util.List;

public class ClassementThemeNiveauAdapter extends BaseAdapter {

    // Une liste de personnes
    private List<Partie> mListP;

    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    public ClassementThemeNiveauAdapter(Context context, List<Partie> aListP) {
        mContext = context;
        mListP = aListP;
        mInflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return mListP.size();
    }
    public Object getItem(int position) {
        return mListP.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;
        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.activity_classification, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }
        //(2) : Récupération des TextView de notre layout
        TextView tvMembre = (TextView)layoutItem.findViewById(R.id.tvMembre);
        TextView tvDate = (TextView)layoutItem.findViewById(R.id.tvDate);
        TextView tvScore = (TextView)layoutItem.findViewById(R.id.tvScore);

        //(3) : Renseignement des valeurs
        tvMembre.setText(mListP.get(position).getMembre().getPseudo());
        tvDate.setText(String.valueOf(mListP.get(position).getDatePartie().toString()));
        tvScore.setText(String.valueOf(mListP.get(position).getScore()));

        //On retourne l'item créé.
        return layoutItem;
    }
}