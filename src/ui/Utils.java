package ui;

import java.awt.*;

public class Utils {
    public enum FontSize {
        H1(32), H2(24), H3(20), H4(16);
        private final int size;

        FontSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    public static Font createFont(Component component, FontSize size) {
        return new Font(component.getFont().getName(), Font.PLAIN, size.getSize());
    }
}
