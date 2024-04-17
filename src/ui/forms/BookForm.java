package ui.forms;

import exception.BookAlreadyExistsException;
import exception.BookNotFoundException;
import exception.InvalidBookException;
import model.Library;
import model.Utils;
import model.books.Book;
import ui.books.BooksTable;
import ui.dialogs.WarningDialog;

import javax.swing.*;
import java.awt.*;

public class BookForm {
    private final JFrame frame = new JFrame("Formulaire Livre");

    // form fields
    private final JComboBox<Utils.BookType> categorySelect = new JComboBox<>(Utils.BookType.values());
    private JTextField titleTextField;
    private JTextField authorTextField;
    private JTextField publicationYearTextField;
    private JTextField isbnTextField;


    public BookForm(Library library, BooksTable booksTable) {
        JButton submitBtn = new JButton("Enregistrer");
        initializeFrame(submitBtn, null);

        // binding action listeners
        submitBtn.addActionListener(e -> {
            try {

                library.addBook(this.titleTextField.getText(),
                        this.authorTextField.getText(),
                        Integer.parseInt(this.publicationYearTextField.getText()),
                        this.isbnTextField.getText(),
                        (Utils.BookType) this.categorySelect.getSelectedItem());
                booksTable.notifyBookAdded();
                frame.dispose();
                System.out.println(library.booksCount());
            } catch (BookAlreadyExistsException | InvalidBookException err) {
                new WarningDialog(frame, "Erreur d'enregisterment", err.getMessage());
            } catch (NumberFormatException nfe) {
                new WarningDialog(frame, "Erreur d'enregistrement", "L'année doit être un nombre");
            }
        });


    }

    public BookForm(Library library, BooksTable booksTable, Book book) {
        JButton submitBtn = new JButton("Enregistrer");
        JButton deleteBtn = new JButton("Supprimer");
        initializeFrame(submitBtn, deleteBtn);
        this.titleTextField.setText(book.getTitle());
        this.authorTextField.setText(book.getAuthor());
        this.publicationYearTextField.setText("" + book.getPublicationYear());
        this.isbnTextField.setText(book.getISBN());

        submitBtn.addActionListener(e -> {
            try {
                if (library.doesBookExist(this.isbnTextField.getText()))
                    throw new BookAlreadyExistsException("Ce livre existe deja");
                if (library.bookHasInvalidFields(book)) throw new InvalidBookException("Un champ est invalide");
                book.setTitle(this.titleTextField.getText());
                book.setAuthor(this.authorTextField.getText());
                book.setPublicationYear(Integer.parseInt(this.publicationYearTextField.getText()));
                book.setISBN(this.isbnTextField.getText());
                this.frame.dispose();
                booksTable.notifyBookAdded();
            } catch (BookAlreadyExistsException | InvalidBookException err) {
                new WarningDialog(frame, "Erreur d'enregisterment", err.getMessage());
            } catch (NumberFormatException nfe) {
                new WarningDialog(frame, "Erreur d'enregistrement", "L'année doit être un nombre");
            }
        });

        deleteBtn.addActionListener(e -> {
            try {
                final int choice = JOptionPane.showConfirmDialog(frame, "Etes-vous sûr(e) de vouloir supprimer ce livre?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_NO_OPTION) {
                    library.removeBook(book);
                    this.frame.dispose();
                    booksTable.notifyBookAdded();
                }
            } catch (BookNotFoundException er) {
                new WarningDialog(frame, "Erreur de suppression", er.getMessage());
            }
        });
    }

    public void initializeFrame(JButton submitBtn, JButton deleteBtn) {
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
        footerPanel.add(submitBtn);

        // add delete btn
        if (deleteBtn != null) {
            footerPanel.add(deleteBtn);
        }


        // final component mounting
        frame.add(panel);
        frame.add(footerPanel);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
