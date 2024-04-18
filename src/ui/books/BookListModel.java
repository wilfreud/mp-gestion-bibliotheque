package ui.books;

import model.books.Book;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Custom list model for displaying books in a JList.
 */
public class BookListModel extends AbstractListModel<Book> {
    private final ArrayList<Book> books;

    /**
     * Constructs a BookListModel with the specified list of books.
     *
     * @param books The list of books to display.
     */
    public BookListModel(ArrayList<Book> books) {
        this.books = books;
    }

    /**
     * Returns the size of the list.
     *
     * @return The number of books in the list.
     */
    @Override
    public int getSize() {
        return books.size();
    }

    /**
     * Returns the book at the specified index.
     *
     * @param index The index of the book to retrieve.
     * @return The book at the specified index.
     */
    @Override
    public Book getElementAt(int index) {
        return books.get(index);
    }
}
