package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.util.Stack;

import ui.Utils.*;

public class MainWindow {
    private final String MAIN_PAGE_CONSTRAINT = "MAIN_PAGE";
    private final String USERS_PAGE_CONSTRAINT = "USERS_PAGE";
    private final String BOOKS_PAGE_CONSTRAINT = "BOOKS_PAGE";
    private final String BORROWS_PAGE_CONSTRAINT = "BORROWS_PAGE";

    private final JPanel mainPanel;

    /*
     * TODO: use grid layout
     *       divide constructor body into smaller parts
     *        move static things to separate file
     * */
    public MainWindow() {
        // create frame
        JFrame mainFrame = new JFrame("tiny-Biblio v0.0.1");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // root panel that will contain the navigable app
        this.mainPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        this.mainPanel.setLayout(cardLayout);

        // Create components
        JPanel homePage = new JPanel(); // first screen
        JLabel menuText = new JLabel("MENU PRINCIPAL");
        menuText.setFont(Utils.createFont(menuText, FontSize.H2));
        homePage.add(menuText);

        JPanel usersPage = new JPanel(); // users screen
        JLabel usersPageText = new JLabel("Utilisateurs");
        usersPageText.setFont(Utils.createFont(usersPageText, FontSize.H2));
        usersPage.add(usersPageText);

        JPanel booksPage = new JPanel(); // books page
        JLabel booksPageText = new JLabel("Livres");
        booksPageText.setFont(Utils.createFont(booksPageText, FontSize.H2));
        booksPage.add(booksPageText);

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

}
