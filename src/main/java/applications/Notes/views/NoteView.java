package applications.Notes.views;

import applications.Notes.models.NoteModel;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import java.awt.*;

public class NoteView extends JPanel {

    private final NoteModel noteModel;
    private Color mainTextColor = Color.WHITE;
    private Color secondaryTextColor = new Color(217, 169, 25);
    private JPanel topLanePanel;
    private JPanel noteContentPanel;
    private JButton returnToNoteListButton;
    private JButton deleteNoteButton;
    private JLabel noteTitleLabel;

    public NoteView(NoteModel noteModel) {
        this.noteModel = noteModel;

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        topLanePanel = createTopLanePanel();
        noteContentPanel = createNoteContentPanel();

        add(topLanePanel, BorderLayout.NORTH);
        add(noteContentPanel, BorderLayout.CENTER);
    }

    private JPanel createTopLanePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        Icon goBackIcon = IconFontSwing.buildIcon(FontAwesome.ANGLE_LEFT, 22, secondaryTextColor);

        returnToNoteListButton = new JButton("Notes");
        returnToNoteListButton.setIcon(goBackIcon);
        returnToNoteListButton.setContentAreaFilled(false);
        returnToNoteListButton.setFocusPainted(false);
        returnToNoteListButton.setBorderPainted(false);
        returnToNoteListButton.setForeground(secondaryTextColor);
        returnToNoteListButton.setFont(new Font("Arial", Font.BOLD, 17));
        returnToNoteListButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        returnToNoteListButton.setToolTipText("Go back to notes");

        noteTitleLabel = new JLabel(noteModel.getNoteTitle());
        noteTitleLabel.setForeground(mainTextColor);
        noteTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        noteTitleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        noteTitleLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        Icon deleteFolderIcon = IconFontSwing.buildIcon(FontAwesome.TRASH_O, 24, secondaryTextColor);
        deleteNoteButton = new JButton(deleteFolderIcon);
        deleteNoteButton.setFocusPainted(false);
        deleteNoteButton.setBorderPainted(false);
        deleteNoteButton.setContentAreaFilled(false);
        deleteNoteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteNoteButton.setToolTipText("Delete this note");

        JLabel noteCreationDateLabel = new JLabel("       créé le "+noteModel.getNoteCreationDate());
        noteCreationDateLabel.setFont(new Font("Arial", Font.ITALIC, 12));

        panel.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.NORTH);
        panel.add(returnToNoteListButton, BorderLayout.WEST);
        panel.add(noteTitleLabel, BorderLayout.CENTER);
        panel.add(deleteNoteButton, BorderLayout.EAST);
        panel.add(noteCreationDateLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createNoteContentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        return panel;
    }

    public JButton getReturnToNoteListButton() {
        return returnToNoteListButton;
    }

    public JButton getDeleteNoteButton() {
        return deleteNoteButton;
    }

    public JLabel getNoteTitleLabel() {
        return noteTitleLabel;
    }
}
