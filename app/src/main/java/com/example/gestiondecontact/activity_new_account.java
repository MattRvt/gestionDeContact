package com.example.gestiondecontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class activity_new_account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
    }

    public void save(View v) {
        if (chekInPut()){
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "compte correctement ajouté", duration);
            toast.show();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("mail",getMail());
            intent.putExtra("password",getPassWord());
            startActivity(intent);
        }
    }

    public boolean chekInPut() {
        String toastText = "";
        boolean logInValide = true;
        //check pswd
        String passWord = getPassWord();
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

    private String getPassWord() {
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
}
