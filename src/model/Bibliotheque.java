package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Bibliotheque {
    private ArrayList<Livre> listeLivres;
    private HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs;

    private StatistiquesBibliotheque stats;

    public Bibliotheque() {
        this.stats = new StatistiquesBibliotheque();
    }

    public void ajouterLivre(Livre livre) {

    }

    public void supprimerLivre(Livre livre) {
    }

    public void rechercherLivre(String text) {
    }

    public void enregistrerEmprunt() {
    }

    public void enregistrerRetour() {
    }

    public boolean verifierEligibilite() {
        return false;
    }

    public void afficherStatistiques(){
        System.out.println("Nombre total livres: " + this.stats.getTotalLivres());
        System.out.println("Nombre total emprunts: " +  this.stats.getTotalEmprunts());
    }
}
