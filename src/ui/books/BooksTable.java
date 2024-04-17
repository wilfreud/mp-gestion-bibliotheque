package ui.books;

import model.books.Book;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class BooksTable extends AbstractTableModel {
    private final ArrayList<Book> data;
    private final String[] columnNames = {"Titre", "Auteur", "Anne Publication", "ISBN", "Action"};

    public BooksTable(ArrayList<Book> booksListRef){
        this.data = booksListRef;
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
        switch (columnIndex) {
            case 0:
                return book.getTitle();
            case 1:
                return book.getAuthor();
            case 2:
                return book.getPublicationYear();
            case 3:
                return book.getISBN();
            case 4:
                return "Action";
            default:
                return null;

        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return columnIndex == 4;
    }
}
