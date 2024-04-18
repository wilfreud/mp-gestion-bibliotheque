import model.Library;
import ui.MainWindow;

public class Main {
    public static void main(String[] args) {

        Library.getInstance();
        new MainWindow();

    }
}
