package com.example.blindtest.Classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable( tableName = "T_Niveau")
public class Niveau {
    @DatabaseField( columnName = "id", generatedId = true)
    private int id;
    public Niveau()
    {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

