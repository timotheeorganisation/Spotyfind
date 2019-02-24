package com.example.blindtest.Classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable( tableName = "T_Question")
public class Question {
    @DatabaseField( columnName = "idQuestion", generatedId = true)
    private int idQuestion;
    @DatabaseField
    private String pathMp3;
    @DatabaseField
    private String pathPicture;
    @DatabaseField (canBeNull = false, foreign = true, foreignColumnName = "id", foreignAutoCreate = true)
    private Theme theme;
    @DatabaseField (canBeNull = false, foreign = true, foreignColumnName = "id", foreignAutoCreate = true)
    private Niveau niveau;

    public Question() {}

    public Question(String pathMp3, String pathPicture, Theme theme, Niveau niveau) {
        this.pathMp3 = pathMp3;
        this.pathPicture = pathPicture;
        this.theme = theme;
        this.niveau = niveau;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getPathMp3() {
        return pathMp3;
    }



    public void setPathMp3(String pathMp3) {
        this.pathMp3 = pathMp3;
    }

    public String getPathPicture() {
        return pathPicture;
    }

    public void setPathPicture(String pathPicture) {
        this.pathPicture = pathPicture;
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
}
