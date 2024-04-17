package ui.users;

import model.User;
import ui.forms.UserForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private final UsersTable model;

    public UsersButtonEditor(UsersTable usersTable, JTable table) {
        super(new JCheckBox());
        this.model = usersTable;
        this.button = new JButton("Modifier");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int rowIndex = table.convertRowIndexToModel(table.getEditingRow());
                User user = model.getUserAt(rowIndex);
                new UserForm(model, user);
            }
        });
    }

    @Override
    public JButton getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return this.button;
    }
}
