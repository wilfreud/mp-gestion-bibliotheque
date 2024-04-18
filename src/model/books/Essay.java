package model.books;

/**
 * Represents an Essay book, which is a type of Book.
 */
public class Essay extends Book {
    private long wordCount;

    /**
     * Constructs an Essay book with specified information.
     *
     * @param title           The title of the essay.
     * @param author          The author of the essay.
     * @param publicationYear The publication year of the essay.
     * @param ISBN            The ISBN (International Standard Book Number) of the essay.
     */
    public Essay(String title, String author, int publicationYear, String ISBN) {
        super(title, author, publicationYear, ISBN);
    }

    /**
     * Constructs an Essay book with specified information and word count.
     *
     * @param title           The title of the essay.
     * @param author          The author of the essay.
     * @param publicationYear The publication year of the essay.
     * @param ISBN            The ISBN (International Standard Book Number) of the essay.
     * @param wordCount       The word count of the essay.
     */
    public Essay(String title, String author, int publicationYear, String ISBN, long wordCount) {
        this(title, author, publicationYear, ISBN);
        this.wordCount = wordCount;
    }

    /**
     * Retrieves the word count of the essay.
     *
     * @return The word count of the essay.
     */
    public long getWordCount() {
        return wordCount;
    }

    /**
     * Sets the word count of the essay.
     *
     * @param wordCount The word count of the essay.
     */
    public void setWordCount(long wordCount) {
        this.wordCount = wordCount;
    }
}
