import java.awt.*;
import java.util.ArrayList;

public class PlateauDeReversi {
    Pion[][] plateau = new Pion[8][8];

    // Initialise board
    public PlateauDeReversi(){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                plateau[x][y] = Pion.LIBRE;
            }
        }
        plateau[4][3] = Pion.NOIR;
        plateau[3][4] = Pion.NOIR;
        plateau[3][3] = Pion.BLANC;
        plateau[4][4] = Pion.BLANC;
    }

    // Display state of game
    public void afficher(){
        afficher(plateau);
    }
    public void afficher(Pion[][] plateau){
        System.out.println(Pion.NOIR.getSymbol() + " : " + Pion.NOIR.getNombre() + " point");
        System.out.println(Pion.BLANC.getSymbol() + " : " + Pion.BLANC.getNombre() + " point");

        System.out.println("  1 2 3 4 5 6 7 8");
        for (int x = 0; x < 8; x++) {       // pour chaque ligne
            System.out.print(x+1 + " ");
            for (int y = 0; y < 8; y++) {   // pour chaque colonne
                System.out.print(plateau[y][x].getSymbol() + " ");
            }
            System.out.println();
        }
    }



    // TODO
    public boolean peutJouer(Pion pion){
        boolean check = false;

        //debug
        System.out.println("***************************Test " + pion.getSymbol());
        afficher();

        // Pour toute les cases du plateau
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                // Pour chaque pion de l'autre couleur trouver
                if (plateau[x][y] == pion.autrePion()) {
                    // Pour chaques cases adjacente à celle trouvé
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            // ON test la position pour voir si elle retourne des pions adverse
                            if ( tester(pion, (x+i)+1, (y+j)+1) >= 1){
                                check = true;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public void poser(Pion pion, int x, int y){ // le joueur pose le pion, retourne les pions adverse concerné
        x = convertCoorToIndex(x);
        y = convertCoorToIndex(y);
        plateau[x][y] = pion;

        ArrayList<int[]> adversePosition = ouSontLesAdversaire(pion, x, y);
        for (int[] adverse : adversePosition) {
            int[] direction = quelDirection(x, y, adverse[0], adverse[1]);
            caseInverser(pion, adverse, direction,true);
        }
    }

    public int tester(Pion pion, int x, int y) {  // retourne le nombre de pion qui changerais de couleur si le joueur choisie cette position
        x = convertCoorToIndex(x);
        y = convertCoorToIndex(y);
        int compteur = 0;

        if ((x <= 7 && x >= 0) && (y <= 7 && y >= 0)) {
            ArrayList<int[]> adversePosition = ouSontLesAdversaire(pion, x, y);

            for (int[] adverse : adversePosition) {
                int[] direction = quelDirection(x, y, adverse[0], adverse[1]);
                compteur += caseInverser(pion, adverse, direction);
            }
        }
        return compteur;
    }

    // Mes methodes privées
        private int caseInverser(Pion pion, int[] adverse, int[] direction){ return caseInverser(pion, adverse, direction,false); }
        private int caseInverser(Pion pion, int[] adverse, int[] direction, boolean change){
            Pion pionAtPosition;
            int compteurDeTour = 1;
            boolean check = true;

            // créer tableau temporaire avec un premier pion retourner
            Pion[][] plateauUpdate = plateau.clone();
            plateauUpdate[ adverse[0] ][ adverse[1] ] = pion.notrePion();

            do {
                pionAtPosition = quiEstLa(adverse[0]+direction[0], adverse[1]+direction[1]);

                if ( pionAtPosition == pion.notrePion()){
                    check = false;
                }else if (pionAtPosition == pion.autrePion()){
                    plateauUpdate[adverse[0]+direction[0]][adverse[1]+direction[1]] = pion.notrePion();
                    compteurDeTour++;
                    direction[0] *= compteurDeTour;
                    direction[1] *= compteurDeTour;
                }else if ( pionAtPosition == Pion.LIBRE ) {
                    compteurDeTour = 0;
                    check = false;
                }
            }while(check);

            // Si on veut retourner les pion d'une position gagnante
            if ( (compteurDeTour >= 1) && change ){
                plateau = plateauUpdate;
            }

            return compteurDeTour;
        }

    private ArrayList<int[]> ouSontLesAdversaire(Pion pion, int x, int y){
        ArrayList<int[]> adversePosition = new ArrayList<>();

        // retourne les position adverse adjacente
        for (int i = -1; i < 2; i++) {              // de x -1 a +1
            for (int j = -1; j < 2; j++) {          // de y -1 a +1
                // Vérifie qu'on est pas au bord du plateau
                if ( ((x+i) <= 7 && (x+i) >= 0) && ((y+j) <= 7 && (y+j) >= 0) ){
                    if (plateau[x+i][y+j] == pion.autrePion()){
                        adversePosition.add(new int[]{x+i,y+j});
                    }
                }
            }
        }
        return adversePosition;
    }

    private int[] quelDirection(int origineX,int origineY, int destinationX, int destinationY){ return new int[]{destinationX-origineX,destinationY-origineY}; }
    private Pion quiEstLa(int x, int y){
        return plateau[x][y];
    }
    // Convert
    public int convertIndexToCoor(int x){ return x + 1; }
    public int convertCoorToIndex(int x){ return x - 1; }
}
