package ui.forms;

import exception.*;
import model.Library;
import model.User;
import ui.dialogs.WarningDialog;
import ui.users.UsersTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import exception.ExceptionUtils;

public class UserForm {
    private final JFrame frame = new JFrame("Formulaire utilisateur");
    private JTextField nameTextField;
    private JTextField idTextField;
    private boolean toggleState = true;
    private final Library library = Library.getInstance();

    public UserForm(UsersTable usersTable) {
        JButton submitBtn = new JButton("Enregistrer");
        initializeFrame(submitBtn, null);

        submitBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idTextField.getText());
                if (id < 0) throw new InvalidUserException("L'identifiant ne peut etre negatif");
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
                new WarningDialog(frame, ExceptionUtils.SAVING_ERROR_TITLE, "Le chand identifiant doit etre un nombre");
            } catch (Exception re) {
                System.err.println(re.getMessage());
                new WarningDialog(frame, ExceptionUtils.UNEXPECTED_ERROR_TITLE, ExceptionUtils.UNEXPECTED_ERROR_MESSAGE);
            }
        });
    }

    public UserForm(UsersTable usersTable, User user) {
        JButton submitBtn = new JButton("Enregistrer");
        JButton deleteBtn = new JButton("Supprimer");
        deleteBtn.setBackground(Color.RED);
        this.toggleState = user.isAllowedToBorrowBook();
        initializeFrame(submitBtn, deleteBtn);

        this.idTextField.setText("" + user.getId());
        this.nameTextField.setText(user.getName());


        submitBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(this.idTextField.getText());
                if (id < 0) throw new InvalidUserException("L'identifiant ne peut etre negatif");
                if (id != user.getId() && this.library.doesUserExist(id)) {
                    throw new UserAlreadyExistException("Cet utilisateur exite deja");
                }
                if (this.nameTextField.getText().isBlank())
                    throw new InvalidUserException("Certaines informations sont invalides");
                user.setId(id);
                user.setName(this.nameTextField.getText());
                user.setAllowedToBorrowBook(toggleState);

                usersTable.notifyUserAdded();
                frame.dispose();
            } catch (UserAlreadyExistException uae) {
                new WarningDialog(frame, ExceptionUtils.SAVING_ERROR_TITLE, uae.getMessage());
            } catch (Exception ee) {
                System.err.println(e);
                new WarningDialog(frame, ExceptionUtils.UNEXPECTED_ERROR_TITLE, ExceptionUtils.UNEXPECTED_ERROR_MESSAGE);
            }
        });

        deleteBtn.addActionListener(e -> {
            try {
                final int choice = JOptionPane.showConfirmDialog(frame, "Etes-vous sûr(e) de vouloir supprimer cet utilisateur?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_NO_OPTION) {
                    this.library.removeUser(user);
                    this.frame.dispose();
                    usersTable.notifyUserAdded();
                }
            } catch (UserNotFoundException er) {
                new WarningDialog(frame, "Erreur de suppression", er.getMessage());
            } catch (Exception err) {
                System.err.println(err.getMessage());
                new WarningDialog(frame, ExceptionUtils.SAVING_ERROR_TITLE, ExceptionUtils.UNEXPECTED_ERROR_MESSAGE);
            }
        });
    }

    private void initializeFrame(JButton subtmitBtn, JButton deleteBtn) {
        frame.setSize(500, 250);
        frame.setResizable(false);
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // mount form fields
        JLabel nameLabel = new JLabel("Nom utilisateur");
        this.nameTextField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameTextField);

        JLabel idLabel = new JLabel("Numero identifiant");
        this.idTextField = new JTextField();
        panel.add(idLabel);
        panel.add(idTextField);

        JLabel cotisationDoneLabel = new JLabel("A jour sur cotisations?");
        JButton toggleButton = new JButton(this.toggleState ? "OUI" : "NON");
        toggleButton.addActionListener(e -> {
            this.toggleState = !this.toggleState;
            toggleButton.setText(this.toggleState ? "OUI" : "NON");
        });
        panel.add(cotisationDoneLabel);
        panel.add(toggleButton);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 10));
        footerPanel.setBorder(new EmptyBorder(0, 150, 0, 100));
        footerPanel.add(subtmitBtn);

        if (deleteBtn != null) {
            footerPanel.add(deleteBtn);
        }

        panel.setLayout(new GridLayout(0, 2));
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));
        frame.add(panel, BorderLayout.NORTH);
        frame.add(footerPanel, BorderLayout.EAST);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
