import java.util.ArrayList;

public class PlateauDeReversi {
    Pion[][] plateau = new Pion[8][8];

    /**
     * Initialise le plateau de jeu
     */
    public PlateauDeReversi(){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                this.plateau[x][y] = Pion.LIBRE;
            }
        }
        this.plateau[4][3] = Pion.NOIR;
        this.plateau[3][4] = Pion.NOIR;
        this.plateau[3][3] = Pion.BLANC;
        this.plateau[4][4] = Pion.BLANC;
    }

    /**
     * Affiche l'état actuel du jeu
     */
    public void afficher(){
        System.out.println(Pion.NOIR.getSymbol() + " : " + Pion.NOIR.getNombre() + " point");
        System.out.println(Pion.BLANC.getSymbol() + " : " + Pion.BLANC.getNombre() + " point");
        System.out.println("  1 2 3 4 5 6 7 8");

        for (int x = 0; x < 8; x++) {
            System.out.print(x+1 + " ");
            for (int y = 0; y < 8; y++) {
                System.out.print(this.plateau[y][x].getSymbol() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Vérifie si il existe au moins une position ou le pion peut jouer
     */
    public boolean peutJouer(Pion pion){
        boolean check = false;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (this.plateau[x][y] == Pion.LIBRE){
                    if (tester(pion,x,y) >= 1) {
                        check = true;
                    }
                }

            }
        }
        return check;
    }

    /**
     * Met à jour le plateau : inverse les pions adverse
     */
    public void poser(Pion pion, int x, int y){
        x = convertCoorToIndex(x);
        y = convertCoorToIndex(y);
        this.plateau[x][y] = pion;

        ArrayList<int[]> adversePosition = ouSontLesAdversaire(pion, x, y);
        for (int[] adverse : adversePosition) {
            int[] direction = quelDirection(x, y, adverse[0], adverse[1]);
            caseInverser(pion, adverse, direction,true);
        }
    }

    /**
     * On test une position, si la méthode retourne une valeur >=1 cela veut dire que la position retourne des pions adverse
     */
    public int tester(Pion pion, int x, int y) {
        x = convertCoorToIndex(x);
        y = convertCoorToIndex(y);
        int compteur = 0;

        if ((x <= 7 && x >= 0) && (y <= 7 && y >= 0) && this.plateau[x][y] == Pion.LIBRE) {

            ArrayList<int[]> adversePosition = ouSontLesAdversaire(pion, x, y);
            for (int[] adverse : adversePosition) {
                int[] direction = quelDirection(x, y, adverse[0], adverse[1]);
                compteur += caseInverser(pion, adverse, direction);
            }
        }
        return compteur;
    }

        private int caseInverser(Pion pion, int[] adverse, int[] direction){ return caseInverser(pion, adverse, direction,false); }

    /**
     * Inverse les pions concerné par un mouvement de joueur
     */
        private int caseInverser(Pion pion, int[] adverse, int[] direction, boolean change){
            Pion pionAtPosition;
            int compteurDeTour = 1;
            boolean check = true;

            // créer tableau temporaire avec un premier pion retourner
            Pion[][] plateauUpdate = cloneTableau();
            plateauUpdate[ adverse[0] ][ adverse[1] ] = pion.notrePion();

            // On test pour chaque itération de direction le pion suivant
            do {
                pionAtPosition = quiEstLa(adverse[0]+direction[0], adverse[1]+direction[1]);
                if ( pionAtPosition == pion.notrePion()){
                    check = false;
                }else if (pionAtPosition == pion.autrePion()){
                    plateauUpdate[adverse[0]+direction[0]][adverse[1]+direction[1]] = pion.notrePion();
                    compteurDeTour++;
                    direction[0] *= compteurDeTour;
                    direction[1] *= compteurDeTour;
                }else { // cas d'un pion libre ou hors limite (= null)
                    compteurDeTour = 0;
                    check = false;
                }
            }while(check);

            // Dans le cas ou on souhaite rendre effectif les modifications
            if ( (compteurDeTour >= 1) && change ){
                this.plateau = plateauUpdate;
                updateNbPion();
            }
            return compteurDeTour;
        }

    /**
     * Met à jour le nombre de pion sur le plateau
     */
    private void updateNbPion() {
        int blanc = 0, noir = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (plateau[x][y] == Pion.BLANC){
                    blanc++;
                }else if (plateau[x][y] == Pion.NOIR){
                    noir++;
                }
            }
        }
        Pion.NOIR.gagne(noir);
        Pion.BLANC.gagne(blanc);
    }

    /**
     * Copie d'un tableau multidimentionnel de type Pion
     */
    private Pion[][] cloneTableau(){
        Pion[][] copieTableau = new Pion[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                copieTableau[x][y] = this.plateau[x][y];
            }
        }
        return copieTableau;
    }

    /**
     * Rerourne les positions des pions adverse adjacent à la position données
     */
    private ArrayList<int[]> ouSontLesAdversaire(Pion pion, int x, int y){
        ArrayList<int[]> adversePosition = new ArrayList<>();

        // retourne les position adverse adjacente
        for (int i = -1; i < 2; i++) {              // de x -1 a +1
            for (int j = -1; j < 2; j++) {          // de y -1 a +1

                // Vérifie qu'on est pas au bord du plateau
                if ( ((x+i) <= 7 && (x+i) >= 0) && ((y+j) <= 7 && (y+j) >= 0) ){
                    if (this.plateau[x+i][y+j] == pion.autrePion()){
                        adversePosition.add(new int[]{x+i,y+j});
                    }
                }
            }
        }
        return adversePosition;
    }

    /**
     * Retourne la direction a suivre
     */
    private int[] quelDirection(int origineX,int origineY, int destinationX, int destinationY){ return new int[]{destinationX - origineX,destinationY - origineY}; }

    /**
     * Retourne le pion à la position données
     */
    private Pion quiEstLa(int x, int y){
        if (( x <= 7 && x >= 0) && (y <= 7 && y >= 0 )){
            return this.plateau[x][y];
        }else{
            return null;
        }
    }

    /**
     * Convertie les valeur saisie par l'utilisateur en index
     */
    public int convertCoorToIndex(int x){ return x - 1; }
}
