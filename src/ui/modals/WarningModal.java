package ui.modals;

import javax.swing.*;
import java.awt.event.ActionListener;

public class WarningModal {

    private JFrame modal;
    private JLabel text;
    private JButton button;

    public WarningModal(String title, String message) {
        this.modal = new JFrame(title);
        this.modal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        // content
        this.text = new JLabel(message);
        this.button = new JButton();

        // mount components
        panel.add(text);
        panel.add(button);
        this.modal.add(panel);

        // enable
        this.modal.setVisible(true);
    }

    public WarningModal(String title, String message, ActionListener action) {
        this(title, message);
        this.button.addActionListener(action);
    }

    public void show() {
        this.modal.setVisible(false);
    }

    public void hide(boolean state) {
        this.modal.setVisible(false);
    }
}
