package ui.books;

import exception.BookNotFoundException;
import exception.ExceptionUtils;
import model.Library;
import model.User;
import model.books.Book;
import ui.dialogs.WarningDialog;
import ui.forms.BookForm;

import javax.swing.*;

/**
 * Custom cell editor for button actions in a JTable column.
 */
public class ButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private final BooksTable model;
    private final Library library = Library.getInstance();

    /**
     * Constructor for a button editor used for modifying books.
     *
     * @param booksTable The BooksTable model associated with the editor.
     * @param table      The JTable instance where the editor is used.
     */
    public ButtonEditor(BooksTable booksTable, JTable table) {
        super(new JCheckBox());
        this.model = booksTable;
        this.button = new JButton("Modifier");
        button.addActionListener(e -> {
            final int rowIndex = table.convertRowIndexToModel(table.getEditingRow());
            Book book = model.getBookAt(rowIndex);
            new BookForm(model, book);
        });
    }

    /**
     * Constructor for a button editor used for book return actions.
     *
     * @param booksTable The BooksTable model associated with the editor.
     * @param table      The JTable instance where the editor is used.
     * @param textBtn    The text displayed on the button.
     * @param user       The user performing the return action.
     */
    public ButtonEditor(BooksTable booksTable, JTable table, String textBtn, User user) {
        super(new JCheckBox());
        this.model = booksTable;
        this.button = new JButton(textBtn);
        button.addActionListener(e -> {
            final int rowIndex = table.convertRowIndexToModel(table.getEditingRow());
            Book book = model.getBookAt(rowIndex);
            final int choice = JOptionPane.showConfirmDialog(null, "Cette action est irréversible", "Confirmation de retour", JOptionPane.YES_NO_OPTION);
            try {
                if (choice == JOptionPane.YES_OPTION) {
                    user.returnBorrowedBook(book);
                    library.registerBookReturn(user, book);
                    booksTable.notifyBookAdded();
                }
            } catch (BookNotFoundException bfe) {
                System.err.println("An error occurred");
                new WarningDialog(null, ExceptionUtils.SAVING_ERROR_TITLE, bfe.getMessage());
            }
        });
    }

    @Override
    public JButton getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return this.button;
    }
}
