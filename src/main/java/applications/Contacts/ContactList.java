package applications.Contacts;

import java.util.*;

/**
 * ContactList represents a list of contact which are stored on your phone.
 *
 * contact list is represented by more than one contacts. Each contact has it's own information.
 *
 * @see Contact
 * @author Henrick neads
 */
public class ContactList {

    /**
     * Creates a list of the contacts
     *
     * @see Contact
     */

    protected List<Contact> contactList;

    /**
     * The empty constructor is used by the object mapper so it can read the information from a Json file containing
     * the information about the contacts.
     *
     */
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
