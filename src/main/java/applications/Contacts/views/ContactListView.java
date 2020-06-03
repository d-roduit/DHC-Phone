package applications.Contacts.views;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ContactListView extends JLabel{

    private Color mainTextColor = Color.WHITE;
    private Color secondaryTextColor = new Color(217, 169, 25);
    private JPanel topLanePanel = createTopLanePanel();
    private JScrollPane contactListScrollPane = createContactListScrollPane();
    private JPanel botLanePanel = createBotLanePanel();
    private JButton addContactButton;

    public ContactListView() {

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        add(topLanePanel, BorderLayout.NORTH);
        add(contactListScrollPane, BorderLayout.CENTER);
        add(botLanePanel, BorderLayout.SOUTH);
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
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        JLabel titleLabel = new JLabel("Contact");
        titleLabel.setForeground(mainTextColor);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        Icon addContactIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ADD, 28, secondaryTextColor);

        Icon addContactIcon2 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PERSON_ADD,28,secondaryTextColor);

        addContactButton = new JButton(addContactIcon2);
        addContactButton.setToolTipText("Add a contact");
        addContactButton.setBorderPainted(false);
        addContactButton.setFocusPainted(false);
        addContactButton.setContentAreaFilled(false);
        addContactButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.WEST);
        panel.add(titleLabel, BorderLayout.CENTER);
        panel.add(addContactButton, BorderLayout.EAST);
        panel.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.SOUTH);

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

        for(int i = 0; i < 10; i++) {
            panel.add(createContactPanel("HES", "8"));
            JSeparator separator = new JSeparator();
            separator.setPreferredSize(new Dimension(350, 2));
            separator.setMaximumSize(new Dimension(separator.getPreferredSize()));
            separator.setBackground(new Color(49, 49, 49));
            separator.setForeground(new Color(49, 49, 49));
            panel.add(separator);
        }

        return panel;
    }

    private JPanel createContactPanel(String name, String nbNotes) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(320, 40));
        panel.setMaximumSize(new Dimension(panel.getPreferredSize()));

        JPanel westPanel = new JPanel();
        westPanel.setOpaque(false);
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BorderLayout());
        eastPanel.setOpaque(false);

        Icon folderIcon = IconFontSwing.buildIcon(FontAwesome.FOLDER_OPEN, 24, secondaryTextColor);

        JLabel folderLabel = new JLabel(folderIcon);

        JLabel folderName = new JLabel(name);
        folderName.setForeground(mainTextColor);

        JLabel folderNbNotes = new JLabel(nbNotes+"   ");
        folderNbNotes.setForeground(mainTextColor);

        Icon angleIcon = IconFontSwing.buildIcon(FontAwesome.ANGLE_RIGHT, 24, secondaryTextColor);

        JLabel angleLabel = new JLabel(angleIcon);

        westPanel.add(folderLabel);
        westPanel.add(folderName);

        eastPanel.add(folderNbNotes, BorderLayout.WEST);
        eastPanel.add(angleLabel, BorderLayout.CENTER);

        panel.add(westPanel, BorderLayout.WEST);
        panel.add(eastPanel, BorderLayout.EAST);

        return panel;
    }

    public void addAddContactListener(ActionListener addContactListener) {
        addContactButton.addActionListener(addContactListener);
    }
}
