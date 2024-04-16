package model;

import exception.AlreadyBorrowedBookExeption;
import exception.BookAlreadyExistsException;
import exception.UnauthorizedBookBorrowException;
import model.books.AudioBook;
import model.books.Book;
import model.books.Essay;
import model.books.Novel;

import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    private ArrayList<Book> booksList;
    private HashMap<User, ArrayList<Book>> userBorrows;

    private LibraryStats stats;

    public Library() {
        this.stats = new LibraryStats();
    }

    public void addBook(Book book) throws BookAlreadyExistsException {
        if (this.doesBookExist(book)) throw new BookAlreadyExistsException("Ce livre existe deja");
        this.booksList.add(book);
    }

    /**
     * TODO: rework this (please)
     */
    public void addBook(String titre, String auteur, int anneePublication, String ISBN, Utils.BookType category) {
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

    public void removeBook(Book book) {
        this.booksList.remove(book);
    }

    public ArrayList<Book> findBook(String text) {
        ArrayList<Book> resultats = new ArrayList<Book>();
        for (Book book : this.booksList)
            if (book.getTitre().contains(text) || book.getAuteur().contains(text) || book.getISBN().contains(text))
                resultats.add(book);


        return resultats;
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

    public boolean doesBookExist(Book book) {
        return this.booksList.contains(book);
    }

}
