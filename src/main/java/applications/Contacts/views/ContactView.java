package applications.Contacts.views;

import applications.Photos.ThumbnailGenerator;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import applications.Contacts.models.Contact;

/**
 * <b>ContactView is the class that represents the display a single contact.</b>
 *
 * @see JPanel
 *
 * @author Henrick Neads
 */

public class ContactView extends JPanel {

    private Contact contact ;

    private Color mainTextColor = Color.WHITE;
    private Color secondaryTextColor = new Color(217, 169, 25);

    private String contactPhotoPath;

    private JPanel topLanePanel;
    private JPanel midLanePanel;
    private JPanel bottomLanePanel;

    private JButton modificationContactButton;
    private JButton returnPreviousButton;

    private JLabel firstNameContactString;
    private JLabel lastNameContactTextField;
    private JLabel cityContactTextField;
    private JLabel phoneNumberContactTextField;
    private JLabel emailContactTextField;

    private JLabel contactphotoLabel;

    public ContactView(Contact contact) {
        this.contact = contact;
        contactPhotoPath = contact.getPhotoPath();

        this.topLanePanel = createTopGridLayout();
        this.midLanePanel = createContactPanel();
        this.bottomLanePanel = createBottomGridLayout();

        setLayout(new GridLayout(3,1));
        setBackground(Color.BLACK);

        add(topLanePanel);
        add(midLanePanel);
        add(bottomLanePanel);
    }

    private JPanel createTopGridLayout(){
        JPanel topGridLayout = new JPanel();
        topGridLayout.setLayout(new BorderLayout());
        topGridLayout.setOpaque(false);

        topGridLayout.add(createTopLanePanel(),BorderLayout.NORTH);
        if (contactPhotoPath != null){
            topGridLayout.add(createPhotoLabel(),BorderLayout.CENTER);
        } else {
            topGridLayout.add(createPhotoButton(),BorderLayout.CENTER);
        }
        topGridLayout.add(Box.createRigidArea(new Dimension(90, 0)), BorderLayout.WEST);
        topGridLayout.add(Box.createRigidArea(new Dimension(90, 0)), BorderLayout.EAST);
        topGridLayout.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.SOUTH);


        return topGridLayout;
    }

    private JPanel createTopLanePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        panel.setOpaque(false);

        Icon returnIconForButton = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_BACKSPACE,28,secondaryTextColor);

        returnPreviousButton = new JButton(returnIconForButton);
        returnPreviousButton.setToolTipText("Return to previous menu");
        returnPreviousButton.setBorderPainted(false);
        returnPreviousButton.setFocusPainted(false);
        returnPreviousButton.setContentAreaFilled(false);
        returnPreviousButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        JLabel titleLabel = new JLabel("Contact");
        titleLabel.setForeground(mainTextColor);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        Icon editContactIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.EDIT,28,secondaryTextColor);

        modificationContactButton = new JButton(editContactIcon);
        modificationContactButton.setToolTipText("edit a contact");
        modificationContactButton.setBorderPainted(false);
        modificationContactButton.setFocusPainted(false);
        modificationContactButton.setContentAreaFilled(false);
        modificationContactButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.add(returnPreviousButton);
        panel.add(titleLabel);
        panel.add(modificationContactButton);

        return panel;
    }

    private JLabel createPhotoButton(){

        Icon addPhotoIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ADD_A_PHOTO,28,Color.orange);

        Border border = BorderFactory.createLineBorder(Color.white);

        contactphotoLabel = new JLabel(addPhotoIcon);
        contactphotoLabel.setOpaque(false);
        contactphotoLabel.setBorder(border);


        return contactphotoLabel;
    }

    private JLabel createPhotoLabel(){
        File imageFile = new File(contactPhotoPath);

        JLabel imageLabel = new JLabel();

        try {
            imageLabel.setIcon(new ImageIcon(ThumbnailGenerator.generate(imageFile, 165, 165)));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return imageLabel;
    }

    public JPanel createContactPanel(){

        JPanel containsContactPersonnalInfoPanel = new JPanel();
        containsContactPersonnalInfoPanel.setLayout(new BorderLayout());
        containsContactPersonnalInfoPanel.setBackground(Color.black);

        JPanel contactDetailsInfo= new JPanel();
        contactDetailsInfo.setLayout(new GridLayout(5,2));
        contactDetailsInfo.setBackground(Color.black);

        contactDetailsInfo.add(firstNameTextPanel());
        contactDetailsInfo.add(firstNameContactTextField());
        contactDetailsInfo.add(lastNameTextPanel());
        contactDetailsInfo.add(lastNameContactTextField());
        contactDetailsInfo.add(cityTextPanel());
        contactDetailsInfo.add(cityContactTextField());
        contactDetailsInfo.add(phoneNumberTextPanel());
        contactDetailsInfo.add(phoneNumberContactTextField());
        contactDetailsInfo.add(emailTextPanel());
        contactDetailsInfo.add(emailContactTextField());

        containsContactPersonnalInfoPanel.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.WEST);
        containsContactPersonnalInfoPanel.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.EAST);
        containsContactPersonnalInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.NORTH);
        containsContactPersonnalInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.SOUTH);
        containsContactPersonnalInfoPanel.add(contactDetailsInfo,BorderLayout.CENTER);

        return containsContactPersonnalInfoPanel;
    }

    public JPanel firstNameTextPanel (){

        JPanel firstName = new JPanel();
        firstName.setLayout(new BorderLayout());
        firstName.setBackground(Color.black);

        JLabel firstNameString = new JLabel("First name: ");
        firstNameString.setOpaque(false);
        firstNameString.setHorizontalAlignment(SwingConstants.LEFT);
        firstNameString.setForeground(mainTextColor);

        firstName.add(firstNameString);

        return firstName;
    }

    public JPanel firstNameContactTextField(){

        JPanel firstNameContactPanel = new JPanel();
        firstNameContactPanel.setLayout(new BorderLayout());
        firstNameContactPanel.setBackground(Color.black);

        firstNameContactString = new JLabel(contact.getFirstName());
        firstNameContactString.setOpaque(false);
        firstNameContactString.setHorizontalAlignment(SwingConstants.LEFT);
        firstNameContactString.setForeground(mainTextColor);

        firstNameContactPanel.add(firstNameContactString);

        return firstNameContactPanel;

    }

    public JPanel lastNameTextPanel (){

        JPanel lastName = new JPanel();
        lastName.setLayout(new BorderLayout());
        lastName.setBackground(Color.black);

        JLabel lastNameString = new JLabel("Last name: ");
        lastNameString.setOpaque(false);
        lastNameString.setHorizontalAlignment(SwingConstants.LEFT);
        lastNameString.setForeground(mainTextColor);

        lastName.add(lastNameString);

        return lastName;
    }

    public JPanel lastNameContactTextField(){

        JPanel lastNameContactPanel = new JPanel();
        lastNameContactPanel.setLayout(new BorderLayout());
        lastNameContactPanel.setBackground(Color.black);

        lastNameContactTextField = new JLabel(contact.getLastName());
        lastNameContactTextField.setOpaque(false);
        lastNameContactTextField.setHorizontalAlignment(SwingConstants.LEFT);
        lastNameContactTextField.setForeground(mainTextColor);

        lastNameContactPanel.add(lastNameContactTextField);

        return lastNameContactPanel;

    }

    public JPanel cityTextPanel (){

        JPanel city = new JPanel();
        city.setLayout(new BorderLayout());
        city.setBackground(Color.black);

        JLabel cityString = new JLabel("City: ");
        cityString.setOpaque(false);
        cityString.setHorizontalAlignment(SwingConstants.LEFT);
        cityString.setForeground(mainTextColor);

        city.add(cityString);

        return city;
    }

    public JPanel cityContactTextField(){

        JPanel cityContactPanel = new JPanel();
        cityContactPanel.setLayout(new BorderLayout());
        cityContactPanel.setBackground(Color.black);

        cityContactTextField = new JLabel(contact.getCity());
        cityContactTextField.setOpaque(false);
        cityContactTextField.setHorizontalAlignment(SwingConstants.LEFT);
        cityContactTextField.setForeground(mainTextColor);

        cityContactPanel.add(cityContactTextField);

        return cityContactPanel;

    }

    public JPanel phoneNumberTextPanel (){

        JPanel phoneNumber = new JPanel();
        phoneNumber.setLayout(new BorderLayout());
        phoneNumber.setBackground(Color.black);

        JLabel phoneNumberString = new JLabel("Phone Number: ");
        phoneNumberString.setOpaque(false);
        phoneNumberString.setHorizontalAlignment(SwingConstants.LEFT);
        phoneNumberString.setForeground(mainTextColor);

        phoneNumber.add(phoneNumberString);

        return phoneNumber;
    }

    public JPanel phoneNumberContactTextField(){

        JPanel phoneNumberContactPanel = new JPanel();
        phoneNumberContactPanel.setLayout(new BorderLayout());
        phoneNumberContactPanel.setBackground(Color.black);

        phoneNumberContactTextField = new JLabel(contact.getPhoneNumber());
        phoneNumberContactTextField.setOpaque(false);
        phoneNumberContactTextField.setHorizontalAlignment(SwingConstants.LEFT);
        phoneNumberContactTextField.setForeground(mainTextColor);

        phoneNumberContactPanel.add(phoneNumberContactTextField);

        return phoneNumberContactPanel;

    }

    public JPanel emailTextPanel (){

        JPanel email = new JPanel();
        email.setLayout(new BorderLayout());
        email.setBackground(Color.black);

        JLabel emailString = new JLabel("Email: ");
        emailString.setOpaque(false);
        emailString.setHorizontalAlignment(SwingConstants.LEFT);
        emailString.setForeground(mainTextColor);

        email.add(emailString);

        return email;
    }

    public JPanel emailContactTextField(){

        JPanel emailContactPanel = new JPanel();
        emailContactPanel.setLayout(new BorderLayout());
        emailContactPanel.setBackground(Color.black);

        emailContactTextField = new JLabel(contact.getEmail());
        emailContactTextField.setOpaque(false);
        emailContactTextField.setHorizontalAlignment(SwingConstants.LEFT);
        emailContactTextField.setForeground(mainTextColor);

        emailContactPanel.add(emailContactTextField);

        return emailContactPanel;

    }

    private JPanel createBottomGridLayout(){

        JPanel displayNoteAddContact = new JPanel(new BorderLayout());
        displayNoteAddContact.setBackground(Color.black);

        JLabel noteLabel = new JLabel("Notes:");
        noteLabel.setForeground(Color.orange);
        noteLabel.setHorizontalAlignment(JLabel.CENTER);
        noteLabel.setFont(new Font("Calibri",Font.BOLD,20));

        Icon addNoteIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.NOTE_ADD,28,Color.orange);

        Border border = BorderFactory.createLineBorder(Color.white);

        JLabel noteTexteContainer = new JLabel(addNoteIcon);
        noteTexteContainer.setBackground(Color.black);
        noteTexteContainer.setBorder(border);


        displayNoteAddContact.add(noteLabel,BorderLayout.NORTH);
        displayNoteAddContact.add(noteTexteContainer,BorderLayout.CENTER);
        displayNoteAddContact.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.WEST);
        displayNoteAddContact.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.EAST);
        displayNoteAddContact.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.SOUTH);

        return displayNoteAddContact;
    }

    public void modifyContactListener(ActionListener addModificationListener) {
        modificationContactButton.addActionListener(addModificationListener);
    }

    public void returnToContactListListener(ActionListener returnToContactList){
        returnPreviousButton.addActionListener(returnToContactList);
    }

}
