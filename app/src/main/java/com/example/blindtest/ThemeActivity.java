package com.example.blindtest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.blindtest.BDD.DatabaseManager;
import com.example.blindtest.Classes.Theme;

import java.util.List;

public class ThemeActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        ScrollView croll = (ScrollView) findViewById((R.id.scrollView));
        LinearLayout layoutTheme = (LinearLayout) findViewById(R.id.layout);
        layoutTheme.removeAllViewsInLayout();
        databaseManager = new DatabaseManager(this);
        List<Theme> themes = databaseManager.readThemes();

        for (final Theme theme : themes) {

            int id = theme.getId();
            final Button btnText = new Button(this);
            btnText.setText((theme.getLibelle()));
            btnText.setId(theme.getId());
            btnText.setBackgroundColor(Color.GREEN) ;
            LinearLayout.LayoutParams layoutParam =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParam.setMargins(0, 10, 0, 10);
            btnText.setLayoutParams(layoutParam);
            layoutTheme.addView(btnText);

            btnText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer x = btnText.getId();
                    String libelleTheme = btnText.getText().toString();

                    Intent niveauActivity = new Intent(ThemeActivity.this, NiveauActivity.class);
                    niveauActivity.putExtra("theme", String.valueOf(x));
                    niveauActivity.putExtra("libelleTheme", libelleTheme);
                    startActivity(niveauActivity);
                }
            });
        }


    }
}
