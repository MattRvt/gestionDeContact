package com.example.gestiondecontact;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class loadContact extends AppCompatActivity implements OnMapReadyCallback{
    public GoogleMap map;
    private int contactID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        Bundle extras = getIntent().getExtras();
        this.contactID = extras.getInt("CONTACT_ID");

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        loadValues();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        EditText textEdit = (EditText) findViewById(R.id.addresse);
        String addresse = textEdit.getText().toString();
        upDateMap(addresse);
    }

    private void upDateMap(String addresse) {
        map.clear();
        Context context = this;

        Geocoder geocoder = new Geocoder(context);

        List<Address> list = null;
        try {
            list = geocoder.getFromLocationName(addresse, 1);
            if (!list.isEmpty()) {
                double latitude = list.get(0).getLatitude();
                double longitude = list.get(0).getLongitude();
                map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }


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
