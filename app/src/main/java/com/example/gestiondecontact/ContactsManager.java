package com.example.gestiondecontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ContactsManager {

    private static final String TABLE_NAME = "animal";
    public static final String KEY_ID ="id_animal";
    public static final String KEY_NOM ="nom_animal";
    public static final String CREATE_TABLE_ANIMAL = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+ KEY_ID +" INTEGER primary key," +
            " "+ KEY_NOM +" TEXT" +
            ");";
    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public ContactsManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addContact(Contact contact) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_NOM, contact.getNom());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modContact(Contact contact) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM, contact.getNom());

        String where = KEY_ID +" = ?";
        String[] whereArgs = {contact.getID()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supContact(Contact contact) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID +" = ?";
        String[] whereArgs = {contact.getID()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Contact getContact(int id) {
        // Retourne l'animal dont l'id est passé en paramètre

        Contact a=new Contact();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ KEY_ID +"="+id, null);
        if (c.moveToFirst()) {
            a.setID(c.getInt(c.getColumnIndex(KEY_ID)));
            a.setNom(c.getString(c.getColumnIndex(KEY_NOM)));
            c.close();
        }

        return a;
    }

    public Cursor getAnimaux() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }
}
