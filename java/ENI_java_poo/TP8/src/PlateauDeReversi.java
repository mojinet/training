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
     * Display state of game
     */
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

    /**
     * test how can capture adverse pion if play in x,y
     * @param pion
     * @param x
     * @param y
     * @return
     */
    public int tester(Pion pion, int x, int y){
        int result;

        if (plateau[x-1][y-1] == Pion.LIBRE){
            result = 1;
        }else{
            result = 0;
        }

        return result;

    }

    /**
     * return array of all position of adjacent adverse's pion
     * @param pion
     * @param x
     * @param y
     * @return
     */
    public int[][] whereIsOtherPion(Pion pion, int x, int y){
        int[][] response = new int[8][];
        int position = 0;

        // set all values of array in -1
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 2; j++) {
                response[i][j] = -1;
            }
        }

        // get all position of adverse's adjacent pion
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (plateau[(x-1)+i][(y-1)+j] == pion.autrePion()){
                    response[position][0] = x+i;
                    response[position][1] = y+j;
                    System.out.println(response[position][0] + " " + response[position][1]);
                    position++;
                }
            }
        }
        return response;
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
        pion.gagne(tester(pion,x,y));
        afficher();
    }
}
