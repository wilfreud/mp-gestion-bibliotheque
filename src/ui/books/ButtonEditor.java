package ui.books;

import exception.BookNotFoundException;
import exception.ExceptionUtils;
import model.Library;
import model.User;
import model.books.Book;
import ui.dialogs.WarningDialog;
import ui.forms.BookForm;

import javax.swing.*;

public class ButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private final BooksTable model;
    private final Library library = Library.getInstance();

    public ButtonEditor(BooksTable BooksTable, JTable table) {
        super(new JCheckBox());
        this.model = BooksTable;
        this.button = new JButton("Modifier");
        button.addActionListener(e -> {
                final int rowIndex = table.convertRowIndexToModel(table.getEditingRow());
                Book book = model.getBookAt(rowIndex);
                new BookForm(model, book);
        });
    }

    public ButtonEditor(BooksTable BooksTable, JTable table, String textBtn, User user) {
        super(new JCheckBox());
        this.model = BooksTable;
        this.button = new JButton(textBtn);
        button.addActionListener(e ->  {
            final int rowIndex = table.convertRowIndexToModel(table.getEditingRow());
            Book book = model.getBookAt(rowIndex);
            final int choice = JOptionPane.showConfirmDialog(null, "Cette action est irreversible", "Confirmation de retour", JOptionPane.YES_NO_OPTION);
            try{
                if (choice == JOptionPane.YES_NO_OPTION) {
                    user.returnBorrowedBook(book);
                    library.registerBookReturn(user, book);
                    BooksTable.notifyBookAdded();
                }
            }catch(BookNotFoundException bfe) {
                System.err.println("Something happenend");
                new WarningDialog(null, ExceptionUtils.SAVING_ERROR_TITLE, bfe.getMessage());
            }
        });
    }

    @Override
    public JButton getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return this.button;
    }
}
