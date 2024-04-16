package ui;

import java.awt.*;

public class Utils {
    public static int DEFAULT_FONT_SIZE = 16;
    public enum FontSize {
        H1(32), H2(24), H3(20);
        private final int size;

        private FontSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    public static Font createFont(Component component) {
        return new Font(component.getFont().getName(), Font.PLAIN, DEFAULT_FONT_SIZE);
    }

    public static Font createFont(Component component, FontSize size) {
        return new Font(component.getFont().getName(), Font.PLAIN, size.getSize());
    }
}
