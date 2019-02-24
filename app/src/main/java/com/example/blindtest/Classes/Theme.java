package com.example.blindtest.Classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable( tableName = "T_Theme" )
public class Theme  {

    @DatabaseField( columnName = "id", generatedId = true)
    private int id;
    @DatabaseField
    private String libelle;


    public Theme()
    {
    }

    public Theme(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
