package ui.users;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Custom cell renderer for rendering buttons within a table cell in the UsersTable.
 */
public class UsersButtonRenderer extends DefaultTableCellRenderer {
    private final JButton button;

    /**
     * Constructs a UsersButtonRenderer with the default button text "Modifier".
     */
    public UsersButtonRenderer() {
        this.button = new JButton("Modifier");
        this.button.setOpaque(true);
    }

    /**
     * Constructs a UsersButtonRenderer with custom button text.
     *
     * @param buttonText The text to display on the button.
     */
    public UsersButtonRenderer(String buttonText) {
        this.button = new JButton("Modifier");
        this.button.setOpaque(true);
        this.button.setText(buttonText);
    }

    /**
     * Returns the JButton component used for rendering the table cell.
     *
     * @param table      The JTable that this renderer belongs to.
     * @param value      The value of the cell.
     * @param isSelected A boolean indicating whether the cell is selected.
     * @param hasFocus   A boolean indicating whether the cell has focus.
     * @param row        The row index of the cell.
     * @param column     The column index of the cell.
     * @return The JButton component used for rendering the table cell.
     */
    @Override
    public JButton getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this.button;
    }
}
