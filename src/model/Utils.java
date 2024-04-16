package model;

public class Utils {
    public final static int MAX_BORROWS = 5;

    public enum BookType {
        AUDIO_BOOK("Livre Audio"),
        NOVEL("Roman"),
        ESSAY("Essai");

        private final String key;

        BookType(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

    }
}
