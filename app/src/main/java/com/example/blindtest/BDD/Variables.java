package com.example.blindtest.BDD;

import android.app.Application;

import com.example.blindtest.Classes.Membre;
import com.example.blindtest.Classes.Niveau;
import com.example.blindtest.Classes.Theme;

public class Variables extends Application {

    //contient les variables de session de l'application
    private String someVariable;
    private int idSelectedTheme;
    private String libelleSelectedTheme;
    private Theme selectedTheme;
    private Membre membreConnecte;
    private Niveau selectedNiveau;

    public String getSomeVariable() {
        return someVariable;
    }
//Utilisation : String s = ((Variables) this.getApplication()).getSomeVariable();

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }
   // Utilisation ((Variables) this.getApplication()).setSomeVariable("foo");

    public int getIdSelectedTheme() {
        return idSelectedTheme;
    }

    public void setIdSelectedTheme(int idSelectedTheme) {
        this.idSelectedTheme = idSelectedTheme;
    }

    public String getLibelleSelectedTheme() {
        return libelleSelectedTheme;
    }

    public void setLibelleSelectedTheme(String libelleSelectedTheme) {
        this.libelleSelectedTheme = libelleSelectedTheme;
    }

    public Theme getSelectedTheme() {
        return selectedTheme;
    }

    public void setSelectedTheme(Theme selectedTheme) {
        this.selectedTheme = selectedTheme;
    }

    public Membre getMembreConnecte() {
        return membreConnecte;
    }

    public void setMembreConnecte(Membre membreConnecte) {
        this.membreConnecte = membreConnecte;
    }

    public Niveau getSelectedNiveau() {
        return selectedNiveau;
    }

    public void setSelectedNiveau(Niveau selectedNiveau) {
        this.selectedNiveau = selectedNiveau;
    }
}
