package com.example.blindtest;

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
    private List<Partie> parties;
    private List<Reponse> resultats;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        lvHistorique = findViewById(R.id.lvHistorique);
        LinearLayout layoutReponses = (LinearLayout) findViewById(R.id.layout);
        layoutReponses.removeAllViewsInLayout();

        databaseManager = new DatabaseManager(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score = extras.getString("score");
        }
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

        //   adapter = new ArrayAdapter<String>(this, R.layout.list-items     //   lvHistorique.addHeaderView(View);
        //Récupération de la liste des personnes
        // ArrayList<Partie> listP = parties;
        //Création et initialisation de l'Adapter pour les personnes
        ClassementThemeNiveauAdapter adapter = new ClassementThemeNiveauAdapter(this, parties2);
        //Récupération du composant ListView
        //Initialisation de la liste avec les données
        lvHistorique.setAdapter(adapter);
        int cpt = 1;

        for(Reponse reponse :resultats)
        {
            int soundId = getResources().getIdentifier(reponse.getQuestion().getPathMp3(), "raw", getApplicationContext().getPackageName());
            final MediaPlayer mp = MediaPlayer.create(ResultatActivity.this, soundId);
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

            Button btnListen = new Button((this));
            btnListen.setText("Réécouter");
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.height = 60;
            lp.width =400;
            btnListen.setLayoutParams(lp);

            btnListen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.stop();
                    mp.release();
                    mp.reset();
                    try {
                        mp.prepare();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mp.start();
                }
            });
            layoutReponses.addView(btnListen);
            TextView tvReponse = new TextView(this);
            tvReponse.setText("Question " + cpt + " : "+reponse.getLibelleReponse());cpt++;
            LinearLayout.LayoutParams layoutParam =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParam.setMargins(0,10,0,10);
            // layoutParam.setMargins(10,mar,10,mar+theme.getId());
            tvReponse.setLayoutParams(layoutParam);
            layoutReponses.addView(tvReponse);
        }
    }
    /*reste à faire :
     * affichage classement du niveau dans Résultat DESIGN
     * voir résultat des questions dans Résultat DESIGN
     * affichage classement by theme/niveau avec combo box
     * mes parties
     * */
}

