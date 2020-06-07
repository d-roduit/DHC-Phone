package applications.Contacts.views;

import applications.Contacts.models.Contact;
import applications.Contacts.models.ContactList;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Contact List View
 *
 * @author Henrick Neads
 *
 */

public class ContactListView extends JPanel {

    // MVC Model object
    private ContactList contactList;
    private Contact contact;

    private Map<JPanel,Contact> contactPanelsMap = new HashMap<JPanel,Contact>();

    private Color mainTextColor = Color.WHITE;
    private Color secondaryTextColor = new Color(217, 169, 25);
    private JPanel topLanePanel;
    private JScrollPane contactListScrollPane;
    private JPanel botLanePanel;

    private JButton addContactButton;
    private JButton returnContactButton;

    public ContactListView(ContactList contactList) {
        this.contactList = contactList;

        this.topLanePanel = createTopLanePanel();
        this.contactListScrollPane = createContactListScrollPane();
        this.botLanePanel = createBotLanePanel();

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        add(this.topLanePanel, BorderLayout.NORTH);
        add(this.contactListScrollPane, BorderLayout.CENTER);
        add(this.botLanePanel,BorderLayout.SOUTH);
        add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.WEST);
    }

     private JPanel createBotLanePanel() {
     JPanel panel = new JPanel();
     panel.setOpaque(false);

     int nbContact = createContactListPanel().getComponentCount() / 2;

     JLabel nbContactLabel = new JLabel(nbContact +" contacts");
     nbContactLabel.setForeground(mainTextColor);

     panel.add(nbContactLabel);

     return panel;
     }

    private JPanel createTopLanePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        panel.setOpaque(false);

        Icon returnIconForButton = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_BACKSPACE,28,secondaryTextColor);
        returnContactButton = new JButton(returnIconForButton);
        returnContactButton.setToolTipText("Return to previous menu");
        returnContactButton.setBorderPainted(false);
        returnContactButton.setFocusPainted(false);
        returnContactButton.setContentAreaFilled(false);
        returnContactButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(returnContactButton);

        JLabel titleLabel = new JLabel("Contact");
        titleLabel.setForeground(mainTextColor);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        Icon addContactIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PERSON_ADD,28,secondaryTextColor);
        addContactButton = new JButton(addContactIcon);
        addContactButton.setToolTipText("Add a contact");
        addContactButton.setBorderPainted(false);
        addContactButton.setFocusPainted(false);
        addContactButton.setContentAreaFilled(false);
        addContactButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(addContactButton);

        return panel;
    }

    private JScrollPane createContactListScrollPane() {
        JScrollPane scrollPane = new JScrollPane(createContactListPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10); //change the scrolling speed

        return scrollPane;
    }

    private JPanel createContactListPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        for (Contact contact : this.contactList.getContactList()) {
            JPanel contactPanel = createContactPanel(contact);
            panel.add(contactPanel);

            // create a map which contains contacts panels associated with their contacts
            contactPanelsMap.put(contactPanel,contact);

            JSeparator separator = new JSeparator();
            separator.setBackground(new Color(49, 49, 49));
            separator.setForeground(new Color(49, 49, 49));
            panel.add(separator);
        }

        return panel;
    }

    private JPanel createContactPanel(Contact contact){

        // Center contact name inside a JPanel with boxes to add padding
        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(new BorderLayout());
        contactPanel.setBackground(Color.BLACK);
        contactPanel.add(Box.createRigidArea(new Dimension(0, 15)), BorderLayout.NORTH);
        contactPanel.add(Box.createRigidArea(new Dimension(0, 5)), BorderLayout.SOUTH);

        // create contact label
        JLabel contactLabel = createContactLabel(contact);

        contactPanel.add(contactLabel, BorderLayout.CENTER);

        return contactPanel;
    }

    private JLabel createContactLabel(Contact contact) {

        this.contact = contact;

        // Add contact name as a JLabel
        JLabel contactLabel = new JLabel(contact.getLastName() + " " + contact.getFirstName());
        contactLabel.setFont(new Font("Arial", Font.BOLD, 14));
        contactLabel.setForeground(mainTextColor);
        contactLabel.setHorizontalAlignment(SwingConstants.LEFT);

        return contactLabel;
    }

    public Map<JPanel, Contact> getContactPanelsMap() {
        return contactPanelsMap;
    }

    public void addAddContactListener(ActionListener addContactListener) {
        this.addContactButton.addActionListener(addContactListener);
    }

    public void returnToHomePage(ActionListener returnToHomePanel){
        this.returnContactButton.addActionListener(returnToHomePanel);
    }

}
