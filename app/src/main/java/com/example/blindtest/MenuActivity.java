package com.example.blindtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.blindtest.BDD.Session;
import com.example.blindtest.BDD.Variables;
import com.example.blindtest.Classes.Membre;

public class MenuActivity extends AppCompatActivity {

    private Button btnLogout, btnJouer, btnHistoriqueParties, btnClassementThemes;
    private Session session;
    private TextView ses;
    private static final String PREFS_NAME = "PreName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnHistoriqueParties = findViewById(R.id.btnHistoriqueParties);
        btnClassementThemes = findViewById(R.id.btnClassementThemes);
        btnJouer = findViewById(R.id.btnJouer);
        ses = findViewById(R.id.edtSession);
        int PRIVATE_MODE =0;

        session = new Session((this));

        if(!session.loggedin())
        {
            logout();
        }
        btnLogout = (Button)findViewById(R.id.btnLogout);

        SharedPreferences prefs = getApplicationContext()
                .getSharedPreferences("MyPref", 0);
        String s = ((Variables) this.getApplication()).getSomeVariable();
        ses.setText("Bienvenue   " + s);
        ses.setHintTextColor(Color.BLACK);

        Membre membre = ((Variables) this.getApplication()).getMembreConnecte();


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        btnJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent themeActivity = new Intent(MenuActivity.this, ThemeActivity.class);
                startActivity(themeActivity);
            }
        });

        btnClassementThemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent classementActivity = new Intent(MenuActivity.this, ClassementActivity.class);
                startActivity(classementActivity);
            }
        });

        btnHistoriqueParties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historiqueActivity = new Intent(MenuActivity.this, HistoriqueActivity.class);
                startActivity(historiqueActivity);
            }
        });
    }

    private void logout()
    {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MenuActivity.this, LoginActivity.class));
    }
}
