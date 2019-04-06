package com.example.blindtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.blindtest.BDD.DatabaseManager;
import com.example.blindtest.BDD.PartieAdapter;
import com.example.blindtest.BDD.Variables;
import com.example.blindtest.Classes.Membre;
import com.example.blindtest.Classes.Partie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;
    private ListView lvHistorique;
    private List<Partie> parties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_historique);
        databaseManager = new DatabaseManager( this);
        lvHistorique = findViewById(R.id.lvHistorique);
       // ListView lvHistorique = (ListView) findViewById(R.id.lvHistorique);
        super.onCreate(savedInstanceState);
        Membre membre = ((Variables) this.getApplication()).getMembreConnecte();
        try
        {
            parties = databaseManager.searchPartiesByMember(membre.getId());
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
       ArrayList<Partie> parties2 = new ArrayList<Partie>(parties);

        //   adapter = new ArrayAdapter<String>(this, R.layout.list-items     //   lvHistorique.addHeaderView(View);
        //Récupération de la liste des personnes
       // ArrayList<Partie> listP = parties;
        //Création et initialisation de l'Adapter pour les personnes
        PartieAdapter adapter = new PartieAdapter(this, parties2);
        //Récupération du composant ListView
        //Initialisation de la liste avec les données
        lvHistorique.setAdapter(adapter);
    }
}
