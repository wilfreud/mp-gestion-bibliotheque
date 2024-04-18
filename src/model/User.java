package model;

import exception.BookNotFoundException;
import exception.InvalidBookException;
import exception.UnauthorizedBookBorrowException;
import model.books.Book;

import java.util.ArrayList;

/**
 * Represents a User in the library system.
 */
public class User {
    private String name;
    private int id;
    private boolean allowedToBorrowBook = true; // in terms of cotisation
    private final ArrayList<Book> borrowedBooks;

    /**
     * Constructs a User with a given name and ID.
     *
     * @param name The name of the user.
     * @param id   The ID of the user.
     */
    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
    }

    /**
     * Adds a book to the user's borrowed books.
     *
     * @param book The book to be borrowed.
     * @throws InvalidBookException          If the book is null.
     * @throws UnauthorizedBookBorrowException If the user is not allowed to borrow books.
     */
    public void addBorrowedBook(Book book) throws InvalidBookException, UnauthorizedBookBorrowException {
        if (book == null) throw new InvalidBookException("Emprunt de livre impossible. Aucun livre choisi");
        if (!this.isAllowedToBorrowBook())
            throw new UnauthorizedBookBorrowException("Emprunt non autorisé, cotisations non à jour");
        else
            this.borrowedBooks.add(book);
    }

    /**
     * Returns a borrowed book.
     *
     * @param book The book to be returned.
     * @throws BookNotFoundException If the book is not found in the borrowed books list.
     */
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
