package applications.Contacts;

import ch.dhc.Application;
import ch.dhc.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Main extends Application {

    CardLayout showContactListCardLayout = new CardLayout();
    JPanel showContactListPanel = new JPanel(showContactListCardLayout);

    CardLayout modificationCardLayout = new CardLayout();
    JPanel modificationContactPanel = new JPanel(modificationCardLayout);

    String name = "Contacts";
    String iconPath = "icon\\app_icon_contacts.png";

    public Main() {

    }

    @Override
    public void onRun() {

        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = Configuration.getInstance();
        ContactListLabel.setShowContactListPanel(showContactListPanel);

        try {

            File contactFile = new File(configuration.getContactDirectoryPath() + "contacts.json");
            ContactList contactList = new ContactList(new ArrayList<Contact>(Arrays.asList(mapper.readValue(contactFile, Contact[].class))));

            //contactList.addContact("Daniel","Gay","Sierre","0786789543","jesuistropbeau@gmail.com");

            JPanel contactListPanel = new JPanel();
            contactListPanel.setLayout(new BorderLayout());
            contactListPanel.setBackground(Color.black);
            String contactPanelString = "contactPanelString";

            //new
            JScrollPane contactScrollBar = new JScrollPane();
            contactScrollBar.createVerticalScrollBar();

            JPanel listeAlphabet = new JPanel();
            listeAlphabet.setLayout(new GridLayout(2, 13));
            listeAlphabet.setOpaque(false);
            listeAlphabet.setForeground(Color.white);

            for (char c = 'A'; c <= 'Z'; c++) {
                listeAlphabet.add(new JLabel(String.valueOf(c)));
            }

            JPanel CL = new JPanel();
            CL.setLayout(new BoxLayout(CL, BoxLayout.Y_AXIS));
            CL.setOpaque(false);

            //new code ci-dessous

            JPanel boutonRetourModificationContactPanel = new JPanel();
            boutonRetourModificationContactPanel.setLayout(new GridLayout(1, 3));
            boutonRetourModificationContactPanel.setBackground(Color.black);
            modificationContactPanel.add(boutonRetourModificationContactPanel, BorderLayout.NORTH);

            String modificationContactPanelString = "modificationContactPanelString";
            showContactListPanel.add(modificationContactPanel, modificationContactPanelString);

            JButton cancelContact = new JButton("Annuler");
            cancelContact.setBackground(Color.black);
            cancelContact.setForeground(Color.orange);
            cancelContact.addActionListener((event -> showContactListCardLayout.show(showContactListPanel, contactPanelString))); // A changer des que possible le String

            boutonRetourModificationContactPanel.add(cancelContact);

            for (Contact contact : contactList.getContactList()) {
                ContactListLabel cll = new ContactListLabel(contact);
                cll.setForeground(Color.white);
                CL.add(cll);
                CL.add(new JSeparator());
            }

            contactListPanel.add(listeAlphabet, BorderLayout.NORTH);
            contactListPanel.add(CL, BorderLayout.CENTER);

            contactScrollBar.add(contactListPanel);

            showContactListPanel.add(contactScrollBar);
            showContactListPanel.add(contactListPanel, contactPanelString);
            add(showContactListPanel);

            showContactListCardLayout.show(showContactListPanel, contactPanelString);

        } catch (IOException excp) {
            System.out.println("Ca marche pas");
        }
    }

    @Override
    public void onClose() {
        System.out.println("Contacts dit 'Au revoir !'");

         /* try {
                mapper.writeValue(file2, Contact[].class);
            } catch(IOException e) {
                System.out.println("Le programme n'a pas pu écrire la liste de contact dans le fichier");
            } catch(){

            */
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

