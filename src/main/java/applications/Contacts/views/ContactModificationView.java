package applications.Contacts.views;

import applications.Contacts.models.Contact;
import applications.Photos.ThumbnailGenerator;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * <b>ContactModificationView is the class that represents the view of editing a contact.</b>
 *
 * @see JPanel
 *
 * @author Henrick Neads
 */

public class ContactModificationView extends JPanel {

    private Contact contact;

    private Color mainTextColor = Color.WHITE;
    private Color secondaryTextColor = new Color(217, 169, 25);

    private JPanel topGridLayoutPanel;
    private JPanel midGridLayoutPanel;
    private JPanel bottomGridLayoutPanel;

    private JButton modificationSaveContactButton;
    private JButton returnPreviousButton;
    private JButton photoButton;
    private JButton deletContactbutton;
    private JButton modifycontactphoto;

    private String contactPhotoPath;

    private JTextField firstNameContactTextField;
    private JTextField lastNameContactTextField;
    private JTextField cityContactTextField;
    private JTextField phoneNumberContactTextField;
    private JTextField emailContactTextField;

    public ContactModificationView(Contact contact,String imagePath) {
        this.contact = contact;
        this.contactPhotoPath = imagePath;

        topGridLayoutPanel = createTopGridLayout();
        midGridLayoutPanel = createMidGridLayout();
        bottomGridLayoutPanel = createBottomGridLayout();

        setLayout(new GridLayout(3,1));
        setBackground(Color.BLACK);

        add(topGridLayoutPanel);
        add(midGridLayoutPanel);
        add(bottomGridLayoutPanel);
    }

    private JPanel createTopGridLayout(){

        JPanel topGridLayout = new JPanel();
        topGridLayout.setLayout(new BorderLayout());
        topGridLayout.setOpaque(false);

        topGridLayout.add(createTopPanel(),BorderLayout.NORTH);
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

    private JPanel createTopPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        panel.setOpaque(false);

        Icon returnIconForButton = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_BACKSPACE,28,secondaryTextColor);

        returnPreviousButton = new JButton(returnIconForButton);
        returnPreviousButton.setToolTipText("Return to contact");
        returnPreviousButton.setBorderPainted(false);
        returnPreviousButton.setFocusPainted(false);
        returnPreviousButton.setContentAreaFilled(false);
        returnPreviousButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(returnPreviousButton);

        JLabel titleLabel = new JLabel("Contact");
        titleLabel.setForeground(mainTextColor);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        JLabel iconContainers = new JLabel();
        iconContainers.setLayout(new GridLayout(1,2));
        iconContainers.setOpaque(false);

        Icon saveContactModification = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SAVE,28,secondaryTextColor);

        modificationSaveContactButton = new JButton(saveContactModification);
        modificationSaveContactButton.setToolTipText("Save Modification");
        modificationSaveContactButton.setBorderPainted(false);
        modificationSaveContactButton.setFocusPainted(false);
        modificationSaveContactButton.setContentAreaFilled(false);
        modificationSaveContactButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconContainers.add(modificationSaveContactButton);

        Icon deleteContactIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DELETE,28,secondaryTextColor);

        deletContactbutton = new JButton(deleteContactIcon);
        deletContactbutton.setToolTipText("Delete Contact");
        deletContactbutton.setBorderPainted(false);
        deletContactbutton.setFocusPainted(false);
        deletContactbutton.setContentAreaFilled(false);
        deletContactbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconContainers.add(deletContactbutton);


        panel.add(iconContainers);

        return panel;
    }

    private JButton createPhotoButton(){

        Icon addPhotoIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ADD_A_PHOTO,28,Color.orange);

        photoButton = new JButton(addPhotoIcon);
        photoButton.setOpaque(false);
        photoButton.setBackground(Color.black);

        return photoButton;
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

    private JPanel createMidGridLayout(){

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

        firstNameContactTextField = new JTextField(contact.getFirstName());
        firstNameContactTextField.setOpaque(false);
        firstNameContactTextField.setHorizontalAlignment(SwingConstants.LEFT);
        firstNameContactTextField.setForeground(mainTextColor);

        firstNameContactPanel.add(firstNameContactTextField);

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

        lastNameContactTextField = new JTextField(contact.getLastName());
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

        cityContactTextField = new JTextField(contact.getCity());
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

        phoneNumberContactTextField = new JTextField(contact.getPhoneNumber());
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

        emailContactTextField = new JTextField(contact.getEmail());
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

        JButton noteTexteContainer = new JButton(addNoteIcon);
        noteTexteContainer.setBackground(Color.black);
        noteTexteContainer.addActionListener(event -> System.out.println("note clicked"));

        displayNoteAddContact.add(noteLabel,BorderLayout.NORTH);
        displayNoteAddContact.add(noteTexteContainer,BorderLayout.CENTER);
        displayNoteAddContact.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.WEST);
        displayNoteAddContact.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.EAST);
        displayNoteAddContact.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.SOUTH);

        return displayNoteAddContact;
    }

    public JTextField getFirstNameContactTextField() {
        return firstNameContactTextField;
    }

    public JTextField getLastNameContactTextField() {
        return lastNameContactTextField;
    }

    public JTextField getCityContactTextField() {
        return cityContactTextField;
    }

    public JTextField getPhoneNumberContactTextField() {
        return phoneNumberContactTextField;
    }

    public JTextField getEmailContactTextField() {
        return emailContactTextField;
    }

    public String getContactPhotoPath() {
        return contactPhotoPath;
    }

    public void removeContactListener(ActionListener removeContact){
        deletContactbutton.addActionListener(removeContact);
    }

    public void modificationContacteSaveListener(ActionListener addSaveListener) {
        modificationSaveContactButton.addActionListener(addSaveListener);
    }

    public void returnToContactInformationLisetener(ActionListener returnToPreviousPanel){
        returnPreviousButton.addActionListener(returnToPreviousPanel);
    }

}
