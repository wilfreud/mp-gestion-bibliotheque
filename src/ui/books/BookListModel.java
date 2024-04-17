package ui.books;

import model.books.Book;

import javax.swing.*;
import java.util.ArrayList;

public class BookListModel extends AbstractListModel<Book> {
    private final ArrayList<Book> books;

    public BookListModel(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public int getSize() {
        return books.size();
    }

    @Override
    public Book getElementAt(int index) {
        return books.get(index);
    }
}
