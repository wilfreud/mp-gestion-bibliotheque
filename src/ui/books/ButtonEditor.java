package ui.books;

import model.Library;
import model.books.Book;
import ui.forms.BookForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private final BooksTable model;

    public ButtonEditor(BooksTable booksTable, JTable table) {
        super(new JCheckBox());
        this.model = booksTable;
        this.button = new JButton("Modifier");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int rowIndex = table.convertRowIndexToModel(table.getEditingRow());
                Book book = model.getBookAt(rowIndex);
                new BookForm(model, book);
            }
        });
    }

    @Override
    public JButton getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return this.button;
    }
}
