package model;

/**
 * Utility class containing constants and enums for the library application.
 */
public class Utils {
    /** Maximum number of books a user can borrow. */
    public final static int MAX_BORROWS = 5;

    /**
     * Enum representing different types of books with corresponding display keys.
     */
    public enum BookType {
        AUDIO_BOOK("Livre Audio"),
        NOVEL("Roman"),
        ESSAY("Essai");

        private final String key;

        /**
         * Constructs a BookType enum with the specified display key.
         *
         * @param key The display key for the book type.
         */
        BookType(String key) {
            this.key = key;
        }

        /**
         * Retrieves the display key of the book type.
         *
         * @return The display key.
         */
        public String getKey() {
            return key;
        }
    }
}
