package ui.forms;

import exception.AlreadyBorrowedBookExeption;
import exception.ExceptionUtils;
import exception.InvalidBookException;
import exception.UnauthorizedBookBorrowException;
import model.Library;
import model.User;
import model.books.Book;
import ui.Utils;
import ui.books.BookListModel;
import ui.books.BooksTable;
import ui.dialogs.WarningDialog;

import javax.swing.*;
import java.awt.*;

public class BookBorrowForm {
    private final Library library = Library.getInstance();
    private Book selectedBook;

    public BookBorrowForm(BooksTable booksTable, User user) {
        JFrame frame = new JFrame("Emprunter un livre");
        frame.setLayout(new BorderLayout());

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // text
        JLabel titleLabel = new JLabel("Choisir un livre");
        titleLabel.setFont(Utils.createFont(titleLabel, Utils.FontSize.H2));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // list of books
        BookListModel bookListModel = new BookListModel(library.getBooksList());
        JList<Book> bookList = new JList<>(bookListModel);
        bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create a scroll pane for the JList
        JScrollPane scrollPane = new JScrollPane(bookList);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JButton submitBtn = new JButton("Emprunter");

        // bind behaviour
        bookList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting())
                selectedBook = bookList.getSelectedValue();
        });

        submitBtn.addActionListener(e -> {
            try {
                user.addBorrowedBook(selectedBook);
                this.library.registerBookBorrow(user, selectedBook);
                booksTable.notifyBookAdded();
                frame.dispose();
            } catch (InvalidBookException | UnauthorizedBookBorrowException | AlreadyBorrowedBookExeption err) {
                new WarningDialog(null, ExceptionUtils.SAVING_ERROR_TITLE, err.getMessage());
            } catch (Exception ex) {
                new WarningDialog(null, ExceptionUtils.UNEXPECTED_ERROR_TITLE, ExceptionUtils.UNEXPECTED_ERROR_MESSAGE);
            }
        });

        // mount components
        frame.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(submitBtn, BorderLayout.SOUTH);

        // open window
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
