package applications.Contacts;

import ch.dhc.Application;
import ch.dhc.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main extends Application {

    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);

    public Main() {
        super("Contacts", "icon\\app_icon_contacts.png");
    }

    @Override
    public void onRun() {
        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = Configuration.getInstance();

        try {
            File file2 = new File(configuration.getContactDirectoryPath() + "contacts.json");
            ContactList contactList = new ContactList(new ArrayList<Contact>(Arrays.asList(mapper.readValue(file2, Contact[].class))));

        //ContactList contactList = mapper.readValue(file2, ContactList.class);

        System.out.println("JSON array to Array objects...");
        for (Contact contact : contactList.getContactList()) { //même que for( int i = 0;i < listeContact.length ; i++ )
            System.out.println(contact);
        }

        System.out.println();
        contactList.addContact("Daniel","Gay","Sierre","0786789543","jesuistropbeau@gmail.com");

        for (Contact contact : contactList.getContactList()) {
            System.out.println(contact);
        }

        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));

        String contactPanelString = "contactPanelString";

        for (Contact contact : contactList.getContactList()) {
            ContactListLabel cll = new ContactListLabel(contact);
            cll.addMouseListener( new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    JPanel newPanel = new JPanel();
                    newPanel.setLayout(new BorderLayout());

                    JPanel boutonRetourPanel = new JPanel();
                    boutonRetourPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                    String boutonRetourString = "boutonRetourString";

                    JPanel boutonModifierPanel = new JPanel();
                    boutonModifierPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
                    String boutonModifierString = "boutonModifierString";

                    JPanel infoPanel = new JPanel();
                    infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));

                    JLabel lastname = new JLabel (contact.getLastName());
                    JLabel firstname = new JLabel (contact.getFirstName());
                    JLabel city = new JLabel (contact.getCity());
                    JLabel email = new JLabel (contact.getEmail());

                    JButton boutonRetour = new JButton("Retour");
                    boutonRetour.addActionListener(event -> cardLayout.show( mainPanel, contactPanelString));
                    boutonRetourPanel.add(boutonRetour);

                    JButton boutonModifier = new JButton("Modifier");
                    boutonRetour.addActionListener(event2 -> System.out.println("button pressed"));
                    boutonModifierPanel.add(boutonModifier);

                    infoPanel.add(firstname);
                    infoPanel.add(lastname);
                    infoPanel.add(city);
                    infoPanel.add(email);

                    newPanel.add(boutonModifier,BorderLayout.EAST);
                    newPanel.add(boutonRetourPanel,BorderLayout.NORTH);
                    newPanel.add(infoPanel,BorderLayout.CENTER);

                    String newPanelString = "newPanelString" + contact.getFirstName();
                    mainPanel.add(newPanel,newPanelString);

                    cardLayout.show(mainPanel,newPanelString);
                }
            });

            contactPanel.add(cll);
            contactPanel.add(new JSeparator());
        }

        mainPanel.add(contactPanel,contactPanelString);

        add(mainPanel);

        cardLayout.show(mainPanel,contactPanelString);


           /* try {
                mapper.writeValue(file2, Contact[].class);
            } catch(IOException e) {
                System.out.println("Le programme n'a pas pu écrire la liste de contact dans le fichier");
            }
            */
        } catch(IOException excp) {
            System.out.println("Ca marche pas");
        }

    }



    @Override
    public void onClose() {
        System.out.println("Contacts dit 'Au revoir !'");
    }

}
