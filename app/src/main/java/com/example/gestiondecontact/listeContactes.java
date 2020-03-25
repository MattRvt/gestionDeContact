package com.example.gestiondecontact;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class listeContactes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_contactes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.icons8_add_user_male_48px);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewConact();
            }
        });



        /*
        TODO: code inutile ?
        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listeContactes.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
    }

    private void loadContacts(){
        ContactsManager contactsManager = new ContactsManager(this);
        ArrayList<HashMap<String, String>> userList = contactsManager.getContacts();
        String[] from = new String[contactsManager.getContactsAtributs().size()];
        from = contactsManager.getContactsAtributs().toArray(from);
        ListAdapter adapter = new SimpleAdapter(listeContactes.this, userList, R.layout.list_row, from, new int[]{R.id.name, R.id.designation,R.id.location}); //TODO: modif dernier parametre
        ListView lv = (ListView) findViewById(R.id.user_list);
        lv.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadContacts();
    }

    private void openNewConact() {
        Intent intent = new Intent(this, newContact.class);
        startActivity(intent);
    }

}




