package applications.Contacts;

import java.util.*;

/**
 * La classe ContactList  représente plusieurs contact regrouper sous forme de liste.
 *
 * une liste de contacte est représentée par plusieurs contacts. chaque contact possède des caractéristiques.
 */
public class ContactList {

    protected List<Contact> contactList;

    public ContactList() {

    }

    public ContactList(List<Contact> contactList) {
        setContactList(contactList);
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public void addContact(String firstName, String lastName, String city, String phoneNumber, String email) {
        this.contactList.add(new Contact(firstName, lastName, city, phoneNumber, email));
    }

    public void removeContact(String lastName) {
        contactList.removeIf(contact -> contact.getLastName().equals(lastName));
    }

    public void sortContact(List<Contact> contactList) {

        Collections.sort((ArrayList) contactList);

    }
}
