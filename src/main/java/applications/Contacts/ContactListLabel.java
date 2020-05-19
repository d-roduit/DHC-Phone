package applications.Contacts;

import javax.swing.*;


public class ContactListLabel extends JLabel {

    private Contact contact ;

    public ContactListLabel (Contact contact){

        super(contact.getLastName() + " " + contact.getFirstName() );
        this.contact=contact;

    }
}
