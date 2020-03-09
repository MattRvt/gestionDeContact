package com.example.gestiondecontact;

public class Service_contacts_manager {

    private static Service_contacts_manager instance;

    public static synchronized Service_contacts_manager getInstance() {
        if (instance == null) {
            instance = new Service_contacts_manager();
        }
        return instance;
    }

    private Service_contacts_manager(){

    }


    public void addOrUpdateContact(Contact contact) {
        if (contactExiste()) {
            update(contact);
        } else {
            add(contact);
        }
    }

    private void add(Contact contact) {

    }
}
