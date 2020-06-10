package applications.Notes.views;

import applications.Notes.models.NoteModel;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;

public class NoteView extends JPanel {

    private final NoteModel noteModel;
    private Color mainTextColor = Color.WHITE;
    private Color secondaryTextColor = new Color(217, 169, 25);
    private JPanel topLanePanel;
    private JPanel centerPanel;
    private JButton returnToNoteListButton;
    private JButton deleteNoteButton;
    private JButton saveNoteButton;
    private JLabel noteTitleLabel;
    private JButton boldButton;
    private JButton italicButton;
    private JButton underlineButton;
    private JButton previewButton;
    private JEditorPane editorPane;

    public NoteView(NoteModel noteModel) {
        this.noteModel = noteModel;

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        topLanePanel = createTopLanePanel();
        centerPanel = createCenterPanel();

        add(topLanePanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

    }

    private JPanel createTopLanePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        Icon goBackIcon = IconFontSwing.buildIcon(FontAwesome.ANGLE_LEFT, 22, secondaryTextColor);

        returnToNoteListButton = new JButton("Notes");
        returnToNoteListButton.setIcon(goBackIcon);
        undecorateButton(returnToNoteListButton);
        returnToNoteListButton.setForeground(secondaryTextColor);
        returnToNoteListButton.setFont(new Font("Arial", Font.BOLD, 17));
        returnToNoteListButton.setToolTipText("Go back to notes");

        noteTitleLabel = new JLabel(noteModel.getNoteTitle());
        noteTitleLabel.setForeground(mainTextColor);
        noteTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        noteTitleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        noteTitleLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        Icon saveNoteIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SAVE, 27, secondaryTextColor);
        saveNoteButton = new JButton(saveNoteIcon);
        undecorateButton(saveNoteButton);
        saveNoteButton.setToolTipText("Save this note");

        Icon deleteNoteIcon = IconFontSwing.buildIcon(FontAwesome.TRASH_O, 24, secondaryTextColor);
        deleteNoteButton = new JButton(deleteNoteIcon);
        undecorateButton(deleteNoteButton);
        deleteNoteButton.setToolTipText("Delete this note");

        JPanel eastPanel = new JPanel();
        eastPanel.setOpaque(false);
        eastPanel.add(saveNoteButton);
        eastPanel.add(deleteNoteButton);

        JLabel noteCreationDateLabel = new JLabel("       créé le "+noteModel.getNoteCreationDate());
        noteCreationDateLabel.setFont(new Font("Arial", Font.ITALIC, 12));

        panel.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.NORTH);
        panel.add(returnToNoteListButton, BorderLayout.WEST);
        panel.add(noteTitleLabel, BorderLayout.CENTER);
        panel.add(eastPanel, BorderLayout.EAST);
        panel.add(noteCreationDateLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        JToolBar toolBar = createToolBar();
        JScrollPane content = createNoteContentScrollPane(noteModel);

        panel.add(toolBar, BorderLayout.NORTH);
        panel.add(content, BorderLayout.CENTER);

        return panel;
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setOpaque(false);

        Icon boldIcon = IconFontSwing.buildIcon(FontAwesome.BOLD, 18, mainTextColor);
        boldButton = new JButton(boldIcon);
        boldButton.setToolTipText("Set the selected text in bold");
        undecorateButton(boldButton);

        Icon italicIcon = IconFontSwing.buildIcon(FontAwesome.ITALIC, 18, mainTextColor);
        italicButton = new JButton(italicIcon);
        italicButton.setToolTipText("Set the selected text in italic");
        undecorateButton(italicButton);

        Icon underlineIcon = IconFontSwing.buildIcon(FontAwesome.UNDERLINE, 18, mainTextColor);
        underlineButton = new JButton(underlineIcon);
        underlineButton.setToolTipText("Underline the selected text");
        undecorateButton(underlineButton);

        Icon previewIcon = IconFontSwing.buildIcon(FontAwesome.SEARCH, 18, mainTextColor);
        previewButton = new JButton(previewIcon);
        previewButton.setToolTipText("Show preview");
        undecorateButton(previewButton);

        toolBar.add(boldButton);
        toolBar.add(italicButton);
        toolBar.add(underlineButton);

        toolBar.addSeparator();

        toolBar.add(previewButton);

        return toolBar;
    }

    private JScrollPane createNoteContentScrollPane(NoteModel noteModel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        editorPane = new JEditorPane();
        editorPane.setContentType("text/plain");
        editorPane.setOpaque(false);
//        editorPane.setEditorKit(new HTMLEditorKit());
        editorPane.setText(noteModel.getContent());


        panel.add(editorPane, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10); //change the scrolling speed

        return scrollPane;
    }

    private void undecorateButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public JButton getReturnToNoteListButton() {
        return returnToNoteListButton;
    }

    public JButton getDeleteNoteButton() {
        return deleteNoteButton;
    }

    public JButton getSaveNoteButton() {
        return saveNoteButton;
    }

    public JLabel getNoteTitleLabel() {
        return noteTitleLabel;
    }

    public JButton getBoldButton() {
        return boldButton;
    }

    public JButton getItalicButton() {
        return italicButton;
    }

    public JButton getUnderlineButton() {
        return underlineButton;
    }

    public JButton getPreviewButton() {
        return previewButton;
    }

    public JEditorPane getEditorPane() {
        return editorPane;
    }

}
