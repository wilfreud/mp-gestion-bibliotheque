package ui;

import model.Library;
import ui.Utils.FontSize;
import ui.books.BooksTable;
import ui.books.ButtonEditor;
import ui.books.ButtonRenderer;
import ui.forms.BookForm;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private final Library libraryRef;
    private final String MAIN_PAGE_CONSTRAINT = "MAIN_PAGE";
    private final String USERS_PAGE_CONSTRAINT = "USERS_PAGE";
    private final String BOOKS_PAGE_CONSTRAINT = "BOOKS_PAGE";
    private final String BORROWS_PAGE_CONSTRAINT = "BORROWS_PAGE";

    private final JPanel mainPanel;

    /*
     * TODO: use grid layout
     *       divide constructor body into smaller parts
     *        move static things to separate file
     *      use `JOptionPane.showConfirmDialog` for confirm dialogs
     * */
    public MainWindow(Library library) {
        this.libraryRef = library;
        // create frame
        JFrame mainFrame = new JFrame("TinyBiblio v0.0.1");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // root panel that will contain the navigable app
        this.mainPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        this.mainPanel.setLayout(cardLayout);

        // Create components
        JPanel homePage = this.makeHomepage(); // first page

        JPanel usersPage = this.makeUsersPage();

        JPanel booksPage = this.makeBooksPage();

        // define navigation components
        JButton homePageBtn = new JButton("Accueil");
        JButton usersPageBtn = new JButton("Utilisateurs");
        JButton booksPageBtn = new JButton("Livres");

        homePageBtn.setMaximumSize(new Dimension(20, 30));

        // bind action listeners
        homePageBtn.addActionListener(e -> {
            cardLayout.show(this.mainPanel, MAIN_PAGE_CONSTRAINT);
        });
        usersPageBtn.addActionListener(e -> {
            cardLayout.show(this.mainPanel, USERS_PAGE_CONSTRAINT);
        });
        booksPageBtn.addActionListener(e -> {
            cardLayout.show(this.mainPanel, BOOKS_PAGE_CONSTRAINT);
        });

        // mount components
        this.mainPanel.add(homePage, MAIN_PAGE_CONSTRAINT);
        this.mainPanel.add(booksPage, BOOKS_PAGE_CONSTRAINT);
        this.mainPanel.add(usersPage, USERS_PAGE_CONSTRAINT);
        mainFrame.add(this.mainPanel);

        JPanel navigationBtnPanel = new JPanel(new GridLayout(3, 1));
        navigationBtnPanel.add(homePageBtn);
        navigationBtnPanel.add(booksPageBtn);
        navigationBtnPanel.add(usersPageBtn);

        mainFrame.add(navigationBtnPanel, BorderLayout.WEST);


        // set visible
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private JPanel makeHomepage() {
        JPanel homePage = new JPanel();
        JLabel menuText = new JLabel("MENU PRINCIPAL");
        menuText.setFont(Utils.createFont(menuText, FontSize.H2));
        homePage.add(menuText);

        return homePage;
    }

    private JPanel makeUsersPage() {
        JPanel usersPage = new JPanel(); // users screen
        JLabel usersPageText = new JLabel("Utilisateurs");
        usersPageText.setFont(Utils.createFont(usersPageText, FontSize.H2));
        usersPage.add(usersPageText);


        return usersPage;
    }

    private JPanel makeBooksPage() {
        JPanel booksPage = new JPanel(); // books page
        booksPage.setLayout(new BoxLayout(booksPage, BoxLayout.Y_AXIS));
        JLabel booksPageText = new JLabel("Livres");
        booksPageText.setFont(Utils.createFont(booksPageText, FontSize.H2));
        booksPage.add(booksPageText);

        // display books
        BooksTable tableModel = new BooksTable(libraryRef.getBooksList());
        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(libraryRef, tableModel, table));


        // container for action buttons
        JPanel actionPanelContainer = new JPanel(new FlowLayout());
        JLabel rowsCountLabel = new JLabel("Nombre de livres: " + libraryRef.booksCount());

        JButton addBookBtn = new JButton("Ajouter");
        addBookBtn.addActionListener(e -> {
            new BookForm(this.libraryRef, tableModel);
        });


        actionPanelContainer.add(rowsCountLabel);
        actionPanelContainer.add(addBookBtn);

        booksPage.add(actionPanelContainer);
        booksPage.add(new JScrollPane(table), BorderLayout.CENTER);

        return booksPage;
    }
}
