package applications.Notes;

import ch.dhc.ImageLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <b>FolderListPanel is the class that represents the folders list.</b>
 *
 * @see JPanel
 *
 * @author Cathy Gay
 */
public class FolderListPanel extends JPanel {

    /**
     * FolderListPanel constructor.
     */
    public FolderListPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        add(createTopLanePanel(), BorderLayout.NORTH);
        add(createFolderListScrollPanel(), BorderLayout.CENTER);
    }


    private JPanel createTopLanePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        JLabel titleLabel = new JLabel("Folders");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 25));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        ImageLabel addFolderButton = new ImageLabel("src\\main\\java\\applications\\Notes\\icon\\add_icon.png");
        addFolderButton.setToolTipText("Add a folder");

        panel.add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.WEST);
        panel.add(titleLabel, BorderLayout.CENTER);
        panel.add(addFolderButton, BorderLayout.EAST);
        panel.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.SOUTH);

        return panel;
    }

    private JScrollPane createFolderListScrollPanel() {
        JScrollPane scrollPane = new JScrollPane(createFolderListPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10); //change the scrolling speed

        return scrollPane;
    }

    private JPanel createFolderListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        for(int i = 0; i < 15; i++) {
            panel.add(createFolderPanel("HES", "8"));
            JSeparator separator = new JSeparator();
            separator.setBackground(new Color(49, 49, 49));
            separator.setForeground(new Color(49, 49, 49));
            panel.add(separator);
        }

        return panel;
    }

    private JPanel createFolderPanel(String name, String nbNotes) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        JPanel westPanel = new JPanel();
        westPanel.setOpaque(false);
        JPanel eastPanel = new JPanel();
        eastPanel.setOpaque(false);

        ImageLabel folderIcon = new ImageLabel("src\\main\\java\\applications\\Notes\\icon\\\\folder_icon.png");

        JLabel folderName = new JLabel(name);
        folderName.setForeground(Color.WHITE);

        JLabel folderNbNotes = new JLabel(nbNotes);
        folderNbNotes.setForeground(Color.WHITE);

        JLabel arrowIcon = new JLabel(">");
        arrowIcon.setForeground(Color.WHITE);

        westPanel.add(folderIcon);
        westPanel.add(folderName);

        eastPanel.add(folderNbNotes);
        eastPanel.add(arrowIcon);

        panel.add(westPanel, BorderLayout.WEST);
        panel.add(eastPanel, BorderLayout.EAST);

        return panel;
    }
}
