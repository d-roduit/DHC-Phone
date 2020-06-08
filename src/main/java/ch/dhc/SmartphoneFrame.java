package ch.dhc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <b>SmartphoneFrame is the class that represents the smartphone JFrame.</b>
 *
 * @see JFrame
 *
 * @author Cathy Gay
 * @author Daniel Roduit
 */
class SmartphoneFrame extends JFrame {

    /**
     * The smartphone background image.
     *
     * @see BackgroundPanel
     * @see SmartphoneFrame#SmartphoneFrame()
     */
    private final BackgroundPanel smartphoneBackgroundPanel = new BackgroundPanel("images/smartphone.png");

    /**
     * The smartphone screen.
     *
     * @see ScreenPanel
     * @see SmartphoneFrame#SmartphoneFrame()
     * @see SmartphoneFrame#createLockButton()
     */
    private final ScreenPanel screenPanel = new ScreenPanel();

    /**
     * The smartphone lock button.
     *
     * @see JButton
     * @see SmartphoneFrame#createLockButton()
     * @see SmartphoneFrame#turnOff()
     */
    private JButton lockButton;

    /**
     * The action listener for handling lock button clicked event.
     *
     * @see #createLockButton()
     * @see ScreenPanel#toggleScreen()
     */
    private ActionListener toggleScreenActionListener;

    /**
     * The timer for handling lock button pressed event.
     *
     * @see #createPressedTimer(int, ActionListener)
     * @see #createLockButton()
     * @see #turnOff()
     */
    private Timer pressedTimer;

    /**
     * SmartphoneFrame constructor.
     * <p>
     *     SmartphoneFrame is created with a fixed size and no decoration.
     *     Its ContentPane is set with the BackgroundPanel smartphone image. The screen JPanel is at its center,
     *     the top is draggable, and a lock and home buttons are at its right and bottom.
     * </p>
     *
     * @see SmartphoneFrame#createDragPanel()
     * @see SmartphoneFrame#createHomeButtonPanel()
     * @see SmartphoneFrame#createLockPanel()
     * @see Box
     * @see SmartphoneFrame#smartphoneBackgroundPanel
     * @see SmartphoneFrame#screenPanel
     */
    public SmartphoneFrame() {
        setSize(400, 795);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        smartphoneBackgroundPanel.add(createDragPanel(), BorderLayout.NORTH);

        smartphoneBackgroundPanel.add(screenPanel, BorderLayout.CENTER);
        smartphoneBackgroundPanel.add(createHomeButtonPanel(), BorderLayout.SOUTH);
        smartphoneBackgroundPanel.add(Box.createRigidArea(new Dimension(28, 0)), BorderLayout.WEST);
        smartphoneBackgroundPanel.add(createLockPanel(), BorderLayout.EAST);

        setContentPane(smartphoneBackgroundPanel);
    }

    /**
     * Returns the lock button JPanel.
     *
     * @return The lock JPanel.
     *
     * @see JPanel
     * @see SmartphoneFrame#createLockButton()
     * @see SmartphoneFrame#SmartphoneFrame() 
     */
    public JPanel createLockPanel() {
        JPanel lockPanel = new JPanel();
        lockPanel.setLayout(new BorderLayout());
        lockPanel.setOpaque(false);

        lockPanel.add(Box.createRigidArea(new Dimension(0, 61)), BorderLayout.NORTH);
        lockPanel.add(Box.createRigidArea(new Dimension(0, 502)), BorderLayout.SOUTH);
        lockPanel.add(createLockButton(), BorderLayout.CENTER);

        return lockPanel;
    }

    /**
     * Returns the lock JButton.
     * <p>
     *     The lock button is created with no decoration and a hand cursor.
     *     Turns the screen on or off when it's clicked.
     *     Stops the program when it's long pressed.
     * </p>
     *
     * @return The lock JButton.
     *
     * @see JButton
     * @see SmartphoneFrame#screenPanel
     * @see ScreenPanel#toggleScreen()
     * @see #createPressedTimer(int, ActionListener)
     * @see SmartphoneFrame#turnOff()
     * @see JOptionPane#showConfirmDialog(Component, Object, String, int) 
     */
    public JButton createLockButton() {
        lockButton = new JButton();
        lockButton.setPreferredSize(new Dimension(27, 0));
        lockButton.setContentAreaFilled(false);
        lockButton.setBorderPainted(false);
        lockButton.setFocusPainted(false);
        lockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        toggleScreenActionListener = actionEvent -> screenPanel.toggleScreen();

        lockButton.addActionListener(toggleScreenActionListener);

        ActionListener taskPerformer = (e) -> {
            if (lockButton.getModel().isPressed()) {

                lockButton.removeActionListener(toggleScreenActionListener);

                int turnOffOption = JOptionPane.showConfirmDialog(
                        SmartphoneFrame.this,
                        "Éteindre le smartphone ?",
                        "Smartphone",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (turnOffOption == JOptionPane.YES_OPTION) {
                    turnOff();
                } else {
                    pressedTimer.stop();

                    lockButton.addActionListener(toggleScreenActionListener);
                }
            }
        };

        pressedTimer = createPressedTimer(4000, taskPerformer);

        lockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                pressedTimer.start();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if (pressedTimer.isRunning()) {
                    pressedTimer.stop();
                }
            }
        });

        return lockButton;
    }

    /**
     * Returns the home button JPanel.
     * <p>
     *     Displays the home screen when the CircleButton is clicked.
     * </p>
     *
     * @return The home button JPanel.
     *
     * @see JPanel
     * @see CircleButton
     * @see SmartphoneFrame#SmartphoneFrame()
     */
    public JPanel createHomeButtonPanel() {
        JPanel homeButtonPanel = new JPanel();

        CircleButton circleButton = new CircleButton(57);
        circleButton.addActionListener(actionEvent -> screenPanel.toggleHome());

        GridBagConstraints gbc = new GridBagConstraints();
        homeButtonPanel.add(circleButton, gbc);

        homeButtonPanel.setLayout(new GridBagLayout());
        homeButtonPanel.setOpaque(false);
        homeButtonPanel.setPreferredSize(new Dimension(0, 90));

        return homeButtonPanel;
    }

    /**
     * Returns the draggable JPanel.
     *
     * @return The draggable JPanel.
     *
     * @see JPanel
     * @see DragPanel
     * @see SmartphoneFrame#SmartphoneFrame()
     */
    public JPanel createDragPanel() {
        DragPanel dragPanel = new DragPanel(this);
        dragPanel.setPreferredSize(new Dimension(0, 89));
        dragPanel.setOpaque(false);

        return dragPanel;
    }

    /**
     * Returns a timer for handling button pressed event.
     *
     * @return The timer.
     *
     * @param delay Number of milliseconds before firing the first event and then delay between each event firing.
     * @param actionListener The task to execute when the delay is reached.
     *
     * @see #createLockButton()
     * @see #lockButton
     * @see Timer
     * @see #pressedTimer
     */
    private Timer createPressedTimer(int delay, ActionListener actionListener) {
        Timer pressedTimer = new Timer(delay, actionListener);
        pressedTimer.setRepeats(false);

        return pressedTimer;
    }

    /**
     * Stops the applications and closes the program.
     *
     * @see #pressedTimer
     * @see #lockButton
     * @see #createLockButton()
     * @see ApplicationManager#closeAllApplications()
     * @see #dispose()
     */
    private void turnOff() {
        ApplicationManager.getInstance().closeAllApplications();
        dispose();
    }

    /**
     * Returns the ScreenPanel.
     *
     * @return The ScreenPanel.
     *
     * @see ScreenPanel
     */
    public ScreenPanel getScreenPanel() {
        return screenPanel;
    }


}
