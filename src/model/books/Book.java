package model.books;

/**
 * Represents an abstract Book with basic information.
 */
public abstract class Book {
    private String title;
    private String author;
    private int publicationYear;
    private String ISBN;

    /**
     * Constructs a Book with specified information.
     *
     * @param title           The title of the book.
     * @param author          The author of the book.
     * @param publicationYear The publication year of the book.
     * @param ISBN            The ISBN (International Standard Book Number) of the book.
     */
    public Book(String title, String author, int publicationYear, String ISBN) {
        this.author = author;
        this.title = title;
        this.publicationYear = publicationYear;
        this.ISBN = ISBN;
    }

    /**
     * Retrieves the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title The title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author The author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Retrieves the publication year of the book.
     *
     * @return The publication year of the book.
     */
    public int getPublicationYear() {
        return publicationYear;
    }

    /**
     * Sets the publication year of the book.
     *
     * @param publicationYear The publication year of the book.
     */
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    /**
     * Retrieves the ISBN of the book.
     *
     * @return The ISBN of the book.
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param ISBN The ISBN of the book.
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Returns a string representation of the book.
     *
     * @return A string representation of the book.
     */
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
