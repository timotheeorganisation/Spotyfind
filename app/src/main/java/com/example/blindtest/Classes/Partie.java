package com.example.blindtest.Classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable( tableName = "T_Partie")
public class Partie {
    @DatabaseField( columnName = "idPartie", generatedId = true)
    private int idPartie;
    @DatabaseField
    private Date datePartie;
    @DatabaseField
    private Integer score;
    @DatabaseField (canBeNull = false, foreign = true, foreignColumnName = "id", foreignAutoCreate = true)
    private Theme theme;
    @DatabaseField (canBeNull = false, foreign = true, foreignColumnName = "id", foreignAutoCreate = true)
    private Niveau niveau;
    @DatabaseField (canBeNull = false, foreign = true, foreignColumnName = "id", foreignAutoCreate = true)
    private Membre membre;

    public Partie() {
    }

    public Partie(Date datePartie, Integer score, Theme theme, Niveau niveau, Membre membre) {
        this.datePartie = datePartie;
        this.score = score;
        this.theme = theme;
        this.niveau = niveau;
        this.membre = membre;
    }

    public int getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
    }

    public Date getDatePartie() {
        return datePartie;
    }

    public void setDatePartie(Date datePartie) {
        this.datePartie = datePartie;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }
}
