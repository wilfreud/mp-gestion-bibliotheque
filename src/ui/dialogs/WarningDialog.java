package ui.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarningDialog {

    private final JButton button;

    public WarningDialog(JFrame parent, String title, String message) {

        Dialog dialog = new Dialog(parent, title, true);
        dialog.setSize(300, 150);
        dialog.setLayout(new GridLayout(2, 0));

        // content
        JLabel text = new JLabel(message);
        text.setForeground(Color.RED);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        this.button = new JButton("Compris");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        // mount components
        dialog.add(text, BorderLayout.CENTER);
        dialog.add(button, BorderLayout.CENTER);

        // enable
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    public WarningDialog(JFrame parent, String title, String message, ActionListener action) {
        this(parent, title, message);
        this.button.addActionListener(action);
    }
}
