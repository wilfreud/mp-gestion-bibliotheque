package model;

import model.books.Book;

import java.util.ArrayList;

public class User {
    private String name;
    private int id;
    private boolean allowedToBorrowBook = true; // in terms of cotisation
    private ArrayList<Book> borrowedBooks;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void addBorredBook(Book book) {
        this.borrowedBooks.add(book);
    }

    public void returnBorrowedBook(Book book) {
        this.borrowedBooks.remove(book);
    }

    public void printBorrowedBooks(){
        this.borrowedBooks.forEach(book -> System.out.println(book.toString()));
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

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public boolean isAllowedToBorrowBook() {
        return allowedToBorrowBook;
    }
    public void setAllowedToBorrowBook(boolean allowedToBorrowBook) {
        this.allowedToBorrowBook = allowedToBorrowBook;
    }

}
