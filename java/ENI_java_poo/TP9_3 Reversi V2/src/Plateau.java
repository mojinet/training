import java.util.ArrayList;

public class Plateau<T> implements Affichable {
    protected int largeur;
    protected int longeur;
    T[][] plateau;

    /**
     * Initialise un plateau de jeu de largeur*longeur avec le param type par défaut sur toute les cases
     * @param largeur qui represente la largeur du plateau de jeu
     * @param longeur qui represente la longeur du plateau de jeu
     * @param type qui représente quel pion par defaut sera placée sur toute les cases
     */
    public Plateau(int largeur, int longeur, T type){
        this.largeur = largeur;
        this.longeur = longeur;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                this.plateau[x][y] = type;
            }
        }
    }

    /**
     * Affiche l'état du plateau actuel
     */
    public void afficher(){
        StringBuilder str = new StringBuilder();
        str.append("  ");
        for (int i = 0; i < largeur; i++) {
            str.append( i + "  ");
        }

        for (int i = 0; i < longeur; i++) {
            str.append(i + "  ");
            for (int j = 0; j < largeur; j++) {
                str.append(liste.get(j).get);
            }
        }
    }

    /**
     * Retourne la valeur d'une case (le pion)
     * @param x qui représente la coordonnée x
     * @param y qui represente la coordonnée y
     * @return le pion qui occupe la case
     */
    public T getValeur(int x, int y){
        return liste.get((x-1) * x + (y-1));
    }

    /**
     * @param x qui représente la coordonnée x
     * @param y qui represente la coordonnée y
     * @param valeur qui représente la valeur du pion que dois prendre la case
     */
    public void setValeur(int x, int y, T valeur){
        liste.set((x-1) * x + (y-1),valeur);
    }

    @Override
    public char getSymbol() {
        return 0;
    }
}
