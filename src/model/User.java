package model;

import model.books.Book;

import java.util.ArrayList;

public class User {
    private String nom;
    private int numeroIdentification;
    private ArrayList<Book> livresEmpruntes;

    public User(String nom, int numeroIdentification) {
        this.nom = nom;
        this.numeroIdentification = numeroIdentification;
    }

    public void emprunterLivre(Book book) {
        this.livresEmpruntes.add(book);
    }

    public void retournerLiver(Book book) {
        this.livresEmpruntes.remove(book);
    }

    public void voirLivreEmpruntes(){
        this.livresEmpruntes.forEach(book -> System.out.println(book.toString()));
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumeroIdentification() {
        return numeroIdentification;
    }

    public void setNumeroIdentification(int numeroIdentification) {
        this.numeroIdentification = numeroIdentification;
    }

    public ArrayList<Book> getLivresEmpruntes() {
        return livresEmpruntes;
    }

    public void setLivresEmpruntes(ArrayList<Book> livresEmpruntes) {
        this.livresEmpruntes = livresEmpruntes;
    }
}
