package fr.modji.listeDeCourses.bo;

public class Item {
    private int id;
    private String nom;

    public Item(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Item(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
