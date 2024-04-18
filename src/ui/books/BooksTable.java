package ui.books;

import model.Library;
import model.books.Book;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Table model for displaying books in a JTable.
 */
public class BooksTable extends AbstractTableModel {
    private static final Map<String, String> classNameMap = new HashMap<>();

    static {
        classNameMap.put("essay", "Essai");
        classNameMap.put("novel", "Roman");
        classNameMap.put("audiobook", "Livre Audio");
    }

    private String getDisplayString(String className) {
        return classNameMap.getOrDefault(className.toLowerCase(), "Unknown");
    }

    private ArrayList<Book> data = Library.getInstance().getBooksList();
    private final String[] columnNames = {"Titre", "Auteur", "Année de Publication", "ISBN", "Genre", "Action"};

    public BooksTable() {
    }

    public BooksTable(ArrayList<Book> bookList) {
        this.data = bookList;
    }

    public void notifyBookAdded() {
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Retrieves the book at the specified index.
     *
     * @param index The index of the book to retrieve.
     * @return The book at the specified index.
     */
    public Book getBookAt(int index) {
        return this.data.get(index);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> book.getTitle();
            case 1 -> book.getAuthor();
            case 2 -> book.getPublicationYear();
            case 3 -> book.getISBN();
            case 4 -> {
                // Get the class name and convert it to display string
                String className = book.getClass().getSimpleName().toLowerCase();
                yield getDisplayString(className);
            }
            case 5 -> "Modifier";
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 5;
    }
}
