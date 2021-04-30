public enum Pion {
    LIBRE, BLANC, NOIR;
    private int nombre = 2;

    public int getNombre(){
        return this.nombre;
    }

    /**
     * @return a char who represent the symbol of pion
     */
    public char getSymbol(){
        char response;
        switch (this.name()){
            case "NOIR" -> response = '●';
            case "BLANC" -> response = 'o';
            default -> response = '·';
        }
        return response;
    }

    /**
     * @return a Pion who is the reverse of this
     */
    public Pion autrePion(){
        Pion response;
        switch (this.name()){
            case "NOIR" -> response = Pion.BLANC;
            case "BLANC" -> response = Pion.NOIR;
            default -> response = Pion.LIBRE;
        }
        return response;
    }

    /**
     * @param nbPion
     */
    //TODO
    public void gagne(int nbPion){
        this.nombre += nbPion;
    }
}
