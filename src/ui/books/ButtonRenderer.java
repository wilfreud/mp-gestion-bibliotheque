package ui.books;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class ButtonRenderer extends DefaultTableCellRenderer {
    private final JButton button = new JButton("Modifier");

    public ButtonRenderer() {
        this.button.setOpaque(true);
    }

    public ButtonRenderer(String buttonText) {
        this.button.setOpaque(true);
        this.button.setText(buttonText);
    }

    @Override
    public JButton getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this.button;
    }
}
