package applications.Contacts.controllers;

import applications.Contacts.Main;
import applications.Contacts.models.ContactList;
import applications.Contacts.models.Contact;
import applications.Contacts.views.ContactAddView;
import applications.Contacts.views.ContactListView;
import applications.Contacts.views.ContactModificationView;
import applications.Contacts.views.ContactView;

/**
 * <b>Folder controller.</b>
 *
 * @author Henrick Neads
 */

public class ContactController {

    private ContactList contactList;
    private Contact contact;
    private ContactAddView contactAddView;
    private ContactListView contactListView;
    private ContactModificationView contactModificationView;
    private ContactView contactView;
    private Main main;

    /*
    public ContactContoller(Main main, ContactView  contactView, ContactModificationView contactModificationView, ContactListView contactListview, ContactAddView contactAddView, ContactModel contactModel, ContactListModel contactListModel ){
        this.main = main;
        this.contactView = contactView;
        this.contactModificationView = contactModificationView;
        this.contactListView = contactListview;
        this.contactAddView = contactAddView;
        this.contactModel = contactModel;
        this.contactListModel = contactListModel;

        this.contactListView.addAddContactListener(e -> this.contactListModel.addContact());

        this.contactView.addAddContactListener(e -> this.contactModel.addContact());
        this.contactView.addDeleteContactListener(e -> this.contactModel.deleteContact());
        this.contactView.addReturnToContactListListener(e -> ((CardLayout)main.getLayout()).show(main, String.valueOf(contactListview.hashCode())));

    } */


}
