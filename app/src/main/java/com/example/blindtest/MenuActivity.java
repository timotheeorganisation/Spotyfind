package com.example.blindtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.blindtest.BDD.Session;
import com.example.blindtest.BDD.Variables;

public class MenuActivity extends AppCompatActivity {

    private Button btnLogout, btnJouer, btnHistorique, btnClassement;
    private Session session;
    private TextView ses;
    private static final String PREFS_NAME = "PreName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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
        ses.setText(s);


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
    }

    private void logout()
    {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MenuActivity.this, LoginActivity.class));
    }
}
