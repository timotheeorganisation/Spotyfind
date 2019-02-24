package com.example.blindtest.Classes;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
@DatabaseTable( tableName = "T_Membre")
public class Membre {
    @DatabaseField( columnName = "id", generatedId = true)
    private int id;
    @DatabaseField
    private String pseudo;
    @DatabaseField
    private String mdp;
    @DatabaseField
    private String mail;
    @DatabaseField
    private Date dateInscription;
    @DatabaseField
    private boolean isAdmin;

    public Membre()
    {
    }

    public Membre(String pseudo, String mdp, String mail, Date dateInscription, boolean isAdmin) {
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.mail = mail;
        this.dateInscription = dateInscription;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    @Override
    public String toString() {
        return pseudo;
    }
}
