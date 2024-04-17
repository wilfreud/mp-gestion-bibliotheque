package model;

import exception.*;
import model.books.AudioBook;
import model.books.Book;
import model.books.Essay;
import model.books.Novel;
import ui.books.BooksTable;

import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    private final ArrayList<Book> booksList;
    private final HashMap<User, ArrayList<Book>> userBorrows;

    private final LibraryStats stats;

    public Library() {
        this.booksList = new ArrayList<>();
        this.userBorrows = new HashMap<>();
        this.stats = new LibraryStats();
        this.booksList.add(new Essay("eh", "oh", 123, "asdas"));
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
        if(!this.booksList.contains(book)) throw new BookNotFoundException("Ce livre n'existe pas");
        this.booksList.remove(book);
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

    public void returnBook(User user, Book book) {
        if (this.userBorrows.containsKey(user)) {
            this.userBorrows.get(user).remove(book);
        }
    }

    public boolean isBookBorrowableByUser(User user) {
        if (this.userBorrows.containsKey(user))
            return this.userBorrows.get(user).size() < Utils.MAX_BORROWS;
        else return true;
    }

    public void showStats() {
        System.out.println("Nombre total livres: " + this.stats.getTotalLivres());
        System.out.println("Nombre total emprunts: " + this.stats.getTotalEmprunts());
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
}
