package model;

import java.util.ArrayList;

public class Utilisateur {
    private String nom;
    private int numeroIdentification;
    private ArrayList<Livre> livresEmpruntes;

    public Utilisateur(String nom, int numeroIdentification) {
        this.nom = nom;
        this.numeroIdentification = numeroIdentification;
    }

    public void emprunterLivre(Livre livre) {
        this.livresEmpruntes.add(livre);
    }

    public void retournerLiver(Livre livre) {
    }

    public void voirLivreEmpruntes(){
        this.livresEmpruntes.forEach(livre -> System.out.println(livre.toString()));
    }
}
