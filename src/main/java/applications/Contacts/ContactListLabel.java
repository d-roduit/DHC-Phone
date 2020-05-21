package applications.Contacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ContactListLabel extends JLabel {

    private Contact contact ;
    private static JPanel showContactListPanel;
    private static CardLayout showContactListCardLayout;

    public ContactListLabel (Contact contact){

        super(contact.getLastName() + " " + contact.getFirstName() );
        this.contact=contact;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                JPanel contactDisplayPanel = new JPanel();
                contactDisplayPanel.setLayout(new BorderLayout());

                JPanel contactPersonnalInfoPanel = new JPanel();
                contactPersonnalInfoPanel.setLayout(new GridLayout(5,2));
                contactPersonnalInfoPanel.setBackground(Color.black);
                contactPersonnalInfoPanel.setSize(100,100);

                JLabel lastNameString = new JLabel("Last name : ");
                lastNameString.setForeground(Color.white);
                contactPersonnalInfoPanel.add(lastNameString);

                JLabel lastname = new JLabel (contact.getLastName());
                lastname.setForeground(Color.white);
                contactPersonnalInfoPanel.add(lastname);

                JLabel firstNameString = new JLabel("First name : ");
                firstNameString.setForeground(Color.white);
                contactPersonnalInfoPanel.add(firstNameString);

                JLabel firstname = new JLabel (contact.getFirstName());
                firstname.setForeground(Color.white);
                contactPersonnalInfoPanel.add(firstname);

                JLabel cityString = new JLabel("City : ");
                cityString.setForeground(Color.white);
                contactPersonnalInfoPanel.add(cityString);

                JLabel city = new JLabel (contact.getCity());
                city.setForeground(Color.white);
                contactPersonnalInfoPanel.add(city);

                JLabel emailString = new JLabel("Email : ");
                emailString.setForeground(Color.white);
                contactPersonnalInfoPanel.add(emailString);

                JLabel email = new JLabel (contact.getEmail());
                email.setForeground(Color.white);
                contactPersonnalInfoPanel.add(email);

                JLabel phoneNumberString = new JLabel("Phone number : ");
                phoneNumberString.setForeground(Color.white);
                contactPersonnalInfoPanel.add(phoneNumberString);

                JLabel phoneNumber = new JLabel(contact.getPhoneNumber());
                phoneNumber.setForeground(Color.white);
                contactPersonnalInfoPanel.add(phoneNumber);

                contactDisplayPanel.add(contactPersonnalInfoPanel,BorderLayout.CENTER);

                String newPanelString = "newPanelString" + contact.getFirstName();
                showContactListPanel.add(contactDisplayPanel,newPanelString);

                contactDisplayPanel.add(createContactDisplayButtonPanel(),BorderLayout.NORTH);

                ((CardLayout)showContactListPanel.getLayout()).show(showContactListPanel,newPanelString);

            }
        });
    }
    private JPanel createContactDisplayButtonPanel(){
        JPanel contactDisplayButtonPanel = new JPanel();
        contactDisplayButtonPanel.setLayout(new GridLayout(1,3));
        contactDisplayButtonPanel.setBackground(Color.black);

        JButton contactBoutonRetour = new JButton("Retour");
        contactBoutonRetour.setBackground(Color.black);
        contactBoutonRetour.setForeground(Color.orange);
        //contactBoutonRetour.addActionListener(event -> showContactListCardLayout.show(showContactListPanel, contactPanelString));
        contactDisplayButtonPanel.add(contactBoutonRetour);

        JLabel contactText = new JLabel("Contact");
        contactText.setForeground(Color.orange);
        contactText.setHorizontalAlignment(JLabel.CENTER);
        contactDisplayButtonPanel.add(contactText);

        JButton contactBoutonModifier = new JButton("Modifier");
        contactBoutonModifier.setForeground(Color.orange);
        contactBoutonModifier.setBackground(Color.black);
        //contactBoutonModifier.addActionListener(event -> showContactListCardLayout.show(showContactListPanel,modificationContactPanelString));
        contactDisplayButtonPanel.add(contactBoutonModifier);
        return contactDisplayButtonPanel;
    }
    public static void setShowContactListPanel(JPanel showContactListPanel) {
        ContactListLabel.showContactListPanel = showContactListPanel;
        setShowContactListCardLayout(((CardLayout)showContactListPanel.getLayout()));
    }

    public static void setShowContactListCardLayout(CardLayout showContactListCardLayout){
        ContactListLabel.showContactListCardLayout=showContactListCardLayout;
    }
}
