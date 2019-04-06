package com.example.blindtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.blindtest.BDD.ClassementThemeNiveauAdapter;
import com.example.blindtest.BDD.DatabaseManager;
import com.example.blindtest.Classes.Niveau;
import com.example.blindtest.Classes.Partie;
import com.example.blindtest.Classes.Theme;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassementActivity extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private List<Partie> parties;
    private ListView lvHistorique;
    private Button btnClassementTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseManager = new DatabaseManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        lvHistorique = findViewById(R.id.lvHistorique);
        btnClassementTheme = findViewById(R.id.btnClassementThemes);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<Theme> themes = databaseManager.readThemes();
        List<Niveau> niveaux = databaseManager.readNiveaux();
        List<String> categories = new ArrayList<>();
        List<String> categoriesNiveaux = new ArrayList<>();


        for (Niveau niveau : niveaux) {
            categoriesNiveaux.add(String.valueOf(niveau.getId()));
        }
        for (Theme theme : themes) {
            categories.add(theme.getLibelle());
        }
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        spinner.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapterNiveau = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesNiveaux);
        spinner2.setAdapter(dataAdapterNiveau);

        btnClassementTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Theme = spinner.getSelectedItem().toString();
                String Niveau = spinner2.getSelectedItem().toString();
                try {
                    Theme themeSelected = databaseManager.searchThemeByLibelle(Theme);
//                    ArrayList<Partie> parties2 = new ArrayList<Partie>(parties);
                    Intent ClassementActivity = new Intent(ClassementActivity.this, ClassementByThemeActivity.class);
                    ClassementActivity.putExtra("theme", themeSelected.getId());
                    ClassementActivity.putExtra("niveau", Integer.parseInt(Niveau));
                    startActivity(ClassementActivity);
                }
                    catch (SQLException e) {
                    e.printStackTrace();
                }

                Log.i("spinner", (Theme + Niveau));

            }
        });
    }

}

