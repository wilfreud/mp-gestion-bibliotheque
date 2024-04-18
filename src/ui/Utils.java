package ui;

import java.awt.*;

/**
 * Utility class for UI-related tasks.
 */
public class Utils {

    /**
     * Represents different font sizes.
     */
    public enum FontSize {
        H1(32), H2(24), H3(20), H4(16);

        private final int size;

        /**
         * Constructs a FontSize with the specified size.
         *
         * @param size The size associated with the FontSize.
         */
        FontSize(int size) {
            this.size = size;
        }

        /**
         * Returns the size associated with this FontSize.
         *
         * @return The size value.
         */
        public int getSize() {
            return size;
        }
    }

    /**
     * Creates a new Font based on the specified component's font name and the given FontSize.
     *
     * @param component The component to derive font information from.
     * @param size      The FontSize enum value specifying the font size.
     * @return A new Font object with the specified size and the font name of the component.
     */
    public static Font createFont(Component component, FontSize size) {
        return new Font(component.getFont().getName(), Font.PLAIN, size.getSize());
    }
}
