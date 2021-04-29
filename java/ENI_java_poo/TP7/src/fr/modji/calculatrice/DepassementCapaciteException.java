package fr.modji.calculatrice;

public class DepassementCapaciteException extends Exception{

    private static final long serialVersionUID = 1L;

    public DepassementCapaciteException(){
        super("Le resultat depasse la capacite de la calculatrice");
    }
}
