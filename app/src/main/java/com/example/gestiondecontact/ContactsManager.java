package com.example.gestiondecontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactsManager {

    private static final String TABLE_NAME = "CONTACT";
    public static final String KEY_ID = "id_contact";
    public static final String KEY_NOM = "nom";
    public static final String KEY_prenom = "prenom";
    public static final String KEY_telephonePortable = "telephonePortable";
    public static final String KEY_telephoneFixe = "telephoneFixe";
    public static final String KEY_email = "email";
    public static final String KEY_dateDeNaiscance = "dateDeNaiscance";
    public static final String KEY_addresse = "addresse";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" +
            " " + KEY_ID + " INTEGER primary key," +
            " " + KEY_NOM + " TEXT," +
            " " + KEY_prenom + " TEXT," +
            " " + KEY_telephonePortable + " TEXT," +
            " " + KEY_telephoneFixe + " TEXT," +
            " " + KEY_email + " TEXT," +
            " " + KEY_dateDeNaiscance + " DATE," +
            " " + KEY_addresse + " TEXT" +
            ");";
    private MySQLite maBaseSQLite; //  gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public ContactsManager(Context context) {
        maBaseSQLite = MySQLite.getInstance(context);
        this.open();
       // maBaseSQLite.onCreate(db);
    }

    public void open() {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        //on ferme l'accès à la BDD
        //db.close();
    }

    public long addContact(Contact contact) {
        // Ajout d'un enregistrement dans la table
        ContentValues values = new ContentValues();

        values.put(KEY_NOM, contact.getNom());
        values.put(KEY_prenom,contact.getPrenom());
        values.put(KEY_telephonePortable,contact.getTelephonePortable());
        values.put(KEY_telephoneFixe,contact.getTelephoneFixe());
        values.put(KEY_email,contact.getEmail());
        values.put(KEY_dateDeNaiscance,contact.getDateDeNaiscance());
        values.put(KEY_addresse,contact.getAddresse());



        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME, null, values);
    }

    public int modContact(Contact contact) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM, contact.getNom());

        String where = KEY_ID + " = ?";
        String[] whereArgs = {contact.getID() + ""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supContact(Contact contact) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID + " = ?";
        String[] whereArgs = {contact.getID() + ""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Contact getContact(int id) {
        // Retourne l'animal dont l'id est passé en paramètre

        Contact a = new Contact();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=" + id, null);
        if (c.moveToFirst()) {
            a.setID(c.getInt(c.getColumnIndex(KEY_ID)));
            a.setNom(c.getString(c.getColumnIndex(KEY_NOM)));
            c.close();
        }

        return a;
    }

    public ArrayList<HashMap<String, String>> getContacts() {
        // sélection de tous les enregistrements de la table
        this.open();
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        //get liste of colomne
        ArrayList<String> attributs = this.getContactsAtributs();
        while (c.moveToNext()) {
            //construction de la HashMap => une ligne
            HashMap row = new HashMap();
            for (String key : attributs) {
                String value = c.getString(c.getColumnIndex(key));
                row.put(key, value);
            }

            list.add(row);
        }
        return list;
    }

    public ArrayList<String> getContactsAtributs() {
        this.open();
        ArrayList<String> attributs = new ArrayList<String>();
        Cursor c = db.rawQuery("PRAGMA table_info(" + TABLE_NAME + ");", null);
        while (c.moveToNext()) {
            String attribut = c.getString(c.getColumnIndex("name"));
            attributs.add(attribut);
        }
        //this.close();
        return attributs;
    }
}
