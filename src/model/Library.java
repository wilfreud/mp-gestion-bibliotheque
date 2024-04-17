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


    private Library() {
        this.booksList = new ArrayList<>();
        this.userBorrows = new HashMap<>();
        this.booksList.add(new Essay("eh", "oh", 123, "asdas"));
        this.usersList = new ArrayList<>();
        this.usersList.add(new User("Yankee suprem", 1));
        this.usersList.add(new User("Jay", 2));
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

    public void registerBookBorrow(User user, Book book) throws UnauthorizedBookBorrowException, InvalidBookException, AlreadyBorrowedBookExeption {
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


    public void registerBookReturn(User user, Book book) throws BookNotFoundException {
        ArrayList<Book> books = this.userBorrows.get(user);
        if (books == null) {
            throw new BookNotFoundException("No borrowed books found for this user");
        }

        boolean removed = books.remove(book);
        if (!removed) {
            throw new BookNotFoundException("The specified book is not borrowed by this user");
        }

        for(Book b : books) {
            System.out.println(b);
        }
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

    public void addUser(String name, int id, boolean allowedCotisation) throws UserAlreadyExistException, InvalidUserException {
        if (name == null || name.isBlank()) throw new InvalidUserException("Informations invalides");
        if (this.doesUserExist(id)) throw new UserAlreadyExistException("Cet utilisateur existe deja");

        User user = new User(name, id);
        user.setAllowedToBorrowBook(allowedCotisation);
        this.usersList.add(user);

        /* frankly, this is (arguably) useless
        A better solution would have been to use a reference to user.borrowedBooks
        ...
        */
        this.userBorrows.put(user, new ArrayList<>());
    }

    public boolean doesUserExist(int id) {
        for (User user : usersList) if (user.getId() == id) return true;
        return false;
    }

    public void removeUser(User user) throws UserNotFoundException {
        if (!this.usersList.remove(user)) throw new UserNotFoundException("Cet utilisateur n'existe pas");
        this.userBorrows.remove(user);
    }

}
