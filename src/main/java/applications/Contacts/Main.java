package applications.Contacts;

import ch.dhc.Application;
import ch.dhc.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Main extends Application {

    CardLayout showContactListCardLayout = new CardLayout();
    JPanel showContactListPanel = new JPanel(showContactListCardLayout);

    CardLayout modificationCardLayout = new CardLayout();
    JPanel modificationContactPanel = new JPanel(modificationCardLayout);

    String contactPanelString = "contactPanelString";
    String modificationContactPanelString = "modificationContactPanelString";

    CardLayout contactAddCardLayout = new CardLayout();
    JPanel contactAddPanel = new JPanel(contactAddCardLayout);
    String contactAddPanelString = "contactAddPanelString";


    String name = "Contacts";
    String iconPath = "icon/app_icon_contacts.png";

    public Main() {

    }

    @Override
    public void onRun() {

        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = Configuration.getInstance();
        ContactListLabel.setShowContactListPanel(showContactListPanel);
        ContactListLabel.setContactPanelString(contactPanelString);
        ContactListLabel.setModificationContactPanelString(modificationContactPanelString);

        Icon saveModificationIncon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SAVE,28,Color.orange);

        try {

            File contactFile = new File(configuration.getContactsFolderPath() + "contacts.json");
            ContactList contactList = new ContactList(new ArrayList<Contact>(Arrays.asList(mapper.readValue(contactFile, Contact[].class))));

            //contactList.addContact("Daniel","Gay","Sierre","0786789543","jesuistropbeau@gmail.com");

            JPanel contactListPanel = new JPanel();
            contactListPanel.setLayout(new BorderLayout());
            contactListPanel.setBackground(Color.black);

            JScrollPane contactScrollBar = new JScrollPane();
            contactScrollBar.createVerticalScrollBar();

            JPanel listeAlphabet = new JPanel();
            listeAlphabet.setLayout(new GridLayout(1, 3));
            listeAlphabet.setBackground(Color.black);
            //listeAlphabet.setOpaque(false);
            //listeAlphabet.setBackground(Color.white);

            //for (char c = 'A'; c <= 'Z'; c++) {
            //    listeAlphabet.add(new JLabel(String.valueOf(c)));
            //}

            Icon returnContactIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_BACKSPACE,28,Color.orange);

            JButton retourHomeFromContactListPanel = new JButton(returnContactIcon);
            retourHomeFromContactListPanel.setBackground(Color.black);
            retourHomeFromContactListPanel.setFocusPainted(false);
            retourHomeFromContactListPanel.setBorderPainted(false);
            retourHomeFromContactListPanel.setContentAreaFilled(false);
            retourHomeFromContactListPanel.setOpaque(false);
            retourHomeFromContactListPanel.addActionListener(event -> System.out.println("Retour Application"));
            listeAlphabet.add(retourHomeFromContactListPanel);

            JLabel contactTextListeContact = new JLabel("Contact");
            contactTextListeContact.setForeground(Color.orange);
            contactTextListeContact.setOpaque(false);
            contactTextListeContact.setHorizontalAlignment(JLabel.CENTER);
            contactTextListeContact.setFont(new Font("Calibri",Font.BOLD,25));
            listeAlphabet.add(contactTextListeContact);

            showContactListPanel.add(contactAddPanel, contactAddPanelString);

            Icon addContactIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PERSON_ADD,28,Color.orange);

            JButton addContactButton = new JButton(addContactIcon);
            addContactButton.setBackground(Color.black);
            addContactButton.setFocusPainted(false);
            addContactButton.setBorderPainted(false);
            addContactButton.setContentAreaFilled(false);
            addContactButton.setOpaque(false);
            addContactButton.addActionListener(event -> showContactListCardLayout.show(showContactListPanel,contactAddPanelString));
            listeAlphabet.add(addContactButton);

            contactAddPanel.setLayout(new GridLayout(3,1));
            contactAddPanel.setBackground(Color.black);

            JPanel contactAddTopPanelBorderLayout = new JPanel();
            contactAddTopPanelBorderLayout.setLayout(new BorderLayout());
            contactAddTopPanelBorderLayout.setBackground(Color.black);

            JPanel topAddContactPanel = new JPanel( new GridLayout(1,3));
            topAddContactPanel.setOpaque(false);

            JButton addContactReturnButton = new JButton(returnContactIcon);
            addContactReturnButton.setBackground(Color.black);
            addContactReturnButton.setFocusPainted(false);
            addContactReturnButton.setBorderPainted(false);
            addContactReturnButton.setContentAreaFilled(false);
            addContactReturnButton.addActionListener((event -> showContactListCardLayout.show(showContactListPanel,contactPanelString)));
            topAddContactPanel.add(addContactReturnButton);

            JLabel contactTextAddTopContact = new JLabel("Contact");
            contactTextAddTopContact.setForeground(Color.orange);
            contactTextAddTopContact.setOpaque(false);
            contactTextAddTopContact.setHorizontalAlignment(JLabel.CENTER);
            contactTextAddTopContact.setFont(new Font("Calibri",Font.BOLD,25));
            topAddContactPanel.add(contactTextAddTopContact);

            JButton addContactSaveButton = new JButton(saveModificationIncon);
            addContactSaveButton.setBackground(Color.black);
            addContactSaveButton.setFocusPainted(false);
            addContactSaveButton.setBorderPainted(false);
            addContactSaveButton.setContentAreaFilled(false);
            addContactSaveButton.addActionListener(event -> System.out.println("Save pressed"));
            topAddContactPanel.add(addContactSaveButton);

            JPanel displayPhotoAddContact = new JPanel();
            displayPhotoAddContact.setLayout(new BorderLayout());
            displayPhotoAddContact.setBackground(Color.black);

            Icon addPhotoIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ADD_A_PHOTO,28,Color.orange);

            JButton photo = new JButton(addPhotoIcon);
            photo.addActionListener(event -> System.out.println("photo cliqué"));
            photo.setBackground(Color.black);
            displayPhotoAddContact.add(Box.createRigidArea(new Dimension(90, 0)), BorderLayout.WEST);
            displayPhotoAddContact.add(Box.createRigidArea(new Dimension(90, 0)), BorderLayout.EAST);
            displayPhotoAddContact.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.SOUTH);

            displayPhotoAddContact.add(photo,BorderLayout.CENTER);

            JButton test = new JButton("test1");
            test.setBackground(Color.black);
            test.setForeground(Color.white);
            test.addActionListener(event -> System.out.println("Contact details clicked"));


            contactAddTopPanelBorderLayout.add(topAddContactPanel,BorderLayout.NORTH);
            contactAddTopPanelBorderLayout.add(displayPhotoAddContact,BorderLayout.CENTER);

            Icon addNoteIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.NOTE_ADD,28,Color.orange);

            JPanel displayNoteAddContact = new JPanel(new BorderLayout());
            displayNoteAddContact.setBackground(Color.black);

            JLabel noteLabel = new JLabel("Notes:");
            noteLabel.setForeground(Color.orange);
            noteLabel.setHorizontalAlignment(JLabel.CENTER);
            noteLabel.setFont(new Font("Calibri",Font.BOLD,20));

            JButton noteTexteContainer = new JButton(addNoteIcon);
            noteTexteContainer.setBackground(Color.black);
            noteTexteContainer.addActionListener(event -> System.out.println("note clicked"));

            displayNoteAddContact.add(noteLabel,BorderLayout.NORTH);
            displayNoteAddContact.add(noteTexteContainer,BorderLayout.CENTER);
            displayNoteAddContact.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.WEST);
            displayNoteAddContact.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.EAST);
            displayNoteAddContact.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.SOUTH);

            contactAddPanel.add(contactAddTopPanelBorderLayout);
            contactAddPanel.add(test);
            contactAddPanel.add(displayNoteAddContact);


            JPanel CL = new JPanel();
            CL.setLayout(new BoxLayout(CL, BoxLayout.Y_AXIS));
            CL.setOpaque(false);

            modificationContactPanel.setBackground(Color.black);
            modificationContactPanel.setLayout(new GridLayout(3,1));

            JPanel boutonRetourModificationContactPanelBorderLayout = new JPanel();
            boutonRetourModificationContactPanelBorderLayout.setLayout(new BorderLayout());
            boutonRetourModificationContactPanelBorderLayout.setBackground(Color.black);

            JPanel boutonRetourModificationContactPanel = new JPanel();
            boutonRetourModificationContactPanel.setLayout(new GridLayout(1, 3));
            boutonRetourModificationContactPanel.setOpaque(false);

            boutonRetourModificationContactPanelBorderLayout.add(boutonRetourModificationContactPanel,BorderLayout.NORTH);
            modificationContactPanel.add(boutonRetourModificationContactPanelBorderLayout);

            showContactListPanel.add(modificationContactPanel, modificationContactPanelString);

            Icon returnModificationtIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_BACKSPACE,28,Color.orange);

            JButton cancelContact = new JButton(returnModificationtIcon);
            cancelContact.setBackground(Color.black);
            cancelContact.setFocusPainted(false);
            cancelContact.setBorderPainted(false);
            cancelContact.setContentAreaFilled(false);
            cancelContact.addActionListener((event -> showContactListCardLayout.show(showContactListPanel,contactPanelString)));
            boutonRetourModificationContactPanel.add(cancelContact);

            JLabel contactTextModification = new JLabel("Contact");
            contactTextModification.setForeground(Color.orange);
            contactTextModification.setOpaque(false);
            contactTextModification.setHorizontalAlignment(JLabel.CENTER);
            contactTextModification.setFont(new Font("Calibri",Font.BOLD,25));
            boutonRetourModificationContactPanel.add(contactTextModification);

            JButton saveContactModifications = new JButton(saveModificationIncon);
            saveContactModifications.setBackground(Color.black);
            saveContactModifications.setFocusPainted(false);
            saveContactModifications.setBorderPainted(false);
            saveContactModifications.setContentAreaFilled(false);
            saveContactModifications.addActionListener(event -> System.out.println("créer sauvegarde modification"));
            boutonRetourModificationContactPanel.add(saveContactModifications);




            for (Contact contact : contactList.getContactList()) {
                ContactListLabel cll = new ContactListLabel(contact);
                cll.setForeground(Color.white);
                CL.add(cll);
                JSeparator separator = new JSeparator();
                separator.setBackground(new Color(49, 49, 49));
                separator.setForeground(new Color(49, 49, 49));
                CL.add(separator);
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

