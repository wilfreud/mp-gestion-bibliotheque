package ui.users;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class UsersButtonRenderer extends DefaultTableCellRenderer {
    private final JButton button = new JButton("Modifier");
    public UsersButtonRenderer() {this.button.setOpaque(true);}

    @Override
    public JButton getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this.button;
    }
}
