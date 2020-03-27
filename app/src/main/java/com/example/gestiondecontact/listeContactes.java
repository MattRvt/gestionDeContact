package com.example.gestiondecontact;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.gestiondecontact.ContactsManager.KEY_NOM;
import static com.example.gestiondecontact.ContactsManager.KEY_prenom;

public class listeContactes extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> userList;
    private BaseAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_contactes);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.icons8_add_user_male_48px);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewConact();
            }
        });

    }

    private void loadContacts(){
        ContactsManager contactsManager = new ContactsManager(this);
        this.userList = contactsManager.getContacts(KEY_NOM,false);
        String[] from = new String[contactsManager.getContactsAtributs().size()];
        from = contactsManager.getContactsAtributs().toArray(from);


        this.adapter = new SimpleAdapter(listeContactes.this, userList, R.layout.list_row, from, new int[]{0,R.id.nom, R.id.prenom});
        ListView lv = (ListView) findViewById(R.id.user_list);

        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.e("LISTVIEW: ", "position= " + position + " id= "+ id);
                DisplayContact(position+1);

            }
        });
    }

    private void updateContacts(String orderKey){

        //this.adapter.notifyDataSetChanged();
        ContactsManager contactsManager = new ContactsManager(this);
        //sthis.userList.clear();
        this.userList = contactsManager.getContacts(orderKey,false);
        adapter.notifyDataSetChanged();

    }



    private void DisplayContact(int position) {
        Intent intent = new Intent(this, loadContact.class);
        intent.putExtra("CONTACT_ID", position);
        startActivity(intent);
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

    public void orderByName(View view) {
        updateContacts(KEY_prenom);
    }

    public void orderByFamilyName(View view) {

        updateContacts(KEY_NOM);
    }
}




