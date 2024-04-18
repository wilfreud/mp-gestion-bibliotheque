package ui.books;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Custom cell renderer for rendering buttons in a JTable column.
 */
public class ButtonRenderer extends DefaultTableCellRenderer {
    private final JButton button = new JButton("Modifier");

    /**
     * Constructs a button renderer with the default button text.
     */
    public ButtonRenderer() {
        this.button.setOpaque(true);
    }

    /**
     * Constructs a button renderer with custom button text.
     *
     * @param buttonText The text to display on the button.
     */
    public ButtonRenderer(String buttonText) {
        this.button.setOpaque(true);
        this.button.setText(buttonText);
    }

    @Override
    public JButton getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this.button;
    }
}
