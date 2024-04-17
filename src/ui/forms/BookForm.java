package ui.forms;

import exception.BookAlreadyExistsException;
import exception.InvalidBookException;
import model.Library;
import model.Utils;
import model.books.Book;
import ui.dialogs.WarningDialog;

import javax.swing.*;
import java.awt.*;

public class BookForm {

    // form fields
    private final JComboBox<Utils.BookType> categorySelect = new JComboBox<>(Utils.BookType.values());
    private final JTextField titleTextField;
    private final JTextField authorTextField;
    private final JTextField publicationYearTextField;
    private final JTextField isbnTextField;
    private final JButton submitBtn = new JButton("Enregistrer");


    public BookForm(Library library) {
        JFrame frame = new JFrame("Formulaire Livre");
        frame.setSize(500, 250);
        frame.setResizable(false);
        JPanel panel = new JPanel(new GridLayout(0, 2));

        // setting & mounting fields
        JLabel categoryLabel = new JLabel("Categorie");
        panel.add(categoryLabel);
        panel.add(categorySelect);

        JLabel titleLabel = new JLabel("Titre");
        this.titleTextField = new JTextField();
        panel.add(titleLabel);
        panel.add(this.titleTextField);

        JLabel authorLabel = new JLabel("Auteur");
        this.authorTextField = new JTextField();
        panel.add(authorLabel);
        panel.add(this.authorTextField);

        JLabel publicationYearLabel = new JLabel("Année de publication");
        this.publicationYearTextField = new JTextField();
        panel.add(publicationYearLabel);
        panel.add(this.publicationYearTextField);

        JLabel isbnLabel = new JLabel("ISBN");
        this.isbnTextField = new JTextField();
        panel.add(isbnLabel);
        panel.add(isbnTextField);

        JPanel footerPanel = new JPanel();
        footerPanel.add(this.submitBtn);

        // binding action listeners
        this.submitBtn.addActionListener(e -> {
            try {

                library.addBook(this.titleTextField.getText(),
                        this.authorTextField.getText(),
                        Integer.parseInt(this.publicationYearTextField.getText()),
                        this.isbnTextField.getText(),
                        (Utils.BookType) this.categorySelect.getSelectedItem());

                System.err.println(library.booksCount());
            } catch (BookAlreadyExistsException | InvalidBookException err) {
                new WarningDialog(frame, "Erreur d'enregisterment", err.getMessage());
            } catch (NumberFormatException nfe) {
                new WarningDialog(frame, "Erreur d'enregistrement", "L'année doit être un nombre");
            }
        });

        // final component mounting
        frame.add(panel);
        frame.add(footerPanel);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public BookForm(Library library, Book book) {
        this(library);
        this.titleTextField.setText(book.getTitle());
        this.authorTextField.setText(book.getAuthor());
        this.publicationYearTextField.setText("" + book.getPublicationYear());
        this.isbnTextField.setText(book.getISBN());

        this.submitBtn.addActionListener(e -> {
            book.setTitle(this.titleTextField.getText());
            book.setAuthor(this.authorTextField.getText());
            book.setPublicationYear(Integer.parseInt(this.publicationYearTextField.getText()));
            book.setISBN(this.isbnTextField.getText());
        });
    }
}
