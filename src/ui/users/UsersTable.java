package ui.users;

import model.Library;
import model.User;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Custom table model for displaying user information in a JTable.
 */
public class UsersTable extends AbstractTableModel {
    private final ArrayList<User> usersList = Library.getInstance().getUsersList();
    private final String[] columnNames = {"Nom", "Identifiant", "A jour paiement", "Action", "Emprunt"};

    /**
     * Constructs a UsersTable with default initialization.
     */
    public UsersTable() {
    }

    /**
     * Notifies the table that a user has been added or updated, triggering a refresh of the displayed data.
     */
    public void notifyUserAdded() {
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.usersList.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int index) {
        return this.columnNames[index];
    }

    /**
     * Retrieves the User object at the specified row index in the table.
     *
     * @param index The row index in the table.
     * @return The User object corresponding to the specified row.
     */
    public User getUserAt(int index) {
        return this.usersList.get(index);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = this.usersList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> user.getName();
            case 1 -> user.getId();
            case 2 -> user.isAllowedToBorrowBook() ? "OUI" : "NON";
            case 3 -> "Modifier";
            case 4 -> "Emprunts";
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3 || columnIndex == 4;
    }
}
