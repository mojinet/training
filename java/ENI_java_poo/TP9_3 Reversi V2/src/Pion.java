public enum Pion {
    LIBRE, BLANC, NOIR;
    private int nombre = 2;

    public int getNombre(){
        return this.nombre;
    }

    /**
     * retourne le symbole du pion : ● o ·
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
     * Retourne l'autre pion
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
     * Retourne notre pion
     */
    public Pion notrePion(){
        Pion response;
        switch (this.name()){
            case "NOIR" -> response = Pion.NOIR;
            case "BLANC" -> response = Pion.BLANC;
            default -> response = Pion.LIBRE;
        }
        return response;
    }
    /**
     * Ajoute le nombre de pion total
     */
    public void gagne(int nbPion){
        this.nombre = nbPion;
    }
}
