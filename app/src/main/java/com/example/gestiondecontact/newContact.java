package com.example.gestiondecontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class newContact extends AppCompatActivity implements OnMapReadyCallback {

    public GoogleMap map;
    private static final int STORAGE_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        EditText searchTo = (EditText) findViewById(R.id.addresse);
        searchTo.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText textEdit = (EditText) findViewById(R.id.addresse);
                String addresse = textEdit.getText().toString();
                upDateMap(addresse);

            }
        });

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


    public void annuler(View v) {
        finish();
    }

    public void valide(View v) {
        //sauvgarde le contacte
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,STORAGE_PERMISSION_CODE);

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


    // Function to check and request permission
    public void checkPermission(String permission, int requestCode)
    {

        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                this,
                permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat
                    .requestPermissions(
                            this,
                            new String[] { permission },
                            requestCode);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
    }
}
