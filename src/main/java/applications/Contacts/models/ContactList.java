package applications.Contacts.models;

import ch.dhc.Configuration;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
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
     * Creates a list of contacts
     *
     * @see Contact
     */

    protected static List<Contact> contactList;

    private ObjectMapper mapper;

    /**
     * The empty constructor is used by the object mapper so it can read the information from a Json file containing
     * the information about the contacts.
     *
     */
    public ContactList() {
        this.mapper = new ObjectMapper();
        this.contactList = new ArrayList<Contact>();
    }
/*
    public ContactList(List<Contact> contactList) {
        setContactList(contactList);
        setMapper(new ObjectMapper());
    }
*/
    /**
     * getContactList function will allow you to retrieve the contact list from an other class.
     *
     * @return contactList
     */

    public List<Contact> getContactList() {
        return this.contactList;
    }


    public void addContact(String firstName, String lastName, String city, String phoneNumber, String email) {
        this.contactList.add(new Contact(firstName, lastName, city, phoneNumber, email));
    }


    /**
     * modifyContact function will modify the contact with the information entered by the user.
     *
     */
    public void modifyContact(Contact contact,String firstName,String lastName,String city,String phoneNumber,String email){
        for(Contact cont : contactList) {
            if(contact.getLastName()==(cont.getLastName())) {
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
                contact.setCity(city);
                contact.setPhoneNumber(phoneNumber);
                contact.setEmail(email);
                break;
            }
        }
    }

    /**
     * removeContact removes a contact from the contact list.
     *
     * @param contact
     */
    public void removeContact(Contact contact) {
        contactList.remove(contact);
    }

    /**
     * This function will sort the contactList of contact by alphabetical order.
     *
     */
    public void sortContact() {
        Collections.sort(this.contactList);
    }

    /**
     * The readContactListFromJSonFile function will read the information contained in the Json file and store it in the object ContactList which is a list.
     *
     * @see ObjectMapper
     * @see ContactList
     * @see Configuration
     * @see File
     *
     */

    public void readContactListFromJSonFile() {
        Configuration configuration = Configuration.getInstance();
        File contactListFile = new File(configuration.getContactsFolderPath() + "contacts.json");
        try {

            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Contact.class);

            List<Contact> contactList = mapper.readValue(contactListFile, type);

            for (Contact c :contactList) {
                this.contactList.add(c);
            }
            /*
            for (Contact c : new ArrayList<Contact>(Arrays.asList(this.mapper.readValue(contactListFile, Contact[].class)))) {
                this.contactList.add(c);
            }*/
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function will write the contactList in a file which is provided by the path.
     *
     * @see ObjectMapper
     * @see File
     * @see Configuration
     * @see ContactList
     */
    public void saveContactList() {
        Configuration configuration = Configuration.getInstance();
        File contactListFile = new File(configuration.getContactsFolderPath() + "contacts.json");

        try {
            this.mapper.writeValue(contactListFile,this.contactList);
        } catch(IOException e) {
            System.out.println("Le programme n'a pas pu écrire la liste de contact dans le fichier");
        }
    }

    public String toString() {
        String output = "";
        for (Contact c : this.contactList) {
            output += " " + c.toString();
        }
        return output;
    }
}
