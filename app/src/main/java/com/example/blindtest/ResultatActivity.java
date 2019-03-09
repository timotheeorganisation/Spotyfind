package com.example.blindtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultatActivity extends AppCompatActivity {
    private TextView edtScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        edtScore = findViewById(R.id.edtScore);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String leString = extras.getString("score");
            edtScore.setText(leString);
        }

        /*reste à faire :
        * Sélection questions en fonction theme et niveau choisis
        * Création partie avec membre theme, niveau et score
        * affichage classement du niveau dans Résultat
        * affichage classement, mes parties
        *
        * */
    }
}
