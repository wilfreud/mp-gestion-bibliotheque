package ui.forms;

import model.User;
import ui.Utils;
import ui.books.BooksTable;
import ui.books.ButtonEditor;
import ui.books.ButtonRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * A form for managing book borrowings by a user.
 */
public class BookBorrowListForm {
    /**
     * Constructs a BookBorrowListForm instance.
     *
     * @param user The user whose borrowings are managed.
     */
    public BookBorrowListForm(User user) {
        // Initialize frame
        JFrame frame = new JFrame("Gestion des emprunts");
        frame.setSize(800, 600);

        // Insert window content
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Page title
        JLabel panelTitle = new JLabel("Liste des emprunts");
        panelTitle.setFont(Utils.createFont(panelTitle, Utils.FontSize.H2));
        panel.add(panelTitle);

        // Data table
        BooksTable tableModel = new BooksTable(user.getBorrowedBooks());
        JTable table = new JTable(tableModel);

        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer("Rendre"));
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(tableModel, table, "Rendre", user));

        // Action button
        JButton createBtn = new JButton("Nouvel emprunt");
        createBtn.addActionListener(e -> new BookBorrowForm(tableModel, user));
        panel.add(createBtn);

        // Open window
        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
