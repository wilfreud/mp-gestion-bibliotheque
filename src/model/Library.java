package model;

import exception.*;
import model.books.AudioBook;
import model.books.Book;
import model.books.Essay;
import model.books.Novel;

import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    // Using singleton pattern to ease the usage across the app
    private static Library instance;
    private final ArrayList<Book> booksList;

    private final ArrayList<User> usersList;
    private final HashMap<User, ArrayList<Book>> userBorrows;

    private final LibraryStats stats;

    private Library() {
        this.booksList = new ArrayList<>();
        this.userBorrows = new HashMap<>();
        this.stats = new LibraryStats();
        this.booksList.add(new Essay("eh", "oh", 123, "asdas"));
        this.usersList = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public ArrayList<Book> getBooksList() {
        return this.booksList;
    }

    public void addBook(Book book) throws BookAlreadyExistsException, InvalidBookException {
        if (this.doesBookExist(book.getISBN())) throw new BookAlreadyExistsException("Ce livre existe deja");
        if (this.bookHasInvalidFields(book)) throw new InvalidBookException("Un champ est in correct");
        this.booksList.add(book);
    }

    /**
     * TODO: rework this (please)
     */
    public void addBook(String titre, String auteur, int anneePublication, String ISBN, Utils.BookType category) throws BookAlreadyExistsException, InvalidBookException {
        if (this.doesBookExist(ISBN)) throw new BookAlreadyExistsException("Ce livre existe deja");
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

    public void removeBook(Book book) throws BookNotFoundException {
        if (!this.booksList.remove(book)) throw new BookNotFoundException("Ce livre n'existe pas");
    }

    public ArrayList<Book> findBook(String text) {
        ArrayList<Book> resultats = new ArrayList<Book>();
        for (Book book : this.booksList)
            if (book.getTitle().contains(text) || book.getAuthor().contains(text) || book.getISBN().contains(text))
                resultats.add(book);


        return resultats;
    }

    public Book findUnique(String isbn) {
        for (Book book : this.booksList) {
            if (book.getISBN().equals(isbn)) return book;
        }
        return null;
    }

    public void borrowBook(User user, Book book) throws UnauthorizedBookBorrowException, AlreadyBorrowedBookExeption {
        if (!this.isBookBorrowableByUser(user)) {
            throw new UnauthorizedBookBorrowException("Cet utilisateur n'est pas éligible");
        }

        if (this.userBorrows.containsKey(user)) {
            ArrayList<Book> books = this.userBorrows.get(user);
            if (this.booksList.contains(book)) throw new AlreadyBorrowedBookExeption("Ce livre a deja été emprunté");
            books.add(book);
        } else {
            ArrayList<Book> initListe = new ArrayList<Book>();
            initListe.add(book);
            this.userBorrows.put(user, initListe);
        }
    }

    public void returnBook(User user, Book book) throws BookNotFoundException {
        if (!this.userBorrows.get(user).remove(book)) throw new BookNotFoundException("Ce livre est introuvable");
    }

    public boolean isBookBorrowableByUser(User user) {
        if (this.userBorrows.containsKey(user))
            return this.userBorrows.get(user).size() < Utils.MAX_BORROWS;
        else return true;
    }


    public boolean doesBookExist(String isbn) {
        for (Book book : this.booksList) if (book.getISBN().equals(isbn)) return true;
        return false;
    }

    public long booksCount() {
        return this.booksList.size();
    }

    public boolean bookHasInvalidFields(Book book) {
        return (book.getTitle().isBlank() || book.getAuthor().isBlank() || book.getISBN().isBlank());
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void addUser(String name, int id) throws UserAlreadyExistException, InvalidUserException {
        if (name == null || name.isBlank()) throw new InvalidUserException("Informations invalides");
        if (this.doesUserExist(id)) throw new UserAlreadyExistException("Cet utilisateur existe deja");

        this.usersList.add(new User(name, id));
    }

    public boolean doesUserExist(int id) {
        for (User user : usersList) if (user.getId() == id) return true;
        return false;
    }

    public void removeUser(User user) throws UserNotFoundException {
        if (!this.usersList.remove(user)) throw new UserNotFoundException("Cet utilisateur n'existe pas");
        this.userBorrows.remove(user);
    }

    public HashMap<User, ArrayList<Book>> getUserBorrows() {
        return userBorrows;
    }

    public LibraryStats getStats() {
        return stats;
    }
}
