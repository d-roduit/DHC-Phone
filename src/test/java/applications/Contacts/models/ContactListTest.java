package applications.Contacts.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactListTest {

    @Test
    void addContact() {

        Contact contact = new Contact();
        Contact contact2 = new Contact();

        List<Contact> contactList;
        contactList = new ArrayList<Contact>();

        assertEquals(0, contactList.size());
        contactList.add(contact);
        assertEquals(1, contactList.size());
        contactList.add(contact2);
        assertEquals(2, contactList.size());
    }

    @Test
    void removeContact() {

        Contact contact = new Contact();
        Contact contact2 = new Contact();

        List<Contact> contactList;
        contactList = new ArrayList<Contact>();

        contactList.add(contact);
        contactList.add(contact2);

        assertEquals(2,contactList.size());
        contactList.remove(contact);
        assertEquals(1,contactList.size());

    }
}