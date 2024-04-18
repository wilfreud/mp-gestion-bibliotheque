package ui.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * A custom dialog for displaying warning messages.
 */
public class WarningDialog {

    /**
     * Constructs a warning dialog with the specified title and message.
     *
     * @param parent  The parent JFrame for the dialog.
     * @param title   The title of the dialog window.
     * @param message The warning message to display.
     */
    public WarningDialog(JFrame parent, String title, String message) {
        Dialog dialog = new Dialog(parent, title, true);
        dialog.setSize(300, 150);
        dialog.setLayout(new GridLayout(2, 0));

        // Content
        JLabel text = new JLabel(message);
        text.setForeground(Color.RED);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        JButton button = new JButton("Compris");
        button.addActionListener(e -> dialog.dispose());

        // Mount components
        dialog.add(text, BorderLayout.CENTER);
        dialog.add(button, BorderLayout.CENTER);

        // Configure dialog
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
