package com.example.blindtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.blindtest.BDD.DatabaseManager;
import com.example.blindtest.Classes.Membre;
import com.example.blindtest.Classes.Niveau;
import com.example.blindtest.Classes.Question;
import com.example.blindtest.Classes.Theme;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;
    private Button btn, btnInscription;
    private Button btnScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //récupération vue concernée
        setContentView(R.layout.activity_main);

        //récupération objets
        btn = (Button) findViewById(R.id.btnSeConnecter);
        btnInscription = findViewById(R.id.btnInscription);
        databaseManager = new DatabaseManager(this);
        //requêtes

     /* databaseManager.insertMembre(new Membre("vn", "vn", "vn", new Date(), true));
        databaseManager.insertMembre(new Membre("user", "user", "user@user.fr", new Date(), false));
        databaseManager.insertTheme(new Theme("Classique"));
        databaseManager.insertTheme(new Theme("Cinema"));
        databaseManager.insertTheme(new Theme("Series"));
        databaseManager.insertNiveau(new Niveau());
        databaseManager.insertNiveau(new Niveau());
        databaseManager.insertNiveau(new Niveau());*/
        List<Membre> membres = databaseManager.readMembres();
        Log.i("membres", membres.get(0).getMail());
        List<Question> questions = databaseManager.readQuestions();

        btn.setEnabled(true);
        //evenement on click )=> changement de fenetre vers login

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginActivity);
            }
        });

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrateActivity = new Intent(MainActivity.this, RegistrateActivity.class);
                startActivity(registrateActivity);
            }
        });
    }
}
