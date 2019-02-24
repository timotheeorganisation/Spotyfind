package com.example.blindtest.BDD;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.blindtest.Classes.Membre;
import com.example.blindtest.Classes.Niveau;
import com.example.blindtest.Classes.Question;
import com.example.blindtest.Classes.Theme;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "Blindtest.db";
    private static final int DATABASE_VERSION = 6;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "mail";
    private static final String COLUMN_PASS = "mdp";
    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Membre.class);
            TableUtils.createTableIfNotExists(connectionSource, Theme.class);
            TableUtils.createTableIfNotExists(connectionSource, Niveau.class);
            TableUtils.createTableIfNotExists(connectionSource, Question.class);
            Log.e("DATABASE", "Base bien crée");
        }
        catch(Exception exception)
        {
            Log.e("DATABASE", "Erreur dans la création de la base", exception);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{
            TableUtils.dropTable(connectionSource, Membre.class, true);
         //   TableUtils.dropTable(connectionSource, Theme.class, true);
            TableUtils.createTableIfNotExists(connectionSource, Theme.class);
            TableUtils.createTableIfNotExists(connectionSource, Niveau.class);
            TableUtils.createTableIfNotExists(connectionSource, Question.class);
            onCreate(database, connectionSource);
            Log.i("DATABASE", "Base bien modifié");
        }
        catch(Exception exception)
        {
            Log.e("DATABASE", "Erreur dans la création de la base", exception);
        }
    }

    public void insertMembre(Membre membre) {
        try {
            Dao<Membre, Integer> dao = getDao(Membre. class);
            dao.create(membre);
            ContentValues values = new ContentValues();
            //values.put(COLUMN_EMAIL, membre.getMail());
            //values.put(COLUMN_PASS, membre.getMdp());

            Log.i("DATABASE", "insertions ok");
        }
        catch(Exception exception)
        {
            Log.e("DATABASE", "Erreur d'insertion dans la base", exception);
        }
    }

    public List<Membre> readMembres(){
        try {
            Dao<Membre, Integer> dao = getDao(Membre.class);
            List<Membre> membres =  dao.queryForAll();
            Log.i("DATABASE", "insertions ok");
            return membres;
        }
        catch(Exception exception)
        {
            Log.e("DATABASE", "Erreur de consult dans la base", exception);
            return null;
        }
    }

    public void insertTheme(Theme theme) {
        try {
            Dao<Theme, Integer> dao = getDao(Theme. class);
            dao.create(theme);
            ContentValues values = new ContentValues();

            Log.i("DATABASE", "insertions ok");
        }
        catch(Exception exception)
        {
            Log.e("DATABASE", "Erreur d'insertion dans la base", exception);
        }
    }
    public List<Theme> readThemes(){
        try {
            Dao<Theme, Integer> dao = getDao(Theme.class);
            List<Theme> themes =  dao.queryForAll();
            Log.i("DATABASE", "insertions themes ok");
            return themes;
        }
        catch(Exception exception)
        {
            Log.e("DATABASE", "Erreur de consult dans la base", exception);
            return null;
        }
    }

    public void insertNiveau(Niveau niveau) {
        try {
            Dao<Niveau, Integer> dao = getDao(Niveau. class);
            dao.create(niveau);
            ContentValues values = new ContentValues();

            Log.i("DATABASE", "insertions ok");
        }
        catch(Exception exception)
        {
            Log.e("DATABASE", "Erreur d'insertion dans la base", exception);
        }
    }
    public List<Niveau> readNiveaux(){
        try {
            Dao<Niveau, Integer> dao = getDao(Niveau.class);
            List<Niveau> niveaux =  dao.queryForAll();
            Log.i("DATABASE", "insertions themes ok");
            return niveaux;
        }
        catch(Exception exception)
        {
            Log.e("DATABASE", "Erreur de consult dans la base", exception);
            return null;
        }
    }

    public void insertQuestion(Question question) {
        try {
            Dao<Question, Integer> dao = getDao(Question. class);
            dao.create(question);
            Log.i("DATABASE", "insertions ok");
        }
        catch(Exception exception)
        {
            Log.e("DATABASE", "Erreur d'insertion dans la base", exception);
        }
    }

   public Theme searchTheme(Integer id) throws SQLException {

       Dao<Theme, Integer> themeDao = getDao(Theme.class);
       Theme theme = themeDao.queryForId(id);

       return  themeDao.queryForId(id);
   }

    public Niveau searchNiveau(Integer id) throws SQLException {

        Dao<Niveau, Integer> themeDao = getDao(Niveau.class);
        Niveau niveau = themeDao.queryForId(id);

        return  themeDao.queryForId(id);
    }

    public Membre searchMembre(String email) throws SQLException {

        Dao<Membre, String> themeDao = getDao(Membre.class);
        QueryBuilder<Membre, String> st = themeDao.queryBuilder();
        st.where().like("mail", email);
        PreparedQuery<Membre> query = st.prepare();
        return themeDao.queryForFirst(query);


        /*
        Dao<Membre, Integer> themeDao = getDao(Membre.class);
        return  themeDao.queryRa()*/
    }

//fonction de connexion
    public boolean getUser(String email, String pass)
    {
        String selectQuery = "select * from T_MEMBRE  where " + COLUMN_EMAIL + " = " + "'" + email + "'" + "and "
            + COLUMN_PASS + "="
        + " '" +pass+ "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0 )
        {
            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    public Cursor getUserDetails(String email, String pass)
    {
        String selectQuery = "select id from T_MEMBRE  where " + COLUMN_EMAIL + " = " + "'"+email +"'" + "and "
                + COLUMN_PASS + "="
                + " '" +pass+ "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        Log.i("DATABASE", "insertions themes ok" + cursor.moveToFirst());

            return cursor;


    }

}