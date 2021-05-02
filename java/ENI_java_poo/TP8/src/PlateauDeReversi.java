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

    // Display grafical state of game
    public void afficher(){
        System.out.println(Pion.NOIR.getSymbol() + " : " + Pion.NOIR.getNombre() + " point");
        System.out.println(Pion.BLANC.getSymbol() + " : " + Pion.BLANC.getNombre() + " point");

        System.out.println("  1 2 3 4 5 6 7 8");
        for (int i = 0; i < 8; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(plateau[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }

    // TODO
    public boolean peutJouer(Pion pion){ return true; } // test si au moins une position ou il est possible de jouer

    public void poser(Pion pion, int x, int y){ // le joueur pose le pion, retourne les pions adverse concerné
        x = convertCoorToIndex(x);
        y = convertCoorToIndex(y);
        plateau[x][y] = pion;
        // TODO retourne les pions adverse
    }

    public int tester(Pion pion, int x, int y){  // retourne le nombre de pion qui changerais de couleur si le joueur choisie cette position
        x = convertCoorToIndex(x);
        y = convertCoorToIndex(y);
        ArrayList<int[]> adversePosition = ouSontLesAdversaire(pion,x,y);
        int compteur = 0;
        //test

        // boucle principal
        for (int[] adverse: adversePosition) {
            
            // initialise les variable
            int compteurDeTour = 1;
            boolean check = true;
            int[] direction = quelDirection(x,y,adverse[0],adverse[1]);
            Pion pionAtPosition;

            do {
                // test qui est le prochain pion
                pionAtPosition = quiEstLa(adverse[0]+direction[0], adverse[1]+direction[1]);

                if ( pionAtPosition == pion.notrePion()){
                    check = false;
                }else if (pionAtPosition == pion.autrePion()){
                    compteurDeTour++;
                    direction[0] *= 2;
                    direction[1] *= 2;
                    //DEBUG MESSAGE
                    System.out.println("Un pion adverse trouver en position : " + convertIndexToCoor(adverse[0] + direction[0]) + " " + convertIndexToCoor(adverse[1] + direction[1]));
                }else{
                    compteur = 0;
                    check = false;
                }
            }while(check);

            compteur += compteurDeTour;
        }

        return compteur;
    }

    // Mes methodes privées
    private ArrayList<int[]> pionARetourner(Pion pion, int posOrigineX, int posOriginY, int[] direction){ return new ArrayList<>(); }

    private ArrayList<int[]> ouSontLesAdversaire(Pion pion, int x, int y){
        ArrayList<int[]> adversePosition = new ArrayList<>();

        // retourne les position adverse adjacente
        for (int i = -1; i < 2; i++) { // de -1 a 1
            for (int j = -1; j < 2; j++) { // de -1 a 1
                if (plateau[x+i][y+j] == pion.autrePion()){
                    adversePosition.add(new int[]{x+i,y+j});
                    // DEBUG INFO
                    System.out.println("Pion adverse en position : " + convertIndexToCoor(x+i) + " " + convertIndexToCoor(y+j) );
                }
            }
        }

        return adversePosition;
    }

    private int[] quelDirection(int origineX,int origineY, int destinationX, int destinationY){
        return new int[]{origineX-origineY,destinationX-destinationY};
    }
    private Pion quiEstLa(int x, int y){ return plateau[x][y]; }

    // Convert
    public int convertIndexToCoor(int x){ return x+1; }
    public int convertCoorToIndex(int x){ return x-1; }


}
