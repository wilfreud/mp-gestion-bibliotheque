package model.books;

import model.Utils;

/**
 * Represents a Novel book, which is a type of Book.
 */
public class Novel extends Book {
    private String subject;
    private final String category;

    /**
     * Constructs a Novel book with specified information.
     *
     * @param title           The title of the novel.
     * @param author          The author of the novel.
     * @param publicationYear The publication year of the novel.
     * @param ISBN            The ISBN (International Standard Book Number) of the novel.
     */
    public Novel(String title, String author, int publicationYear, String ISBN) {
        super(title, author, publicationYear, ISBN);
        this.category = Utils.BookType.NOVEL.getKey();
    }

    /**
     * Constructs a Novel book with specified information and subject.
     *
     * @param title           The title of the novel.
     * @param author          The author of the novel.
     * @param publicationYear The publication year of the novel.
     * @param ISBN            The ISBN (International Standard Book Number) of the novel.
     * @param subject         The subject of the novel.
     */
    public Novel(String title, String author, int publicationYear, String ISBN, String subject) {
        this(title, author, publicationYear, ISBN);
        this.subject = subject;
    }

    /**
     * Retrieves the category of the novel.
     *
     * @return The category of the novel.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Retrieves the subject of the novel.
     *
     * @return The subject of the novel.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the novel.
     *
     * @param subject The subject to set for the novel.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
