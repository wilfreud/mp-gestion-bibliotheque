package ui.forms;

import model.User;
import ui.Utils;
import ui.books.BooksTable;
import ui.books.ButtonEditor;
import ui.books.ButtonRenderer;

import javax.swing.*;
import java.awt.*;

public class BookBorrowListForm {
    public BookBorrowListForm(User user) {
        // initialize frame
        JFrame frame = new JFrame("Gestion des emprunts");
        frame.setSize(800, 600);

        // insert window content
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // page title
        JLabel panelTitle = new JLabel("Liste des emprunts");
        panelTitle.setFont(Utils.createFont(panelTitle, Utils.FontSize.H2));
        panel.add(panelTitle);


        // data table
        BooksTable tableModel = new BooksTable(user.getBorrowedBooks());
        JTable table = new JTable(tableModel);

        table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer("Rendre"));
        table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(tableModel, table, "Rendre", user));

        // action button
        JButton createBtn = new JButton("Nouvel emprunt");
        createBtn.addActionListener(e -> new BookBorrowForm(tableModel, user));
        panel.add(createBtn);

        // open window
        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
