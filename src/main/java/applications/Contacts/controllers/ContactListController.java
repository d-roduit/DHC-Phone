package applications.Contacts.controllers;

import applications.Contacts.models.Contact;
import applications.Contacts.models.ContactList;
import applications.Contacts.views.ContactAddView;
import applications.Contacts.views.ContactListView;
import applications.Contacts.views.ContactModificationView;
import applications.Contacts.views.ContactView;
import applications.Contacts.Main;
import java.awt.event.*;

/**
 * ContactList Controller
 *
 * @author Henrick Neads
 *
 */

public class ContactListController {

    private ContactList contactList;
    private ContactListView contactListView;
    private ContactAddView contactAddView;

    private Main main;

    public ContactListController(Main main, ContactList contactList, ContactListView contactListView, ContactAddView contactAddView) {
        this.main = main;

        this.contactList = contactList;
        this.contactListView = contactListView;
        this.contactAddView = contactAddView;

        this.contactAddView.addSaveContactListener(e-> {
            String firstName = contactAddView.getFirstNameContactTextField().getText();
            String lastName = contactAddView.getLastNameContactTextField().getText();
            String city = contactAddView.getCityContactTextField().getText();
            String phoneNumber = contactAddView.getPhoneNumberContactTextField().getText();
            String email = contactAddView.getEmailContactTextField().getText();

            contactList.addContact(firstName,lastName,city,phoneNumber,email);
            saveContacts();
            contactList.sortContact();
            contactListView.repaint(); //comment rafraichir la contact list pour l'affichage ?
            this.main.getCardLayout().show(this.main,String.valueOf(contactListView.hashCode()));

        });
        this.contactAddView.addReturnToContactList(e -> this.main.getCardLayout().show(this.main, String.valueOf(contactListView.hashCode())));
        this.contactAddView.addPhotoToContactListener(e -> System.out.println("photo clicked"));

        this.contactListView.addAddContactListener(e->this.main.getCardLayout().show(this.main, String.valueOf(contactAddView.hashCode())));
        this.contactListView.returnToHomePage(e-> System.out.println("return to home panel "));
        this.contactListView.getContactPanelsMap().forEach((contactPanel,contact) ->{

            contactPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    ContactView contactView = new ContactView(contact);
                    contactView.modifyContactListener(event->{
                        ContactModificationView contactModificationView = new ContactModificationView(contact);
                        main.add(contactModificationView,String.valueOf(contactModificationView.hashCode()));
                        main.getCardLayout().show(main,String.valueOf(contactModificationView.hashCode()));

                        contactModificationView.modificationContacteSaveListener(evt->{
                            System.out.println("button pressed");

                            main.getCardLayout().show(main,String.valueOf(contactView.hashCode()));
                        });

                        contactModificationView.removeContactListener(evt -> {
                            System.out.println("remove contact");
                            removeContact(contact);
                            saveContacts();
                            contactList.sortContact();
                            contactListView.repaint();//comment rafraichir la contact list pour l'affichage ?
                            main.getCardLayout().show(main,String.valueOf(contactListView.hashCode()));
                        });

                        contactModificationView.returnToContactInformationLisetener(evt ->main.getCardLayout().show(main,String.valueOf(contactView.hashCode())));
                    });
                    main.add(contactView,String.valueOf(contactView.hashCode()));
                    main.getCardLayout().show(main,String.valueOf(contactView.hashCode()));

                    contactView.returnToContactListListener(evt ->main.getCardLayout().show(main,String.valueOf(contactListView.hashCode())));
                }
            });
        });
    }

    public void removeContact(Contact contact){
        this.contactList.removeContact(contact);
    }

    public void saveContacts() {
        this.contactList.saveContactList();
    }
}
