package ui;

import model.Library;
import model.User;
import model.books.Book;
import ui.Utils.FontSize;
import ui.books.BooksTable;
import ui.books.ButtonEditor;
import ui.books.ButtonRenderer;
import ui.forms.BookForm;
import ui.forms.UserForm;
import ui.users.UsersButtonEditor;
import ui.users.UsersButtonRenderer;
import ui.users.UsersTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainWindow {
    private final Library libraryRef;
    private final String MAIN_PAGE_CONSTRAINT = "MAIN_PAGE";
    private final String USERS_PAGE_CONSTRAINT = "USERS_PAGE";
    private final String BOOKS_PAGE_CONSTRAINT = "BOOKS_PAGE";

    private final JPanel mainPanel;

    public MainWindow() {
        this.libraryRef = Library.getInstance();
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
        homePageBtn.addActionListener(e -> { // always re-render the component
            JPanel refreshedHomePage = makeHomepage();
            mainPanel.remove(homePage);
            mainPanel.add(refreshedHomePage, MAIN_PAGE_CONSTRAINT);
            cardLayout.show(mainPanel, MAIN_PAGE_CONSTRAINT);
        });
        usersPageBtn.addActionListener(e -> cardLayout.show(this.mainPanel, USERS_PAGE_CONSTRAINT));
        booksPageBtn.addActionListener(e -> cardLayout.show(this.mainPanel, BOOKS_PAGE_CONSTRAINT));

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
        homePage.setLayout(new BoxLayout(homePage, BoxLayout.Y_AXIS));

        JLabel menuText = new JLabel("Accueil");
        menuText.setFont(Utils.createFont(menuText, FontSize.H2));
        menuText.setAlignmentX(Component.CENTER_ALIGNMENT);
        homePage.add(menuText);

        // Retrieve user borrows from the library
        HashMap<User, ArrayList<Book>> borrowsList = this.libraryRef.getUserBorrows();

        // Calculate statistics
        int numUsersBorrowed = borrowsList.size();

        int numBorrowedBooks = 0;
        for (ArrayList<Book> books : borrowsList.values()) {
            numBorrowedBooks += books.size();
        }

        // Display the statistics
        JLabel usersLabel = new JLabel("Nombre d'utilisateurs ayant emprunté : " + numUsersBorrowed);
        usersLabel.setFont(Utils.createFont(usersLabel, FontSize.H4));

        JLabel booksLabel = new JLabel("Nombre total de livres empruntés : " + numBorrowedBooks);
        booksLabel.setFont(Utils.createFont(booksLabel, FontSize.H4));

        // Calculate average books per user
        double averageBooksPerUser = numUsersBorrowed > 0 ? (double) numBorrowedBooks / numUsersBorrowed : 0.0;
        JLabel averageBooksLabel = new JLabel("Moyenne de livres empruntés par utilisateur : " + String.format("%.2f", averageBooksPerUser));
        averageBooksLabel.setFont(Utils.createFont(averageBooksLabel, FontSize.H4));

        // Find user with the most borrows
        User userWithMostBorrows = null;
        int maxBorrows = 0;
        for (Map.Entry<User, ArrayList<Book>> entry : borrowsList.entrySet()) {
            int numUserBorrows = entry.getValue().size();
            if (numUserBorrows > maxBorrows) {
                maxBorrows = numUserBorrows;
                userWithMostBorrows = entry.getKey();
            }
        }
        JLabel mostBorrowsLabel = new JLabel("Utilisateur avec le plus d'emprunts : " + (userWithMostBorrows != null ? userWithMostBorrows.getName() : "N/A"));
        mostBorrowsLabel.setFont(Utils.createFont(mostBorrowsLabel, FontSize.H4));

        // Create a panel to hold the statistics labels
        JPanel statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new BoxLayout(statisticsPanel, BoxLayout.Y_AXIS));
        statisticsPanel.add(usersLabel);
        statisticsPanel.add(Box.createVerticalStrut(10));
        statisticsPanel.add(booksLabel);
        statisticsPanel.add(Box.createVerticalStrut(10));
        statisticsPanel.add(averageBooksLabel);
        statisticsPanel.add(Box.createVerticalStrut(10));
        statisticsPanel.add(mostBorrowsLabel);

        // Add the statistics panel to the homepage
        homePage.add(Box.createVerticalStrut(20));
        homePage.add(statisticsPanel);
        homePage.add(Box.createVerticalGlue());

        return homePage;
    }


    private JPanel makeUsersPage() {
        JPanel usersPage = new JPanel(); // Users screen
        usersPage.setLayout(new BorderLayout());

        // Header label
        JLabel usersPageText = new JLabel("Utilisateurs");
        usersPageText.setFont(Utils.createFont(usersPageText, FontSize.H2));
        usersPage.add(usersPageText, BorderLayout.NORTH);

        // Table
        UsersTable tableModel = new UsersTable();
        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(3).setCellRenderer(new UsersButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new UsersButtonEditor(tableModel, table, "Modifier"));

        table.getColumnModel().getColumn(4).setCellRenderer(new UsersButtonRenderer("Emprunts"));
        table.getColumnModel().getColumn(4).setCellEditor(new UsersButtonEditor(tableModel, table, "Emprunts", true));

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        usersPage.add(tableScrollPane, BorderLayout.CENTER);

        // Action panel (Add User button)
        JPanel actionPanel = new JPanel();
        JButton addUserBtn = new JButton("Ajouter");
        addUserBtn.addActionListener(e -> new UserForm(tableModel));
        actionPanel.add(addUserBtn);

        usersPage.add(actionPanel, BorderLayout.SOUTH);

        return usersPage;
    }

    private JPanel makeBooksPage() {
        JPanel booksPage = new JPanel(); // books page
        booksPage.setLayout(new BoxLayout(booksPage, BoxLayout.Y_AXIS));
        JLabel booksPageText = new JLabel("Livres");
        booksPageText.setFont(Utils.createFont(booksPageText, FontSize.H2));
        booksPage.add(booksPageText);

        // display books
        BooksTable tableModel = new BooksTable();
        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(tableModel, table));


        // container for action buttons
        JPanel actionPanelContainer = new JPanel(new FlowLayout());

        JButton addBookBtn = new JButton("Ajouter");
        addBookBtn.addActionListener(e -> new BookForm(tableModel));


//        actionPanelContainer.add(rowsCountLabel);
        actionPanelContainer.add(addBookBtn);

        booksPage.add(actionPanelContainer);
        booksPage.add(new JScrollPane(table), BorderLayout.CENTER);

        return booksPage;
    }
}
