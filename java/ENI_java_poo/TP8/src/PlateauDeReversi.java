public class PlateauDeReversi {
    Pion[][] plateau = new Pion[8][8];

    /**
     * initial position of game
     */
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

    /**
     * return a grafik state of game
     */
    public void afficher(){
        System.out.println("  1 2 3 4 5 6 7 8");
        for (int i = 0; i < 8; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(plateau[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }

    /**
     * test how can capture adverse pion if play in x,y
     * @param pion
     * @param x
     * @param y
     * @return
     */
    public int tester(Pion pion, int x, int y){
        return 0;
    }

    /**
     * return boolean who represent if player can play
     * @param pion
     * @return
     */
    public boolean peutJouer(Pion pion){
        return true;
    }

    /**
     * Player play at (x,y)
     * @param pion
     * @param x
     * @param y
     */
    public void poser(Pion pion, int x, int y){
        plateau[x-1][y-1] = pion;
        afficher();
    }
}
