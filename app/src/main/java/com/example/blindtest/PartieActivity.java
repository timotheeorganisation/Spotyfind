package com.example.blindtest;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.blindtest.BDD.DatabaseManager;
import com.example.blindtest.BDD.Variables;
import com.example.blindtest.Classes.Membre;
import com.example.blindtest.Classes.Niveau;
import com.example.blindtest.Classes.Partie;
import com.example.blindtest.Classes.Question;
import com.example.blindtest.Classes.Reponse;
import com.example.blindtest.Classes.Theme;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

public class PartieActivity extends AppCompatActivity {
    private TextView edtScore, textQuestion;
    private DatabaseManager databaseManager;
    private Button btnRep1, btnRep2, btnRep3, btnRep4;
    private boolean trouve;
    public int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie);
        edtScore = findViewById(R.id.edtScore);
        btnRep1 = findViewById(R.id.btnRep1);
        btnRep2 = findViewById(R.id.btnRep2);
        btnRep3 = findViewById(R.id.btnRep3);
        btnRep4 = findViewById(R.id.btnRep4);

        edtScore.setText("0");
        index = 0;


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
        Niveau niveauSelect = ((Variables) this.getApplication()).getSelectedNiveau();
        Theme theme = ((Variables) this.getApplication()).getSelectedTheme();
        Membre m = ((Variables) this.getApplication()).getMembreConnecte();
     /*   databaseManager.insertQuestion(new Question("vangelis_cinema_3_cinema_3", "",theme, niveauSelect));
        databaseManager.insertQuestion(new Question("barrylyndon_cinema_3", "",theme, niveauSelect));

       databaseManager.insertReponses(new Reponse("Pirates des Caraïbes", false, questions.get(0)));
       databaseManager.insertReponses(new Reponse("2001 L'odysée de l'Espace", false, questions.get(0)));
       databaseManager.insertReponses(new Reponse("Matrix", false, questions.get(0)));
       databaseManager.insertReponses(new Reponse("Conquest of Paradise", false, questions.get(0)));*/
        List<Question> questions = databaseManager.readQuestions();
        try {
            alimenter(questions);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void alimenter(final List<Question> questions) throws SQLException {
        Question question = questions.get(0);

        List<Reponse> reponses = databaseManager.searchReponses(question);

        Log.i("reponses : ", String.valueOf(reponses.size()));
        //recupération path de la musique
        String pathMp3 = question.getPathMp3();
        int soundId = getResources().getIdentifier(pathMp3, "raw", getApplicationContext().getPackageName());

        final MediaPlayer mp = MediaPlayer.create(PartieActivity.this, soundId);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // mp.setDataSource(getApplicationContext(), pathMp3);
        //    mp.setDataSource(getApplicationContext(), uri);
        try {
            mp.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mp.start();

        int myInt = (reponses.get(0).getBoolReponse()) ? 1 : 0;
        int myInt2 = (reponses.get(1).getBoolReponse()) ? 1 : 0;
        int myInt3 = (reponses.get(2).getBoolReponse()) ? 1 : 0;
        int myInt4 = (reponses.get(3).getBoolReponse()) ? 1 : 0;

        //  int rep = (reponses.get(0).getBoolReponse());
        btnRep1.setText(reponses.get(0).getLibelleReponse());
        btnRep1.setId(myInt);
        btnRep2.setText(reponses.get(1).getLibelleReponse());
        btnRep2.setId(myInt2);
        btnRep3.setText(reponses.get(2).getLibelleReponse());
        btnRep3.setId(myInt3);
        btnRep4.setText(reponses.get(3).getLibelleReponse());
        btnRep4.setId(myInt4);
        btnRep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.reset();
                trouve = true;
                int score = Integer.parseInt(edtScore.getText().toString());
                Log.i("bool", String.valueOf(btnRep1.getId()));
                String valeur = String.valueOf(btnRep1.getId());
                if (valeur.equals("1")) {
                    score += 10;
                    Log.i("trouvé", "yes paappapappapa");
                } else {
                    score -= 10;
                    Log.i("merde", "snif j'ai dur");
                }
                edtScore.setText(String.valueOf(score));
                suivant(questions);
            }
        });

        btnRep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.reset();
                trouve = true;
                int score = Integer.parseInt(edtScore.getText().toString());
                Log.i("bool", String.valueOf(btnRep2.getId()));
                String valeur = String.valueOf(btnRep2.getId());
                if (valeur.equals("1")) {
                    score += 10;
                    Log.i("trouvé", "yes paappapappapa");
                } else {
                    score -= 10;
                    Log.i("merde", "snif j'ai dur");
                }
                edtScore.setText(String.valueOf(score));
                suivant(questions);
            }
        });

        btnRep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.reset();
                trouve = true;
                Log.i("bool", String.valueOf(btnRep3.getId()));
                int score = Integer.parseInt(edtScore.getText().toString());
                String valeur = String.valueOf(btnRep3.getId());
                if (valeur.equals("1")) {
                    score += 10;
                    Log.i("trouvé", "yes paappapappapa");
                } else {
                    score -= 10;
                    Log.i("merde", "snif j'ai dur");
                }
                edtScore.setText(String.valueOf(score));
                suivant(questions);
            }
        });

        btnRep4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.reset();
                trouve = true;
                int score = Integer.parseInt(edtScore.getText().toString());
                Log.i("bool", String.valueOf(btnRep4.getId()));
                String valeur = String.valueOf(btnRep4.getId());
                if (valeur.equals("1")) {
                    score += 10;
                    Log.i("trouvé", "yes paappapappapa");
                } else {
                    score -= 10;
                    Log.i("merde", "snif j'ai dur");
                }
                edtScore.setText(String.valueOf(score));
                suivant(questions);

            }
        });

    }

    public void suivant(List<Question> questions) {
        if (questions.size() != 0 && index <= 5) {
            questions.remove(0);
            try {
                index++;
                Log.i("index", String.valueOf(index));
                alimenter(questions);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Intent ResultatActivity = new Intent(PartieActivity.this, ResultatActivity.class);
            ResultatActivity.putExtra("score", edtScore.getText().toString());
            startActivity(ResultatActivity);
            Log.i("end", "jeud fini");
        }
    }


}