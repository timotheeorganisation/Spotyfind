 package com.example.blindtest;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.blindtest.BDD.DatabaseManager;
import com.example.blindtest.BDD.Session;
import com.example.blindtest.BDD.Variables;
import com.example.blindtest.Classes.Membre;

import java.sql.SQLException;


 public class LoginActivity extends AppCompatActivity   {

    private EditText edtPassword, edtEmail;
    private Button btnInscription, btnLogin;
    private DatabaseManager databaseManager;
    private Session session;
    private static final String PREFS_NAME = "PreName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtMail);
        edtPassword = findViewById(R.id.edtPassword);
        btnInscription = findViewById(R.id.btnInscription);
        btnLogin = findViewById(R.id.btnLogin);
        databaseManager = new DatabaseManager( this );
        session = new Session(this);
        int PRIVATE_MODE =0;

        SharedPreferences prefs = getApplicationContext()
                .getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("key_name", edtEmail.getText().toString());
        editor.commit();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrateActivity = new Intent(LoginActivity.this, RegistrateActivity.class);
                startActivity(registrateActivity);
            }

        });
    }

    private void login(){
        String email = edtEmail.getText().toString();
        String pass = edtPassword.getText().toString();
        if(databaseManager.getUser(email, pass))
        {
            databaseManager.getUserDetails(email, pass);
            ((Variables) this.getApplication()).setSomeVariable(email);
            try
            {
                Membre membreco = databaseManager.searchMembre(email);
                 ((Variables) this.getApplication()).setMembreConnecte(membreco);
            }
            catch (SQLException e)
            {
                e.printStackTrace();

            }
            session.setLoggedin(true);
            Intent registrateActivity = new Intent(LoginActivity.this, MenuActivity.class);
            registrateActivity.putExtra("email", email);
            startActivity(registrateActivity);
            finish();
        }
        else
        {
            new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("Erreur")
                    .setMessage("Erreur de connexion, veuillez rééssayer.")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).create().show();
        }
    }
}