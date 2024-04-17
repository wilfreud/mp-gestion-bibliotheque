package ui.users;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class UsersButtonRenderer extends DefaultTableCellRenderer {
    private final JButton button;

    public UsersButtonRenderer() {
        this.button = new JButton("Modifier");
        this.button.setOpaque(true);
    }

    public UsersButtonRenderer(String buttonText) {
        this.button = new JButton("Modifier");
        this.button.setOpaque(true);
        this.button.setText(buttonText);
    }

    @Override
    public JButton getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this.button;
    }
}
