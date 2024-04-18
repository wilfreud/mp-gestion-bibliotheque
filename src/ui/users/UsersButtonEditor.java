package ui.users;

import model.User;
import ui.forms.BookBorrowListForm;
import ui.forms.UserForm;

import javax.swing.*;

/**
 * Custom cell editor for handling button actions within a table cell in the UsersTable.
 */
public class UsersButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private final UsersTable model;

    /**
     * Constructs a UsersButtonEditor with the specified UsersTable, JTable, and button message.
     *
     * @param usersTable The UsersTable instance to associate with this editor.
     * @param table      The JTable instance associated with this editor.
     * @param btnMessage The message displayed on the button.
     */
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

    /**
     * Constructs a UsersButtonEditor with the specified UsersTable, JTable, button message, and flag indicating the action type.
     *
     * @param usersTable      The UsersTable instance to associate with this editor.
     * @param table           The JTable instance associated with this editor.
     * @param btnMessage      The message displayed on the button.
     * @param isBorrowRelated A boolean flag indicating if the button action is related to borrowing.
     */
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

    /**
     * Returns the button component used as the cell editor component.
     *
     * @param table      The JTable that this editor belongs to.
     * @param value      The value of the cell.
     * @param isSelected A boolean indicating whether the cell is selected.
     * @param row        The row index of the cell.
     * @param column     The column index of the cell.
     * @return The JButton component used as the cell editor component.
     */
    @Override
    public JButton getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return this.button;
    }
}
