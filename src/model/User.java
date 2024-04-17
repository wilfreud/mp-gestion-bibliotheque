package model;

import exception.BookNotFoundException;
import exception.InvalidBookException;
import exception.UnauthorizedBookBorrowException;
import model.books.Book;

import java.util.ArrayList;

public class User {
    private String name;
    private int id;
    private boolean allowedToBorrowBook = true; // in terms of cotisation
    private final ArrayList<Book> borrowedBooks;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
    }

    public void addBorrowedBook(Book book) throws InvalidBookException, UnauthorizedBookBorrowException {
        if (book == null) throw new InvalidBookException("Emprunt de livre impossible. Aucun livre choisi");
        if (!this.isAllowedToBorrowBook())
            throw new UnauthorizedBookBorrowException("Emprunt non autorisé, côtisations non à jour");
        else
            this.borrowedBooks.add(book);

    }

    public void returnBorrowedBook(Book book) throws BookNotFoundException {
        if (book == null) throw new BookNotFoundException("Ce livre est introuvable");
        this.borrowedBooks.remove(book);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }


    public boolean isAllowedToBorrowBook() {
        return allowedToBorrowBook;
    }

    public void setAllowedToBorrowBook(boolean allowedToBorrowBook) {
        this.allowedToBorrowBook = allowedToBorrowBook;
    }

}
