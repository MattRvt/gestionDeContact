package com.example.gestiondecontact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class newContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
    }

    public void annuler(View v) {
        finish();
    }

    public void valide(View v) {
        //sauvgarde le contacte
        ContactsManager contacts = new ContactsManager(this);
        contacts.open();
        EditText textEdit;

         textEdit = (EditText) findViewById(R.id.nom);
        String nom = textEdit.getText().toString();

        textEdit = (EditText) findViewById(R.id.prenom);
        String prenom = textEdit.getText().toString();

        textEdit = (EditText) findViewById(R.id.telephonePortable);
        String telephonePortable = textEdit.getText().toString();

        textEdit = (EditText) findViewById(R.id.telephoneFixe);
        String telephoneFixe = textEdit.getText().toString();

        textEdit = (EditText) findViewById(R.id.email);
        String email = textEdit.getText().toString();

        textEdit = (EditText) findViewById(R.id.dateDeNaiscance);
        String dateDeNaiscance = textEdit.getText().toString();

        textEdit = (EditText) findViewById(R.id.addresse);
        String addresse = textEdit.getText().toString();

        Contact contact = new Contact(nom, prenom, telephonePortable, telephoneFixe, email, dateDeNaiscance, addresse);

        contacts.addContact(contact);

        finish();
    }
}
