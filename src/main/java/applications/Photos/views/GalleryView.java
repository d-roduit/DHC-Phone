package applications.Photos.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class GalleryView extends JPanel {

    JPanel topBarPanel = createTopBarPanel();
    JPanel albumsPanel = createAlbumsPanel();
    JButton goBackButton;
    JButton createAlbumButton;

    public GalleryView() {
        setLayout(new BorderLayout());
        setOpaque(false);

        add(topBarPanel, BorderLayout.NORTH);
        add(albumsPanel, BorderLayout.CENTER);
    }

    private JPanel createTopBarPanel() {
        Color textColor = new Color(217, 169, 25);
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        JPanel topBarPanel = new JPanel();
        topBarPanel.setOpaque(false);
        topBarPanel.setLayout(new BorderLayout());

        goBackButton = new JButton("<-");
        goBackButton.setForeground(textColor);
        goBackButton.setCursor(handCursor);
        goBackButton.setFocusPainted(false);
        goBackButton.setBorderPainted(false);
        goBackButton.setContentAreaFilled(false);
        goBackButton.setOpaque(false);

        JLabel viewTitle = new JLabel("Galerie");
        viewTitle.setFont(new Font("Arial", Font.BOLD, 25));
        viewTitle.setHorizontalAlignment(JLabel.CENTER);
        viewTitle.setForeground(Color.WHITE);

        createAlbumButton = new JButton("+");
        createAlbumButton.setForeground(textColor);
        createAlbumButton.setCursor(handCursor);
        createAlbumButton.setFocusPainted(false);
        createAlbumButton.setBorderPainted(false);
        createAlbumButton.setContentAreaFilled(false);
        createAlbumButton.setOpaque(false);

        topBarPanel.add(goBackButton, BorderLayout.WEST);
        topBarPanel.add(viewTitle, BorderLayout.CENTER);
        topBarPanel.add(createAlbumButton, BorderLayout.EAST);

        return topBarPanel;
    }

    private JPanel createAlbumsPanel() {
        JPanel albumsPanel = new JPanel();
        albumsPanel.setOpaque(false);
        albumsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        albumsPanel.setLayout(new GridLayout(0, 2, 30, 30));

        for (int i = 0; i < 6; i++) {
            albumsPanel.add(createAlbumPreviewPanel());
        }

        return albumsPanel;
    }

    private JPanel createAlbumPreviewPanel() {
        JPanel albumPreviewPanel = new JPanel();
        albumPreviewPanel.setBackground(Color.DARK_GRAY);
        return albumPreviewPanel;
    }

    public void addGoBackButtonListener(ActionListener goBackButtonListener) {
        goBackButton.addActionListener(goBackButtonListener);
    }

    public void addCreateAlbumButtonListener(ActionListener createAlbumButtonListener) {
        createAlbumButton.addActionListener(createAlbumButtonListener);
    }



}
