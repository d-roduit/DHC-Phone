package applications.Contacts.models;

import applications.Contacts.models.Contact;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * ceci est la classe teste pour la classe contact..
 */

public class ContactTest extends TestCase {

    private Contact contact;

    protected void setUp() throws Exception {
        super.setUp();
        contact = new Contact("Benoit", "Cruchon","Echichens","0218024833","BenoitCruchon@hotmail.com",null );
    }

    @Test
    public void testPersonne() {
        assertNotNull("L'instance was not created", contact);
    }

    @Test
    public void testGetLastName() {
        assertEquals("Le last name is incorrect", "Cruchon", contact.getLastName());
    }

    @Test
    public void testSetLastName() {
        contact.setLastName("nom2");
        assertEquals("Le last name is incorrect", "nom2", contact.getLastName());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("the first name is incorrect", "Benoit", contact.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        contact.setFirstName("prenom2");
        assertEquals("the first name is incorrect", "prenom2", contact.getFirstName());
    }

    @Test
    public void testGetCity() {
        assertEquals("The city is incorrect", "Echichens", contact.getCity());
    }

    @Test
    public void testSetCity() {
        contact.setCity("Lausanne");
        assertEquals("The city is incorrect", "Lausanne", contact.getCity());
    }

    @Test
    public void testGetPhoneNumber() {
        assertEquals("The phone number is incorrect", "0218024833", contact.getPhoneNumber());
    }

    @Test
    public void testSetPhoneNumber() {
        contact.setPhoneNumber("0765433221");
        assertEquals("The phone number is incorrect", "0765433221", contact.getPhoneNumber());
    }

    @Test
    public void testGetPhotoPath() {
        assertEquals("The photo path is incorrect", null, contact.getPhotoPath());
    }

    @Test
    public void testSetPhotoPath() {
        contact.setPhotoPath("Abosulte folder path");
        assertEquals("The photo path is incorrect", "Abosulte folder path", contact.getPhotoPath());
    }


}