package com.example.blindtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.blindtest.BDD.DatabaseManager;
import com.example.blindtest.BDD.Variables;
import com.example.blindtest.Classes.Niveau;
import com.example.blindtest.Classes.Theme;

import java.sql.SQLException;
import java.util.List;

public class NiveauActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;
    private TextView edtSelectedTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveau);
        LinearLayout layoutTheme = (LinearLayout) findViewById(R.id.layout);
        layoutTheme.removeAllViewsInLayout();
        databaseManager = new DatabaseManager( this );
        List<Niveau> niveaux = databaseManager.readNiveaux();

   //     edtSelectedTheme = findViewById(R.id.selectedTheme);
     //   edtSelectedTheme.setText(this.getIntent().getExtras().toString("theme"));

        //  Theme theme = ((Variables) this.getApplication()).getSelectedTheme();
       Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            //on enregistre l'oid et le libellé du thême
            String leString = extras.getString("theme");
            String libelle = extras.getString("libelleTheme");
           String toast = (leString)+ " " + (libelle);

            if(leString != null) {
                ((Variables) this.getApplication()).setIdSelectedTheme(Integer.parseInt(leString));

                ((Variables) this.getApplication()).setLibelleSelectedTheme(libelle);
                try {
                    Theme theme = databaseManager.searchTheme(Integer.parseInt(leString));
                    ((Variables) this.getApplication()).setSelectedTheme(theme);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            edtSelectedTheme.setText(("pas de sting"));
        }

        for(final Niveau niveau : niveaux)
        {
            final Button btnText = new Button(this);
            btnText.setBackgroundColor(0xFFCFDBEC);
            String lvl = " Niveau "+ String.valueOf (niveau.getId());
            btnText.setText(lvl);
            btnText.setId(niveau.getId());
            LinearLayout.LayoutParams layoutParam =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParam.setMargins(0,10,0,10);
            // layoutParam.setMargins(10,mar,10,mar+theme.getId());
            btnText.setLayoutParams(layoutParam);
            layoutTheme.addView(btnText);

            btnText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer x = btnText.getId();
                    Intent niveauActivity = new Intent(NiveauActivity.this, PartieActivity.class);
                    niveauActivity.putExtra("niveau",x);
                    startActivity(niveauActivity);
                }
            });
        }
    }
}
