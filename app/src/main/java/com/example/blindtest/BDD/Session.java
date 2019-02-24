package com.example.blindtest.BDD;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;
    private String Mail;

    public Session(Context ctx)
    {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("com.example.blindtest", Context.MODE_PRIVATE);
        this.editor = prefs.edit();
        editor.putString("id", "2");
        editor.commit();
    }

    public void setLoggedin(boolean loggedin)
    {
        editor.putBoolean("loggedInmode", loggedin);
        editor.commit();
    }

    public boolean loggedin()
    {
        return prefs.getBoolean("loggedInmode", false);
    }

    public void setMail(String mail)
    {
            this.Mail = mail;
    }

    public String getMail()
    {
        return this.Mail;
    }

}
