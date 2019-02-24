package com.example.blindtest;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.blindtest.BDD.DatabaseManager;
import com.example.blindtest.BDD.Variables;
import com.example.blindtest.Classes.Membre;
import com.example.blindtest.Classes.Niveau;
import com.example.blindtest.Classes.Partie;
import com.example.blindtest.Classes.Question;
import com.example.blindtest.Classes.Theme;

import java.sql.SQLException;

public class PartieActivity extends AppCompatActivity {
    private TextView edtSelectedTheme;
    private DatabaseManager databaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie);
        edtSelectedTheme = findViewById(R.id.edtTest);
        databaseManager = new DatabaseManager(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int niveau = extras.getInt("niveau");
            Log.i("NIVEAU", String.valueOf(niveau));
            try {
                Niveau niv = databaseManager.searchNiveau((niveau));

                ((Variables) this.getApplication()).setSelectedNiveau(niv);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        int soundId = getResources().getIdentifier("vangelis", "raw", getApplicationContext().getPackageName());
        Niveau niveauSelect = ((Variables) this.getApplication()).getSelectedNiveau();
        Theme theme = ((Variables) this.getApplication()).getSelectedTheme();
        Membre m = ((Variables) this.getApplication()).getMembreConnecte();


       /* edtSelectedTheme.set(m.getPseudo());
        databaseManager.insertQuestion(new Question("vangelis","", theme, niveauSelect ));
*/
        MediaPlayer mediaPlayer = MediaPlayer.create(PartieActivity.this, soundId);
        mediaPlayer.start();
    }
    }

