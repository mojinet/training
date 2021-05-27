package fr.modji.listeDeCourses.bo;

/**
 * Classe d'exemple pour la mise en place du pattern DAO
 * Possede 2 attributs : nom et id
 */
public class Exemple {
    private String name;
    private int id;

    // Constructeur
    public Exemple(String name){
        this.name = name;
    }
    public Exemple(String name, int id){
        this.name = name;
        this.id = id;
    }

    // Getter
    public String getName(){
        return this.name;
    }
    public int getId() { return id; }
}
