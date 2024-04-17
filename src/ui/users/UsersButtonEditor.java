package ui.users;

import model.User;
import ui.forms.BookBorrowListForm;
import ui.forms.UserForm;

import javax.swing.*;

public class UsersButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private final UsersTable model;

    public UsersButtonEditor(UsersTable usersTable, JTable table, String btnMessage) {
        super(new JCheckBox());
        this.model = usersTable;
        this.button = new JButton(btnMessage);
        this.button.addActionListener(e -> {
            final int rowIndex = table.convertRowIndexToModel(table.getEditingRow());
            User user = model.getUserAt(rowIndex);
            new UserForm(model, user);
        });
    }

    public UsersButtonEditor(UsersTable usersTable, JTable table, String btnMessage, boolean isBorrowRelated) {
        super(new JCheckBox());
        this.model = usersTable;
        this.button = new JButton(btnMessage);
        this.button.addActionListener(e -> {
            final int rowIndex = table.convertRowIndexToModel(table.getEditingRow());
            User user = model.getUserAt(rowIndex);

            if (isBorrowRelated) {
                new BookBorrowListForm(user);
            } else {
                System.out.println("Not handled");
            }

        });
    }

    @Override
    public JButton getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return this.button;
    }
}
