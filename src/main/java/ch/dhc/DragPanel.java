package ch.dhc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * <b>DragPanel is the class that allows to drag a JFrame.</b>
 *
 * @see JPanel
 *
 * @author Daniel Roduit
 */
public class DragPanel extends JPanel {

    /**
     * Initial click of DragPanel.
     *
     * @see Point
     * @see DragPanel#DragPanel(JFrame)
     */
    private Point initialClick;

    /**
     * Parent JFrame of DragPanel.
     *
     * @see JFrame
     * @see DragPanel#DragPanel(JFrame)
     */
    private JFrame parent;

    /**
     * DragPanel constructor.
     * <p>
     *    DragPanel is created with a move cursor.
     * </p>
     *
     * @param parent
     *              The parent JFrame of DragPanel.
     */
    public DragPanel(final JFrame parent) {
        this.parent = parent;
        setCursor(new Cursor(Cursor.MOVE_CURSOR));


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                // get location of Window
                int thisX = parent.getLocation().x;
                int thisY = parent.getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                parent.setLocation(X, Y);
            }
        });
    }
}