package com.example.gestiondecontact;

public class Contact {
         private String nom;
         private String prenom;
         private String telephonePortable;
         private String telephoneFixe;
         private String email;
         private String dateDeNaiscance;
         private String addresse;
         private int ID;

    public Contact(String nom, String prenom, String telephonePortable, String telephoneFixe, String email, String dateDeNaiscance, String addresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephonePortable = telephonePortable;
        this.telephoneFixe = telephoneFixe;
        this.email = email;
        this.dateDeNaiscance = dateDeNaiscance;
        this.addresse = addresse;
    }















    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephonePortable(String telephonePortable) {
        this.telephonePortable = telephonePortable;
    }

    public void setTelephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateDeNaiscance(String dateDeNaiscance) {
        this.dateDeNaiscance = dateDeNaiscance;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephonePortable() {
        return telephonePortable;
    }

    public String getTelephoneFixe() {
        return telephoneFixe;
    }

    public String getEmail() {
        return email;
    }

    public String getDateDeNaiscance() {
        return dateDeNaiscance;
    }

    public String getAddresse() {
        return addresse;
    }

    public int getID() {
        return ID;
    }
}
