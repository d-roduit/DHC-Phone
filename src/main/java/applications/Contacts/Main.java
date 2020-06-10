package applications.Contacts;

import applications.Contacts.controllers.ContactListController;
import applications.Contacts.models.ContactList;
import applications.Contacts.views.ContactAddView;
import applications.Contacts.views.ContactListView;
import ch.dhc.Application;
import java.awt.*;


public class Main extends Application {

    // create cardlayout
    private CardLayout cardLayout = new CardLayout();

    // MVC Controllers
    private ContactListController contactListController;

    // App name and Icon
    String name = "Contacts";
    String iconPath = "icon/app_icon_contacts.png";

    public Main() {
    }

    @Override
    public void onRun() {

        // Create and initiate model
        ContactList contactList = new ContactList();

        // Read Json file and store in Memory
        contactList.readContactListFromJSonFile();

        // Sort contact in contact list
        contactList.sortContact();

        // Initiate unique views
        ContactListView contactListView = new ContactListView(contactList);
        ContactAddView contactAddView = new ContactAddView(null);

        // Initiate controller
        ContactListController contactListController = new ContactListController(this, contactList, contactListView, contactAddView);

        // Initiate cardlayouts
        setLayout(cardLayout);
        add(contactAddView,String.valueOf((contactAddView.hashCode())));
        add(contactListView, String.valueOf(contactListView.hashCode()));

        // Show contact list card layout
        cardLayout.show(this, String.valueOf(contactListView.hashCode()));

    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    @Override
    public void onClose() {

        System.out.println("Contacts dit 'Au revoir !'");

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIconPath() {
        return iconPath;
    }
}

