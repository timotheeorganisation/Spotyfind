package com.example.blindtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.blindtest.BDD.DatabaseManager;
import com.example.blindtest.Classes.Membre;

import java.util.Date;
import java.util.regex.Pattern;

public class RegistrateActivity extends AppCompatActivity {

    private EditText pseudo;
    private EditText password1;
    private EditText password2;
    private EditText email;
    private Button   btnInscription;
    private DatabaseManager databaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrate);

        pseudo = (EditText) findViewById(R.id.edtPseudo);
        password1 = (EditText) findViewById(R.id.edtPassword);
        password2 = (EditText) findViewById(R.id.edtPassword2);
        email = (EditText) findViewById(R.id.edtEmail);
        btnInscription = (Button) findViewById(R.id.btnInscription);
        databaseManager = new DatabaseManager( this );

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    String login = pseudo.getText().toString();
                    String mdp1 = password1.getText().toString();
                    String mdp2 = password2.getText().toString();
                    String mail = email.getText().toString();

                    if (!mdp1.equals(mdp2) || login.equals("") || mail.equals("") || mdp1.equals("") || mdp1.contains(" "))
                    {
                        new AlertDialog.Builder(RegistrateActivity.this)
                                .setTitle("Erreur")
                                .setMessage("Les mots de Passe doivent être identiques et tous les champs doivent être renseignés")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                }).create().show();
                    }
                    else if(!checkEmail(mail)) {
                    new AlertDialog.Builder(RegistrateActivity.this)
                            .setTitle("Erreur")
                            .setMessage("email doit etre valide")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).create().show();
                }
                    else
                    {
                        //Encryption mdp
                       /* byte[] md5input = mdp1.getBytes();
                        BigInteger md5Data = null;
                        try  {
                            md5Data = new BigInteger(un,md5.encryptMD5(md5input));
                        }
                        catch (Exception e)  {
                            e.printStackTrace();
                        }
                        String md5Str = md5Data.toString(16);
                        if(md5Str.length()< 32 )
                        {
                            md5Str = 0 + md5Str;
                        }
*/
                        //insertion Base du nouveau Membre
                        databaseManager.insertMembre(new Membre(login, mdp1, mail, new Date(), false));
                        Intent loginActivity = new Intent(RegistrateActivity.this, LoginActivity.class);
                        startActivity(loginActivity);
                    }
                }
                catch(Exception e)
                {
                    new AlertDialog.Builder(RegistrateActivity.this)
                            .setTitle("Erreur")
                            .setMessage("Erreur" + e.getMessage())
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create().show();
                }
            }
        });
    }

    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
}
