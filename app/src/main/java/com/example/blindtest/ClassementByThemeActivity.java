package com.example.blindtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.blindtest.BDD.ClassementThemeNiveauAdapter;
import com.example.blindtest.BDD.DatabaseManager;
import com.example.blindtest.Classes.Partie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassementByThemeActivity extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private List<Partie> parties;
    private ListView lvHistorique;
    private Integer theme, niveau;
    private TextView tvLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseManager = new DatabaseManager(this);
        setContentView(R.layout.activity_classement_by_theme);
        lvHistorique = findViewById(R.id.lvHistorique);
        tvLabel = findViewById(R.id.tvLabel);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            theme = extras.getInt("theme");
            niveau = extras.getInt("niveau");
            tvLabel.setText("Classement du niveau" + String.valueOf(niveau));
        }
        try {
            parties = databaseManager.searchPartiesByThemeAndLevel(theme, niveau);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        ClassementThemeNiveauAdapter adapter = new ClassementThemeNiveauAdapter(this, parties);
        //Récupération du composant ListView
        //Initialisation de la liste avec les données
        lvHistorique.setAdapter(adapter);
    }
}
