package ui.books;

import model.Library;
import model.books.Book;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BooksTable extends AbstractTableModel {
    private final ArrayList<Book> data = Library.getInstance().getBooksList();
    private final String[] columnNames = {"Titre", "Auteur", "Anne Publication", "ISBN", "Action"};

    public BooksTable(){
    }

    public void notifyBookAdded() {
        fireTableDataChanged();
    }

    @Override
    public int getRowCount(){
        return this.data.size();
    }

    @Override
    public int getColumnCount(){
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

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
            case 4 -> "Action";
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return columnIndex == 4;
    }
}
