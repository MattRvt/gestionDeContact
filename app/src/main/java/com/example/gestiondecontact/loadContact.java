package com.example.gestiondecontact;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class loadContact extends AppCompatActivity {

    private int contactID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        Bundle extras = getIntent().getExtras();
        this.contactID = extras.getInt("CONTACT_ID");
        loadValues();
    }

    private void loadValues() {

        ContactsManager contacts = new ContactsManager(this);
        Contact contact = contacts.getContact(contactID);

        EditText textEdit;

        textEdit = (EditText) findViewById(R.id.nom);
        textEdit.setText(contact.getNom());

        textEdit = (EditText) findViewById(R.id.prenom);
        textEdit.setText(contact.getPrenom());

        textEdit = (EditText) findViewById(R.id.telephonePortable);
        textEdit.setText(contact.getTelephonePortable());

        textEdit = (EditText) findViewById(R.id.telephoneFixe);
        textEdit.setText(contact.getTelephonePortable());

        textEdit = (EditText) findViewById(R.id.email);
        textEdit.setText(contact.getEmail());

        textEdit = (EditText) findViewById(R.id.dateDeNaiscance);
        textEdit.setText(contact.getDateDeNaiscance());

        textEdit = (EditText) findViewById(R.id.addresse);
        textEdit.setText(contact.getAddresse());


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
        contact.setID(this.contactID);
        int i = contacts.updateContact(contact);
        Log.i("BDD", i + " lignes modifier");
        finish();
    }
}
