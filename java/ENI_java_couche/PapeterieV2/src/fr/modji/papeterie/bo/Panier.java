package fr.modji.papeterie.bo;

import java.util.ArrayList;

/**
 * Panier représente ce que l'utilisateur veut acheter, le panier contient des Ligne
 */
public class Panier {
    private float montant;
    private ArrayList<Ligne> liste = new ArrayList<>();

    /**
     * Ajoute un ligne au panier, celle ci est définie par l'article et sa quantités
     * @param article qui est une instance de l'article souhaité
     * @param qte qui est la quantités d'article
     */
    public void addLigne(Article article, int qte) {
        Ligne ligne = new Ligne(article,qte);
        this.liste.add(ligne);
    }

    /**
     * Met à jour une ligne de panier en modifiant la quantité
     * @param index L'index de la ligne dans liste
     * @param newQte la nouvelle quantités mis à jour
     */
    public void updateLigne(int index, int newQte) {
            liste.get(index).setQte(newQte);
    }

    /**
     * Suprime une ligne du panier
     * @param index L'index de la ligne dans liste
     */
    public void removeLigne(int index) {
        liste.remove(index);
    }

    @Override
    public String toString() {
        return "Panier : " + System.getProperty("line.separator" ) +
                liste +
                '}' + System.getProperty("line.separator" ) +
                "Valeur du panier : " + montant
                ;
    }

    //Getter & Setter
    public float getMontant() { return montant; }
    public Ligne getLigne(int index){ return liste.get(index); }
    public ArrayList<Ligne> getLignesPanier(){ return liste; }
}
