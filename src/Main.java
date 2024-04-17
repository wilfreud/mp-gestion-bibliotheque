import model.Library;
import ui.MainWindow;

public class Main {
    public static void main(String[] args) {

        final Library library = new Library();
        MainWindow win = new MainWindow(library);

    }
}
