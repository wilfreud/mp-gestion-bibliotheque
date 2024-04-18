package model;

import exception.*;
import model.books.AudioBook;
import model.books.Book;
import model.books.Essay;
import model.books.Novel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Library class represents a singleton library system that manages books and user borrowing activities.
 */
public class Library {
    // Using singleton pattern to ease the usage across the app
    private static Library instance;
    private final ArrayList<Book> booksList;
    private final ArrayList<User> usersList;

    public HashMap<User, ArrayList<Book>> getUserBorrows() {
        return userBorrows;
    }

    private final HashMap<User, ArrayList<Book>> userBorrows;

    private Library() {
        this.booksList = new ArrayList<>();
        this.userBorrows = new HashMap<>();
        this.booksList.add(new Essay("eh", "oh", 123, "asdas"));
        this.usersList = new ArrayList<>();
        this.usersList.add(new User("Yankee suprem", 1));
        this.usersList.add(new User("Jay", 2));
    }

    /**
     * Retrieves the singleton instance of the Library.
     *
     * @return The singleton instance of the Library.
     */
    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    /**
     * Retrieves the list of books in the library.
     *
     * @return The list of books in the library.
     */
    public ArrayList<Book> getBooksList() {
        return this.booksList;
    }

    /**
     * Adds a new book to the library.
     *
     * @param titre            The title of the book.
     * @param auteur           The author of the book.
     * @param anneePublication The publication year of the book.
     * @param ISBN             The ISBN of the book.
     * @param category         The category of the book (Essay, Novel, AudioBook).
     * @throws BookAlreadyExistsException If the book with the provided ISBN already exists in the library.
     * @throws InvalidBookException       If required book information (title, author, ISBN) is blank.
     */
    public void addBook(String titre, String auteur, int anneePublication, String ISBN, Utils.BookType category)
            throws BookAlreadyExistsException, InvalidBookException {
        if (this.doesBookExist(ISBN)) throw new BookAlreadyExistsException("Ce livre existe déjà");
        if (titre.isBlank() || auteur.isBlank() || ISBN.isBlank())
            throw new InvalidBookException("Remplissez les champs obligatoires");

        switch (category) {
            case ESSAY:
                this.booksList.add(new Essay(titre, auteur, anneePublication, ISBN));
                break;
            case NOVEL:
                this.booksList.add(new Novel(titre, auteur, anneePublication, ISBN));
                break;
            case AUDIO_BOOK:
                this.booksList.add(new AudioBook(titre, auteur, anneePublication, ISBN));
        }
    }

    /**
     * Removes a book from the library.
     *
     * @param book The book to be removed.
     * @throws BookNotFoundException If the specified book is not found in the library.
     */
    public void removeBook(Book book) throws BookNotFoundException {
        if (!this.booksList.remove(book)) throw new BookNotFoundException("Ce livre n'existe pas");
    }

    /**
     * Registers a book borrowing activity for a user.
     *
     * @param user The user borrowing the book.
     * @param book The book being borrowed.
     * @throws UnauthorizedBookBorrowException If the user is not authorized to borrow more books.
     * @throws InvalidBookException           If the book or user information is invalid.
     * @throws AlreadyBorrowedBookExeption    If the book is already borrowed by the user.
     */
    public void registerBookBorrow(User user, Book book)
            throws UnauthorizedBookBorrowException, InvalidBookException, AlreadyBorrowedBookExeption {
        if (book == null) {
            throw new InvalidBookException("Aucun livre choisi");
        }
        if (user == null) {
            throw new UnauthorizedBookBorrowException("Aucun utilisateur cible choisi");
        }
        if (!this.isBookBorrowableByUser(user)) {
            throw new UnauthorizedBookBorrowException("Cet utilisateur n'est pas éligible pour emprunter plus de livres");
        }

        ArrayList<Book> borrowedBooks = this.userBorrows.getOrDefault(user, new ArrayList<>());

        if (borrowedBooks.contains(book)) {
            throw new AlreadyBorrowedBookExeption("Ce livre a déjà été emprunté par cet utilisateur");
        }

        borrowedBooks.add(book);

        this.userBorrows.put(user, borrowedBooks);
    }

    /**
     * Registers a book return activity for a user.
     *
     * @param user The user returning the book.
     * @param book The book being returned.
     * @throws BookNotFoundException If the book is not found in the user's borrowed books list.
     */
    public void registerBookReturn(User user, Book book) throws BookNotFoundException {
        ArrayList<Book> books = this.userBorrows.get(user);
        if (books == null) {
            throw new BookNotFoundException("No borrowed books found for this user");
        }

        boolean removed = books.remove(book);
        if (!removed) {
            throw new BookNotFoundException("The specified book is not borrowed by this user");
        }
    }

    /**
     * Checks if a user is eligible to borrow more books.
     *
     * @param user The user to check.
     * @return True if the user can borrow more books, false otherwise.
     */
    public boolean isBookBorrowableByUser(User user) {
        if (this.userBorrows.containsKey(user))
            return this.userBorrows.get(user).size() < Utils.MAX_BORROWS;
        else return true;
    }

    /**
     * Checks if a book with the specified ISBN exists in the library.
     *
     * @param isbn The ISBN of the book to check.
     * @return True if the book exists, false otherwise.
     */
    public boolean doesBookExist(String isbn) {
        for (Book book : this.booksList) if (book.getISBN().equals(isbn)) return true;
        return false;
    }

    /**
     * Checks if a book has any invalid fields (title, author, ISBN).
     *
     * @param book The book to check.
     * @return True if the book has invalid fields, false otherwise.
     */
    public boolean bookHasInvalidFields(Book book) {
        return (book.getTitle().isBlank() || book.getAuthor().isBlank() || book.getISBN().isBlank());
    }

    /**
     * Retrieves the list of users in the library.
     *
     * @return The list of users in the library.
     */
    public ArrayList<User> getUsersList() {
        return usersList;
    }

    /**
     * Adds a new user to the library.
     *
     * @param name             The name of the user.
     * @param id               The ID of the user.
     * @param allowedCotisation The status of the user's payment.
     * @throws UserAlreadyExistException If a user with the same ID already exists in the library.
     * @throws InvalidUserException     If the user information is invalid.
     */
    public void addUser(String name, int id, boolean allowedCotisation)
            throws UserAlreadyExistException, InvalidUserException {
        if (name == null || name.isBlank()) throw new InvalidUserException("Informations invalides");
        if (this.doesUserExist(id)) throw new UserAlreadyExistException("Cet utilisateur existe déjà");

        User user = new User(name, id);
        user.setAllowedToBorrowBook(allowedCotisation);
        this.usersList.add(user);

        this.userBorrows.put(user, new ArrayList<>());
    }

    /**
     * Checks if a user with the specified ID exists in the library.
     *
     * @param id The ID of the user to check.
     * @return True if the user exists, false otherwise.
     */
    public boolean doesUserExist(int id) {
        for (User user : usersList) if (user.getId() == id) return true;
        return false;
    }

    /**
     * Removes a user from the library.
     *
     * @param user The user to be removed.
     * @throws UserNotFoundException If the specified user is not found in the library.
     */
    public void removeUser(User user) throws UserNotFoundException {
        if (!this.usersList.remove(user)) throw new UserNotFoundException("Cet utilisateur n'existe pas");
        this.userBorrows.remove(user);
    }
}
