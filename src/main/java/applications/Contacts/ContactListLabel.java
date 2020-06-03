package applications.Contacts;

import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

        /**
        * <b>ContactListLabel is the class that represents list of contacts and their labels</b>
        *
        * @see JPanel
        *
        * @author Henrick Neads
        */

public class ContactListLabel extends JLabel {

    private Contact contact ;
    private static JPanel showContactListPanel;
    private static CardLayout showContactListCardLayout;
    private static String contactPanelString;
    private static String modificationContactPanelString;

            /**
             *
             * ContactListLabel constructor.
             *
             * @param contact
             *          It's one person of the contact list.
             */

    public ContactListLabel (Contact contact){

        super(contact.getLastName() + " " + contact.getFirstName() );
        this.contact=contact;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                JPanel contactDisplayPanel = new JPanel();
                contactDisplayPanel.setLayout(new GridLayout(3,1));
                contactDisplayPanel.setOpaque(false);

                JPanel contactPersonnalInfoPanel = new JPanel();
                contactPersonnalInfoPanel.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(0,25,10,0);
                contactPersonnalInfoPanel.setBackground(Color.black);

                JLabel lastNameString = new JLabel("Last name : ");
                lastNameString.setForeground(Color.white);
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.anchor= GridBagConstraints.LINE_START;
                contactPersonnalInfoPanel.add(lastNameString,gbc);

                JLabel lastname = new JLabel (contact.getLastName());
                lastname.setForeground(Color.white);
                gbc.gridx = 1;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.anchor= GridBagConstraints.LINE_START;
                contactPersonnalInfoPanel.add(lastname,gbc);

                JLabel firstNameString = new JLabel("First name : ");
                firstNameString.setForeground(Color.white);
                gbc.gridx = 0;
                gbc.gridy = 6;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.anchor= GridBagConstraints.LINE_START;
                contactPersonnalInfoPanel.add(firstNameString,gbc);

                JLabel firstname = new JLabel (contact.getFirstName());
                firstname.setForeground(Color.white);
                gbc.gridx = 1;
                gbc.gridy = 6;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.anchor= GridBagConstraints.FIRST_LINE_START;
                contactPersonnalInfoPanel.add(firstname,gbc);

                JLabel cityString = new JLabel("City : ");
                cityString.setForeground(Color.white);
                gbc.gridx = 0;
                gbc.gridy = 7;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.anchor= GridBagConstraints.LINE_START;
                contactPersonnalInfoPanel.add(cityString,gbc);

                JLabel city = new JLabel (contact.getCity());
                city.setForeground(Color.white);
                gbc.gridx = 1;
                gbc.gridy = 7;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.anchor= GridBagConstraints.LINE_START;
                contactPersonnalInfoPanel.add(city,gbc);

                JLabel emailString = new JLabel("Email : ");
                emailString.setForeground(Color.white);
                gbc.gridx = 0;
                gbc.gridy = 8;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.anchor= GridBagConstraints.LINE_START;
                contactPersonnalInfoPanel.add(emailString,gbc);

                JLabel email = new JLabel (contact.getEmail());
                email.setForeground(Color.white);
                gbc.gridx = 1;
                gbc.gridy = 8;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.anchor= GridBagConstraints.LINE_START;
                contactPersonnalInfoPanel.add(email,gbc);

                JLabel phoneNumberString = new JLabel("Phone number : ");
                phoneNumberString.setForeground(Color.white);
                gbc.gridx = 0;
                gbc.gridy = 9;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.anchor= GridBagConstraints.LINE_START;
                contactPersonnalInfoPanel.add(phoneNumberString,gbc);

                JLabel phoneNumber = new JLabel(contact.getPhoneNumber());
                phoneNumber.setForeground(Color.white);
                gbc.gridx = 1;
                gbc.gridy = 9;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.anchor= GridBagConstraints.LINE_START;
                contactPersonnalInfoPanel.add(phoneNumber,gbc);

                //contactDisplayPanel.add(createContactDisplayButtonPanel());

                contactDisplayPanel.add(createPhotoDisplayPanel());

                contactDisplayPanel.add(contactPersonnalInfoPanel);

                contactDisplayPanel.add(createNoteDisplayPanel());

                String contactListPanelString = "newPanelString" + contact.getFirstName();
                showContactListPanel.add(contactDisplayPanel,contactListPanelString);

                ((CardLayout)showContactListPanel.getLayout()).show(showContactListPanel,contactListPanelString);

            }
        });
    }

    private JPanel createNoteDisplayPanel(){
        JPanel displayNote = new JPanel(new BorderLayout());
        displayNote.setBackground(Color.black);

        JLabel noteLabel = new JLabel("Notes");
        noteLabel.setForeground(Color.orange);
        noteLabel.setHorizontalAlignment(JLabel.CENTER);
        noteLabel.setFont(new Font("Calibri",Font.BOLD,20));

        JButton noteTexteContainer = new JButton("texte");
        noteTexteContainer.setForeground(Color.orange);

        displayNote.add(noteLabel,BorderLayout.NORTH);
        displayNote.add(noteTexteContainer,BorderLayout.CENTER);
        displayNote.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.WEST);
        displayNote.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.EAST);
        displayNote.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.SOUTH);

        return displayNote;
    }

    private JPanel createPhotoDisplayPanel(){
        JPanel displayPhoto = new JPanel();
        displayPhoto.setLayout(new BorderLayout());
        displayPhoto.setBackground(Color.black);

        JButton photo = new JButton("Photo");
        photo.setBackground(Color.white);
        displayPhoto.add(createContactDisplayButtonPanel(),BorderLayout.NORTH);
        displayPhoto.add(Box.createRigidArea(new Dimension(90, 0)), BorderLayout.WEST);
        displayPhoto.add(Box.createRigidArea(new Dimension(90, 0)), BorderLayout.EAST);

        displayPhoto.add(photo,BorderLayout.CENTER);

        return displayPhoto;
    }

    private JPanel createContactDisplayButtonPanel(){

        JPanel containsContactDisplayButtonPanel = new JPanel();
        containsContactDisplayButtonPanel.setLayout(new BorderLayout());
        containsContactDisplayButtonPanel.setBackground(Color.black);

        JPanel contactDisplayButtonPanel = new JPanel();
        contactDisplayButtonPanel.setLayout(new GridLayout(1,3));
        contactDisplayButtonPanel.setBackground(Color.black);

        Icon returnContactListIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_BACKSPACE,28,Color.orange);

        JButton contactBoutonRetour = new JButton(returnContactListIcon);
        contactBoutonRetour.setBackground(Color.black);
        contactBoutonRetour.setFocusPainted(false);
        contactBoutonRetour.setBorderPainted(false);
        contactBoutonRetour.setContentAreaFilled(false);
        contactBoutonRetour.addActionListener(event -> ((CardLayout)showContactListPanel.getLayout()).show(showContactListPanel, contactPanelString));
        contactDisplayButtonPanel.add(contactBoutonRetour);

        JLabel contactText = new JLabel("Contact");
        contactText.setForeground(Color.orange);
        contactText.setBackground(Color.black);
        contactText.setHorizontalAlignment(JLabel.CENTER);
        contactText.setFont(new Font("Calibri",Font.BOLD,25));
        contactDisplayButtonPanel.add(contactText);

        Icon editContactIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.EDIT,28,Color.orange);

        JButton contactBoutonModifier = new JButton(editContactIcon);
        contactBoutonModifier.setBackground(Color.black);
        contactBoutonModifier.setFocusPainted(false);
        contactBoutonModifier.setBorderPainted(false);
        contactBoutonModifier.setContentAreaFilled(false);
        contactBoutonModifier.addActionListener(event -> ((CardLayout)showContactListPanel.getLayout()).show(showContactListPanel,modificationContactPanelString));
        contactDisplayButtonPanel.add(contactBoutonModifier);

        containsContactDisplayButtonPanel.add(contactDisplayButtonPanel,BorderLayout.NORTH);

        return containsContactDisplayButtonPanel;
    }
    public static void setShowContactListPanel(JPanel showContactListPanel) {
        ContactListLabel.showContactListPanel = showContactListPanel;
        setShowContactListCardLayout(((CardLayout)showContactListPanel.getLayout()));
    }

    public static void setShowContactListCardLayout(CardLayout showContactListCardLayout){
        ContactListLabel.showContactListCardLayout=showContactListCardLayout;
    }

    public static void setContactPanelString(String contactPanelString){
        ContactListLabel.contactPanelString = contactPanelString;
    }

    public static void setModificationContactPanelString(String modificationContactPanelString){
        ContactListLabel.modificationContactPanelString = modificationContactPanelString;
    }

}
