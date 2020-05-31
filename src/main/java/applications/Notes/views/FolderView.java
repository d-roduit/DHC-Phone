package applications.Notes.views;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * <b>FolderPanel is the class that represents the notes list of a folder.</b>
 *
 * @see JPanel
 *
 * @author Cathy Gay
 */
public class FolderView extends JPanel {

    private Color mainTextColor = Color.WHITE;
    private Color secondaryTextColor = new Color(217, 169, 25);
    private JPanel topLanePanel = createTopLanePanel();
    private JScrollPane notesListScrollPane = createNotesListScrollPane();
    private JPanel botLanePanel = createBotLanePanel();
    private JButton addNoteButton;
    private JButton deleteFolderButton;

    /**
     * FolderPanel constructor.
     */
    public FolderView() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        add(topLanePanel, BorderLayout.NORTH);
        add(notesListScrollPane, BorderLayout.CENTER);
        add(botLanePanel, BorderLayout.SOUTH);
    }

    private JPanel createBotLanePanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        int nbNotes = createNotesListPanel().getComponentCount() / 2;
        JLabel nbNotesLabel = new JLabel(nbNotes +" notes");
        nbNotesLabel.setForeground(mainTextColor);

        panel.add(nbNotesLabel);

        return panel;
    }

    private JPanel createTopLanePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        Icon goBackIcon = IconFontSwing.buildIcon(FontAwesome.ANGLE_LEFT, 22, secondaryTextColor);

        JButton returnToFolderList = new JButton("Folders");
        returnToFolderList.setIcon(goBackIcon);
        returnToFolderList.setContentAreaFilled(false);
        returnToFolderList.setFocusPainted(false);
        returnToFolderList.setBorderPainted(false);
        returnToFolderList.setForeground(secondaryTextColor);
        returnToFolderList.setFont(new Font("Arial", Font.BOLD, 17));
        returnToFolderList.setCursor(new Cursor(Cursor.HAND_CURSOR));
        returnToFolderList.setToolTipText("Go back to folders");

        JLabel titleLabel = new JLabel("Notes");
        titleLabel.setForeground(mainTextColor);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));

        Icon addNoteIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ADD, 28, secondaryTextColor);

        Icon deleteFolderIcon = IconFontSwing.buildIcon(FontAwesome.TRASH_O, 24, secondaryTextColor);

        addNoteButton = new JButton(addNoteIcon);
        addNoteButton.setFocusPainted(false);
        addNoteButton.setBorderPainted(false);
        addNoteButton.setContentAreaFilled(false);
        addNoteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addNoteButton.setToolTipText("Add a note");

        deleteFolderButton = new JButton(deleteFolderIcon);
        deleteFolderButton.setFocusPainted(false);
        deleteFolderButton.setBorderPainted(false);
        deleteFolderButton.setContentAreaFilled(false);
        deleteFolderButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteFolderButton.setToolTipText("Delete this folder");

        JPanel eastPanel = new JPanel();
        eastPanel.setOpaque(false);

        eastPanel.add(addNoteButton);
        eastPanel.add(deleteFolderButton);

        panel.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.NORTH);
        panel.add(returnToFolderList, BorderLayout.WEST);
        panel.add(titleLabel, BorderLayout.CENTER);
        panel.add(eastPanel, BorderLayout.EAST);
        panel.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.SOUTH);

        return panel;
    }

    private JScrollPane createNotesListScrollPane() {
        JScrollPane scrollPane = new JScrollPane(createNotesListPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10); //change the scrolling speed

        return scrollPane;
    }

    private JPanel createNotesListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        for(int i = 0; i < 15; i++) {
            panel.add(createNotePanel("Homework"));
            JSeparator separator = new JSeparator();
            separator.setPreferredSize(new Dimension(342, 2));
            separator.setMaximumSize(new Dimension(separator.getPreferredSize()));
            separator.setBackground(new Color(49, 49, 49));
            separator.setForeground(new Color(49, 49, 49));
            panel.add(separator);
        }

        return panel;
    }

    private JPanel createNotePanel(String name) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(320, 40));
        panel.setMaximumSize(new Dimension(panel.getPreferredSize()));

        JLabel noteTitle = new JLabel(name);
        noteTitle.setForeground(mainTextColor);

        Icon goToNoteIcon = IconFontSwing.buildIcon(FontAwesome.ANGLE_RIGHT, 20, mainTextColor);

        JLabel goToNoteLabel = new JLabel(goToNoteIcon);

        panel.add(Box.createRigidArea(new Dimension(10, 0)), BorderLayout.WEST);
        panel.add(noteTitle, BorderLayout.CENTER);
        panel.add(goToNoteLabel, BorderLayout.EAST);

        return panel;
    }

    public void addAddNoteListener(ActionListener addNoteListener) {
        addNoteButton.addActionListener(addNoteListener);
    }

    public void addDeleteFolderListener(ActionListener deleteFolderListener) {
        deleteFolderButton.addActionListener(deleteFolderListener);
    }
}
