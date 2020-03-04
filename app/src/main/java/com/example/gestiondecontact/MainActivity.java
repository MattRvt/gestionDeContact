package com.example.gestiondecontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import accountGestions.Service_account_manager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Service_account_manager accounts = Service_account_manager.getInstance();
        Intent intent = this.getIntent();
        String mail = intent.getStringExtra("mail");
        String password = intent.getStringExtra("password");
        if (mail != null && password != null) {
            accounts.addAccount(mail, password);
        } else {
            Log.e("ajout de compte", "erreur");
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openNewAccountActivity(View v) {
        Intent intent = new Intent(this, activity_new_account.class);
        startActivity(intent);
    }

    public void connect(View v) {
        if (chekInPut()) {
            checkLogIn();
        }
    }

    /**
     * ouvre l'activié principal
     */
    public void openActivity() {
        Intent intent = new Intent(this, contactsListe.class);
        startActivity(intent);
    }

    /**
     * test la validité des ID
     */
    public void checkLogIn() {
        Service_account_manager accounts = Service_account_manager.getInstance();
        if (accounts.connect(getPassword(), getMail())) {
            openActivity();
        } else {
            String text = "Identifiant invalide";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    private String getPassword() {
        String motDePasse;
        TextView pswd = (TextView) findViewById(R.id.pswd);
        motDePasse = pswd.getText().toString();
        return motDePasse;
    }

    private String getMail() {
        String mail;
        TextView mailInPut = (TextView) findViewById(R.id.mail);
        mail = mailInPut.getText().toString();
        return mail;
    }

    /**
     * test le validité des entrées
     *
     * @return
     */
    public boolean chekInPut() {
        String toastText = "";
        boolean logInValide = true;
        //check pswd
        String passWord = getPassword();
        if (passWord.length() < 8) {
            toastText += "longeur de mot de passe invalide. \n";
            logInValide = false;
        }
        //check mail
        if (!isValid(getMail())) {
            toastText += "eMail invalide. \n";
            logInValide = false;
        }
        if (!logInValide) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, toastText, duration);
            toast.show();
        }
        return logInValide;
    }

    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
