package ui.forms;

import exception.*;
import model.Library;
import model.User;
import ui.dialogs.WarningDialog;
import ui.users.UsersTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A form for creating or editing user details.
 */
public class UserForm {
    private final JFrame frame = new JFrame("Formulaire utilisateur");
    private JTextField nameTextField;
    private JTextField idTextField;
    private boolean toggleState = true;
    private final Library library = Library.getInstance();

    /**
     * Constructs a UserForm instance for creating a new user.
     *
     * @param usersTable The UsersTable associated with this form.
     */
    public UserForm(UsersTable usersTable) {
        JButton submitBtn = new JButton("Enregistrer");
        initializeFrame(submitBtn, null);

        submitBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idTextField.getText());
                if (id < 0) {
                    throw new InvalidUserException("L'identifiant ne peut être négatif");
                }
                this.library.addUser(
                        nameTextField.getText(),
                        id,
                        toggleState
                );
                usersTable.notifyUserAdded();
                frame.dispose();
            } catch (InvalidUserException | UserAlreadyExistException ex) {
                new WarningDialog(frame, "Format d'enregistrement", ex.getMessage());
            } catch (NumberFormatException nef) {
                new WarningDialog(frame, "Erreur de sauvegarde", "Le champ identifiant doit être un nombre");
            } catch (Exception re) {
                System.err.println(re.getMessage());
                new WarningDialog(frame, "Erreur inattendue", ExceptionUtils.UNEXPECTED_ERROR_MESSAGE);
            }
        });
    }

    /**
     * Constructs a UserForm instance for editing an existing user.
     *
     * @param usersTable The UsersTable associated with this form.
     * @param user       The user to be edited.
     */
    public UserForm(UsersTable usersTable, User user) {
        JButton submitBtn = new JButton("Enregistrer");
        JButton deleteBtn = new JButton("Supprimer");
        deleteBtn.setBackground(Color.RED);
        this.toggleState = user.isAllowedToBorrowBook();
        initializeFrame(submitBtn, deleteBtn);

        this.idTextField.setText(String.valueOf(user.getId()));
        this.nameTextField.setText(user.getName());

        submitBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(this.idTextField.getText());
                if (id < 0) {
                    throw new InvalidUserException("L'identifiant ne peut être négatif");
                }
                if (id != user.getId() && this.library.doesUserExist(id)) {
                    throw new UserAlreadyExistException("Cet utilisateur existe déjà");
                }
                if (this.nameTextField.getText().isBlank()) {
                    throw new InvalidUserException("Certains champs sont invalides");
                }
                user.setId(id);
                user.setName(this.nameTextField.getText());
                user.setAllowedToBorrowBook(toggleState);

                usersTable.notifyUserAdded();
                frame.dispose();
            } catch (UserAlreadyExistException uae) {
                new WarningDialog(frame, "Erreur de sauvegarde", uae.getMessage());
            } catch (NumberFormatException nfe) {
                new WarningDialog(frame, "Erreur de sauvegarde", "Le champ identifiant doit être un nombre");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                new WarningDialog(frame, "Erreur inattendue", ExceptionUtils.UNEXPECTED_ERROR_MESSAGE);
            }
        });

        deleteBtn.addActionListener(e -> {
            try {
                int choice = JOptionPane.showConfirmDialog(frame, "Êtes-vous sûr(e) de vouloir supprimer cet utilisateur ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    this.library.removeUser(user);
                    frame.dispose();
                    usersTable.notifyUserAdded();
                }
            } catch (UserNotFoundException er) {
                new WarningDialog(frame, "Erreur de suppression", er.getMessage());
            } catch (Exception err) {
                System.err.println(err.getMessage());
                new WarningDialog(frame, "Erreur inattendue", ExceptionUtils.UNEXPECTED_ERROR_MESSAGE);
            }
        });
    }

    /**
     * Initializes the frame components for the user form.
     *
     * @param submitBtn The submit button for the form.
     * @param deleteBtn The delete button for the form (optional).
     */
    private void initializeFrame(JButton submitBtn, JButton deleteBtn) {
        frame.setSize(500, 250);
        frame.setResizable(false);
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Mount form fields
        JLabel nameLabel = new JLabel("Nom utilisateur");
        this.nameTextField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameTextField);

        JLabel idLabel = new JLabel("Numéro identifiant");
        this.idTextField = new JTextField();
        panel.add(idLabel);
        panel.add(idTextField);

        JLabel cotisationDoneLabel = new JLabel("À jour sur les cotisations ?");
        JButton toggleButton = new JButton(this.toggleState ? "OUI" : "NON");
        toggleButton.addActionListener(e -> {
            this.toggleState = !this.toggleState;
            toggleButton.setText(this.toggleState ? "OUI" : "NON");
        });
        panel.add(cotisationDoneLabel);
        panel.add(toggleButton);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 10));
        footerPanel.setBorder(new EmptyBorder(0, 150, 0, 100));
        footerPanel.add(submitBtn);

        // Add delete button if provided
        if (deleteBtn != null) {
            footerPanel.add(deleteBtn);
        }

        // Final component mounting
        frame.add(panel, BorderLayout.NORTH);
        frame.add(footerPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
