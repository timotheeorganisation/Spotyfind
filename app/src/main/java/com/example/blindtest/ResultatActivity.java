package com.example.blindtest;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.blindtest.BDD.ClassementThemeNiveauAdapter;
import com.example.blindtest.BDD.DatabaseManager;
import com.example.blindtest.BDD.PartieAdapter;
import com.example.blindtest.BDD.Variables;
import com.example.blindtest.Classes.Membre;
import com.example.blindtest.Classes.Niveau;
import com.example.blindtest.Classes.Partie;
import com.example.blindtest.Classes.Question;
import com.example.blindtest.Classes.Reponse;
import com.example.blindtest.Classes.Theme;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultatActivity extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private String score;
    private ListView lvHistorique;
    private TextView tvScore;
    private List<Partie> parties;
    private List<Reponse> resultats;
    private Button btnRejouer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        lvHistorique = findViewById(R.id.lvHistorique);
        tvScore = findViewById(R.id.tvScore);
        LinearLayout layoutReponses = (LinearLayout) findViewById(R.id.layout);
        layoutReponses.removeAllViewsInLayout();
        btnRejouer = findViewById(R.id.btnRejouer);
        databaseManager = new DatabaseManager(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score = extras.getString("score");
        }
        tvScore.setText("Votre Score " + String.valueOf(score));
        Niveau niveauSelect = ((Variables) this.getApplication()).getSelectedNiveau();
        Theme theme = ((Variables) this.getApplication()).getSelectedTheme();
        Membre m = ((Variables) this.getApplication()).getMembreConnecte();
        databaseManager.insertPartie((new Partie(new Date(), Integer.parseInt(score), theme, niveauSelect, m)));
        try {
            resultats = databaseManager.searchReponseByQuestion(theme.getId(), niveauSelect.getId());

            parties = databaseManager.searchPartiesByThemeAndLevel(theme.getId(), niveauSelect.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Partie> parties2 = new ArrayList<Partie>(parties);

        ClassementThemeNiveauAdapter adapter = new ClassementThemeNiveauAdapter(this, parties2);
        lvHistorique.setAdapter(adapter);
        int cpt = 1;

        for(Reponse reponse :resultats)
        {
            int soundId = getResources().getIdentifier(reponse.getQuestion().getPathMp3(), "raw", getApplicationContext().getPackageName());
            final MediaPlayer mp = MediaPlayer.create(ResultatActivity.this, soundId);
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.height = 60;
            lp.width =400;
            TextView tvReponse = new TextView(this);
            tvReponse.setText("Question " + cpt + " : "+reponse.getLibelleReponse());
            cpt++;
            LinearLayout.LayoutParams layoutParam =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParam.setMargins(0,10,0,10);
            // layoutParam.setMargins(10,mar,10,mar+theme.getId());
            tvReponse.setLayoutParams(layoutParam);
            layoutReponses.addView(tvReponse);
        }

        btnRejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity = new Intent(ResultatActivity.this, MenuActivity.class);
                startActivity(Activity);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ResultatActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}

